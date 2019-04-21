package com.team.hellochat.interf;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

/**
 * Created by Sweven on 2019/4/21.
 * Email:sweventears@Foxmail.com
 */
public class PhoneNumberTextWatcher implements TextWatcher {

    private EditText phone;
    private int lastContentLength = 0;
    private boolean isDelete = false;

    public PhoneNumberTextWatcher(EditText phone) {
        this.phone = phone;
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        StringBuffer buffer = new StringBuffer(s);
        //是否为输入状态
        isDelete = s.length() <= lastContentLength;

        //输入是第4，第9位，这时需要插入空格
        if (!isDelete && (s.length() == 4 || s.length() == 9)) {
            if (s.length() == 4) {
                buffer.insert(3, " ");
            } else {
                buffer.insert(8, " ");
            }
            setContent(buffer);
        }

        //删除的位置到4，9时，剔除空格
        if (isDelete && (s.length() == 4 || s.length() == 9)) {
            buffer.deleteCharAt(buffer.length() - 1);
            setContent(buffer);
        }

        lastContentLength = buffer.length();
    }

    /**
     * 添加或删除空格EditText的设置
     *
     * @param buffer s
     */
    private void setContent(StringBuffer buffer) {
        phone.setText(buffer.toString());
        //移动光标到最后面
        phone.setSelection(buffer.length());
    }
}
