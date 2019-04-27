package com.team.hellochat.app;

import android.content.Context;

import com.bumptech.glide.Glide;
import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.module.GlideModule;

/**
 * Created by Sweven on 2019/4/26.
 * Email:sweventears@Foxmail.com
 */
public class AppGlideModule implements GlideModule {


    /**
     * Lazily apply options to a {@link GlideBuilder} immediately before the Glide singleton is
     * created.
     *
     * <p>
     * This method will be called once and only once per implementation.
     * </p>
     *
     * @param context An Application {@link Context}.
     * @param builder The {@link GlideBuilder} that will be used to create Glide.
     */
    @Override
    public void applyOptions(Context context, GlideBuilder builder) {
        //        builder.setDiskCache();//自定义磁盘缓存

//        builder.setMemoryCache();//自定义内存缓存

//        builder.setBitmapPool(); //自定义图片池

//        builder.setDiskCacheService();//自定义本地缓存的线程池

//        builder.setResizeService();//自定义核心处理的线程池

        builder.setDecodeFormat(DecodeFormat.PREFER_ARGB_8888);//自定义图片质量
    }

    /**
     * Lazily register components immediately after the Glide singleton is created but before any requests can be
     * started.
     *
     * <p>
     * This method will be called once and only once per implementation.
     * </p>
     *
     * @param context An Application {@link Context}.
     * @param glide   The newly created Glide singleton.
     */
    @Override
    public void registerComponents(Context context, Glide glide) {

    }

}
