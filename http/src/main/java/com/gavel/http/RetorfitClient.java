package com.gavel.http;

import android.support.annotation.NonNull;

import com.gavel.http.modify.GsonConverterFactory;
import com.gavel.http.utils.LoggerInterceptor;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Headers;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.CallAdapter;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Created by jhhuang on 2017/8/7.
 * QQ:781913268
 * Description：xxx
 */
public class RetorfitClient
{
    public static final String URL_BASE = "http://192.168.2.131:8091/";

    private static Converter.Factory scalarsconverterfactory = ScalarsConverterFactory.create();
    private static Converter.Factory gsonConverterFactory = GsonConverterFactory.create();
    private static CallAdapter.Factory rxJava2CallAdapterFactory = RxJava2CallAdapterFactory.create();

    private Retrofit retrofit;

//    public RetorfitClient()
//    {
//        this(URL_BASE);
//    }

    RetorfitClient(String url)
    {

        OkHttpClient.Builder builder = new OkHttpClient.Builder()
                .addInterceptor(new HeaderInterceptor())
                .connectTimeout(10000L, TimeUnit.MILLISECONDS)
                .readTimeout(10000L, TimeUnit.MILLISECONDS);

        builder.addInterceptor(new LoggerInterceptor("http", true));

        OkHttpClient okHttpClient = builder.build();

        retrofit = new Retrofit.Builder()
                .client(okHttpClient)
                .addConverterFactory(scalarsconverterfactory)
                .addConverterFactory(gsonConverterFactory)
                .addCallAdapterFactory(rxJava2CallAdapterFactory)
                .baseUrl(url)
                .build();

    }


    public <T> T create(Class<T> clazz)
    {
        return retrofit.create(clazz);
    }

    /**
     * 全局头信息
     */
    private class HeaderInterceptor implements Interceptor
    {

        @Override
        public Response intercept(@NonNull Chain chain) throws IOException
        {
            Request request = chain.request();
            String path = request.url().encodedPath();
            //这里设置成你的全局header
            Headers headers = request.headers();
            Request.Builder builder = chain.request().newBuilder()
                    .headers(headers);
            builder.addHeader("platForm", "Android");
            Request interRequest = builder.build();
            return chain.proceed(interRequest);
        }
    }
}
