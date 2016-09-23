package com.qianfeng.notepad;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by 蔡灿武 on 2016/9/17.
 */
public class MyAdapter extends BaseAdapter{
    private Context context;
    private List<Note> list;
    private LayoutInflater inflater;

    public MyAdapter(Context context, List<Note> list) {
        this.context = context;
        this.list = list;
        inflater=LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if(view==null){
            view=inflater.inflate(R.layout.lv_item,viewGroup,false);
            holder=new ViewHolder();
            holder.tv1 = (TextView) view.findViewById(R.id.tv1);
            holder.tv2 = (TextView) view.findViewById(R.id.tv2);
            holder.tv3 = (TextView) view.findViewById(R.id.tv3);
            holder.tv4 = (TextView) view.findViewById(R.id.tv4);
            view.setTag(holder);
        }else{
            holder= (ViewHolder) view.getTag();
        }
        Note note =list.get(i);
        holder.tv1.setText(note.getUsername());
        holder.tv2.setText(note.getTime());
        holder.tv3.setText(note.getTitle());
        holder.tv4.setText(note.getContent());
        return view;
    }
    class ViewHolder{
        TextView tv1,tv2,tv3,tv4;
    }
}
