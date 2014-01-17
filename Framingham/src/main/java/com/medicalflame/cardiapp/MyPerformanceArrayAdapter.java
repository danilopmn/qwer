package com.medicalflame.cardiapp;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.medicalflame.cardiapp.R;

import java.util.ArrayList;
import java.util.List;

public class MyPerformanceArrayAdapter extends BaseAdapter {
    private final Context context;
    private String name;
    private List<ItemAdapter> names;
    private boolean flag;

    public MyPerformanceArrayAdapter(Context profileActivity, List<ItemAdapter> ial, boolean b, String name) {
        this(profileActivity, ial,b);
        this.name = name;
    }

    public void setList(ArrayList<ItemAdapter> list) {
        this.names = list;
    }

    static class ViewHolder {
        public TextView t1;
        public TextView t2;
    }

    public MyPerformanceArrayAdapter(Context context, List<ItemAdapter> names, boolean f) {
        this.context = context;
        this.names = names;
        flag = f;
    }

    @Override
    public int getCount() {
        return names.size();
    }

    @Override
    public ItemAdapter getItem(int position) {
        return names.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if( position == 0 && !flag){
            return getFirstView(convertView);
        }
        View rowView = convertView;
        //if (rowView == null ) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            if( !flag ){
                rowView = inflater.inflate(R.layout.row_layout2, null);
            } else{
                rowView = inflater.inflate(R.layout.row_layout, null);
            }
            ViewHolder viewHolder = new ViewHolder();
            viewHolder.t1 = (TextView) rowView.findViewById(R.id.text_view1);
            viewHolder.t2 = (TextView) rowView.findViewById(R.id.text_view2);
            rowView.setTag(viewHolder);
        //}


        ViewHolder holder = (ViewHolder) rowView.getTag();
        ItemAdapter s = names.get(position);
        holder.t1.setText(s.name);
        Integer sec = new Integer(s.pct);
        holder.t2.setText(sec.toString()+"%");
        if( sec <= 9 ){
            holder.t2.setTextColor(Color.parseColor("#469F67"));
        } else if ( sec <= 19){
            holder.t2.setTextColor(Color.parseColor("#F1BF5F"));
        } else {
            holder.t2.setTextColor(Color.parseColor("#9F4648"));
        }

        return rowView;
    }

    private View getFirstView(View convertView) {
        View rowView = convertView;
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        rowView = inflater.inflate(R.layout.first_row_layout, null);
        //((TextView) rowView.findViewById(R.id.textViewNomeProfile)).setText(name);
        ((TextView) rowView.findViewById(R.id.textViewDataProfile)).setText(names.get(0).name);
        ((TextView) rowView.findViewById(R.id.textViewFramighamProfile)).setText(names.get(0).pct + "%");

        int sec = Integer.parseInt(names.get(0).pct);
        if( sec <= 9 ){
            ((TextView) rowView.findViewById(R.id.textViewNomeProfile)).setText("Risco baixo");
            ((TextView) rowView.findViewById(R.id.textViewNomeProfile)).setTextColor(Color.parseColor("#469F67"));
            ((TextView) rowView.findViewById(R.id.textViewFramighamProfile)).setTextColor(Color.parseColor("#469F67"));
        } else if ( sec <= 19){
            ((TextView) rowView.findViewById(R.id.textViewNomeProfile)).setText("Risco moderado");
            ((TextView) rowView.findViewById(R.id.textViewNomeProfile)).setTextColor(Color.parseColor("#F1BF5F"));
            ((TextView) rowView.findViewById(R.id.textViewFramighamProfile)).setTextColor(Color.parseColor("#F1BF5F"));
        } else {
            ((TextView) rowView.findViewById(R.id.textViewNomeProfile)).setText("Risco alto");
            ((TextView) rowView.findViewById(R.id.textViewNomeProfile)).setTextColor(Color.parseColor("#9F4648"));
            ((TextView) rowView.findViewById(R.id.textViewFramighamProfile)).setTextColor(Color.parseColor("#9F4648"));
        }

        return rowView;
    }
}