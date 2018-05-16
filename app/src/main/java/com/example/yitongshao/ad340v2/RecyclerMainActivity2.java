package com.example.yitongshao.ad340v2;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.LogPrinter;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class RecyclerMainActivity2 extends AppCompatActivity {
    RequestQueue mQueue;
    Context context;
    RecyclerView myRecyclerView;
    LinearLayoutManager myLayoutManager;
    CameraAdapter myAdapter;
    ArrayList<Camera> str;
    Camera cam= new Camera();
    String url ="https://web6.seattle.gov/Travelers/api/Map/Data?zoomId=13&type=2";

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cam_recycler);
        context = getApplicationContext();
        str= new ArrayList<Camera>();
        myRecyclerView = findViewById(R.id.recycler_cam);
        myLayoutManager = new LinearLayoutManager(context);
        myRecyclerView.setLayoutManager(myLayoutManager);
        myRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        myAdapter= new CameraAdapter(context,str);
        myRecyclerView.setAdapter(myAdapter);
        mQueue = Volley.newRequestQueue(this);
        jsonParse();

        }
    public void jsonParse(){
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray features = response.getJSONArray("Features");
                    for(int i=0;i<features.length();i++) {
                        JSONObject obj= features.getJSONObject(i);
                        JSONArray camera = obj.getJSONArray("Cameras");
                        for(int j=0;j<camera.length();j++){
                            String id = camera.getJSONObject(j).getString("Id");
                            String desc = camera.getJSONObject(j).getString("Description");
                            String url = camera.getJSONObject(j).getString("ImageUrl");
                            String type = camera.getJSONObject(j).getString("Type");

                            if (type.equals("sdot")){
                                url="http://www.seattle.gov/trafficcams/images/"+url;
                            }else if(type.equals("wsdot")){
                                url="http://images.wsdot.wa.gov/nw/"+url;
                            }

                            cam.setCam_id(id);
                            cam.setDescription(desc);
                            cam.setImageUrl(url);
                            cam.setType(type);
                            str.add(cam);
                            Log.d("my tag",id);

                        }
                        //myAdapter= new CameraAdapter(getApplicationContext(),str);
                        myRecyclerView.setAdapter(myAdapter);
                       // myAdapter.notifyDataSetChanged();

                    }



                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        mQueue.add(request);
    }

    }
