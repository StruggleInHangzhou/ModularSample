package com.gavel.main.base.mvp;


import android.support.annotation.NonNull;

import com.gavel.core.component.RetryWithDelayFunc1;
import com.gavel.http.ex.ApiException;
import com.gavel.http.ex.ResultException;
import com.gavel.http.ex.ToastException;
import com.gavel.main.R;
import com.trello.rxlifecycle2.LifecycleProvider;
import com.trello.rxlifecycle2.LifecycleTransformer;
import com.trello.rxlifecycle2.android.ActivityEvent;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import io.reactivex.Observable;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DefaultObserver;
import io.reactivex.schedulers.Schedulers;
import retrofit2.HttpException;

/**
 * Created by jhhuang on 2016/12/13.
 * QQ:781913268
 * Description：ActivityPresenter
 */
public class ActivityPresenter<V extends ActivityView, M extends Moudle> extends BasePresenter<V, M> implements LifecycleProvider<ActivityEvent>
{

    @NonNull
    @Override
    public final Observable<ActivityEvent> lifecycle()
    {
        return getMvpView().lifecycle();
    }

    @NonNull
    @Override
    public final <T> LifecycleTransformer<T> bindUntilEvent(@NonNull ActivityEvent event)
    {
        return getMvpView().bindUntilEvent(event);
    }

    @NonNull
    @Override
    public final <T> LifecycleTransformer<T> bindToLifecycle()
    {
        return getMvpView().bindToLifecycle();
    }

    public <T> ObservableTransformer<T, T> callbackOnIOThread()
    {
        return tObservable -> tObservable.subscribeOn(Schedulers.io())
                .retryWhen(RetryWithDelayFunc1.create())
                .filter(t -> isViewAttached())
                .compose(bindToLifecycle());
    }

    public <T> ObservableTransformer<T, T> verifyOnMainThread()
    {
        return tObservable -> tObservable.filter(t -> isViewAttached())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public abstract class NetSubseriber<T> extends DefaultObserver<T>
    {

        @Override
        public void onStart()
        {
            super.onStart();
            if (isViewAttached())
            {
                getMvpView().showLoading();
            }
        }

        @Override
        public void onComplete()
        {
            if (isViewAttached())
            {
                getMvpView().hideLoading();
            }
        }

        @Override
        public void onError(Throwable e)
        {
            e.printStackTrace();
            if (!isViewAttached())
            {
                return;
            }
            getMvpView().hideLoading();
            if (e instanceof ToastException)
            {
                getMvpView().toast(e.getMessage());
            } else if (e instanceof ResultException)
            {
                onResultError(((ResultException) e));
            } else if (e instanceof HttpException)
            {
                onHttpError(((HttpException) e));
            } else if (e instanceof SocketTimeoutException)
            {
                onSocketTimeoutException(((SocketTimeoutException) e));
            } else if (e instanceof ApiException)
            {
                onApiException(((ApiException) e));
            } else if (e instanceof ConnectException)
            {
                onConnectException(((ConnectException) e));
            }  else if (e instanceof UnknownHostException)
            {
                onUnknownHostException(((UnknownHostException) e));
            }else
            {
                onOtherError(e);
            }
        }

        public void onApiException(ApiException e)
        {

        }

        public void onUnknownHostException(UnknownHostException e)
        {
            getMvpView().toast(R.string.core_error_check_http);
        }

        public void onOtherError(Throwable e)
        {
            getMvpView().toast(R.string.core_error_not_know);
        }

        public void onResultError(ResultException e)
        {
            getMvpView().toast(e.getMessage());
        }

        public void onHttpError(HttpException e)
        {
            getMvpView().toast(R.string.core_error_http);
        }

        public void onSocketTimeoutException(SocketTimeoutException e)
        {
            getMvpView().toast(R.string.core_error_socket_time_out);
        }

        public void onConnectException(ConnectException e)
        {
            getMvpView().toast(R.string.core_error_not_connect);
        }
    }

}