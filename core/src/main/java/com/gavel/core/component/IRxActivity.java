package com.gavel.core.component;

import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.CheckResult;
import android.support.annotation.NonNull;

import com.orhanobut.logger.Logger;
import com.tbruyelle.rxpermissions2.RxPermissions;
import com.trello.rxlifecycle2.LifecycleProvider;
import com.trello.rxlifecycle2.LifecycleTransformer;
import com.trello.rxlifecycle2.RxLifecycle;
import com.trello.rxlifecycle2.android.ActivityEvent;
import com.trello.rxlifecycle2.android.RxLifecycleAndroid;

import io.reactivex.Observable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.subjects.BehaviorSubject;
import me.yokeyword.fragmentation.SupportActivity;

/**
 * Created by jhhuang on 2017/2/22.
 * QQ:781913268
 * Description：IRxActivity
 */
public class IRxActivity extends SupportActivity implements LifecycleProvider<ActivityEvent>
{
    private final BehaviorSubject<ActivityEvent> lifecycleSubject = BehaviorSubject.create();

    protected Consumer<Throwable> onError;
    protected Action onCompleted;
    protected RxPermissions mRxPermissions;

    @NonNull
    @CheckResult
    public final Observable<ActivityEvent> lifecycle()
    {
        return this.lifecycleSubject.hide();
    }

    @NonNull
    @CheckResult
    public final <T> LifecycleTransformer<T> bindUntilEvent(@NonNull ActivityEvent event)
    {
        return RxLifecycle.bindUntilEvent(this.lifecycleSubject, event);
    }

    @NonNull
    @CheckResult
    public final <T> LifecycleTransformer<T> bindToLifecycle()
    {
        return RxLifecycleAndroid.bindActivity(this.lifecycleSubject);
    }

    @Override
    @CallSuper
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        onError = Throwable::printStackTrace;
        onCompleted = () -> Logger.i(this.getClass().getName() + "onCompleted");
        mRxPermissions = new RxPermissions(this);
        mRxPermissions.setLogging(true);
        Logger.i(getClass().getName()+":onCreate");
        lifecycleSubject.onNext(ActivityEvent.CREATE);
    }

    @Override
    @CallSuper
    protected void onStart()
    {
        super.onStart();
        this.lifecycleSubject.onNext(ActivityEvent.START);
        Logger.i(getClass().getName()+":onStart");
    }

    @Override
    @CallSuper
    protected void onResume()
    {
        super.onResume();
        this.lifecycleSubject.onNext(ActivityEvent.RESUME);
        Logger.i(getClass().getName()+":onResume");
    }

    @Override
    @CallSuper
    protected void onPause()
    {
        this.lifecycleSubject.onNext(ActivityEvent.PAUSE);
        super.onPause();
        Logger.i(getClass().getName()+":onPause");
    }

    @Override
    @CallSuper
    protected void onStop()
    {
        this.lifecycleSubject.onNext(ActivityEvent.STOP);
        super.onStop();
        Logger.i(getClass().getName()+":onStop");
    }

    @Override
    @CallSuper
    protected void onDestroy()
    {
        this.lifecycleSubject.onNext(ActivityEvent.DESTROY);
        super.onDestroy();
        Logger.i(getClass().getName()+":onDestroy");
    }
}