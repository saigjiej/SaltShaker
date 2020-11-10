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

public class PlantingCustomAdapter extends RecyclerView.Adapter<PlantingCustomAdapter.CustomViewHolder> {
    private ArrayList<Planting> arrayList;
    private Context context;

    public PlantingCustomAdapter(ArrayList<Planting> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.planting_list_item, parent, false);
        CustomViewHolder holder = new CustomViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder holder, int position) {
        Glide.with(holder.itemView)
                .load(arrayList.get(position).getPoster())
                .into(holder.iv_poster);
        holder.tv_title.setText(arrayList.get(position).getTitle());
        holder.tv_plant.setText(arrayList.get(position).getPlant());
        holder.tv_flower.setText(arrayList.get(position).getFlower());
        holder.tv_degree.setText(arrayList.get(position).getDegree());
        holder.tv_water.setText(arrayList.get(position).getWater());
    }

    @Override
    public int getItemCount() {
        // 삼항 연산자
        return (arrayList != null ? arrayList.size() : 0);
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder {
        ImageView iv_poster;
        TextView tv_title;
        TextView tv_plant;
        TextView tv_flower;
        TextView tv_degree;
        TextView tv_water;

        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);
            this.iv_poster = itemView.findViewById(R.id.iv_poster);
            this.tv_title = itemView.findViewById(R.id.tv_title);
            this.tv_degree = itemView.findViewById(R.id.tv_degree);
            this.tv_flower = itemView.findViewById(R.id.tv_flower);
            this.tv_plant = itemView.findViewById(R.id.tv_plant);
            this.tv_water = itemView.findViewById(R.id.tv_water);
        }
    }
}
