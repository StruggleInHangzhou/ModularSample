package com.gavel.main.ui.activity.home;

import com.gavel.main.base.mvp.ActivityPresenter;

import javax.inject.Inject;

/**
 * @author jiahua
 * @Description: $description
 * @date 2017/08/19
 */
public class HomePresenter extends ActivityPresenter<HomeContract.IView, HomeMoudle> implements HomeContract.IPresenter
{
    @Inject
    public HomePresenter()
    {
    }
}
