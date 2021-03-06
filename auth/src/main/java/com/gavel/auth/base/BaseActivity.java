package com.gavel.auth.base;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;

import com.alibaba.android.arouter.launcher.ARouter;
import com.blankj.utilcode.util.ToastUtils;
import com.gavel.auth.R;
import com.gavel.auth.base.mvp.ActivityPresenter;
import com.gavel.auth.base.mvp.ActivityView;
import com.orhanobut.logger.Logger;


/**
 * Created by jhhuang on 2017/2/22.
 * QQ:781913268
 * Description：BaseActivity
 */
public abstract class BaseActivity<T extends ActivityPresenter> extends MvpActivity<T> implements ActivityView
{
    private FrameLayout rootView;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        ARouter.getInstance().inject(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.auth_activity_base);
        fbi();
        rootView.addView(loadContentView(), 0);
        initViews(savedInstanceState);
        initData();
        initEvents();
    }

    private void fbi()
    {
        rootView = (FrameLayout) findViewById(R.id.root);
    }

    @Override
    protected void onNewIntent(Intent intent)
    {
        super.onNewIntent(intent);
        setIntent(intent);
    }

    @Override
    public void setContentView(int layoutResID)
    {
        super.setContentView(layoutResID);
        this.setStatusBar();
    }

    @Override
    protected void onDestroy()
    {
        super.onDestroy();
    }

    /**
     * 加载视图 setContentView()
     */
    protected abstract View loadContentView();

    /**
     * initialize the view in the layout
     *
     * @param savedInstanceState 状态信息
     */
    protected void initViews(Bundle savedInstanceState)
    {
        Logger.i(getClass().getName() + ":initViews");
    }

    /**
     * initialize the Activity data
     */
    protected void initData()
    {
        Logger.i(getClass().getName() + ":initData");
    }

    /**
     * initialize event
     */
    protected void initEvents()
    {
//        Logger.i(getClass().getName() + ":initEvents");
    }

    protected void setStatusBar()
    {
        if (!ifFullScreen())
        {
//            BarUtils.setStatusBarColor(this, Color.parseColor("#FF4800"));
        }
    }

    /**
     * 判断当前activity是否为全屏
     *
     * @return true？全屏：不是全屏
     */
    protected boolean ifFullScreen()
    {
        WindowManager.LayoutParams attrs = getWindow().getAttributes();
        return ((attrs.flags & WindowManager.LayoutParams.FLAG_FULLSCREEN) == WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    /**
     * @param to 窗口透明度 1f表示正常。0表示全黑
     */
    public void backgroundAlpha(float to)
    {
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.alpha = to;
        getWindow().setAttributes(lp);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
    }

    /**
     * 更新颜色
     *
     * @param swipe 布局
     */
    public void setSwipeColor(SwipeRefreshLayout swipe)
    {
        swipe.setColorSchemeResources(android.R.color.holo_red_light, android.R.color.holo_blue_light, android.R.color.holo_green_light, android.R.color.holo_orange_light);
    }


    @Override
    public void showLoading()
    {
    }

    @Override
    public void showLoading(String msg)
    {
    }

    @Override
    public void hideLoading()
    {
    }

    @Override
    public void toast(String msg)
    {
        ToastUtils.showShort(msg);
    }

    @Override
    public void toast(int resId)
    {
        ToastUtils.showShort(resId);
    }

}