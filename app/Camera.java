package com.example.yitongshao.ad340v2;

import com.google.android.gms.maps.model.LatLng;

public class Camera {
    private double lat;
    private double lng;
    private String cam_id;
    private  String description;
    private String imageUrl;
    private String type;

    public Camera(){}

    public Camera(double lat,double lng, String cam_id,String description,String imageUrl,String type){
        this.lat=lat;
        this.lng=lng;
        this.cam_id=cam_id;
        this.description=description;
        this.imageUrl=imageUrl;
        this.type=type;

    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    public String getCam_id() {
        return cam_id;
    }

    public String getDescription() {
        return description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getType() {
        return type;
    }

    public void setCam_id(String cam_id) {
        this.cam_id = cam_id;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public void setType(String type) {
        this.type = type;
    }
}
