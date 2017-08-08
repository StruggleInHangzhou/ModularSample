package com.gavel.auth.base.mvp;


import com.trello.rxlifecycle2.LifecycleProvider;
import com.trello.rxlifecycle2.android.FragmentEvent;

/**
 * Created by jhhuang on 2016/12/13.
 * QQ:781913268
 * Description：FragmentView
 */
public interface FragmentView extends MvpView,LifecycleProvider<FragmentEvent>
{

}
