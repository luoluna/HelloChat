package com.team.hellochat.adapter;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.team.hellochat.R;
import com.team.hellochat.activity.ChatRoomActivity;
import com.team.hellochat.bean.ChatRoom;
import com.team.hellochat.utils.DateUtil;
import com.team.hellochat.utils.DisplayUtils;
import com.team.hellochat.view.StickyViewHelper;

import java.util.List;

/**
 * Created by Sweven on 2019/4/1.
 * Email:sweventears@Foxmail.com
 */
public class ChatRoomAdapter extends RecyclerView.Adapter<ChatRoomAdapter.ChatRoomHoldView> {

    private Activity activity;
    private List<ChatRoom> list;
    private LayoutInflater inflater;

    public ChatRoomAdapter(Activity activity, List<ChatRoom> list) {
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
        ChatRoom room = list.get(position);
        Glide.with(activity)
                .load(room.getIconUri())
                .into(hold.roomIcon);
        hold.roomTitle.setText(room.getTitle());
        hold.roomTime.setText(DateUtil.getDateString(room.getPutTime()));
        hold.roomMessage.setText(room.getMessage());
        hold.redPoint.setText(getMessageCount(room.getMessageCount()));

        StickyViewHelper stickyViewHelper = new StickyViewHelper(activity, hold.redPoint, R.layout.red_point);
        setViewOut2InRangeUp(stickyViewHelper);
        setViewOutRangeUp(position, stickyViewHelper);
        setViewInRangeUp(stickyViewHelper);
        setViewInRangeMove(stickyViewHelper);
        setViewOutRangeMove(stickyViewHelper);
    }

    private String getMessageCount(int count) {
        if (count < 100) {
            return String.valueOf(count);
        } else {
            return "99+";
        }
    }

    /**
     * view在范围外移动执行此Runnable
     *
     * @param stickyViewHelper
     */
    private void setViewOutRangeMove(StickyViewHelper stickyViewHelper) {
        stickyViewHelper.setViewOutRangeMoveRun(new Runnable() {
            @Override
            public void run() {
                DisplayUtils.showToast(activity, "ViewOutRangeMove");
            }
        });
    }

    /**
     * view在范围内移动指此此Runnable
     *
     * @param stickyViewHelper
     */
    private void setViewInRangeMove(StickyViewHelper stickyViewHelper) {
        stickyViewHelper.setViewInRangeMoveRun(new Runnable() {
            @Override
            public void run() {
                DisplayUtils.showToast(activity, "ViewInRangeMove");
            }
        });
    }

    /**
     * view没有移出过范围，在范围内松手
     *
     * @param stickyViewHelper
     */
    private void setViewInRangeUp(StickyViewHelper stickyViewHelper) {
        stickyViewHelper.setViewInRangeUpRun(new Runnable() {
            @Override
            public void run() {
                DisplayUtils.showToast(activity, "ViewInRangeUp");
                notifyDataSetChanged();
            }
        });
    }

    /**
     * view移出范围，最后在范围外松手
     *
     * @param position
     * @param stickyViewHelper
     */
    private void setViewOutRangeUp(final int position, StickyViewHelper stickyViewHelper) {
        stickyViewHelper.setViewOutRangeUpRun(new Runnable() {
            @Override
            public void run() {
                DisplayUtils.showToast(activity, "ViewOutRangeUp");
                list.remove(position);
                notifyDataSetChanged();
            }
        });
    }

    /**
     * view移出过范围，最后在范围内松手执行次Runnable
     *
     * @param stickyViewHelper
     */
    private void setViewOut2InRangeUp(StickyViewHelper stickyViewHelper) {
        stickyViewHelper.setViewOut2InRangeUpRun(new Runnable() {
            @Override
            public void run() {
                DisplayUtils.showToast(activity, "ViewOut2InRangeUp");
                notifyDataSetChanged();
            }
        });
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
            ChatRoom room = list.get(getAdapterPosition());
            Intent intent = new Intent(activity, ChatRoomActivity.class);
            intent.putExtra("type", room.isGroup());
            intent.putExtra("message", room.getMessage());
            activity.startActivity(intent);
        }
    }
}
