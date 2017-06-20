package com.mulcam.c901.ari.androidquest;

import android.app.AlertDialog;
import android.app.Fragment;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.HashMap;
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

    private TextView title;
    private TextView writedate;
    private TextView readcount;
    private TextView content;
    private TextView people;
    private TextView contact;
    private TextView address;
    private  Map<String, Object> boardList;
    private Map<String, Object> addrList;
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        //신청하기
        applyuser_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                view = ((MainActivity)getActivity()).getLayoutInflater().inflate(R.layout.dialog_applyuser,null);

                TextView boardNo_edit = (TextView) view.findViewById(R.id.apply_boardNo_edit);
                boardNo_edit.setText(String.format("%.0f",boardList.get("boardNo")));

                final AlertDialog dialog = new AlertDialog.Builder(getActivity())
                        .setView(view)
                        .create();
                dialog.show();
                Button btn_cancel = (Button) view.findViewById(R.id.apply_btn_cancel);
                Button btn_ok = (Button) view.findViewById(R.id.apply_btn_ok);
                //신청하기 취소
                btn_cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
                btn_ok.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                });

            }
        });

        //신고하기
        police_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        //즐겨찾기
        bookmark_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        Log.d("viewBoard", "뷰보드플래그 인");

        view = inflater.inflate(R.layout.page_viewboard, container, false);

        applyuser_btn = (Button)view.findViewById(R.id.viewboard_applyuser_btn);
        bookmark_btn = (Button)view.findViewById(R.id.viewboard_bookmark_btn);
        police_btn = (Button)view.findViewById(R.id.viewboard_police_btn);

        title = (TextView) view.findViewById(R.id.viewboard_title);
        writedate = (TextView) view.findViewById(R.id.viewboard_writedate);
        readcount = (TextView) view.findViewById(R.id.viewboard_readcount);
        people = (TextView) view.findViewById(R.id.viewboard_people);
        contact = (TextView) view.findViewById(R.id.viewboard_contact);
        address = (TextView) view.findViewById(R.id.viewboard_address);
        content = (TextView) view.findViewById(R.id.viewboard_content);


        return view;
    }



    public void setBoard(final String boardNo)
    {
        Log.d("viewBoard","setBoard in");

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
                Log.d("viewBoard"," onResponse title "+String.valueOf(boardList.get("title")));
                Log.d("viewBoard"," onResponse readcount "+String.valueOf(boardList.get("readCount")));
                readcount.setText(String.format("%.0f",boardList.get("readCount")) );
                people.setText(String.format("%.0f",boardList.get("people"))+"명");

                title.setText(String.valueOf(boardList.get("title")));
                writedate.setText(String.valueOf(boardList.get("date")));

                if( (String.valueOf( boardList.get("contactNo")).equals("1"))){
                    contact.setText("Kakao");
                }
                else if((String.valueOf( boardList.get("contactNo")).equals("2"))){
                    contact.setText("email");
                }
                else if( (String.valueOf( boardList.get("contactNo")).equals("3"))){
                    contact.setText("HP");
                }

                address.setText(String.valueOf(addrList.get("sido")+" " + addrList.get("gungu")));
                content.setText(String.valueOf(boardList.get("content")));



            }

            @Override
            protected Nullable doInBackground(Nullable... params) {
                RetrofitInterface service = RetrofitService.getInstance();
                Log.d("viewBoard"," doInBackground boardNo " + boardNo);
                Call<HashMap<String, Object>> call = service.getBoard(Integer.parseInt(boardNo));
                call.enqueue(new Callback<HashMap<String,Object>>() {
                    @Override
                    public void onResponse(Call<HashMap<String, Object>> call, Response<HashMap<String, Object>> response) {
                        HashMap<String, Object> data = response.body();


                        boardList = (Map<String, Object>)data.get("boardList");
                        addrList = (Map<String, Object>)data.get("addrList");

                        Log.d("viewBoard"," onResponse board "+String.valueOf(boardList));
                        Log.d("viewBoard"," onResponse addr "+String.valueOf(addrList));

//
                        publishProgress();

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

