package com.gai.spoke.presenter.imple;

import android.app.Activity;
import android.content.Context;
import android.view.animation.Animation;

import com.gai.spoke.bis.imple.SplashModel;
import com.gai.spoke.bis.interf.ISplashModel;
import com.gai.spoke.presenter.base.BasePresenter;
import com.gai.spoke.presenter.interf.ISplashPresenter;
import com.gai.spoke.view.interf.ISplashView;

public class SplashPresenter extends BasePresenter<ISplashView> implements ISplashPresenter {

    ISplashModel  iSplashModel;
    public SplashPresenter() {

        iSplashModel = new SplashModel();
    }

    @Override
    public void initialized() {
        final ISplashView splashView=getView();
        splashView.intializeViewsData(iSplashModel.getVersionName((Context) getView()),
                iSplashModel.getCopyright((Context) getView()),
                iSplashModel.getBackgroundImageResID());

        Animation animation = iSplashModel.getBackgroundImageAnimation((Context)getView());
//        animation.setDuration(2000);
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                splashView.navigateToHomePage();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        splashView.animateBackgroundImage(animation);
    }
}
