package com.team.hellochat.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.team.hellochat.R;
import com.team.hellochat.adapter.FriendsAdapter;
import com.team.hellochat.base.BaseFragment;
import com.team.hellochat.bean.AddressBook;
import com.team.hellochat.bean.Friend;
import com.team.hellochat.manager.AddressBookManager;
import com.team.hellochat.view.ShowEmpty;

import java.util.List;

/**
 * Created by Sweven on 2019/3/29.
 * Email:sweventears@Foxmail.com
 */
public class FriendsFragment extends BaseFragment {

    private View fragment;

    //top
    private TextView title;

    private ShowEmpty showEmpty;
    private RecyclerView recyclerView;
    private FriendsAdapter friendsAdapter;

    //data
    private AddressBook addressBook = new AddressBook();
    private List<Friend> list = addressBook.getFriends();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        fragment = inflater.inflate(R.layout.fragment_friends, null);
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
        title = fragment.findViewById(R.id.bar_title);
        recyclerView = fragment.findViewById(R.id.friends_friend_fragment_list);
        showEmpty = fragment.findViewById(R.id.show_empty);
    }

    @Override
    protected void initData() {
        title.setText(getString(R.string.friends_fragment_title));

        showEmpty.load();

        getAddressBook();
        friendsAdapter = new FriendsAdapter(getActivity(), list);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(friendsAdapter);

    }

    public void getAddressBook() {
        addressBook = AddressBookManager.getInstance().getAddressBook();

        list = addressBook.getFriends();
        if (list.size() > 0) {
            showEmpty.hidden();
        } else {
            showEmpty.loadNoData();
            showEmpty.setText("你还没有添加好友o(╥﹏╥)o");
        }
    }
}
