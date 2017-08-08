package com.gavel.auth.base.mvp;


import com.trello.rxlifecycle2.LifecycleProvider;
import com.trello.rxlifecycle2.android.ActivityEvent;

/**
 * Created by jhhuang on 2016/12/13.
 * QQ:781913268
 * Description：ActivityView
 */
public interface ActivityView extends MvpView,LifecycleProvider<ActivityEvent>
{

}
