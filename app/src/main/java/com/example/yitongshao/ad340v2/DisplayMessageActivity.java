package com.example.yitongshao.ad340v2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;

public class DisplayMessageActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_display_message);




            Intent intent = getIntent ();
            String message = intent.getStringExtra (MainActivity.EXTRA_MESSAGE);


            TextView textView = findViewById (R.id.textView);
            textView.setText (message);


    }


}
