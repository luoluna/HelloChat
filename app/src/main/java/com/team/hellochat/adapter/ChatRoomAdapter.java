package com.team.hellochat.adapter;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.team.hellochat.R;
import com.team.hellochat.activity.ChatRoomMessageActivity;
import com.team.hellochat.app.App;
import com.team.hellochat.bean.ChatMessage;
import com.team.hellochat.bean.ChatRoomItem;
import com.team.hellochat.bean.HeadPicture;
import com.team.hellochat.bean.MessageInfo;
import com.team.hellochat.bean.MessageType;
import com.team.hellochat.utils.DateUtil;

import java.util.List;

import static com.team.hellochat.app.App.IntentLabel.CHAT_ROOM_ICON;
import static com.team.hellochat.app.App.IntentLabel.CHAT_ROOM_TYPE;
import static com.team.hellochat.app.App.IntentLabel.CHAT_ROOM_WITH_ID;
import static com.team.hellochat.app.App.IntentLabel.MESSAGE_FILE;

/**
 * Created by Sweven on 2019/4/1.
 * Email:sweventears@Foxmail.com
 */
public class ChatRoomAdapter extends RecyclerView.Adapter<ChatRoomAdapter.ChatRoomHoldView> {

    private Activity activity;
    private List<ChatRoomItem> list;
    private LayoutInflater inflater;

    public ChatRoomAdapter(Activity activity, List<ChatRoomItem> list) {
        this.activity = activity;
        this.list = list;
        inflater = LayoutInflater.from(activity);
    }

    @NonNull
    @Override
    public ChatRoomHoldView onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = inflater.inflate(R.layout.item_list_chat_room, viewGroup, false);
        return new ChatRoomHoldView(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ChatRoomHoldView hold, int position) {
        ChatRoomItem room = list.get(position);
        ChatMessage message = room.getMessage();
        Glide.with(activity)
                .load(HeadPicture.getResId(room.getIcon()))
                .into(hold.roomIcon);
        hold.roomTitle.setText(room.getTitle());
        hold.roomTime.setText(DateUtil.getDateString(room.getRecentMessage().getTime()));

        if (room.getMessage().getList().size() > 0) {
            MessageInfo info = message.getRecentMessage();
            hold.roomMessage.setText(info.getType() == MessageType.TEXT ? info.getInformation() : info.getType().getTipName());
        }
        if (message.getNoReadCount() > 0) {
            hold.redPoint.setVisibility(View.VISIBLE);
            hold.redPoint.setText(getMessageCount(message.getNoReadCount()));
        } else {
            hold.redPoint.setVisibility(View.GONE);
        }

    }

    private String getMessageCount(int count) {
        if (count < 100) {
            return String.valueOf(count);
        } else {
            return "99+";
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ChatRoomHoldView extends RecyclerView.ViewHolder implements View.OnClickListener {

        private ImageView roomIcon;
        private TextView roomTitle;
        private TextView roomMessage;
        private TextView roomTime;
        private TextView redPoint;

        public ChatRoomHoldView(@NonNull View view) {
            super(view);
            roomIcon = view.findViewById(R.id.iv_chat_room_icon);
            roomTitle = view.findViewById(R.id.tv_chat_room_title);
            roomMessage = view.findViewById(R.id.tv_chat_room_message);
            roomTime = view.findViewById(R.id.tv_chat_room_get_time);
            redPoint = view.findViewById(R.id.tv_red_point);

            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            ChatRoomItem room = list.get(getAdapterPosition());
            Intent intent = new Intent(activity, ChatRoomMessageActivity.class);
            intent.putExtra(CHAT_ROOM_TYPE, room.isGroup());
            intent.putExtra(MESSAGE_FILE, room.getMessage().getFile());
            intent.putExtra(CHAT_ROOM_WITH_ID, room.getWithUid());
            intent.putExtra(CHAT_ROOM_ICON, room.getIcon());
            intent.putExtra(App.IntentLabel.CHAT_ROOM_TITLE, room.getTitle());
            activity.startActivity(intent);
            new Handler().postDelayed(() -> {
                redPoint.setText(getMessageCount(0));
                redPoint.setVisibility(View.GONE);
            }, 1000);

        }
    }
}
