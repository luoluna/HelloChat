package com.team.hellochat.manager;

import android.content.Context;

import com.team.hellochat.bean.Collect;
import com.team.hellochat.utils.JsonUtil;
import com.team.hellochat.utils.PreferenceUtil;

import java.util.ArrayList;
import java.util.List;

import static com.team.hellochat.app.App.SharedLabel.COLLECTS;

/**
 * Created by Sweven on 2019/4/27.
 * Email:sweventears@Foxmail.com
 */
public class CollectManager {
    private static CollectManager instance;

    private CollectsList list;

    public static CollectManager getInstance() {
        if (instance == null) {
            synchronized (CollectManager.class) {
                instance = new CollectManager();
            }
        }
        return instance;
    }

    public static CollectManager getInstance(Context context) {
        if (instance == null) {
            synchronized (CollectManager.class) {
                instance = new CollectManager();
            }
        }
        instance.list = JsonUtil.jsonToObject(new PreferenceUtil(context).getString(COLLECTS), new CollectsList());
        return instance;
    }

    public void addCollect(Context context, Collect collect) {
        getList().add(0, collect);
        save(context);
    }

    public void cancelCollect(Context context, int uid) {
        for (int i = 0; i < getList().size(); i++) {
            if (getList().get(i).getId() == uid) {
                getList().remove(i);
            }
        }
        save(context);
    }

    public boolean isCollect(int uid) {
        for (int i = 0; i < getList().size(); i++) {
            if (getList().get(i).getId() == uid) {
                return true;
            }
        }
        return false;
    }

    public List<Collect> getList() {
        return list.getCollects();
    }

    private void save(Context context) {
        new PreferenceUtil(context).save(COLLECTS, JsonUtil.object2Json(list));
    }

    static class CollectsList {
        private List<Collect> collects = new ArrayList<>();

        public List<Collect> getCollects() {
            return collects;
        }

        public void setCollects(List<Collect> collects) {
            this.collects = collects;
        }
    }
}
