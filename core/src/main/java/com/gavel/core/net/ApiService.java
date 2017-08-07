package com.gavel.core.net;


import com.gavel.http.entity.CommonResponse;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by jhhuang on 2017/6/1
 * QQ:781913268
 * Description：xxx
 */
public interface ApiService
{
    @FormUrlEncoded
    @POST("userAppInfo/create")
    Observable<CommonResponse<String>> sendAppInfo(@FieldMap Map<String, String> params);
}
