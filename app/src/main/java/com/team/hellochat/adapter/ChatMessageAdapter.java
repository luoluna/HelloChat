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
import com.team.hellochat.bean.ChatMessage;

import java.util.List;

/**
 * Created by Sweven on 2019/3/31.
 * Email:sweventears@Foxmail.com
 */
public class ChatMessageAdapter extends BaseRecyclerAdapter {

    public ChatMessageAdapter(Activity activity, List<ChatMessage> list) {
        super(activity, list);
        this.activity = activity;
        this.list = list;
        inflater = LayoutInflater.from(activity);
    }

    @NonNull
    @Override
    public ChatMessageHoldView onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = inflater.inflate(R.layout.item_list_chat_message, viewGroup, false);
        return new ChatMessageHoldView(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
        ChatMessageHoldView hold = (ChatMessageHoldView) viewHolder;
        ChatMessage message = (ChatMessage) list.get(position);

        if (message.isSelf()) {
            hold.selfChat.setVisibility(View.VISIBLE);
            hold.otherChat.setVisibility(View.GONE);

            hold.selfChatMessage.setText(message.getMessage());
            Glide.with(activity)
                    .load(message.getHeadPhoto())
                    .into(hold.selfHeadPhoto);
        } else {
            hold.otherChat.setVisibility(View.VISIBLE);
            hold.selfChat.setVisibility(View.GONE);

            hold.otherChatMessage.setText(message.getMessage());
            Glide.with(activity)
                    .load(message.getHeadPhoto())
                    .into(hold.otherHeadPhoto);

        }
    }

    public void sendSelfMessage(String message) {
        ChatMessage chatMessage = new ChatMessage(0, 1, "", true, message, "我", System.currentTimeMillis());
        list.add(chatMessage);
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
