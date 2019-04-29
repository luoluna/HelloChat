package com.team.hellochat.adapter;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.team.hellochat.R;
import com.team.hellochat.bean.HeadPicture;
import com.team.hellochat.utils.WindowUtil;

/**
 * Created by Sweven on 2019/4/2.
 * Email:sweventears@Foxmail.com
 */
public class HeadStoreAdapter extends RecyclerView.Adapter<HeadStoreAdapter.ViewHolder> {

    private Activity activity;
    private LayoutInflater inflater;

    private OnclickItemListener onclickItemListener;

    public HeadStoreAdapter(Activity activity) {
        this.activity = activity;
        inflater = LayoutInflater.from(activity);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_list_head_photo_store, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        int resId = HeadPicture.values()[position + 1].getResId();
        Glide.with(activity)
                .load(resId)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.photo);
    }

    @Override
    public int getItemCount() {
        return HeadPicture.values().length - 1;
    }

    public void setOnclickItemListener(OnclickItemListener onclickItemListener) {
        this.onclickItemListener = onclickItemListener;
    }

    public interface OnclickItemListener {
        void onClick(int avatar);
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private ImageView photo;

        public ViewHolder(View itemView) {
            super(itemView);
            photo = itemView.findViewById(R.id.head_photo);
            int width = WindowUtil.getWindowWidth(activity);
            int needWidth = width / 4 - 50;
            FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(needWidth, needWidth);
            photo.setLayoutParams(params);
            photo.setOnClickListener(this);
        }

        /**
         * Called when a view has been clicked.
         *
         * @param v The view that was clicked.
         */
        @Override
        public void onClick(View v) {
            if (onclickItemListener != null) {
                onclickItemListener.onClick(getAdapterPosition() + 1);
            }
        }
    }
}
