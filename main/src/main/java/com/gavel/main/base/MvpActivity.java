package com.gavel.main.base;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.gavel.core.CoreActivity;
import com.gavel.core.CoreApplication;
import com.gavel.core.di.moudle.ActivityModule;
import com.gavel.main.base.di.AuthActivityComponent;
import com.gavel.main.base.di.DaggerAuthActivityComponent;
import com.gavel.main.base.mvp.MvpView;
import com.gavel.main.base.mvp.Presenter;

import javax.inject.Inject;

/**
 * Created by jhhuang on 2016/12/13.
 * QQ:781913268
 * Description：Mvp + dragger2
 */
public abstract class MvpActivity<T extends Presenter> extends CoreActivity implements MvpView
{
    @Inject
    protected T mPresenter;
    protected AppCompatActivity mContext;

    @SuppressWarnings("unchecked")
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        initInject();
        mContext = this;
        if (mPresenter != null)
        {
            mPresenter.attachView(this);
        }
    }

    @Override
    protected void onDestroy()
    {
        super.onDestroy();
        mPresenter.detachView();
    }

    /**
     * dragger2注册
     */
    protected abstract void initInject();

    protected AuthActivityComponent getActivityComponent()
    {
        return DaggerAuthActivityComponent.builder()
                .appComponent(CoreApplication.getAppComponent())
                .activityModule(getActivityModule())
                .build();
    }

    protected ActivityModule getActivityModule()
    {
        return new ActivityModule(this);
    }
}