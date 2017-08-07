package com.gavel.core.utils;

import io.reactivex.ObservableTransformer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by jhhuang on 2016/10/14.
 * QQ:781913268
 * Description：封装一些rx的方法
 */
public class RxUtils
{
    private static final String TAG = "RxUtils";

    /**
     *
     * @return 触发线程在子线程
     */
    public static <T> ObservableTransformer<T, T> rxSchedulerHelper()
    {
        return tObservable -> tObservable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    /**
     * 可自定义触发线程
     */
    public static <T> ObservableTransformer<T, T> rxSchedulerHelper(Scheduler scheduler)
    {
        return tObservable -> tObservable.subscribeOn(scheduler)
                .observeOn(AndroidSchedulers.mainThread());
    }

}
