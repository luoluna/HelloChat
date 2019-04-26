package com.team.hellochat.adapter;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.team.hellochat.R;
import com.team.hellochat.activity.LookMessageActivity;
import com.team.hellochat.base.BaseRecyclerAdapter;
import com.team.hellochat.bean.HeadPicture;
import com.team.hellochat.bean.MessageInfo;
import com.team.hellochat.bean.MessageType;
import com.team.hellochat.manager.UserManager;
import com.team.hellochat.view.MessageView;

import java.util.List;

import static com.team.hellochat.app.App.IntentLabel.MESSAGE_INFO;
import static com.team.hellochat.app.App.IntentLabel.MESSAGE_TYPE;

/**
 * Created by Sweven on 2019/3/31.
 * Email:sweventears@Foxmail.com
 */
public class ChatMessageAdapter extends BaseRecyclerAdapter<MessageInfo> {

    public ChatMessageAdapter(Activity activity, List<MessageInfo> list) {
        super(activity, list);
    }

    @NonNull
    @Override
    public ChatMessageHoldView onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = inflater.inflate(R.layout.item_list_chat_message, viewGroup, false);
        switch (i) {
            case MessageType._TEXT:
                break;
            case MessageType._IMAGE:
                break;
            case MessageType._VIDEO:
                break;
            case MessageType._VOICE:
                break;
            case MessageType._CARD:
                break;
        }
        return new ChatMessageHoldView(view);
    }

    @Override
    public int getItemViewType(int position) {

        return list.get(position).getType().getIndex();
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
        ChatMessageHoldView hold = (ChatMessageHoldView) viewHolder;
        MessageInfo message = list.get(position);

        if (message.getUid() == UserManager.getInstance().getUid()) {
            hold.selfChat.setVisibility(View.VISIBLE);
            hold.otherChat.setVisibility(View.GONE);

            hold.selfChatMessage.setMessage(MessageView.WHO_ME, message.getType(), message.getInformation());
            Glide.with(activity)
                    .load(HeadPicture.getResId(message.getAvatar()))
                    .into(hold.selfHeadPhoto);
        } else {
            hold.otherChat.setVisibility(View.VISIBLE);
            hold.selfChat.setVisibility(View.GONE);

            hold.otherChatMessage.setMessage(MessageView.WHO_OTHER, message.getType(), message.getInformation());
            Glide.with(activity)
                    .load(HeadPicture.getResId(message.getAvatar()))
                    .into(hold.otherHeadPhoto);
        }
    }

    public void sendSelfMessage(MessageInfo info) {
        list.add(info);
        notifyDataSetChanged();
        if (onSendListener != null) {
            onSendListener.onSuccess(getItemCount(),info);
        }
    }

    public void putMessage(List<MessageInfo> list) {
        this.list=list;
        notifyDataSetChanged();
    }

    public class ChatMessageHoldView extends RecyclerView.ViewHolder implements View.OnClickListener {

        private RelativeLayout otherChat;
        private RelativeLayout selfChat;
        private ImageView otherHeadPhoto;
        private ImageView selfHeadPhoto;
        private MessageView otherChatMessage;
        private MessageView selfChatMessage;

        public ChatMessageHoldView(@NonNull View view) {
            super(view);
            otherChat = view.findViewById(R.id.other_chat);
            selfChat = view.findViewById(R.id.self_chat);
            otherHeadPhoto = view.findViewById(R.id.head_photo);
            selfHeadPhoto = view.findViewById(R.id.self_head_photo);
            otherChatMessage = view.findViewById(R.id.other_chat_message);
            selfChatMessage = view.findViewById(R.id.self_chat_message);

            otherChatMessage.setOnClickListener(this);
            selfChatMessage.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Intent intent = new Intent(activity, LookMessageActivity.class);
            intent.putExtra(MESSAGE_TYPE, list.get(getAdapterPosition()).getType().getName());
            intent.putExtra(MESSAGE_INFO, list.get(getAdapterPosition()).getInformation());
            activity.startActivity(intent);
        }
    }

    private OnSendListener onSendListener;

    public interface OnSendListener {
        void onSuccess(int count,MessageInfo messageInfo);

        void onFail();
    }

    public void setOnSendListener(OnSendListener onSendListener) {
        this.onSendListener = onSendListener;
    }
}
