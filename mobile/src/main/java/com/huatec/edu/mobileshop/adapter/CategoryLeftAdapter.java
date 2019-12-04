package com.huatec.edu.mobileshop.adapter;

import android.app.Activity;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.huatec.edu.mobileshop.R;
import com.huatec.edu.mobileshop.http.entity.CategoryEntity;

import java.util.List;
import java.util.Locale;

public class CategoryLeftAdapter extends RecyclerView.Adapter<CategoryLeftAdapter.LeftViewHolder>implements View.OnClickListener {
    private final List<CategoryEntity> datas;
    private final Activity mContext;
    private OnItemClickListener onItemClickListener;
    private int select = 0;

    public CategoryLeftAdapter(Activity activity, List<CategoryEntity> data) {
        this.datas = data;
        this.mContext = activity;
    }

    public void setOnItemClickListener(OnItemClickListener l) {
        this.onItemClickListener = l;
    }

    @NonNull
    @Override
    public LeftViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_category_left, parent, false);
        view.setOnClickListener(this);
        return new LeftViewHolder(view);
    }

    public void onBindViewHolder(@NonNull LeftViewHolder holder, int position) {
        if (select == position) {
            holder.ll_item.setBackgroundResource(android.R.color.white);
        }else {
            holder.ll_item.setBackgroundColor(Color.parseColor("#fff3f4f6"));
        }
        CategoryEntity entity=datas.get(position);
        holder.itemView.setTag(position);
        holder.tv_name.setText(entity.getName());
    }
    @Override
    public int getItemCount(){
        if(datas!=null){
            return datas.size();
        }
        return 0;
    }
    public void setSelect(int select){
        this.select=select;
        notifyDataSetChanged();
    }
    @Override
    public void onClick(View v){
        if(onItemClickListener!=null){
            int position=(int)v.getTag();
            CategoryEntity entity=datas.get(position);
            onItemClickListener.onItemClick(v,position,entity);
        }
    }
    public class LeftViewHolder extends RecyclerView.ViewHolder{
        public final TextView tv_name;
        public  final LinearLayout ll_item;
        public LeftViewHolder(View itemView){
            super(itemView);
            tv_name=itemView.findViewById(R.id.tv_name);
            ll_item=itemView.findViewById(R.id.ll_item);
        }
    }
    public interface OnItemClickListener {
        void onItemClick(View view, int position, CategoryEntity entity);
    }
}