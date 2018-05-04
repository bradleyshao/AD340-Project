package com.example.yitongshao.myad340;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    public static final String EXTRA_MESSAGE = "com.example.MyAD340.MESSAGE";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void sendMessage(View view){

        Intent intent = new Intent(this, DisplayMessageActivity.class);
        EditText editText = (EditText) findViewById(R.id.editText);
        String message = editText.getText().toString();
        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
    }
    public void showList(View view) {
        Intent intent = new Intent(this, RecyclerMainActivity.class);
        startActivity(intent);
    }
    public void toastB(View view) {
        Toast.makeText(getApplicationContext(), "Hello World1",
                Toast.LENGTH_SHORT).show();
    }
    public void toastC(View view) {
        Toast.makeText(getApplicationContext(), "Hello World2",
                Toast.LENGTH_SHORT).show();
    }
    public void toastD(View view) {
        Toast.makeText(getApplicationContext(), "Hello World3",
                Toast.LENGTH_SHORT).show();
    }


}
