package com.example.yitongshao.ad340v2;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class MainAdapter extends RecyclerView.Adapter <MainAdapter.ViewHolder> {
    private static final String TAG = "MainAdapter";
    private  Context acontext;
    private ArrayList<String> amovie= new ArrayList<> ();
    private ArrayList<String> ayear=new ArrayList<> ();
    private ArrayList<String> adName=new ArrayList<> ();
    private ArrayList<String> asummary=new ArrayList<> ();
    private ArrayList<String>   amUrl= new ArrayList<> ();


    public MainAdapter(Context context,ArrayList<String>movie, ArrayList<String>year,ArrayList<String>dName,ArrayList<String>summary, ArrayList<String>mUrl){
        this.acontext=context;
        this.amovie=movie;
        this.ayear=year;
        this.adName=dName;
        this.asummary=summary;
        this.amUrl=mUrl;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.list,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MainAdapter.ViewHolder holder, final int position ) {
        holder.name.setText(amovie.get(position));
        holder.parentLayout.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                Intent intents=new Intent (acontext,movie_activity.class);
                intents.putExtra ("Movie Name",amovie.get(position));
                intents.putExtra("Movie Url",amUrl.get(position));
                intents.putExtra ("Movie Year",ayear.get (position));
                intents.putExtra ("Movie Director",adName.get (position));
                intents.putExtra ("Movie Summary",asummary.get (position));
                acontext.startActivity (intents);
            }
        });


    }

    @Override
    public int getItemCount() {
        return amovie.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView name;
        RelativeLayout parentLayout;
        public ViewHolder(View itemView) {
            super(itemView);
            name= (TextView) itemView.findViewById(R.id.full_name);
            parentLayout = itemView.findViewById (R.id.parent_layout);

        }
    }
}
