package com.gavel.auth.ui.activity.welcome;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import com.alibaba.android.arouter.launcher.ARouter;
import com.gavel.auth.R;
import com.gavel.auth.base.BaseActivity;
import com.gavel.auth.databinding.AuthActivityWelComeBinding;
import com.gavel.core.other.Config;
import com.gavel.core.other.PathConfig;
import com.jakewharton.rxbinding2.view.RxView;

import java.util.concurrent.TimeUnit;

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
        RxView.clicks(mBinding.tvTestEmpty)
                .compose(bindToLifecycle())
                .throttleFirst(Config.TIME_THROTTLE, TimeUnit.MILLISECONDS)
                .subscribe(aVoid -> mPresenter.requsetEmpty(), onError);

        RxView.clicks(mBinding.tvTestEmptyList)
                .compose(bindToLifecycle())
                .throttleFirst(Config.TIME_THROTTLE, TimeUnit.MILLISECONDS)
                .subscribe(aVoid -> mPresenter.requsetEmptyList(), onError);

        RxView.clicks(mBinding.tvTestObject)
                .compose(bindToLifecycle())
                .throttleFirst(Config.TIME_THROTTLE, TimeUnit.MILLISECONDS)
                .subscribe(aVoid -> mPresenter.requsetObject(), onError);

        RxView.clicks(mBinding.tvTestObjectNull)
                .compose(bindToLifecycle())
                .throttleFirst(Config.TIME_THROTTLE, TimeUnit.MILLISECONDS)
                .subscribe(aVoid -> mPresenter.requsetObjectNull(), onError);

        RxView.clicks(mBinding.tvTestList)
                .compose(bindToLifecycle())
                .throttleFirst(Config.TIME_THROTTLE, TimeUnit.MILLISECONDS)
                .subscribe(aVoid -> mPresenter.requsetList(), onError);

        RxView.clicks(mBinding.tvTestNullList)
                .compose(bindToLifecycle())
                .throttleFirst(Config.TIME_THROTTLE, TimeUnit.MILLISECONDS)
                .subscribe(aVoid -> mPresenter.requsetListNull(), onError);

        RxView.clicks(mBinding.tvActionMain)
                .compose(bindToLifecycle())
                .throttleFirst(Config.TIME_THROTTLE, TimeUnit.MILLISECONDS)
                .subscribe(aVoid -> action2main(), onError);

    }

    private void action2main()
    {
        ARouter.getInstance().build(PathConfig.MAIN_HOME)
                .navigation();
    }
}
