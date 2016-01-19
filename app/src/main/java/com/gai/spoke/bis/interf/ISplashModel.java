package com.gai.spoke.bis.interf;

import android.content.Context;
import android.view.animation.Animation;

/**
 * Created by gai on 15/12/31.
 */
public interface ISplashModel {
    Animation getBackgroundImageAnimation(Context context);

    int getBackgroundImageResID();

    String getVersionName(Context context);

    String getCopyright(Context context);
}
