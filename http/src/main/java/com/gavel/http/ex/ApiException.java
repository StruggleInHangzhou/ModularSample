package com.gavel.http.ex;


import com.gavel.http.constant.ResponseCode;

/**
 * Created by jhhuang on 2016/9/19.
 * QQ:781913268
 * Description：联网约定异常
 */
public class ApiException extends RuntimeException
{
    private ResponseCode responseCode;

    public ResponseCode getResponseCode()
    {
        return responseCode;
    }

    public void setResponseCode(ResponseCode responseCode)
    {
        this.responseCode = responseCode;
    }

    public ApiException(ResponseCode responseCode)
    {
        super(responseCode.getText());
        this.responseCode = responseCode;
    }
}
