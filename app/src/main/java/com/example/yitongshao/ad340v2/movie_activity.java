package com.example.yitongshao.ad340v2;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class movie_activity extends AppCompatActivity {
    private static  final String TAG = "movie activity";
    protected void onCreate(@Nullable Bundle saveInstanceState){
        super.onCreate (saveInstanceState);
        setContentView (R.layout.movie_gallery);
        getIncomingIntent ();
    }
    private void getIncomingIntent(){
        if(getIntent().hasExtra ("Movie Url")&&getIntent().hasExtra ("Movie Name")){
            Log.d (TAG,"getIncomingIntent: found intent extras.");
            String image_url=getIntent ().getStringExtra("Movie Url");
            String movie_name= getIntent ().getStringExtra ("Movie Name");
            String dir_name= getIntent ().getStringExtra ("Movie Director");
            String movie_year= getIntent ().getStringExtra ("Movie Year ");
            String movie_summary= getIntent ().getStringExtra ("Movie Summary");
            setImage (image_url,movie_name,movie_year,dir_name,movie_summary);
        }
    }
    private void setImage(String image_url, String movie_name, String year,String dName, String summ){
        TextView name=findViewById (R.id.movie_name);
        name.setText (movie_name);
        ImageView image=findViewById (R.id.movie_image);
        Glide.with(this).asBitmap ().load(image_url).into(image);
        TextView Myear=findViewById (R.id.movie_year);
        Myear.setText (year);
        TextView MdName=findViewById (R.id.movie_dir);
        MdName.setText (dName);
        TextView Msum=findViewById (R.id.movie_summary);
        Msum.setText (summ);
    }
}
