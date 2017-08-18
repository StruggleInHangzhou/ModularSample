package com.gavel.main.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.blankj.utilcode.util.ToastUtils;
import com.gavel.main.base.mvp.FragmentPresenter;
import com.gavel.main.base.mvp.FragmentView;


/**
 * Created by jhhuang on 2017/2/22.
 * QQ:781913268
 * Description：BaseFragment
 */
public abstract class BaseFragment<T extends FragmentPresenter> extends MvpFragment<T> implements FragmentView
{
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        parseDataFormBundle(getArguments());
        return loadContentView(inflater, container, savedInstanceState);
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState)
    {
        super.onLazyInitView(savedInstanceState);
        initViews(savedInstanceState);
        initData();
        initEvent();
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onResume()
    {
        super.onResume();
    }

    @Override
    public void hideLoading()
    {
        try
        {
            ((BaseActivity) mContext).hideLoading();
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void showLoading()
    {
        try
        {
            ((BaseActivity) mContext).showLoading();
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void showLoading(String msg)
    {
        try
        {
            ((BaseActivity) mContext).showLoading(msg);
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void backgroundAlpha(float to)
    {
        try
        {
            ((BaseActivity) mContext).backgroundAlpha(to);
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void toast(String msg)
    {
        ToastUtils.showShortSafe(msg);
    }

    @Override
    public void toast(int resId)
    {
        ToastUtils.showShortSafe(resId);
    }

    /**
     * 解析数据
     *
     * @param bundle 创建对象时传进来的数据
     */
    protected void parseDataFormBundle(Bundle bundle)
    {
    }

    /**
     * 加载视图
     */
    protected abstract View loadContentView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState);

    /**
     * initialize the view in the layout
     *
     * @param savedInstanceState
     */
    protected void initViews(Bundle savedInstanceState)
    {

    }

    /**
     * initialize the Activity data
     */
    protected void initData()
    {

    }

    /**
     * initialize event
     */
    protected void initEvent()
    {

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
}