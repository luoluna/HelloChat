package com.team.hellochat.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Sweven on 2019/4/18.
 * Email:sweventears@Foxmail.com
 */
public class FriendAdapter extends RecyclerView.Adapter<FriendAdapter.FriendViewHold> {
    @NonNull
    @Override
    public FriendViewHold onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull FriendViewHold friendViewHold, int i) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class FriendViewHold extends RecyclerView.ViewHolder {
        public FriendViewHold(@NonNull View itemView) {
            super(itemView);
        }
    }
}
