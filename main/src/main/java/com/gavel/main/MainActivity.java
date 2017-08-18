package com.gavel.main;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.gavel.core.other.PathConfig;
import com.gavel.main.base.BaseActivity;
import com.gavel.main.databinding.MainActivityMainBinding;
import com.gavel.main.ui.activity.main.MainContract;
import com.gavel.main.ui.activity.main.MainPresenter;

/**
 * @author jiahua
 * @Description: $description
 * @date 2017/08/18
 */
@Route(path = PathConfig.MAIN_MAIN)
public class MainActivity extends BaseActivity<MainPresenter> implements MainContract.IView
{
    private MainActivityMainBinding mBinding;

    @Override
    protected void initInject()
    {
        getActivityComponent().inject(this);
    }

    @Override
    protected View loadContentView()
    {
        mBinding = DataBindingUtil.inflate(LayoutInflater.from(mContext), R.layout.main_activity_main, null, false);
        return mBinding.getRoot();
    }

    @Override
    protected void initViews(Bundle savedInstanceState)
    {
        super.initViews(savedInstanceState);
    }

    @Override
    protected void initData()
    {
        super.initData();
    }

    @Override
    protected void initEvents()
    {
        super.initEvents();
    }
}
