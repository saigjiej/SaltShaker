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

public class MovieCustomAdapter extends RecyclerView.Adapter<MovieCustomAdapter.CustomViewHolder> {
    private ArrayList<Movie> arrayList;
    private Context context;

    public MovieCustomAdapter(ArrayList<Movie> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_list_item, parent, false);
        CustomViewHolder holder = new CustomViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder holder, int position) {
        Glide.with(holder.itemView)
                .load(arrayList.get(position).getPoster())
                .into(holder.iv_poster);
        holder.tv_title.setText(arrayList.get(position).getTitle());
        holder.tv_genre.setText(arrayList.get(position).getGenre());
        holder.tv_summary.setText(arrayList.get(position).getSummary());
    }

    @Override
    public int getItemCount() {
        // 삼항 연산자
        return (arrayList != null ? arrayList.size() : 0);
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder {
        ImageView iv_poster;
        TextView tv_title;
        TextView tv_genre;
        TextView tv_summary;

        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);
            this.iv_poster = itemView.findViewById(R.id.iv_poster);
            this.tv_title = itemView.findViewById(R.id.tv_title);
            this.tv_genre = itemView.findViewById(R.id.tv_genre);
            this.tv_summary = itemView.findViewById(R.id.tv_summary);
        }
    }
}
