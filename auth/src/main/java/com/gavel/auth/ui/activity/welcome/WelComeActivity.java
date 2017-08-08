package com.gavel.auth.ui.activity.welcome;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import com.gavel.auth.R;
import com.gavel.auth.base.BaseActivity;
import com.gavel.auth.databinding.AuthActivityWelComeBinding;
/**
 * @author jiahua
 * @Description: $description
 * @date 2017/08/08
 */
public class WelComeActivity extends BaseActivity<WelComePresenter> implements WelComeContract.IView
{
    private AuthActivityWelComeBinding mBinding;

    @Override
    protected void initInject()
    {
        getActivityComponent().inject(this);
    }

    @Override
    protected View loadContentView()
    {
        mBinding = DataBindingUtil.inflate(LayoutInflater.from(mContext), R.layout.auth_activity_wel_come, null, false);
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
