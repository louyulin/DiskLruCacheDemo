package com.example.louyulin.diskdemo;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dllo on 18/1/11.
 */

public class HomeListAdapter extends BaseAdapter {

    private List<HomeArticalBean.DataBean.DatasBean> list = new ArrayList<>();

    public void setList(List<HomeArticalBean.DataBean.DatasBean> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        if (list.size()==0){
            return 0;
        }else {
            return list.size();
        }
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, final ViewGroup parent) {
        Holder holder;
        if (convertView == null){
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_homelv,parent,false);
            holder = new Holder(convertView);
            convertView.setTag(holder);
        }else {
            holder = (Holder) convertView.getTag();
        }
        holder.titletv.setText(list.get(position).getTitle());
        holder.zuozhetv.setText(list.get(position).getNiceDate()+"  " +"来自" +"  " + list.get(position).getAuthor());
        Glide.with(parent.getContext())
                .load("http://www.wanandroid.com/blogimgs/62c1bd68-b5f3-4a3c-a649-7ca8c7dfabe6.png")
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.iv);
        return convertView;
    }

    class Holder{
        TextView titletv , zuozhetv;
        ImageView iv;
        public Holder(View view) {
            titletv = view.findViewById(R.id.item_title_tv);
            zuozhetv = view.findViewById(R.id.item_zuozhe_tv);
            iv = view.findViewById(R.id.iv);
        }
    }
}
