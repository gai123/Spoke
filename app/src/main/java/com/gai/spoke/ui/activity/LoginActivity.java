package com.gai.spoke.ui.activity;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.gai.spoke.R;
import com.gai.spoke.presenter.imple.LoginPresenter;
import com.gai.spoke.utils.SLog;
import com.gai.spoke.view.interf.ILoginView;

/**
 * Created by gai on 15/12/31.
 */
public class LoginActivity extends BaseActivity<ILoginView, LoginPresenter<ILoginView>> implements ILoginView, View.OnClickListener {


    private static final String TAG = "LoginActivity";
    Button mEmailSignInButton;
    EditText mPassword;
    EditText mEmail;

    @Override
    protected void initView() {
        mEmail = (EditText) findViewById(R.id.email);
        mPassword = (EditText) findViewById(R.id.password);
        mEmailSignInButton = (Button) findViewById(R.id.email_sign_in_button);
        mEmailSignInButton.setOnClickListener(this);
    }

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_login;
    }

    @Override
    protected LoginPresenter<ILoginView> createPresenter() {
        return new LoginPresenter();
    }

    @Override
    public void onClick(View v) {
        int ids = v.getId();
        switch (ids) {
            case R.id.email_sign_in_button:
//                Toast.makeText(this,"login",Toast.LENGTH_SHORT).show();
                SLog.i(TAG, mEmail.getText().toString() + "\t" + mPassword.getText().toString());
                mPresenter.login(mEmail.getText().toString(), mPassword.getText().toString());
                break;
        }
    }

    @Override
    public void showLoading(String msg) {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showError(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showException(String msg) {

    }

    @Override
    public void showNetError() {

    }
}
