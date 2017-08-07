package com.gavel.http.entity;

/**
 * Created by jhhuang on 2016/10/20.
 * QQ:781913268
 * Description：只有一个code值
 */
public class ResultResponse
{
    private static final String TAG = "ResultResponse";
    private int errorCode = -1;

    public int getErrorCode()
    {
        return errorCode;
    }

    public void setErrorCode(int errorCode)
    {
        this.errorCode = errorCode;
    }
}
