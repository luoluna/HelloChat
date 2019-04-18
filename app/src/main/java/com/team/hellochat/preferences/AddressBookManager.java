package com.team.hellochat.preferences;

import android.content.Context;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.team.hellochat.bean.AddressBook;
import com.team.hellochat.bean.DiscussionGroup;
import com.team.hellochat.bean.Friend;
import com.team.hellochat.utils.PreferenceUtil;

import java.io.IOException;
import java.util.List;

/**
 * Created by Sweven on 2019/4/18.
 * Email:sweventears@Foxmail.com
 */
public class AddressBookManager {
    private static final String ADDRESS_BOOK = "address_book";
    private static AddressBookManager instance;

    private AddressBook addressBook;

    public static AddressBookManager getInstance(Context context) {
        if (instance == null) {
            synchronized (AddressBookManager.class) {
                instance = new AddressBookManager();
            }
        }
        try {
            instance.addressBook = new ObjectMapper().readValue(new PreferenceUtil(context).getString(ADDRESS_BOOK), AddressBook.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
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
        try {
            new PreferenceUtil(context).save(ADDRESS_BOOK, new ObjectMapper().writeValueAsString(addressBook));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}
