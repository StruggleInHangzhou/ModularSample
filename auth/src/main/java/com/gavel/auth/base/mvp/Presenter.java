package com.gavel.auth.base.mvp;

/**
 * Created by jhhuang on 2016/12/13.
 * QQ:781913268
 * Description：Presenter
 */
public interface Presenter<V extends MvpView>
{
    /**
     * 关联
     *
     * @param mvpView 视图层代表
     */
    void attachView(V mvpView);

    /**
     * 解除关联
     */
    void detachView();

}
