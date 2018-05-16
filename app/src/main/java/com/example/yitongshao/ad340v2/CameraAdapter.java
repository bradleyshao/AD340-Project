package com.example.yitongshao.ad340v2;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;

public class CameraAdapter extends RecyclerView.Adapter <CameraAdapter.ViewHolder> {
    Context context;

    ArrayList<Camera> str;

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView camimage;
        TextView camname;
        public ViewHolder(View view){
            super(view);
            camname=(TextView)view.findViewById(R.id.cam_name);
            camimage=(ImageView)view.findViewById(R.id.imageView);

        }

    }

    public CameraAdapter(Context context, ArrayList<Camera> str) {
        this.context = context;
        this.str = str;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.camlist, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull CameraAdapter.ViewHolder holder, int position) {

        holder.camname.setText( str.get(position).getCam_id());
       Glide.with(context).load(str.get(position).getImageUrl()).into(holder.camimage);
    }


    @Override
    public int getItemCount() {
        return str.size();
    }


}
