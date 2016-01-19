package com.gai.spoke.presenter.imple;

import android.widget.Toast;

import com.gai.spoke.bean.User;
import com.gai.spoke.bis.callback.OnLoginListener;
import com.gai.spoke.bis.imple.UserModel;
import com.gai.spoke.bis.interf.IUserModel;

import com.gai.spoke.presenter.base.BasePresenter;
import com.gai.spoke.presenter.interf.ILoginPresenter;
import com.gai.spoke.view.interf.ILoginView;

/**
 * Created by gai on 15/12/25.
 */
public class LoginPresenter extends BasePresenter<ILoginView> implements ILoginPresenter {
    IUserModel userModel;


    public LoginPresenter() {
        this.userModel = new UserModel(this);
    }

    @Override
    public void initialized() {

    }

    public void login(String userName, String password) {
        User user = new User();
        user.setPassword(password);
        user.setUserName(userName);
        userModel.login(user, new OnLoginListener() {
            @Override
            public void loginSuccess(User user) {
                getView().showError("");

            }

            @Override
            public void loginFailed() {

            }
        });
    }
}
