package com.mulcam.c901.ari.androidquest;


import android.app.Fragment;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.gson.internal.LinkedTreeMap;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by student on 2017-06-16.
 */

public class WelldoBoardFragment extends Fragment {
    private BoardAdapter adapter;
    private ListView main_lv;
    private View view;


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        main_lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d("welldoFrag", "리스트뷰 클릭클릭");
                Log.d("welldoFrag", String.valueOf(position));
                Log.d("welldoFrag", "boardNo " + String.format("%.0f", (double)((Map<String, Object>)parent.getItemAtPosition(position)).get("boardNo") ));

                String boardNo = String.format("%.0f", (double)((Map<String, Object>)parent.getItemAtPosition(position)).get("boardNo"));

                getBoardNo(boardNo);

            }
        });
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
//        return super.onCreateView(inflater, container, savedInstanceState);

        view = inflater.inflate(R.layout.boardlistview, container, false);
        main_lv = (ListView)view.findViewById(R.id.main_lv);
        setList();

        return view;
    }

    private void setList()
    {
        adapter = new BoardAdapter(getActivity(), R.layout.list_board, new ArrayList<Map<String, Object> >());
        main_lv.setAdapter(adapter);

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
//                RetrofitInterface service = client.create(RetrofitInterface.class);
                RetrofitInterface service = RetrofitService.getInstance();
                Call<HashMap<String, Object>> call = service.repo();
                call.enqueue(new Callback<HashMap<String,Object>>() {
                    @Override
                    public void onResponse(Call<HashMap<String, Object>> call, Response<HashMap<String, Object>> response) {
                        Map<String, Object> hash = response.body();
                        Map<String, Object> hash2 = (LinkedTreeMap<String, Object>)hash.get("list0");
                        List<Map<String, Object>> hash3 = (List<Map<String, Object>>)hash2.get("boardList");
                        adapter.addAll(hash3);
                    }

                    @Override
                    public void onFailure(Call<HashMap<String, Object>> call, Throwable t) {

                    }
                });
                return null;
            }
        }.execute();
    }
    public void getBoardNo(String boardNo){
        Log.d("welldoFrag", "boardNo send");
        ((MainActivity)getActivity()).setBoardNoforviewBoard(boardNo);

    }

}
