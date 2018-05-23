package com.example.yitongshao.ad340v2;

import com.google.android.gms.maps.model.LatLng;

public class Camera {
    private LatLng latLng;
    private String cam_id;
    private  String description;
    private String imageUrl;
    private String type;

    public Camera(){}

    public Camera(LatLng latLng, String cam_id,String description,String imageUrl,String type){
        this.latLng=latLng;
        this.cam_id=cam_id;
        this.description=description;
        this.imageUrl=imageUrl;
        this.type=type;

    }

    public LatLng getLatLng() {
        return latLng;
    }

    public void setLatLng(LatLng latLng) {
        this.latLng = latLng;
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
