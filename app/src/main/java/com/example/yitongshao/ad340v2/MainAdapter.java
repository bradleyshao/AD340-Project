package com.example.yitongshao.ad340v2;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class MainAdapter extends RecyclerView.Adapter <MainAdapter.ViewHolder> {
    String bigList[][];


    public MainAdapter(String[][] list){
        bigList=list;
    }
    @NonNull
    @Override
    public MainAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.list,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MainAdapter.ViewHolder holder, int position ) {
        String str="";
        for (int i = 0; i <1 ; i++) {
            for(int j=0; j<5;j++){
                str= str+System.lineSeparator()+bigList[i][j];
            }

        }
        holder.name.setText(str);
    }

    @Override
    public int getItemCount() {
        return bigList.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView name;
        public ViewHolder(View itemView) {
            super(itemView);
            name= (TextView) itemView.findViewById(R.id.full_name);
        }
    }
}
