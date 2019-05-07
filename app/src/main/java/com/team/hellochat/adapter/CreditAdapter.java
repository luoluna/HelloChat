package com.team.hellochat.adapter;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.team.hellochat.R;
import com.team.hellochat.activity.AccountSecurityActivity;
import com.team.hellochat.app.Setting;
import com.team.hellochat.bean.UpCredit;
import com.team.hellochat.bean.User;

import java.util.ArrayList;
import java.util.List;

import static com.team.hellochat.activity.CreditActivity.GOTO_ACCOUNT;

/**
 * Created by Sweven on 2019/5/6.
 * Email:sweventears@Foxmail.com
 */
public class CreditAdapter extends RecyclerView.Adapter<CreditAdapter.CreditHoldView> {

    private User user;
    private Activity activity;
    private LayoutInflater inflater;
    private List<UpCredit> list = new ArrayList<>();

    public CreditAdapter(Activity activity, User user) {
        this.activity = activity;
        this.user = user;
        inflater = LayoutInflater.from(activity);
        getUpMethod();
    }

    @NonNull
    @Override
    public CreditHoldView onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = inflater.inflate(R.layout.item_list_credit, viewGroup, false);
        return new CreditHoldView(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CreditHoldView hold, int position) {
        UpCredit upCredit = list.get(position);
        hold.tips.setText(upCredit.getMethod());
        if (!upCredit.isGoto()) {
            hold.btn.setVisibility(View.GONE);
        } else {
            hold.btn.setVisibility(View.VISIBLE);
        }
    }

    private void getUpMethod() {
        list.clear();
        if (Setting.isEmpty(user.getIdCard())) {
            list.add(new UpCredit("idCard", "添加身份证验证可提高信用点", true));
        }
        if (Setting.isEmpty(user.getEmail())) {
            list.add(new UpCredit("email", "绑定邮箱可提高信用点", true));
        }
        list.add(new UpCredit("addNewFriend", "每添加两个好友增加一点信用点", false));
        list.add(new UpCredit("addCollected", "每被五个用户关注增加一点信用点", false));
        list.add(new UpCredit("loginDaily", "每登录十天增加一点信用点", false));

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void update(User user) {
        this.user=user;
        getUpMethod();
        notifyDataSetChanged();
    }

    public class CreditHoldView extends RecyclerView.ViewHolder implements View.OnClickListener {

        private RelativeLayout item;
        private TextView tips, btn;

        public CreditHoldView(@NonNull View view) {
            super(view);
            item = view.findViewById(R.id.item);
            tips = view.findViewById(R.id.tv_up_credit_tips);
            btn = view.findViewById(R.id.tv_up_credit_btn);

            btn.setOnClickListener(this);
            item.setOnClickListener(this);
            item.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            UpCredit upCredit = list.get(getAdapterPosition());
            if (upCredit.isGoto()) {
                Intent intent = new Intent(activity, AccountSecurityActivity.class);
                activity.startActivityForResult(intent, GOTO_ACCOUNT);
            }
        }
    }
}
