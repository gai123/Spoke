package com.gai.spoke.ui.activity;

import android.content.Intent;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.TextView;

import com.gai.spoke.MainActivity;
import com.gai.spoke.R;
import com.gai.spoke.presenter.imple.SplashPresenter;
import com.gai.spoke.presenter.interf.ISplashPresenter;
import com.gai.spoke.view.interf.ISplashView;

/**
 * Created by gai on 15/12/31.
 */
public class SplashActivity extends BaseActivity<ISplashView, SplashPresenter> implements ISplashView {


    ImageView mSplashImage;
    TextView mCopyright;
    TextView mVersionName;

    @Override
    protected void initView() {
        mSplashImage = (ImageView) findViewById(R.id.splash_image);
        mCopyright = (TextView) findViewById(R.id.splash_copyright);
        mVersionName = (TextView) findViewById(R.id.splash_version_name);
    }

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_splash;
    }

    @Override
    protected SplashPresenter createPresenter() {
        return new SplashPresenter();
    }

    @Override
    public void intializeViewsData(String versionName, String copyright, int backgroundImageResID) {
        mCopyright.setText(copyright);
        mVersionName.setText(versionName);
        mSplashImage.setImageResource(backgroundImageResID);
    }


    @Override
    public void animateBackgroundImage(Animation animation) {
        mSplashImage.setAnimation(animation);
    }



    boolean isLogin = false;

    @Override
    public void navigateToHomePage() {

        if (isLogin) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
        } else {
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
            finish();
        }
    }
}
