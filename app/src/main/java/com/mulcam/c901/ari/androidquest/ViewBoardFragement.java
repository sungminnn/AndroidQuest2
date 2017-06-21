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
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.ArrayList;
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

    private TextView title;
    private TextView writedate;
    private TextView readcount;
    private TextView reword1;
    private TextView reword2;
    private TextView reword3;
    private TextView view_content;
    private TextView people;
    private TextView contact;
    private TextView address;
    private  Map<String, Object> boardList;
    private Map<String, Object> addrList;
    private int boardNo ;
    private String selectReward;
    private String contactAnswer;
    private String apply_content;
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        //신청하기
        applyuser_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                view = ((MainActivity)getActivity()).getLayoutInflater().inflate(R.layout.dialog_applyuser,null);

                TextView boardNo_edit = (TextView) view.findViewById(R.id.apply_boardNo_edit);
                TextView contact_edit = (TextView) view.findViewById(R.id.apply_contact_edit);
                TextView contactNo = (TextView) view.findViewById(R.id.apply_contactNo);
                TextView content_edit = (TextView) view.findViewById(R.id.apply_content_edit);

                //글번호
                boardNo_edit.setText(String.format("%.0f",boardList.get("boardNo"))+"번");

                //보상
                RadioGroup reward_rg = (RadioGroup) view.findViewById(R.id.apply_reward_rg);

                RadioButton reward1 = new RadioButton((MainActivity)getActivity());
                reward1.setText(String.valueOf(boardList.get("reward1")));
                reward1.setLayoutParams
                        (new RadioGroup.LayoutParams
                                (RadioGroup.LayoutParams.WRAP_CONTENT, RadioGroup.LayoutParams.WRAP_CONTENT));
                reward_rg.addView(reward1);

                if(!((String)boardList.get("reward2")).equals("")){
                    RadioButton reward2 = new RadioButton((MainActivity)getActivity());
                    reward2.setText(String.valueOf(boardList.get("reward2")));
                    reward2.setLayoutParams
                            (new RadioGroup.LayoutParams
                                    (RadioGroup.LayoutParams.WRAP_CONTENT, RadioGroup.LayoutParams.WRAP_CONTENT));
                    reward_rg.addView(reward2);
                }

                if(!((String)boardList.get("reward3")).equals("")){
                    RadioButton reward3 = new RadioButton((MainActivity)getActivity());
                    reward3.setText(String.valueOf(boardList.get("reward3")));
                    reward3.setLayoutParams
                            (new RadioGroup.LayoutParams
                                    (RadioGroup.LayoutParams.WRAP_CONTENT, RadioGroup.LayoutParams.WRAP_CONTENT));
                    reward_rg.addView(reward3);
                }

                //연락방법 넣기
                if( (String.valueOf( boardList.get("contactNo")).equals("1.0"))){
                    contactNo.setText("Kakao");
                }
                else if((String.valueOf( boardList.get("contactNo")).equals("2.0"))){
                    contactNo.setText("email");
                }
                else if( (String.valueOf( boardList.get("contactNo")).equals("3.0"))){
                    contactNo.setText("HP");
                }

                // 넘겨줄 보드노
                boardNo = Integer.parseInt(String.format("%.0f",boardList.get("boardNo")));
                // 선택된 보상 가져오기
                Log.d("viewBoard_radio", String.valueOf(reward_rg.getCheckedRadioButtonId() ));

//                switch (reward_rg.getCheckedRadioButtonId()){
//                    case
//                }
                //넘겨줄 연락처
                contactAnswer = contact_edit.getText().toString();

                //넘겨줄 내용
                apply_content = content_edit.getText().toString();


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
                        Log.d("viewBoard","신청하기 클릭");
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
                                Call<HashMap<String, Object>> call = service.applyProc();
                                call.enqueue(new Callback<HashMap<String,Object>>() {
                                    @Override
                                    public void onResponse(Call<HashMap<String, Object>> call, Response<HashMap<String, Object>> response) {
                                        Map<String, Object> hash = response.body();
                                        Map<String, Object> hash2 = (Map<String, Object>)hash.get("list1");
                                        List<Map<String, Object>> hash3 = (List<Map<String, Object>>)hash2.get("boardList");
                                        Log.d("todo",String.valueOf(hash3));
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
        view_content = (TextView) view.findViewById(R.id.viewboard_content);
        reword1 = (TextView) view.findViewById(R.id.viewboard_reword_1);
        reword2 = (TextView) view.findViewById(R.id.viewboard_reword_2);
        reword3 = (TextView) view.findViewById(R.id.viewboard_reword_3);


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

                if( (String.valueOf( boardList.get("contactNo")).equals("1.0"))){
                    contact.setText("Kakao");
                }
                else if((String.valueOf( boardList.get("contactNo")).equals("2.0"))){
                    contact.setText("email");
                }
                else if( (String.valueOf( boardList.get("contactNo")).equals("3.0"))){
                    contact.setText("HP");
                }

                address.setText(String.valueOf(addrList.get("sido")+" " + addrList.get("gungu")));
                view_content.setText(String.valueOf(boardList.get("content")));

                reword1.setText(String.valueOf(boardList.get("reward1")));
                if(!((String)boardList.get("reward2")).equals("")){
                    reword2.setText(String.valueOf(boardList.get("reward2")));
                }
                if(!((String)boardList.get("reward3")).equals("")){
                    reword3.setText(String.valueOf(boardList.get("reward3")));
                }



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

    /**
     * Created by student on 2017-06-16.
     */

    public static class TodoBoardFragment extends Fragment {
        private BoardAdapter adapter;
        private ListView main_lv;
        private View view;
        private ViewBoardFragement viewBoard;
        private EditText boardNo_edt;


        @Override
        public void onActivityCreated(@Nullable Bundle savedInstanceState) {
            super.onActivityCreated(savedInstanceState);
            main_lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Log.d("todoFrag", "리스트뷰 클릭클릭");
                        Log.d("todoFrag", String.valueOf(position));
                        Log.d("todoFrag", "boardNo " + String.format("%.0f", (double)((Map<String, Object>)parent.getItemAtPosition(position)).get("boardNo") ));

                        String boardNo = String.format("%.0f", (double)((Map<String, Object>)parent.getItemAtPosition(position)).get("boardNo"));

                        getBoardNo(boardNo);

                    }
            });
        }

        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
            view = inflater.inflate(R.layout.boardlistview, container, false);
            main_lv = (ListView) view.findViewById(R.id.main_lv);
            boardNo_edt = (EditText) view.findViewById(R.id.board_boardNo);
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
                    RetrofitInterface service = RetrofitService.getInstance();
                    Call<HashMap<String, Object>> call = service.repo();
                    call.enqueue(new Callback<HashMap<String,Object>>() {
                        @Override
                        public void onResponse(Call<HashMap<String, Object>> call, Response<HashMap<String, Object>> response) {
                            Map<String, Object> hash = response.body();
                            Map<String, Object> hash2 = (Map<String, Object>)hash.get("list1");
                            List<Map<String, Object>> hash3 = (List<Map<String, Object>>)hash2.get("boardList");
                            Log.d("todo",String.valueOf(hash3));
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
                ((MainActivity)getActivity()).setBoardNoforviewBoard(boardNo);
        }


    }
}

