package com.team.hellochat.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.team.hellochat.R;
import com.team.hellochat.base.BaseFragment;
import com.team.hellochat.bean.HeadPicture;
import com.team.hellochat.bean.User;
import com.team.hellochat.manager.UserManager;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Sweven on 2019/3/29.
 * Email:sweventears@Foxmail.com
 */
public class PersonalFragment extends BaseFragment implements View.OnClickListener {

    private View fragment;

    private CircleImageView ivHeadPicture;
    private TextView tvNickname, tvCreditPoint;
    private LinearLayout lyPersonalCenter, lyCollect, lyCreditPoint, lySetting, lyHelp, lyAbout;

    private User user;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        fragment = inflater.inflate(R.layout.fragment_personal, null);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        bindView();
        initData();
    }

    @Override
    protected void bindView() {
        ivHeadPicture = fragment.findViewById(R.id.personal_head_picture);
        tvNickname = fragment.findViewById(R.id.personal_account_nickname);
        lyPersonalCenter = fragment.findViewById(R.id.personal_info_layout);
        lyCollect = fragment.findViewById(R.id.personal_collects_item);
        lyCreditPoint = fragment.findViewById(R.id.personal_credit_point_item);
        lySetting = fragment.findViewById(R.id.personal_setting_item);
        lyHelp = fragment.findViewById(R.id.personal_help_feedback_item);
        lyAbout = fragment.findViewById(R.id.personal_about_we_item);
        tvCreditPoint = fragment.findViewById(R.id.tv_credit_point);

        addOnClick(ivHeadPicture);
        addOnClick(tvNickname);
        addOnClick(lyPersonalCenter);
        addOnClick(lyCollect);
        addOnClick(lyCreditPoint);
        addOnClick(lySetting);
        addOnClick(lyHelp);
        addOnClick(lyAbout);
    }

    @Override
    protected void initData() {
        setInfo();
    }

    private void setInfo() {
        user = UserManager.getInstance().getUser();
        ivHeadPicture.setImageResource(HeadPicture.getResId(user.getAvatar()));
        tvNickname.setText(user.getNickname());
        tvCreditPoint.setText(String.valueOf(user.getCreditPoint()));
    }

    private void addOnClick(View view) {
        view.setOnClickListener(this);
    }


    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.personal_head_picture:
            case R.id.personal_account_nickname:
                break;
            case R.id.personal_info_layout:
                break;
            case R.id.personal_collects_item:
                bindView();
            case R.id.personal_credit_point_item:
                break;
            case R.id.personal_setting_item:
                break;
            case R.id.personal_help_feedback_item:
                break;
            case R.id.personal_about_we_item:
                break;
        }
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden) {
            setInfo();
        }
    }

    @Override
    public void onStart() {
        setInfo();
        super.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
    }
}
