package com.team.hellochat.manager;

import android.content.Context;

import com.team.hellochat.bean.AddressBook;
import com.team.hellochat.bean.DiscussionGroup;
import com.team.hellochat.bean.Friend;
import com.team.hellochat.utils.JsonUtil;
import com.team.hellochat.utils.PreferenceUtil;

import java.util.List;

/**
 * 通讯录管理
 * Created by Sweven on 2019/4/18.
 * Email:sweventears@Foxmail.com
 */
public class AddressBookManager {
    private static final String ADDRESS_BOOK = "address_book";
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
        instance.addressBook = JsonUtil.jsonToObject(new PreferenceUtil(context, ADDRESS_BOOK).getString(ADDRESS_BOOK), new AddressBook());
        return instance;
    }

    public List<Friend> getFriends() {
        return addressBook.getFriends();
    }

    public List<DiscussionGroup> getDiscussionGroups() {
        return addressBook.getGroups();
    }

    public void addNewFriend(Context context, Friend friend) {
        addressBook.getFriends().add(friend);
        save(context);
    }

    public void addNewDiscussionGroup(Context context, DiscussionGroup discussionGroup) {
        addressBook.getGroups().add(discussionGroup);
        save(context);
    }

    public AddressBook getAddressBook() {
        return addressBook;
    }

    private void save(Context context) {
        new PreferenceUtil(context).save(ADDRESS_BOOK, JsonUtil.object2Json(addressBook));
    }
}
