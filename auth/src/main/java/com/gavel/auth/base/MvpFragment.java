package com.gavel.auth.base;


import android.content.Context;
import android.os.Bundle;

import com.gavel.auth.base.di.AuthFragmentComponent;
import com.gavel.auth.base.di.DaggerAuthFragmentComponent;
import com.gavel.auth.base.mvp.MvpView;
import com.gavel.auth.base.mvp.Presenter;
import com.gavel.core.CoreApplication;
import com.gavel.core.CoreFragment;
import com.gavel.core.di.moudle.FragmentModule;

import javax.inject.Inject;

/**
 * Created by jhhuang on 2016/12/13.
 * QQ:781913268
 * Description：Mvp+dragger2
 */
public abstract class MvpFragment<T extends Presenter> extends CoreFragment implements MvpView
{
    protected Context mContext;
    @Inject
    protected T mPresenter;

    @Override
    public void onAttach(Context context)
    {
        mContext = context;
        super.onAttach(context);
    }

    @SuppressWarnings("unchecked")
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        initInject();
        mPresenter.attachView(this);
    }

    protected AuthFragmentComponent getFragmentComponent()
    {
        return DaggerAuthFragmentComponent.builder()
                .appComponent(CoreApplication.getAppComponent())
                .fragmentModule(getFragmentModule())
                .build();
    }

    protected FragmentModule getFragmentModule()
    {
        return new FragmentModule(this);
    }

    @Override
    public void onDestroy()
    {
        super.onDestroy();
        mPresenter.detachView();
    }

    protected abstract void initInject();
}