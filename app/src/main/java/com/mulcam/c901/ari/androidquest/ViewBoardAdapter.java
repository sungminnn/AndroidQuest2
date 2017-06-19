package com.mulcam.c901.ari.androidquest;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
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
 * Created by student on 2017-06-19.
 */

public class ViewBoardAdapter extends ArrayAdapter<Map<String, Object>> {
    private Context context;
    private int resId;
    private List<Map<String, Object>> list  = null;


    public ViewBoardAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull List<Map<String, Object>> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resId = resource;
        if(objects == null)
            this.list = new ArrayList<>();
        else
            this.list = objects;
        Log.i("viewboardApter", "생성자");

    }

    public void setViewBoard(List<Map<String, Object>> objects)
    {
        this.list = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        TextView viewboard_title;
        if( convertView == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(resId, parent, false);
        }
        viewboard_title = (TextView) convertView.findViewById(R.id.viewboard_title);
        viewboard_title.setText((String)list.get(position).get("title"));
        Log.i("Adapter", "viewBoard_getView");

        return convertView;
    }
}
