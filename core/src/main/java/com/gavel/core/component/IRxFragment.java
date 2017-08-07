package com.gavel.core.component;

import android.os.Bundle;
import android.support.annotation.CheckResult;
import android.support.annotation.NonNull;
import android.view.View;

import com.orhanobut.logger.Logger;
import com.tbruyelle.rxpermissions2.RxPermissions;
import com.trello.rxlifecycle2.LifecycleProvider;
import com.trello.rxlifecycle2.LifecycleTransformer;
import com.trello.rxlifecycle2.RxLifecycle;
import com.trello.rxlifecycle2.android.FragmentEvent;
import com.trello.rxlifecycle2.android.RxLifecycleAndroid;

import io.reactivex.Observable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.subjects.BehaviorSubject;
import me.yokeyword.fragmentation.SupportFragment;

/**
 * Created by jhhuang on 2017/2/22.
 * QQ:781913268
 * Description：IRxFragment
 */
public class IRxFragment extends SupportFragment implements LifecycleProvider<FragmentEvent>
{
    private final BehaviorSubject<FragmentEvent> lifecycleSubject = BehaviorSubject.create();

    protected Consumer<Throwable> onError;
    protected Action onCompleted;
    protected RxPermissions mRxPermissions;

    @NonNull
    @CheckResult
    public final Observable<FragmentEvent> lifecycle() {
        return this.lifecycleSubject.hide();
    }

    @NonNull
    @CheckResult
    public final <T> LifecycleTransformer<T> bindUntilEvent(@NonNull FragmentEvent event) {
        return RxLifecycle.bindUntilEvent(this.lifecycleSubject, event);
    }

    @NonNull
    @CheckResult
    public final <T> LifecycleTransformer<T> bindToLifecycle() {
        return RxLifecycleAndroid.bindFragment(this.lifecycleSubject);
    }

    @Override
    public void onAttach(android.app.Activity activity) {
        super.onAttach(activity);
        mRxPermissions = new RxPermissions(activity);
        mRxPermissions.setLogging(true);
        Logger.i(getClass().getName()+":onAttach");
        lifecycleSubject.onNext(FragmentEvent.ATTACH);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        onError = Throwable::printStackTrace;
        onCompleted = () -> Logger.i(this.getClass().getName()+"-onCompleted");
        Logger.i(getClass().getName()+":onCreate");
        lifecycleSubject.onNext(FragmentEvent.CREATE);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        lifecycleSubject.onNext(FragmentEvent.CREATE_VIEW);
        Logger.i(getClass().getName()+":onViewCreated");
    }

    @Override
    public void onStart() {
        super.onStart();
        lifecycleSubject.onNext(FragmentEvent.START);
        Logger.i(getClass().getName()+":onStart");
    }

    @Override
    public void onResume() {
        super.onResume();
        lifecycleSubject.onNext(FragmentEvent.RESUME);
        Logger.i(getClass().getName()+":onResume");
    }

    @Override
    public void onPause() {
        lifecycleSubject.onNext(FragmentEvent.PAUSE);
        super.onPause();
        Logger.i(getClass().getName()+":onPause");
    }

    @Override
    public void onStop() {
        Logger.i(getClass().getName()+":onStop");
        lifecycleSubject.onNext(FragmentEvent.STOP);
        super.onStop();
    }

    @Override
    public void onDestroyView() {
        Logger.i(getClass().getName()+":onDestroyView");
        lifecycleSubject.onNext(FragmentEvent.DESTROY_VIEW);
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        Logger.i(getClass().getName()+":onDestroy");
        lifecycleSubject.onNext(FragmentEvent.DESTROY);
        super.onDestroy();
    }

    @Override
    public void onDetach() {
        Logger.i(getClass().getName()+":onDetach");
        lifecycleSubject.onNext(FragmentEvent.DETACH);
        super.onDetach();
    }
}