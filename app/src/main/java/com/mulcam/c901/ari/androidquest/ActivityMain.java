package com.mulcam.c901.ari.androidquest;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.ListView;

import com.google.gson.internal.LinkedTreeMap;

import java.util.*;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Jin on 2017-06-14.
 */

public class ActivityMain extends Activity{
    private List<HashMap<String, Object>> list0;
    private List<HashMap<String,Object>> list1;
    private BoardAdapter adapter;
    private ListView main_lv;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setList();
    }
    private void setList()
    {
        final Retrofit client = new Retrofit.Builder().baseUrl("http://10.0.3.2:8080/Quest/")
                        .addConverterFactory(GsonConverterFactory.create()).build();

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




                        adapter = new BoardAdapter(ActivityMain.this, R.layout.board, hash3);
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

}
