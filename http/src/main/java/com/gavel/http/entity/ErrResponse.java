package com.gavel.http.entity;

/**
 * Created by jhhuang on 2016/10/20.
 * QQ:781913268
 * Description：错误信息
 */
public class ErrResponse
{
    private static final String TAG = "ErrResponse";
    private int errorCode;
    private String errorMessage;
    private String data;

    public int getErrorCode()
    {
        return errorCode;
    }

    public void setErrorCode(int errorCode)
    {
        this.errorCode = errorCode;
    }

    public String getErrorMessage()
    {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage)
    {
        this.errorMessage = errorMessage;
    }

    public String getData()
    {
        return data;
    }

    public void setData(String data)
    {
        this.data = data;
    }
}
