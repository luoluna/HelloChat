package com.team.hellochat.adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.team.hellochat.R;
import com.team.hellochat.activity.ChatRoomMessageActivity;
import com.team.hellochat.activity.PersonalHomePageActivity;
import com.team.hellochat.base.BaseRecyclerAdapter;
import com.team.hellochat.bean.Discovery;
import com.team.hellochat.manager.CollectManager;
import com.team.hellochat.manager.UserManager;
import com.team.hellochat.utils.WindowUtil;

import java.util.List;

import static com.team.hellochat.app.App.IntentLabel.CHAT_ROOM_ICON;
import static com.team.hellochat.app.App.IntentLabel.CHAT_ROOM_TITLE;
import static com.team.hellochat.app.App.IntentLabel.CHAT_ROOM_TYPE;
import static com.team.hellochat.app.App.IntentLabel.CHAT_ROOM_WITH_ID;
import static com.team.hellochat.app.App.IntentLabel.MESSAGE_FILE;
import static com.team.hellochat.app.App.IntentLabel.USER_ID;

/**
 * Created by Sweven on 2019/4/27.
 * Email:sweventears@Foxmail.com
 */
public class DiscoveryAdapter extends BaseRecyclerAdapter<Discovery> {

    public DiscoveryAdapter(Activity activity, List<Discovery> list) {
        super(activity, list);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = inflater.inflate(R.layout.item_list_discovery, viewGroup, false);
        return new DiscoveryViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
        DiscoveryViewHolder holder = (DiscoveryViewHolder) viewHolder;
        Discovery discovery = list.get(position);
        int myCredit = UserManager.getInstance().getUser().getCreditPoint();

        Glide.with(activity)
                .load(R.drawable.background_personal_head)
                .into(holder.ivCardBackground);
        holder.tvName.setText("昵称：" + discovery.getNickname());
        holder.tvAge.setText("性别：" + discovery.getSex().getLabel());
        holder.tvSex.setText("年龄：" + discovery.getAge());
        holder.tvCreditPoint.setText("信用点：" + (myCredit >= discovery.getCreditPoint() ? discovery.getCreditPoint() : "***"));

        holder.ivCollect.setImageResource(CollectManager.getInstance().isCollect(discovery.getId()) ? R.drawable.ic_collected : R.drawable.ic_collect);
    }

    class DiscoveryViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private CardView item;
        private ImageView ivCardBackground;
        private TextView tvName, tvCreditPoint, tvAge, tvSex, tvAddress;
        private ImageView ivStarChat, ivCollect;
        private LinearLayout lyStarChat, lyCollect;

        public DiscoveryViewHolder(@NonNull View view) {
            super(view);
            item = view.findViewById(R.id.card_item);
            ivCardBackground = view.findViewById(R.id.iv_discovery_card_background);
            tvName = view.findViewById(R.id.tv_discovery_card_name);
            tvCreditPoint = view.findViewById(R.id.tv_discovery_card_credit_point);
            tvAge = view.findViewById(R.id.tv_discovery_card_age);
            tvSex = view.findViewById(R.id.tv_discovery_card_sex);
            tvAddress = view.findViewById(R.id.tv_discovery_card_address);
            ivStarChat = view.findViewById(R.id.iv_start_chat);
            ivCollect = view.findViewById(R.id.iv_collect);
            lyStarChat = view.findViewById(R.id.ly_start_chat);
            lyCollect = view.findViewById(R.id.ly_collect);

            int width = WindowUtil.getWindowWidth(activity);
            RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) ivCardBackground.getLayoutParams();
            params.height = width / 2;
            ivCardBackground.setLayoutParams(params);

            item.setOnClickListener(this);
            lyStarChat.setOnClickListener(this);
            lyCollect.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Discovery discovery = list.get(getAdapterPosition());
            int myCredit = UserManager.getInstance().getUser().getCreditPoint();
            switch (v.getId()) {
                case R.id.card_item:
                    if (myCredit >= discovery.getCreditPoint()) {
                        Intent intent = new Intent(activity, PersonalHomePageActivity.class);
                        intent.putExtra(USER_ID, discovery.getId());
                        activity.startActivity(intent);
                    } else {
                        toast.showShort("您的信用点不足以查看对方的信息");
                    }
                    break;
                case R.id.ly_start_chat:
                    if (myCredit >= discovery.getCreditPoint()) {
                        toast.showShort("开始聊天");
                        Intent intent = new Intent(activity, ChatRoomMessageActivity.class);
                        intent.putExtra(CHAT_ROOM_TYPE, false);
                        intent.putExtra(MESSAGE_FILE, "");
                        intent.putExtra(CHAT_ROOM_TITLE, discovery.getNickname());
                        intent.putExtra(CHAT_ROOM_WITH_ID, discovery.getId());
                        intent.putExtra(CHAT_ROOM_ICON, discovery.getAvatar());
                        activity.startActivity(intent);
                    } else {
                        toast.showShort("您的信用点不足以与对方聊天");
                    }
                    break;
                case R.id.ly_collect:
                    list.get(getAdapterPosition()).setCollect(!discovery.isCollect());
                    boolean isCollect = list.get(getAdapterPosition()).isCollect();
                    ivCollect.setImageResource(isCollect ? R.drawable.ic_collected : R.drawable.ic_collect);
                    if (isCollect) {
                        CollectManager.getInstance().addCollect(activity, list.get(getAdapterPosition()));
                    } else {
                        CollectManager.getInstance().cancelCollect(activity, list.get(getAdapterPosition()).getId());
                    }
                    break;
            }
        }
    }
}
