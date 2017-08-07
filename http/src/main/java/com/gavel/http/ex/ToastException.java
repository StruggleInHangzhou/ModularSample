package com.gavel.http.ex;

/**
 * Created by jhhuang on 2016/10/20.
 * QQ:781913268
 * Description：需要提示的异常类
 */
public class ToastException extends RuntimeException
{

    public ToastException(String detailMessage)
    {
        super(detailMessage);
    }

    public ToastException()
    {
        super();
    }

    public ToastException(Throwable throwable)
    {
        super(throwable);
    }

    public ToastException(String detailMessage, Throwable throwable)
    {
        super(detailMessage, throwable);
    }

}
