package com.mulcam.c901.ari.androidquest;

import android.app.Fragment;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.gson.internal.LinkedTreeMap;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by student on 2017-06-19.
 */

public class ViewBoardFragement extends Fragment {
    private OkHttpClient okhttp;
    private View view;

    //page_viewBoard
    private Button applyuser_btn;
    private Button bookmark_btn;
    private Button police_btn;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.page_viewboard, container, false);

        applyuser_btn = (Button)view.findViewById(R.id.viewboard_applyuser_btn);
        bookmark_btn = (Button)view.findViewById(R.id.viewboard_bookmark_btn);
        police_btn = (Button)view.findViewById(R.id.viewboard_police_btn);
        setList();

        return view;
    }
    public void setView_main_lv()
    {
        Log.d("asd","asd");
    }
    private void setList()
    {

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
                RetrofitInterface service = RetrofitService.getInstance();
                Call<HashMap<String, Object>> call = service.repo();
                call.enqueue(new Callback<HashMap<String,Object>>() {
                    @Override
                    public void onResponse(Call<HashMap<String, Object>> call, Response<HashMap<String, Object>> response) {
                        Map<String, Object> hash = response.body();
                        Map<String, Object> hash2 = (LinkedTreeMap<String, Object>)hash.get("list1");
                        List<Map<String, Object>> hash3 = (List<Map<String, Object>>)hash2.get("boardList");
//                        adapter.addAll(hash3);
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

