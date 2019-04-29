package com.team.hellochat.utils;

import android.content.Context;
import android.os.Environment;

import java.io.File;
import java.io.IOException;

public class FileUtil {

    private static final String TAG = FileUtil.class.getName();

    public static File directory(String path) {
        File file = new File(path);
        if (!file.exists()) {
            if (!file.mkdirs()) {
                new LogUtil(TAG).w("Unable to create external files directory");
                return null;
            }
        }
        return file;
    }

    public static File file(String path) {
        File file = new File(path);
        if (!file.exists()) {
            try {
                if (!file.createNewFile()) {
                    new LogUtil(TAG).w("Unable to create external files");
                    return null;
                }
            } catch (IOException e) {
                e.printStackTrace();
                new LogUtil(TAG).w("Unable to create external files");
                return null;
            }
        }
        return file;
    }

    public static File getExternalStorageDirectory() {
        return Environment.getExternalStorageDirectory();
    }

    public static String getExternalStoragePath() {
        return Environment.getExternalStorageDirectory().getPath();
    }

    private static File getExternalFilesDir(Context context) {
        File dataDir = new File(new File(Environment.getExternalStorageDirectory(), "Android"), "data");
        File appFilesDir = new File(new File(dataDir, context.getPackageName()), "files");
        if (!appFilesDir.exists()) {
            if (!appFilesDir.mkdirs()) {
                new LogUtil(TAG).w("Unable to create external files directory");
                return null;
            }
        }
        return appFilesDir;
    }

    private static File getExternalCacheDir(Context context) {
        File dataDir = new File(new File(Environment.getExternalStorageDirectory(), "Android"), "data");
        File appCacheDir = new File(new File(dataDir, context.getPackageName()), "cache");
        if (!appCacheDir.exists()) {
            if (!appCacheDir.mkdirs()) {
                new LogUtil(TAG).w("Unable to create external cache directory");
                return null;
            }
        }
        return appCacheDir;
    }

    /**
     * delete directory
     */
    public static boolean deleteFiles(File root) {
        File files[] = root.listFiles();
        if (files != null) {
            for (File f : files) {
                if (!f.isDirectory() && f.exists()) { // 判断是否存在
                    if (!f.delete()) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    /**
     * delete file
     */
    public static boolean deleteFile(String filePath) {
        File file = new File(filePath);
        if (file.exists()) {
            if (file.isFile()) {
                if (!file.delete()) {
                    return false;
                }
            } else {
                String[] filePaths = file.list();
                for (String path : filePaths) {
                    deleteFile(filePath + File.separator + path);
                }
                if (!file.delete()) {
                    return false;
                }
            }
        }
        return true;
    }
}
