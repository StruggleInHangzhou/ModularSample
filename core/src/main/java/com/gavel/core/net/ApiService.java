package com.gavel.core.net;


import com.gavel.basic.auth.dto.UserDTO;
import com.gavel.http.entity.CommonResponse;

import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.Field;
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
    @POST("auth/register")
    Observable<CommonResponse<String>> register(@FieldMap Map<String, String> params);

    @FormUrlEncoded
    @POST("auth/app/login")
    Observable<CommonResponse<String>> appLogin(@FieldMap Map<String, String> params);

    @FormUrlEncoded
    @POST("auth/test/empty")
    Observable<CommonResponse<String>> testEmpty(@Field("mobile") String param);

    @POST("auth/test/empty/list")
    Observable<CommonResponse<List<UserDTO>>> testEmptyList();

    @POST("auth/test/object")
    Observable<CommonResponse<UserDTO>> testObject();

    @POST("auth/test/object/null")
    Observable<CommonResponse<UserDTO>> testObjectNull();

    @POST("auth/test/list")
    Observable<CommonResponse<List<UserDTO>>> testList();

    @POST("auth/test/null/list")
    Observable<CommonResponse<List<UserDTO>>> requsetListNull();

}
