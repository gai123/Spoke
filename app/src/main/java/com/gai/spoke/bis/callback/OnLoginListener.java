package com.gai.spoke.bis.callback;

import com.gai.spoke.bean.User;

/**
 * Created by gai on 16/1/2.
 */
public interface OnLoginListener {


    void loginSuccess(User user);

    void loginFailed();
}
