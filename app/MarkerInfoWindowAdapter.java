package com.example.yitongshao.ad340v2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Callback;

public class MarkerInfoWindowAdapter implements GoogleMap.InfoWindowAdapter {

    private Context context;
    public MarkerInfoWindowAdapter(Context context) {
            this.context = context.getApplicationContext();
        }

    @Override
    public View getInfoWindow(Marker marker) {
        return null;
    }

    @Override
    public View getInfoContents(Marker marker) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v =  inflater.inflate(R.layout.location_info, null);

        LatLng latLng = marker.getPosition();
        ImageView cam = (ImageView) v.findViewById(R.id.cam_image);
        TextView desc = (TextView) v.findViewById(R.id.info);
        desc.setText(marker.getTitle());
        camUrl camurl = (camUrl)marker.getTag();
        String url= camurl.getcamURL();
        Picasso.with(v.getContext()).load(url).error(R.mipmap.ic_launcher).resize(450, 330).into(cam, new MarkerCallback(marker));

        return v;
    }
    static class MarkerCallback implements Callback {
        Marker marker = null;

        MarkerCallback(Marker marker)
        {
            this.marker = marker;
        }

        @Override
        public void onError() {}

        @Override
        public void onSuccess()
        {
            if (marker == null)
            {
                return;
            }

            if (!marker.isInfoWindowShown())
            {
                return;
            }

            marker.hideInfoWindow();
            marker.showInfoWindow();
        }
    }
}
