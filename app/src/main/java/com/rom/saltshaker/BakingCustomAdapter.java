package com.rom.saltshaker;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class BakingCustomAdapter extends RecyclerView.Adapter<BakingCustomAdapter.CustomViewHolder> {
    private ArrayList<Baking> arrayList;
    private Context context;

    public BakingCustomAdapter(ArrayList<Baking> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.baking_list_item, parent, false);
        CustomViewHolder holder = new CustomViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder holder, int position) {
        Glide.with(holder.itemView)
                .load(arrayList.get(position).getPoster())
                .into(holder.iv_poster);
        holder.tv_title.setText(arrayList.get(position).getTitle());
        holder.tv_material.setText(arrayList.get(position).getMaterial());
        holder.tv_how.setText(arrayList.get(position).getHow());
        holder.tv_materialText.setText(arrayList.get(position).getMaterialText());
        holder.tv_making1.setText(arrayList.get(position).getMaking1());
        holder.tv_making2.setText(arrayList.get(position).getMaking2());
        holder.tv_making3.setText(arrayList.get(position).getMaking3());
        holder.tv_making4.setText(arrayList.get(position).getMaking4());
    }

    @Override
    public int getItemCount() {
        // 삼항 연산자
        return (arrayList != null ? arrayList.size() : 0);
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder {
        ImageView iv_poster;
        TextView tv_title;
        TextView tv_material;
        TextView tv_how;
        TextView tv_materialText;
        TextView tv_making1;
        TextView tv_making2;
        TextView tv_making3;
        TextView tv_making4;

        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);
            this.iv_poster = itemView.findViewById(R.id.iv_poster);
            this.tv_title = itemView.findViewById(R.id.tv_title);
            this.tv_material = itemView.findViewById(R.id.tv_material);
            this.tv_how = itemView.findViewById(R.id.tv_how);
            this.tv_materialText = itemView.findViewById(R.id.tv_materialText);
            this.tv_making1 = itemView.findViewById(R.id.tv_making1);
            this.tv_making2 = itemView.findViewById(R.id.tv_making2);
            this.tv_making3 = itemView.findViewById(R.id.tv_making3);
            this.tv_making4 = itemView.findViewById(R.id.tv_making4);
        }
    }
}
