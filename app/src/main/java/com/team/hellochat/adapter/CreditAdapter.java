package com.team.hellochat.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.team.hellochat.base.BaseRecyclerAdapter;

/**
 * Created by Sweven on 2019/5/6.
 * Email:sweventears@Foxmail.com
 */
public class CreditAdapter extends RecyclerView.Adapter<CreditAdapter.CreditHoldView> {
    @NonNull
    @Override
    public CreditHoldView onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull CreditHoldView creditHoldView, int i) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class CreditHoldView extends RecyclerView.ViewHolder {
        public CreditHoldView(@NonNull View itemView) {
            super(itemView);
        }
    }
}
