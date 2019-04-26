package com.team.hellochat.interf;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import com.team.hellochat.bean.MessageType;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Sweven on 2019/4/25.
 * Email:sweventears@Foxmail.com
 */
public class SendMessageTextWatch implements TextWatcher {

    private EditText editText;
    private SendMessageCallBack sendMessageCallBack;

    public SendMessageTextWatch() {
    }

    public SendMessageTextWatch(EditText editText) {
        this.editText = editText;
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        String text = s.toString();
        if (isSogouInputImage(text)) {
            editText.setText("");
            if (sendMessageCallBack != null) {
                sendMessageCallBack.onResult(getImageUri(text), MessageType.IMAGE.getName());
            }
        }
    }

    private boolean isSogouInputImage(String imageString) {
        String pattern = "\\[.{1,}],点击\\[ http://pinyin.cn/[A-Za-z0-9]{1,} ]查看表情";

        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(imageString);
        return m.matches();
    }

    private String getImageUri(String match) {
        String after = "";
        if (isSogouInputImage(match)) {
            String pattern1 = "\\[.{1,}],点击\\[ ";
            String pattern2 = " ]查看表情";
            after = match.replaceAll(pattern1, "");
            after = after.replaceAll(pattern2, "");
        }
        return after;
    }

    public SendMessageTextWatch setSendMessageCallBack(SendMessageCallBack sendMessageCallBack) {
        this.sendMessageCallBack = sendMessageCallBack;
        return this;
    }
}
