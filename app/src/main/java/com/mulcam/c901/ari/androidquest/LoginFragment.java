package com.mulcam.c901.ari.androidquest;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

/**
 * Created by student on 2017-06-21.
 */

public class LoginFragment extends Fragment {
    private View view;
    private EditText login_id;
    private EditText login_pw;
    private ImageView login_btn;

    private String id;
    private String pw;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.login, container, false);

        login_id = (EditText) view.findViewById(R.id.page_login_id_edt);
        login_pw = (EditText) view.findViewById(R.id.page_login_pw_edt);
        login_btn = (ImageView) view.findViewById(R.id.page_login_img_btn);

        return view;

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                id = login_id.getText().toString();
                pw = login_pw.getText().toString();
                Log.d("login",id+pw);
            }
        });

    }
}
