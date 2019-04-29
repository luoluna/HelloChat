package com.team.hellochat.bean;

import com.team.hellochat.R;

/**
 * Created by Sweven on 2019/4/28.
 * Email:sweventears@Foxmail.com
 */
public enum BackgroundImage {
    DEFAULT_BG(R.drawable.background_personal_head, 0x01);
    private int resId;
    private int index;

    BackgroundImage(int resId, int index) {
        this.resId = resId;
        this.index = index;
    }

    public int getResId(int index) {
        for (BackgroundImage value : BackgroundImage.values()) {
            if (index == value.index) {
                return value.resId;
            }
        }
        return DEFAULT_BG.getResId();
    }

    public BackgroundImage getBG(int index) {
        for (BackgroundImage value : BackgroundImage.values()) {
            if (index == value.index) {
                return value;
            }
        }
        return DEFAULT_BG;
    }

    public int getResId() {
        return resId;
    }

    public int getIndex() {
        return index;
    }
}
