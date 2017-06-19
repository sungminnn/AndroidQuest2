package com.mulcam.c901.ari.androidquest;

import android.app.FragmentManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import java.util.HashMap;
import java.util.List;

import okhttp3.OkHttpClient;

/**
 * Created by Jin on 2017-06-14.
 */

public class MainActivity extends FragmentActivity{
    private List<HashMap<String, Object>> list0;
    private List<HashMap<String,Object>> list1;
    private LinearLayout main_view;

    private Button todo_btn;
    private Button welldo_btn;

    private TodoBoardFragment listBoard;

    private BoardAdapter adapter;
    private ListView main_lv;
    private OkHttpClient okhttp;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        todo_btn = (Button) findViewById(R.id.main_todo_btn);
        welldo_btn = (Button) findViewById(R.id.main_welldo_btn);
        main_view = (LinearLayout) findViewById(R.id.main_view);
//        main_lv = (ListView) findViewById(R.id.main_lv) ;

        listBoard = new TodoBoardFragment();


        todo_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "해주세요 게시판에 들어오셨습니다", Toast.LENGTH_SHORT).show();
                Log.i("main","해주세요게시판 in");

                FragmentManager fm = getFragmentManager();
                fm.beginTransaction().replace(R.id.main_view, listBoard).commit();


            }
        });

    }


}
