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
    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1;

    protected GoogleMap mMap;
    RequestQueue mQueue;


    private Boolean locationPermissions = false;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.location_activity);
        getLocationPermission();


    }
public void setUpMap(){

    SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
            .findFragmentById(R.id.current_location);
    mapFragment.getMapAsync(this);

}





    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setOnMyLocationButtonClickListener(onMyLocationButtonClickListener);
        mMap.setOnMyLocationClickListener(onMyLocationClickListener);
        if (locationPermissions) {

            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                    ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                return;
            }
            mMap.setMyLocationEnabled(true);
        }

        mQueue = Volley.newRequestQueue(this);
        String url = "https://web6.seattle.gov/Travelers/api/Map/Data?zoomId=17&type=2";
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray = response.getJSONArray("Features");

                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject feature = jsonArray.getJSONObject(i);

                                JSONArray coordinates = feature.getJSONArray("PointCoordinate");
                                double latitude = coordinates.getDouble(0);
                                double longitude = coordinates.getDouble(1);

                                JSONArray cameras = feature.getJSONArray("Cameras");
                                JSONObject camera = cameras.getJSONObject(0);
                                String type = camera.getString("Type");
                                String imageURL = camera.getString("ImageUrl");
                                if (type.equals("sdot")) {
                                    imageURL = "http://www.seattle.gov/trafficcams/images/" + imageURL;
                                } else {
                                    imageURL = "http://images.wsdot.wa.gov/nw/" + imageURL;
                                }
                                String camDescription = camera.getString("Description");

                                LatLng camCoord = new LatLng(latitude, longitude);
                                MarkerOptions markerOptions = new MarkerOptions();

                                markerOptions.position(camCoord).title(camDescription);

                                camUrl camurl = new camUrl ();
                                camurl.setcamURL (imageURL);

                                MarkerInfoWindowAdapter customInfoWindow = new MarkerInfoWindowAdapter (location.this);
                                mMap.setInfoWindowAdapter(customInfoWindow);

                                //add map markers
                                Marker m = mMap.addMarker(markerOptions);
                                m.setTag(camurl);
                                m.showInfoWindow();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
                ;
            }
        });
        mQueue.add(request);
    }




    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        locationPermissions = false;
        switch (requestCode) {
            case LOCATION_PERMISSION_REQUEST_CODE: {
                if (grantResults.length > 0) {
                    for (int i = 0; i < grantResults.length; i++) {
                        if (grantResults[i] != PackageManager.PERMISSION_GRANTED) {
                            locationPermissions = false;
                            return;
                        }
                    }
                    locationPermissions= true;
                    setUpMap();
                }
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
                    LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());

                    mMap.addCircle(circleOptions);
                    Toast.makeText(getApplicationContext(), "My current location",
                            Toast.LENGTH_SHORT).show();
                    mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
                }
            };

    private void getLocationPermission() {
        String[] permissions = {Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION};
        if (ContextCompat.checkSelfPermission(this.getApplicationContext(),
                Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            if (ContextCompat.checkSelfPermission(this.getApplicationContext(),
                    Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                locationPermissions= true;
                setUpMap();
            } else {
                ActivityCompat.requestPermissions(this, permissions, LOCATION_PERMISSION_REQUEST_CODE);
            }
        } else {
            ActivityCompat.requestPermissions(this, permissions, LOCATION_PERMISSION_REQUEST_CODE);
        }
    }






}





