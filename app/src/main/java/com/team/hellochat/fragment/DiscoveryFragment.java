package com.team.hellochat.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PagerSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.team.hellochat.R;
import com.team.hellochat.adapter.DiscoveryAdapter;
import com.team.hellochat.base.BaseFragment;
import com.team.hellochat.bean.Discovery;
import com.team.hellochat.bean.User;
import com.team.hellochat.manager.UserDatabaseManager;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sweven on 2019/3/29.
 * Email:sweventears@Foxmail.com
 */
public class DiscoveryFragment extends BaseFragment implements View.OnClickListener {

    private View fragment;

    private ImageView ivSearch, ivAdd;
    private RecyclerView recyclerView;
    private DiscoveryAdapter discoveryAdapter;
    private List<Discovery> list = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        fragment = inflater.inflate(R.layout.fragment_discovery, null);
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
        ivSearch = fragment.findViewById(R.id.iv_discovery_search);
        ivAdd = fragment.findViewById(R.id.iv_discovery_add);
        recyclerView = fragment.findViewById(R.id.discovery_list);
    }

    @Override
    protected void initData() {

        receiveData();
        discoveryAdapter = new DiscoveryAdapter(activity, list);
        LinearLayoutManager layoutManager = new LinearLayoutManager(activity);
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(layoutManager);
        new PagerSnapHelper().attachToRecyclerView(recyclerView);
        recyclerView.setAdapter(discoveryAdapter);

        ivAdd.setOnClickListener(this);
        ivSearch.setOnClickListener(this);
    }

    long[] currentPosition;
    int LONGITUDE = 0;
    int LATITUDE = 1;
    private static final int RANGE = 300;
    private List<User> users=new ArrayList<>();

    private void receiveData() {
//        users=searchUser(currentPosition[LONGITUDE],currentPosition[LATITUDE],RANGE);

        this.list = UserDatabaseManager.getInstance().getDiscovery(activity);
    }

    private List<User> searchUser(long currentLongitude,long currentLatitude, long range) {

        return new ArrayList<>();
    }

    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_discovery_add:
                break;
            case R.id.iv_discovery_search:
                toast.showShort("待开发中，敬请期待~");
                break;
        }
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden) {
            receiveData();
            recyclerView.setAdapter(discoveryAdapter);
        }
    }


}
