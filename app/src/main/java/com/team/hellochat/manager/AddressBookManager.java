package com.team.hellochat.manager;

import android.content.Context;
import android.content.SharedPreferences;

import com.team.hellochat.utils.Bean2AnotherBean;
import com.team.hellochat.bean.AddressBook;
import com.team.hellochat.bean.DiscussionGroup;
import com.team.hellochat.bean.Friend;
import com.team.hellochat.bean.User;
import com.team.hellochat.utils.JsonUtil;
import com.team.hellochat.utils.PreferenceUtil;

import java.util.List;

import static com.team.hellochat.app.App.SharedLabel.ADDRESS_BOOK;

/**
 * 通讯录管理
 * Created by Sweven on 2019/4/18.
 * Email:sweventears@Foxmail.com
 */
public class AddressBookManager {
    private static AddressBookManager instance;

    private AddressBook addressBook;

    public static AddressBookManager getInstance() {
        if (instance == null) {
            synchronized (AddressBookManager.class) {
                instance = new AddressBookManager();
            }
        }
        return instance;
    }

    public static AddressBookManager getInstance(Context context) {
        if (instance == null) {
            synchronized (AddressBookManager.class) {
                instance = new AddressBookManager();
            }
        }
        instance.addressBook = JsonUtil.jsonToObject(new PreferenceUtil(context).getString(ADDRESS_BOOK), new AddressBook());
        return instance;
    }

    public boolean isFriend(int uid) {
        for (Friend friend : addressBook.getFriends()) {
            if (friend.getId() == uid) {
                return true;
            }
        }
        return false;
    }

    public void addFriend(Context context,int uid){
        User user=UserDatabaseManager.getInstance().getUserByUid(uid);
        Friend friend= Bean2AnotherBean.User2Friend(user);
        addressBook.getFriends().add(friend);
        save(context);
    }

    public List<Friend> getFriends() {
        return addressBook.getFriends();
    }

    public List<DiscussionGroup> getDiscussionGroups() {
        return addressBook.getGroups();
    }

    public void addNewFriend(Context context, Friend friend) {
        addressBook.getFriends().add(friend);
        addressBook.setFriendsCount(addressBook.getFriendsCount() + 1);
        save(context);
    }
    public void addNewFriend(Context context, int uid) {
        User user=UserDatabaseManager.getInstance().getUserByUid(uid);
        Friend friend=Bean2AnotherBean.User2Friend(user);
        addressBook.getFriends().add(friend);
        addressBook.setFriendsCount(addressBook.getFriendsCount() + 1);
        save(context);
    }
    public void addNewDiscussionGroup(Context context, DiscussionGroup discussionGroup) {
        addressBook.getGroups().add(discussionGroup);
        addressBook.setGroupsCount(addressBook.getGroupsCount() + 1);
        save(context);
    }

    public void setAddressBook(Context context, AddressBook addressBook) {
        this.addressBook = addressBook;
        save(context);
    }

    public AddressBook getAddressBook() {
        return addressBook;
    }

    private void save(Context context) {
        SharedPreferences.Editor editor = new PreferenceUtil(context).getEditor();
        editor.putString(ADDRESS_BOOK, JsonUtil.object2Json(addressBook));
        editor.apply();
    }
}
