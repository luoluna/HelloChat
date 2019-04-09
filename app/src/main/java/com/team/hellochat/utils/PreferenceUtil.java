package com.team.hellochat.utils;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import com.team.hellochat.app.App;

import java.util.Map;
import java.util.Set;

/**
 * Created by Sweven on 2019/4/2.
 * Email:sweventears@Foxmail.com
 */
public class PreferenceUtil {

    private Activity activity;
    private SharedPreferences preferences;

    public PreferenceUtil(Activity activity) {
        this.activity = activity;
        preferences = activity.getSharedPreferences(App.SHARED_PREFERENCE, Context.MODE_PRIVATE);
    }

    /**
     * @param key
     * @return default null
     */
    public String getString(String key) {
        return preferences.getString(key, null);
    }

    /**
     * @param key
     * @return default 0
     */
    public int getInt(String key) {
        return preferences.getInt(key, 0);
    }

    /**
     * @param key
     * @return default 0
     */
    public float getFloat(String key) {
        return preferences.getFloat(key, 0);
    }

    /**
     * @param key
     * @return default false
     */
    public boolean getBoolean(String key) {
        return preferences.getBoolean(key, false);
    }

    /**
     * @param key
     * @return default 0
     */
    public long getLong(String key) {
        return preferences.getLong(key, 0);
    }

    /**
     * @param key
     * @return default null
     */
    public Set<String> getStringSet(String key) {
        return preferences.getStringSet(key, null);
    }

    public String getString(String key, String defValue) {
        return preferences.getString(key, defValue);
    }

    public int getInt(String key, int defValue) {
        return preferences.getInt(key, defValue);
    }

    public float getFloat(String key, float defValue) {
        return preferences.getFloat(key, defValue);
    }

    public boolean getBoolean(String key, boolean defValue) {
        return preferences.getBoolean(key, defValue);
    }

    public long getLong(String key, long defValue) {
        return preferences.getLong(key, defValue);
    }

    public Set<String> getStringSet(String key, Set<String> defValue) {
        return preferences.getStringSet(key, defValue);
    }

    public Map<String, ?> getAll() {
        return preferences.getAll();
    }
}
