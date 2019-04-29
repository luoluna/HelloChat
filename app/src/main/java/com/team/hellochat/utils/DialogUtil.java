package com.team.hellochat.utils;

import android.app.Activity;

import com.team.hellochat.view.MessageDialog;
import com.team.hellochat.view.OnClickChooseListener;


/**
 * Created by Sweven on 2019/4/4.
 * Email:sweventears@Foxmail.com
 */
public class DialogUtil {

    public static void ShowTips(Activity activity, String message,OnConfirmListener onConfirmListener) {
        final MessageDialog dialog = new MessageDialog(activity);
        dialog.setTips(message);
        dialog.setCancel("取消");
        dialog.setConfirm("确定");
        dialog.setOnClickChooseListener(new OnClickChooseListener() {
            @Override
            public void onCancel() {
                dialog.dismiss();
            }

            @Override
            public void onConfirm() {
                if (onConfirmListener != null) {
                    onConfirmListener.onClick();
                }
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    public interface OnConfirmListener {
        void onClick();
    }
}
