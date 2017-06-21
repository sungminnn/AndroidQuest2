package com.mulcam.c901.ari.androidquest;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.Image;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Jin on 2017-06-21.
 */

public class LoginActivity extends Activity{

    private EditText login_id_edt;
    private EditText login_pw_edt;
    private ImageView login_img_btn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.page_login);

        login_id_edt = (EditText) findViewById(R.id.page_login_id_edt);
        login_pw_edt = (EditText) findViewById(R.id.page_login_pw_edt);
        login_img_btn = (ImageView) findViewById(R.id.page_login_img_btn);
        login_id_edt.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                RetrofitInterface service = RetrofitService.getInstance();
                Call<HashMap<String, Object>> call = service.login_id(login_id_edt.getText().toString());
                call.enqueue(new Callback<HashMap<String,Object>>() {
                    @Override
                    public void onResponse(Call<HashMap<String, Object>> call, Response<HashMap<String, Object>> response) {
                        Map<String, Object> resp = response.body();
                        int isCorrect = (int)resp.get("isCorrect");
                        switch (isCorrect)
                        {
                            case 1:
                                Log.d("login" , "성공1");

                                break;
                            case 2:
                                Log.d("login" , "id실패");
                                break;
                        }
                    }

                    @Override
                    public void onFailure(Call<HashMap<String, Object>> call, Throwable t) {

                    }
                });
            }
        });

        login_img_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RetrofitInterface service = RetrofitService.getInstance();
                Call<HashMap<String, Object>> call = service.login(login_id_edt.getText().toString(), login_pw_edt.getText().toString());
                call.enqueue(new Callback<HashMap<String,Object>>() {
                    @Override
                    public void onResponse(Call<HashMap<String, Object>> call, Response<HashMap<String, Object>> response) {
                        Map<String, Object> resp = response.body();
                        int isCorrect = (int)resp.get("what");
                        switch (isCorrect)
                        {
                            case 1:
                                Log.d("login" , "성공1");
                                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                startActivity(intent);
                                break;
                            case 2:
                                Log.d("login" , "id실패");
                                break;
                            case 3:
                                Log.d("login" , "pw실패");
                                break;
                        }
                    }

                    @Override
                    public void onFailure(Call<HashMap<String, Object>> call, Throwable t) {

                    }
                });
            }
        });
//        SharedPreferences shpf = getPreferences()
    }
}
