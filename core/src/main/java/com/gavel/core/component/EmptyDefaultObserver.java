package com.gavel.core.component;

import io.reactivex.observers.DefaultObserver;

/**
 * Created by jhhuang on 2017/4/11
 * QQ:781913268
 * Description：xxx
 */
public abstract class EmptyDefaultObserver<T> extends DefaultObserver<T>
{
    @Override
    public void onError(Throwable e)
    {
        e.printStackTrace();
    }

    @Override
    public void onComplete()
    {

    }
}
