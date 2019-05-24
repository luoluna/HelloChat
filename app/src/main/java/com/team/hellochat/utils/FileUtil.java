package com.team.hellochat.utils;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileUtil {

    private static LogUtil log=new LogUtil("FileUtil");

    public static String headPhotoPath(Context context) {
        return getExternalFilesDir(context).getPath() + "head_photo.png";
    }

    public static String saveHeadPhoto(Context context, int resId) {
        File file = new File(headPhotoPath(context));
        //使用BitmapFactory把res下的图片转换成Bitmap对象
        Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), resId);
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(file);
            //使用图片压缩对图片进行处理  压缩的格式  可以是JPEG、PNG、WEBP
            //第二个参数是图片的压缩比例，第三个参数是写入流
            boolean success = bitmap.compress(Bitmap.CompressFormat.PNG, 100, fos);
            return success ? file.getPath() : null;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        } finally {
            if (fos != null) {
                try {
                    fos.flush();
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static File getExternalFilesDir(Context context) {
        File dataDir = new File(new File(getSDCardPath(), "Android"), "data");
        File appFilesDir = new File(new File(dataDir, context.getPackageName()), "files");
        if (!appFilesDir.exists()) {
            if (!appFilesDir.mkdirs()) {
                log.w("Unable to create external files directory");
                return null;
            }
        }
        return appFilesDir;
    }

    private static File getExternalCacheDir(Context context) {
        File dataDir = new File(new File(getSDCardPath(), "Android"), "data");
        File appCacheDir = new File(new File(dataDir, context.getPackageName()), "cache");
        if (!appCacheDir.exists()) {
            if (!appCacheDir.mkdirs()) {
                log.w("Unable to create external cache directory");
                return null;
            }
        }
        return appCacheDir;
    }

    public static String getSDCardPath() {
        return Environment.getExternalStorageDirectory() + "/";
    }

    public static void  installApk(Context context, String apkPath){
        File apkFile = new File(apkPath);
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK); //如果没有添加这条，安装完毕后不会有打开选项，而是直接退出。
        intent.setDataAndType(Uri.fromFile(apkFile), "application/vnd.android.package-archive");
        context.startActivity(intent);
    }

    /**
     * 创建文件或文件夹
     *
     * @param fileName 文件名或问文件夹名
     */
    public static void createFile(String fileName) {
        File file = new File(getSDCardPath() + fileName);
        if (fileName.indexOf(".") != -1) {
            // 说明包含，即使创建文件, 返回值为-1就说明不包含.,即使文件
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("创建了文件");
        } else {
            // 创建文件夹
            file.mkdir();
            System.out.println("创建了文件夹");
        }

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
