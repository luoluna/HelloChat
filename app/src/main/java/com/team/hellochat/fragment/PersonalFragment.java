package com.team.hellochat.fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.team.hellochat.R;
import com.team.hellochat.activity.CollectActivity;
import com.team.hellochat.activity.HeadPhotoStoreActivity;
import com.team.hellochat.activity.LoginActivity;
import com.team.hellochat.activity.SettingActivity;
import com.team.hellochat.base.BaseFragment;
import com.team.hellochat.bean.HeadPicture;
import com.team.hellochat.bean.User;
import com.team.hellochat.manager.LogInManager;
import com.team.hellochat.manager.UserManager;

import de.hdodenhof.circleimageview.CircleImageView;

import static android.app.Activity.RESULT_OK;
import static com.team.hellochat.activity.SettingActivity.LOG_OUT;

/**
 * Created by Sweven on 2019/3/29.
 * Email:sweventears@Foxmail.com
 */
public class PersonalFragment extends BaseFragment implements View.OnClickListener {

    private static final int OPEN_HEAD_STORE = 0x11;
    private static final int OPEN_SETTING = 0x12;
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
                showCutHeadPictureDialog();
                break;
            case R.id.personal_info_layout:
                break;
            case R.id.personal_collects_item:
                startActivity(new Intent(activity, CollectActivity.class));
                bindView();
            case R.id.personal_credit_point_item:
                break;
            case R.id.personal_setting_item:
                Intent intent = new Intent(activity, SettingActivity.class);
                startActivityForResult(intent, OPEN_SETTING);
                break;
            case R.id.personal_help_feedback_item:
                break;
            case R.id.personal_about_we_item:
                break;
        }
    }

    private void showCutHeadPictureDialog() {
        String[] s = new String[]{"头像库", "相机", "图库"};
        new AlertDialog.Builder(activity)
                .setTitle("头像来源")
                .setItems(s, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case 0:
                                Intent intent = new Intent(activity, HeadPhotoStoreActivity.class);
                                activity.startActivityForResult(intent, OPEN_HEAD_STORE);
                                break;
                            default:
                                toast.showShort("暂不支持，敬请期待~");
                                break;
                        }
                    }
                })
                .setNegativeButton("取消", null)
                .create()
                .show();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == OPEN_HEAD_STORE) {
            if (resultCode == RESULT_OK) {
                int avatar = data.getIntExtra("avatar", user.getAvatar());
                int resId = HeadPicture.getResId(avatar);
                ivHeadPicture.setImageResource(resId);
                user.setAvatar(avatar);
                UserManager.getInstance().setAvatar(activity, avatar);
            }
        } else if (requestCode == OPEN_SETTING) {
            if (resultCode == LOG_OUT) {
                activity.finish();
                LogInManager.getInstance().setLog(activity, false);
                startActivity(new Intent(activity, LoginActivity.class));
            }
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
