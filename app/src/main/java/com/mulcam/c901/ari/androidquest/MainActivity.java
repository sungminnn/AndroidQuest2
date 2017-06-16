package com.mulcam.c901.ari.androidquest;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.ListView;

import com.google.gson.internal.LinkedTreeMap;

import java.io.IOException;
import java.net.URLDecoder;
import java.util.*;
import java.util.List;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Jin on 2017-06-14.
 */

public class MainActivity extends Activity{
    private List<HashMap<String, Object>> list0;
    private List<HashMap<String,Object>> list1;
    private BoardAdapter adapter;
    private ListView main_lv;
    private OkHttpClient okhttp;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setList();
    }
    private void setList()
    {
        okhttp = setInterceptor();
        final Retrofit client = new Retrofit.Builder().baseUrl("http://10.0.3.2:8080/Quest/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .client(okhttp)
                        .build();

        new AsyncTask<Nullable, Nullable, Nullable>()
        {

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
            }

            @Override
            protected void onPostExecute(Nullable nullable) {
                super.onPostExecute(nullable);
//                adapter.setList(list0);
//                adapter.notifyDataSetChanged();

            }

            @Override
            protected void onProgressUpdate(Nullable... values) {
                super.onProgressUpdate(values);
            }

            @Override
            protected Nullable doInBackground(Nullable... params) {
                RetrofitInterface service = client.create(RetrofitInterface.class);
                Call<HashMap<String, Object>> call = service.repo();
                call.enqueue(new Callback<HashMap<String, Object>>() {
                    @Override
                    public void onResponse(Call<HashMap<String, Object>> call, Response<HashMap<String, Object>> response) {
                        Map<String, Object> hash = response.body();
                        Map<String, Object> hash2 = (LinkedTreeMap<String, Object>)hash.get("list1");
                        List<Map<String, Object>> hash3 = (List<Map<String, Object>>)hash2.get("boardList");




                        adapter = new BoardAdapter(MainActivity.this, R.layout.board, hash3);
                        main_lv = (ListView) findViewById(R.id.main_lv);
                        main_lv.setAdapter(adapter);
                    }

                    @Override
                    public void onFailure(Call<HashMap<String, Object>> call, Throwable t) {

                    }
                });
                return null;
            }
        }.execute();
    }
    private OkHttpClient setInterceptor()
    {
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
