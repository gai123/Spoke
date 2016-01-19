package com.gai.spoke.utils;

import android.util.Log;

/**
 * Created by gai on 15/12/30.
 */
public class SLog {
    public static boolean DEBUG = true;


    public static void i(String tag, String msg) {
        if (DEBUG) {
            Log.i(tag, msg);
        }
    }


    public static void e(String tag, String msg) {
        if (DEBUG) {
            Log.e(tag, msg);
        }

    }

    public static void d(String tag, String msg) {
        if (DEBUG) {
            Log.d(tag, msg);
        }
    }
}
