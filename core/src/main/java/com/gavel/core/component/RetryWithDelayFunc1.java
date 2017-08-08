package com.gavel.core.component;


import com.gavel.http.constant.ResponseCode;
import com.gavel.http.ex.ApiException;
import com.gavel.http.ex.ResultException;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.functions.Function;

/**
 * Created by jhhuang on 2016/10/20.
 * QQ:781913268
 * Description：失败的重复三次
 */
public class RetryWithDelayFunc1 implements Function<Observable<? extends Throwable>, Observable<?>>
{
    private final int maxRetries;
    private final int retryDelayMillis;
    private int retryCount;

    public static RetryWithDelayFunc1 create()
    {
        return new RetryWithDelayFunc1();
    }

    public static RetryWithDelayFunc1 create(int maxRetries, int retryDelayMillis)
    {
        return new RetryWithDelayFunc1(maxRetries, retryDelayMillis);
    }

    private RetryWithDelayFunc1()
    {
        this(3, 20);
    }

    private RetryWithDelayFunc1(int maxRetries, int retryDelayMillis)
    {
        this.maxRetries = maxRetries;
        this.retryDelayMillis = retryDelayMillis;
        this.retryCount = 0;
    }

    @Override
    public Observable<?> apply(Observable<? extends Throwable> error)
    {
        return error.flatMap(throwable ->
        {

            if (throwable instanceof ApiException)
            {
                ResponseCode responseCode = ((ApiException) throwable).getResponseCode();
                switch (responseCode)
                {
                    case AUTH_FAILURE:
                        RxBus.getDefault().post(new FromApiEvent(ResponseCode.AUTH_FAILURE));
                        return Observable.error(throwable);
                    default:
                        return Observable.error(throwable);
                }

            }

            if (throwable instanceof ResultException)
            {
                return Observable.error(throwable);
            }

            if (++retryCount <= maxRetries)
            {
                return Observable.timer(retryDelayMillis,
                        TimeUnit.MILLISECONDS);
            }
            return Observable.error(throwable);
        });
    }

}
