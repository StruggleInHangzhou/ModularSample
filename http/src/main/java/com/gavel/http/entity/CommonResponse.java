package com.gavel.http.entity;

import com.gavel.http.modify.ListTypeAdapterFactory;
import com.google.gson.annotations.JsonAdapter;

/**
 * Created by jhhuang on 2016/9/19.
 * QQ:781913268
 * Description：返回的实体类泛型
 */
public class CommonResponse<T>
{
    private int errorCode;
    private String errorMessage;

    @JsonAdapter(ListTypeAdapterFactory.class)
    private T data;

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

    public T getData()
    {
        return data;
    }

    public void setData(T data)
    {
        this.data = data;
    }
}
