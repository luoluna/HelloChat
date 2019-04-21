package com.team.hellochat.bean;


import android.util.Log;

import com.team.hellochat.R;

/**
 * Created by Sweven on 2019/4/10.
 * Email:sweventears@Foxmail.com
 */
public enum HeadPicture {
    DEFAULT(R.drawable.default_head_photo, 0),
    ACG01(R.drawable.head_photo_acg_01, 1),
    ACG02(R.drawable.head_photo_acg_02, 2),
    ACG03(R.drawable.head_photo_acg_03, 3),
    ACG04(R.drawable.head_photo_acg_04, 4),
    ACG05(R.drawable.head_photo_acg_05, 5),
    ACG06(R.drawable.head_photo_acg_06, 6),
    ACG07(R.drawable.head_photo_acg_07, 7),
    ACG08(R.drawable.head_photo_acg_08, 8),
    ACG09(R.drawable.head_photo_acg_09, 9),
    ACG10(R.drawable.head_photo_acg_10, 10),
    CARTOON01(R.drawable.head_photo_cartoon_01, 11),
    CARTOON02(R.drawable.head_photo_cartoon_02, 12),
    CARTOON03(R.drawable.head_photo_cartoon_03, 13),
    CARTOON04(R.drawable.head_photo_cartoon_04, 14),
    CARTOON05(R.drawable.head_photo_cartoon_05, 15),
    CARTOON06(R.drawable.head_photo_cartoon_06, 16),
    CARTOON07(R.drawable.head_photo_cartoon_07, 17),
    CARTOON08(R.drawable.head_photo_cartoon_08, 18),
    CARTOON09(R.drawable.head_photo_cartoon_09, 19),
    CARTOON10(R.drawable.head_photo_cartoon_10, 20),
    STAR01(R.drawable.head_photo_star_01, 21),
    STAR02(R.drawable.head_photo_star_02, 22),
    STAR03(R.drawable.head_photo_star_03, 23),
    STAR04(R.drawable.head_photo_star_04, 24),
    STAR05(R.drawable.head_photo_star_05, 25),
    STAR06(R.drawable.head_photo_star_06, 26),
    STAR07(R.drawable.head_photo_star_07, 27),
    STAR08(R.drawable.head_photo_star_08, 28),
    STAR09(R.drawable.head_photo_star_09, 29),
    STAR10(R.drawable.head_photo_star_10, 30),
    SUPERMAN01(R.drawable.head_photo_superman_01, 31),
    SUPERMAN02(R.drawable.head_photo_superman_02, 32),
    SUPERMAN03(R.drawable.head_photo_superman_03, 33),
    SUPERMAN04(R.drawable.head_photo_superman_04, 34),
    SUPERMAN05(R.drawable.head_photo_superman_05, 35),
    SUPERMAN06(R.drawable.head_photo_superman_06, 36);

    private int resId;
    private int avatar;

    HeadPicture(int resId, int avatar) {
        this.resId = resId;
        this.avatar = avatar;
    }

    public static int getResId(int avatar) {
        for (HeadPicture picture : HeadPicture.values()) {
            if (picture.getAvatar() == avatar) {
                return picture.getResId();
            }
        }
        return HeadPicture.DEFAULT.resId;
    }

    public static int getResId(String avatarSt) {
        int avatar = 0;
        try {
            avatar = Integer.parseInt(avatarSt);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            Log.e("enum-HeadPicture::::", "avatar is not number");
        }
        for (HeadPicture picture : HeadPicture.values()) {
            if (picture.getAvatar() == avatar) {
                return picture.getResId();
            }
        }
        return HeadPicture.DEFAULT.resId;
    }

    public int getResId() {
        return resId;
    }

    public void setResId(int resId) {
        this.resId = resId;
    }

    public int getAvatar() {
        return avatar;
    }

    public void setAvatar(int avatar) {
        this.avatar = avatar;
    }

}
