package com.gai.spoke.presenter.base;

import java.lang.ref.Reference;
import java.lang.ref.WeakReference;

/**
 * Created by gai on 15/12/30.
 */
public abstract class BasePresenter<T> {
    /**
     * view 接口类型弱引用
     */
    protected Reference<T> mVIewRef;

    /**
     * 简历关联
     *
     * @param view
     */
    public void attachView(T view) {
        mVIewRef = new WeakReference<T>(view);
    }

    protected T getView() {
        return mVIewRef.get();
    }

    public boolean isViewAttached() {
        return mVIewRef != null && mVIewRef.get() != null;
    }

    public void detachView() {
        if (mVIewRef != null) {
            mVIewRef.clear();
            mVIewRef = null;
        }
    }


    public abstract void initialized();
}
