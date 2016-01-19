package com.gai.spoke.view.interf;

import android.view.animation.Animation;

/**
 * Created by gai on 15/12/31.
 */
public interface ISplashView {


    void intializeViewsData(String versionName, String copyright, int backgroundImageResID);



    void animateBackgroundImage(Animation animation);



    void navigateToHomePage();
}
