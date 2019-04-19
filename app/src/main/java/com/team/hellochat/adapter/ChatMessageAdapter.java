package com.team.hellochat.adapter;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.team.hellochat.R;
import com.team.hellochat.base.BaseRecyclerAdapter;
import com.team.hellochat.bean.MessageInfo;
import com.team.hellochat.bean.MessageType;
import com.team.hellochat.preferences.UserManager;

import java.util.List;

/**
 * Created by Sweven on 2019/3/31.
 * Email:sweventears@Foxmail.com
 */
public class ChatMessageAdapter extends BaseRecyclerAdapter<MessageInfo> {

    public ChatMessageAdapter(Activity activity, List<MessageInfo> list) {
        super(activity, list);
        this.activity = activity;
        this.list = list;
        inflater = LayoutInflater.from(activity);
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

            hold.selfChatMessage.setText(message.getInformation());
            Glide.with(activity)
                    .load(message.getHeadPictureUri())
                    .into(hold.selfHeadPhoto);
        } else {
            hold.otherChat.setVisibility(View.VISIBLE);
            hold.selfChat.setVisibility(View.GONE);

            hold.otherChatMessage.setText(message.getInformation());
            Glide.with(activity)
                    .load(message.getHeadPictureUri())
                    .into(hold.otherHeadPhoto);
        }
    }

    public void sendSelfMessage(String message) {
        MessageInfo info = new MessageInfo();
        info.setUid(UserManager.getInstance().getUid());
        info.setType(MessageType.TEXT);
        info.setInformation(message);
        list.add(info);
        notifyDataSetChanged();
        if (onSendListener != null) {
            onSendListener.onSuccess(getItemCount());
        }
    }

    public class ChatMessageHoldView extends RecyclerView.ViewHolder {

        private RelativeLayout otherChat;
        private RelativeLayout selfChat;
        private ImageView otherHeadPhoto;
        private ImageView selfHeadPhoto;
        private TextView otherChatMessage;
        private TextView selfChatMessage;

        public ChatMessageHoldView(@NonNull View view) {
            super(view);
            otherChat = view.findViewById(R.id.other_chat);
            selfChat = view.findViewById(R.id.self_chat);
            otherHeadPhoto = view.findViewById(R.id.head_photo);
            selfHeadPhoto = view.findViewById(R.id.self_head_photo);
            otherChatMessage = view.findViewById(R.id.other_chat_message);
            selfChatMessage = view.findViewById(R.id.self_chat_message);
        }
    }

    private OnSendListener onSendListener;

    public interface OnSendListener {
        void onSuccess(int count);

        void onFail();
    }

    public void setOnSendListener(OnSendListener onSendListener) {
        this.onSendListener = onSendListener;
    }
}
