package com.gai.spoke.bis.task;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.gai.spoke.bean.LoginConfig;
import com.gai.spoke.bis.callback.OnLoginListener;
import com.gai.spoke.config.Constant;
import com.gai.spoke.presenter.imple.LoginPresenter;
import com.gai.spoke.utils.SLog;
import com.gai.spoke.utils.XmppConnectionManager;

import org.jivesoftware.smack.SmackException;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smack.packet.XMPPError;
import org.jivesoftware.smack.tcp.XMPPTCPConnection;

import java.io.IOException;

/**
 * Created by gai on 16/1/1.
 */
public class LoginTask extends AsyncTask<String, Integer, Integer> {

    private static final String TAG = "LoginTask";
    //    private Context context;
    private LoginConfig loginConfig;
    private LoginPresenter loginPresenter;
    private OnLoginListener loginListener;
    XmppConnectionManager xmppConnectionManager = null;
    XMPPTCPConnection xmpptcpConnection;

    @Override
    protected Integer doInBackground(String... params) {
        login(loginConfig);

        return 1;
    }


    public LoginTask(LoginPresenter loginPresenter, LoginConfig loginConfig, OnLoginListener loginListener) {
//        this.context = context;
        this.loginPresenter = loginPresenter;
        this.loginConfig = loginConfig;
        xmppConnectionManager = XmppConnectionManager.getInstance();

    }


    @Override
    protected void onPostExecute(Integer integer) {
        super.onPostExecute(integer);
        SLog.i(TAG, integer + "");


        switch (integer) {
            case Constant.LOGIN_SUCCESS:
                loginListener.loginSuccess(null);
                break;
            case Constant.LOGIN_ERROR:
                loginListener.loginFailed();
//                loginPresenter.
                break;
        }
    }

    public Integer login(LoginConfig loginConfig) {
        loginConfig.setXmppHost("192.168.31.179");
        loginConfig.setXmppPort(5222);
        loginConfig.setXmppServiceName("zpfdeMacBook-Pro.loca");
        xmpptcpConnection = xmppConnectionManager.init(loginConfig);

        try {
            xmpptcpConnection.connect();
            xmpptcpConnection.login();
            return Constant.LOGIN_SUCCESS;
        } catch (XMPPException e) {
            SLog.e(TAG, Log.getStackTraceString(e));

            return Constant.LOGIN_ERROR;
        } catch (SmackException e) {
            SLog.e(TAG, Log.getStackTraceString(e));
            return Constant.LOGIN_ERROR;
        } catch (IOException e) {
            SLog.e(TAG, Log.getStackTraceString(e));
            return Constant.LOGIN_ERROR;
        }

    }
}
