package com.example.yitongshao.ad340v2;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class MainAdapter extends RecyclerView.Adapter <MainAdapter.ViewHolder> {
    ArrayList<String> movie;
    ArrayList<String> dName;
    ArrayList<String> year;
    ArrayList<String> summary;
    Context context;


    public MainAdapter(Context context,ArrayList<String>movie, ArrayList<String>year,ArrayList<String>dName,ArrayList<String>summary){
        this.context=context;
        this.movie=movie;
        this.year=year;
        this.dName=dName;
        this.summary=summary;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.list,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MainAdapter.ViewHolder holder, int position ) {
        holder.name.setText(movie.get(position));
    }

    @Override
    public int getItemCount() {
        return movie.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView name;
        public ViewHolder(View itemView) {
            super(itemView);
            name= (TextView) itemView.findViewById(R.id.full_name);
        }
    }
}
