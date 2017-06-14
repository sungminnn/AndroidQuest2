package com.mulcam.c901.ari.androidquest;

import android.content.Context;
import android.database.DataSetObserver;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Jin on 2017-06-14.
 */

public class BoardAdapter extends BaseAdapter {

    private Context context;
    private int resId;
    private List<Map<String, Object>> list  = null;
    public BoardAdapter(Context context, int resId, List<Map<String, Object>> list)
    {
        this.context = context;
        this.resId = resId;
        if(list == null)
            this.list = new ArrayList<>();
        else
            this.list = list;

    }
    public void setList(List<Map<String, Object>> list)
    {
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final TextView board_title;
        if(convertView == null)
        {
            LayoutInflater inflater
                    = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(resId, parent, false);
        }
        board_title = (TextView)convertView.findViewById(R.id.board_title);
        board_title.setText((String)list.get(position).get("title"));


        return convertView;
    }
}
