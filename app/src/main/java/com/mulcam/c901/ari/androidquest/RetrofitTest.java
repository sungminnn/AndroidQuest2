package com.mulcam.c901.ari.androidquest;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.internal.LinkedTreeMap;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.*;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;


/**
 * Created by Jin on 2017-06-13.
 */


public class RetrofitTest extends Activity {

    TextView retrofit_main;
    private Button retrofit_btn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrofit);

        retrofit_main = (TextView) findViewById(R.id.retrofit_main);
        retrofit_btn = (Button) findViewById(R.id.retrofit_btn);
        final Retrofit client = new Retrofit.Builder().baseUrl("http://10.0.3.2:8080/Quest/")
                .addConverterFactory(GsonConverterFactory.create()).build();

        retrofit_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AsyncTask<Nullable, Nullable, Nullable>()
                {

                    @Override
                    protected void onPreExecute() {
                        super.onPreExecute();
                    }

                    @Override
                    protected void onPostExecute(Nullable nullable) {
                        super.onPostExecute(nullable);
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
                                retrofit_main.setText(String.valueOf(hash3));
                            }

                            @Override
                            public void onFailure(Call<HashMap<String, Object>> call, Throwable t) {

                            }
                        });
                        return null;
                    }
                }.execute();
            }
        });

    }
}