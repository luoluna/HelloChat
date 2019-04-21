package com.team.hellochat.utils;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.Html;
import android.widget.TextView;

/**
 * Created by Sweven on 2019/4/1.
 * Email:sweventears@Foxmail.com
 */
public class ChatMessageUtil {

    private static String TAG() {
        return ChatMessageUtil.class.getSimpleName();
    }

    public static void addImageForTextView(Context context, TextView view, String html) {
        view.setText(Html.fromHtml(html, new Html.ImageGetter() {
            @Override
            public Drawable getDrawable(String source) {
                new LogUtil(TAG()).a("项目图片测试_source:" + source);
                int id = Integer.parseInt(source);
                Drawable drawable = context.getResources().getDrawable(id, null);
                drawable.setBounds(0, 0, drawable.getIntrinsicWidth(),
                        drawable.getIntrinsicHeight());
                return drawable;
            }
        }, null));
    }

    public String removeTag(String information) {
        int first = information.indexOf("[text]") + 6;
        int end = information.indexOf("[/text]");

        return information.substring(first, end);
    }

    public static void main(String[] arg) {
        ChatMessageUtil util = new ChatMessageUtil();
        String text = "[text]hello[/text]";

        System.out.println(util.removeTag(text));
    }
}
