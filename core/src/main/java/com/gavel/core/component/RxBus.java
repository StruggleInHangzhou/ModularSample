package com.gavel.core.component;

import com.jakewharton.rxrelay2.PublishRelay;
import com.jakewharton.rxrelay2.Relay;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;


/**
 * Created by jhhuang on 2016/10/14.
 * QQ:781913268
 * Description：事件处理
 */
public class RxBus
{
    private final Relay<Object> bus;


    // PublishSubject只会把在订阅发生的时间点之后来自原始Observable的数据发射给观察者
    private RxBus()
    {
        bus = PublishRelay.create().toSerialized();
    }

    public static RxBus getDefault()
    {
        return RxBusHolder.sInstance;
    }

    private static class RxBusHolder
    {
        private static final RxBus sInstance = new RxBus();
    }

    private boolean hasObserverable()
    {
        return bus.hasObservers();
    }

    // 提供了一个新的事件
    public void post(Object obj)
    {
        if (hasObserverable())
        {
            bus.accept(obj);
        }
    }

    // 根据传递的 eventType 类型返回特定类型(eventType)的 被观察者
    public <T> Observable<T> toObserverable(Class<T> eventType)
    {
        return bus.ofType(eventType).observeOn(AndroidSchedulers.mainThread());
    }

}
