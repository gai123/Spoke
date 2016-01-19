package com.gai.spoke.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.gai.spoke.presenter.base.BasePresenter;

/**
 * Created by gai on 15/12/30.
 */
public abstract class BaseActivity<V, T extends BasePresenter<V>> extends AppCompatActivity {
    protected T mPresenter;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getContentViewLayoutID());
        initView();
        mPresenter = createPresenter();
        mPresenter.attachView((V) this);
        mPresenter.initialized();
    }

    /**
     * 初始化view
     */
    protected abstract void initView();


    /**
     * 获取布局id
     * @return
     */
    protected abstract int getContentViewLayoutID();


    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.detachView();
    }

    /**
     * 创建presenter
     * @return
     */
    protected abstract T createPresenter();
}
