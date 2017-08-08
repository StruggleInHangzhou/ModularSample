package com.gavel.auth.ui.activity.welcome;

import com.gavel.auth.base.mvp.ActivityPresenter;

import javax.inject.Inject;

/**
 * @author jiahua
 * @Description: $description
 * @date 2017/08/08
 */
public class WelComePresenter extends ActivityPresenter<WelComeContract.IView, WelComeMoudle> implements WelComeContract.IPresenter
{
    @Inject
    public WelComePresenter()
    {
    }
}
