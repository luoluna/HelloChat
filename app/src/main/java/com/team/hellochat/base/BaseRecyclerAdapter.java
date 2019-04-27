package com.team.hellochat.base;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;

import com.team.hellochat.utils.LogUtil;
import com.team.hellochat.utils.ToastUtil;

import java.util.List;

/**
 * Created by Sweven on 2019/4/1.
 * Email:sweventears@Foxmail.com
 */
public abstract class BaseRecyclerAdapter<T> extends RecyclerView.Adapter {

    protected Activity activity;
    protected List<T> list;
    protected LayoutInflater inflater;
    protected ToastUtil toast;
    protected LogUtil log;

    public BaseRecyclerAdapter(Activity activity, List<T> list) {
        this.activity = activity;
        this.list = list;
        inflater = LayoutInflater.from(activity);
        toast = new ToastUtil(activity);
        log = new LogUtil(this.getClass().getName());
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }
}
