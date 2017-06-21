package com.mulcam.c901.ari.androidquest;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Jin on 2017-06-14.
 */

public class BoardAdapter extends ArrayAdapter<Map<String, Object>> {


    public BoardAdapter(@NonNull Context context, @LayoutRes int resId, @NonNull List<Map<String, Object>> list) {
        super(context, resId, list);
        this.context = context;
        this.resId = resId;
        if(list == null)
            this.list = new ArrayList<>();
        else
            this.list = list;
        Log.i("boardApter", "생성자");


    }

    private Context context;
    private int resId;
    private List<Map<String, Object>> list  = null;
//    public BoardAdapter(Context context, int resId, List<Map<String, Object>> icon_list)
//    {
//        this.context = context;
//        this.resId = resId;
//        if(icon_list == null)
//            this.icon_list = new ArrayList<>();
//        else
//            this.icon_list = icon_list;
//
//    }
    public void setList(List<Map<String, Object>> list)
    {
        this.list = list;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final TextView board_title;
        TextView writeuser;
        TextView board_writedate;
        TextView board_readcount;

        if(convertView == null)
        {
            LayoutInflater inflater
                    = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(resId, parent, false);
        }
        board_title = (TextView)convertView.findViewById(R.id.board_title);
        board_title.setText((String)list.get(position).get("title"));
//        Log.i("Adapter", "page_viewboard");

        writeuser = (TextView) convertView.findViewById(R.id.board_writeuser);
        writeuser.setText((String)list.get(position).get("nickname"));
        board_writedate = (TextView) convertView.findViewById(R.id.board_writedate);
        board_writedate.setText((String)list.get(position).get("date"));
        board_readcount = (TextView) convertView.findViewById(R.id.board_readcount);
        board_readcount.setText(String.format("%.0f", (double)list.get(position).get("readCount")));



        return convertView;
    }
}
