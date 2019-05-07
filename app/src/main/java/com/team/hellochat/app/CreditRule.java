package com.team.hellochat.app;

import com.team.hellochat.bean.User;
import com.team.hellochat.manager.AddressBookManager;
import com.team.hellochat.utils.RegMatchUtil;

/**
 * Created by Sweven on 2019/4/27.
 * Email:sweventears@Foxmail.com
 */
public class CreditRule {

    private User user;

    public CreditRule(User user) {
        this.user = user;
        calculateCreditPoint();
    }

    private void calculateCreditPoint() {
        user.setCreditPoint(basics() +
                phone(user.getPhone()) +
                email(user.getEmail()) +
                idCard(user.getIdCard()) +
                beCollected() +
                friend() +
                loginDay());
    }

    private int basics() {
        return 120;
    }

    private int phone(String phone) {
        return phone.length() > 0 ? 50 : 0;
    }

    private int email(String email) {
        return email.length() > 0 ? 30 : 0;
    }

    private int idCard(String idCard) {
        return idCard.length() > 0 ? 100 : 0;
    }

    private int beCollected() {
        int mun = 12;
        return mun / 5;
    }

    private int friend() {
        int mun = AddressBookManager.getInstance().getFriends().size();
        return mun / 2;
    }

    private int loginDay() {
        int mun = 26;
        return mun / 10;
    }

    public int messageError(String message) {
        return RegMatchUtil.isError(message) ? -1 : 0;
    }

    public int getCreditPoint() {
        return user.getCreditPoint();
    }
}
