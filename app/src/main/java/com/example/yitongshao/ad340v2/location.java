package com.example.yitongshao.ad340v2;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.Point;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class location extends AppCompatActivity  implements OnMapReadyCallback {
    private static final int LOCATION_PERMISSION_REQUEST_CODE = 9;

    protected GoogleMap mMap;
    RequestQueue mQueue;
    ArrayList<Camera> cameras;
    Camera cam= new Camera();
    String url ="https://web6.seattle.gov/Travelers/api/Map/Data?zoomId=13&type=2";
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.location_activity);
        cameras= new ArrayList<Camera>();


        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.current_location);
        mapFragment.getMapAsync(this);

    }






    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        mMap.setOnMyLocationButtonClickListener(onMyLocationButtonClickListener);
        mMap.setOnMyLocationClickListener(onMyLocationClickListener);
        enableMyLocationIfPermitted();
        mMap.getUiSettings().setZoomControlsEnabled(true);
        mMap.setMinZoomPreference(10);
        mQueue = Volley.newRequestQueue(this);
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray features = response.getJSONArray("Features");
                            for (int i = 0; i < features.length(); i++) {
                                JSONObject obj = features.getJSONObject(i);
                                JSONArray coordinates = obj.getJSONArray("PointCoordinate");
                                double lat= coordinates.getDouble(0);
                                double lng = coordinates.getDouble(1);
                                JSONArray camera = obj.getJSONArray("Cameras");
                                for (int j = 0; j < camera.length(); j++) {
                                    String id = camera.getJSONObject(j).getString("Id");
                                    String desc = camera.getJSONObject(j).getString("Description");
                                    String url = camera.getJSONObject(j).getString("ImageUrl");
                                    String type = camera.getJSONObject(j).getString("Type");

                                    if (type.equals("sdot")) {
                                        url = "http://www.seattle.gov/trafficcams/images/" + url;
                                    } else if (type.equals("wsdot")) {
                                        url = "http://images.wsdot.wa.gov/nw/" + url;
                                    }
                                    cameras.add(new Camera(lat,lng, id, desc, url, type));
                                    LatLng latlng = new LatLng(lat,lng);
                                    MarkerOptions markerOptions = new MarkerOptions();
                                    markerOptions.position(latlng).title(desc);
                                    camUrl cam_url=new camUrl();
                                    cam_url.setcamURL(url);
                                    MarkerInfoWindowAdapter mkAdapter = new MarkerInfoWindowAdapter(location.this);
                                    mMap.setInfoWindowAdapter(mkAdapter);
                                    Marker m = mMap.addMarker(markerOptions);
                                    m.setTag(desc);
                                    m.showInfoWindow();
                                }

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
    private void enableMyLocationIfPermitted() {
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION,
                            Manifest.permission.ACCESS_FINE_LOCATION},
                    LOCATION_PERMISSION_REQUEST_CODE);
        } else if (mMap != null) {
            mMap.setMyLocationEnabled(true);
        }
    }

    private void showDefaultLocation() {
        Toast.makeText(this, "Location permission not granted, " +
                        "showing default location",
                Toast.LENGTH_SHORT).show();
        LatLng redmond = new LatLng(47.6739881, -122.121512);
        mMap.moveCamera(CameraUpdateFactory.newLatLng(redmond));
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        switch (requestCode) {
            case LOCATION_PERMISSION_REQUEST_CODE: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    enableMyLocationIfPermitted();
                } else {
                    showDefaultLocation();
                }
                return;
            }

        }
    }

    private GoogleMap.OnMyLocationButtonClickListener onMyLocationButtonClickListener =
            new GoogleMap.OnMyLocationButtonClickListener() {
                @Override
                public boolean onMyLocationButtonClick() {
                    mMap.setMinZoomPreference(10);
                    return false;
                }
            };

    private GoogleMap.OnMyLocationClickListener onMyLocationClickListener =
            new GoogleMap.OnMyLocationClickListener() {
                @Override
                public void onMyLocationClick(@NonNull Location location) {

                    mMap.setMinZoomPreference(10);

                    CircleOptions circleOptions = new CircleOptions();
                    circleOptions.center(new LatLng(location.getLatitude(),
                            location.getLongitude()));


                    mMap.addCircle(circleOptions);
                    Toast.makeText(getApplicationContext(), "My current location",
                            Toast.LENGTH_SHORT).show();
                }
            };



}





