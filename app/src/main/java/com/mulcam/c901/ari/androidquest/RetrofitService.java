package com.mulcam.c901.ari.androidquest;

import android.util.Log;

import java.io.IOException;
import java.net.URLDecoder;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.ResponseBody;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Jin on 2017-06-19.
 */

class RetrofitService {
    private static RetrofitInterface service;
    private static OkHttpClient okhttp;
    private RetrofitService()
    {

    }
    public static RetrofitInterface getInstance()
    {
        Log.d("RetrofitService", "getInstance");
        if(service == null)
        {
            Log.d("RetrofitService", "service null");
            okhttp = setInterceptor();
            Retrofit client = new Retrofit.Builder().baseUrl("http://10.0.3.2:8080/Quest/")
                                    .addConverterFactory(GsonConverterFactory.create())
                                    .client(okhttp)
                                    .build();
            service = client.create(RetrofitInterface.class);
        }
        Log.d("RetrofitService", "service return");
        return service;
    }
    private static OkHttpClient setInterceptor()
        {
            Log.d("RetrofitService", "OkHttpClient");
            OkHttpClient.Builder builder = new OkHttpClient.Builder().addInterceptor(new Interceptor() {
                @Override
                public okhttp3.Response intercept(Chain chain) throws IOException {
                    Request original = chain.request();

                    // 헤더를 자유 자재로 변경
                    Request.Builder builder = original.newBuilder();
                    builder.addHeader("Content-Type","application/json; charset=utf-8");
                    builder.addHeader("Accept","application/json; charset=utf-8");

                    builder.method(original.method(),original.body());
                    Request request = builder.build();

                    okhttp3.Response response = chain.proceed(request);

                    // 아래 소스는 response로 오는 데이터가 URLEncode 되어 있을 때
                    // URLDecode 하는 소스 입니다.
                    return response.newBuilder()
                            .body(ResponseBody.create(response.body().contentType()
                                    , URLDecoder.decode(response.body().string(),"utf-8")))
                            .build();
                }
            });


            OkHttpClient okHttp = builder.build();
            return okHttp;
        }
}
