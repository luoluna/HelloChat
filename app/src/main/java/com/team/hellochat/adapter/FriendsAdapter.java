package com.team.hellochat.adapter;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.team.hellochat.R;
import com.team.hellochat.activity.ChatRoomMessageActivity;
import com.team.hellochat.app.Setting;
import com.team.hellochat.base.BaseRecyclerAdapter;
import com.team.hellochat.bean.Friend;
import com.team.hellochat.bean.HeadPicture;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

import static com.team.hellochat.app.App.IntentLabel.CHAT_ROOM_TITLE;
import static com.team.hellochat.app.App.IntentLabel.CHAT_ROOM_TYPE;
import static com.team.hellochat.app.App.IntentLabel.CHAT_ROOM_WITH_ID;
import static com.team.hellochat.app.App.IntentLabel.MESSAGE_FILE;

/**
 * Created by Sweven on 2019/4/18.
 * Email:sweventears@Foxmail.com
 */
public class FriendsAdapter extends BaseRecyclerAdapter<Friend> {

    public FriendsAdapter(Activity activity, List<Friend> list) {
        super(activity, list);
    }

    @NonNull
    @Override
    public FriendsViewHold onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = inflater.inflate(R.layout.item_list_friends_friend_fragment, viewGroup, false);
        return new FriendsViewHold(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
        FriendsViewHold hold = (FriendsViewHold) viewHolder;
        Friend friend = list.get(position);

        hold.name.setText(friend.getRemark());
        Glide.with(activity)
                .load(HeadPicture.getResId(friend.getAvatar()))
                .optionalCircleCrop()
                .into(hold.icon);
    }

    public class FriendsViewHold extends RecyclerView.ViewHolder implements View.OnClickListener {

        private CircleImageView icon;
        private TextView name;

        public FriendsViewHold(@NonNull View itemView) {
            super(itemView);
            icon = itemView.findViewById(R.id.civ_head_picture);
            name = itemView.findViewById(R.id.tv_friend_name);

            itemView.setOnClickListener(this);
        }

        /**
         * Called when a view has been clicked.
         *
         * @param v The view that was clicked.
         */
        @Override
        public void onClick(View v) {
            Friend friend = list.get(getAdapterPosition());
            Intent intent = new Intent(activity, ChatRoomMessageActivity.class);
            intent.putExtra(CHAT_ROOM_TYPE, false);
            intent.putExtra(MESSAGE_FILE, Setting.getChatRoomFile(friend.getId()));
            intent.putExtra(CHAT_ROOM_TITLE, friend.getRemark());
            intent.putExtra(CHAT_ROOM_WITH_ID,friend.getId());
            activity.startActivity(intent);
        }
    }
}
