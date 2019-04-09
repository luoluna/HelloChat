package com.team.hellochat.base;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.team.hellochat.utils.LogUtil;
import com.team.hellochat.utils.ToastUtil;

/**
 * Created by Sweven on 2019/3/30.
 * Email:sweventears@Foxmail.com
 */
public class BaseFragment extends Fragment {

    protected Activity activity;
    protected ToastUtil toast;
    protected LogUtil log;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activity = getActivity();
        toast = new ToastUtil(activity);
        log = new LogUtil(getActivity().getLocalClassName());
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    protected void bindView() {
    }

    protected void initData() {
    }
}
