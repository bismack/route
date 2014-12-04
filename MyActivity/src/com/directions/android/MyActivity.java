package com.directions.android;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import android.location.Location;

import com.directions.route.Route;
import com.directions.route.Routing;
import com.directions.route.RoutingListener;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

public class MyActivity extends FragmentActivity implements RoutingListener
{
    protected GoogleMap map;
    protected LatLng start;
    protected LatLng end;
    
	private String loc0;
	private String loc1;
	private String des0;
	private String des1;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        SupportMapFragment fm = (SupportMapFragment)  getSupportFragmentManager().findFragmentById(R.id.map);
        map = fm.getMap();
        
        setFields();

        CameraUpdate center = CameraUpdateFactory.newLatLng(new LatLng(Double.parseDouble(loc0),Double.parseDouble(loc1)));
        CameraUpdate zoom = CameraUpdateFactory.zoomTo(15);

        map.moveCamera(center);
        map.animateCamera(zoom);       
        
        start = new LatLng(Double.parseDouble(loc0),Double.parseDouble(loc1));
        end = new LatLng(Double.parseDouble(des0),Double.parseDouble(des1));

        Routing routing = new Routing(Routing.TravelMode.WALKING);
        routing.registerListener(this);
        routing.execute(start, end);
       
    }
    
    public void setFields() {
    	Intent intent = getIntent();
    	loc0 = getIntent().getStringExtra("location0");
    	loc1 = getIntent().getStringExtra("location1");
    	des0 = getIntent().getStringExtra("destination0");
    	des1 = getIntent().getStringExtra("destination1");

    }
    

    public float getDistance(double lat1, double lon1, double lat2, double lon2) {
        android.location.Location homeLocation = new android.location.Location("");
        homeLocation .setLatitude(lat1);
        homeLocation .setLongitude(lon1);

        android.location.Location targetLocation = new android.location.Location("");
        targetLocation .setLatitude(lat2);
        targetLocation .setLongitude(lon2);

        float distanceInMeters =  targetLocation.distanceTo(homeLocation);
        return distanceInMeters;
    }
   

    @Override
    public void onRoutingFailure() {
      // The Routing request failed
    }

    @Override
    public void onRoutingStart() {
      // The Routing Request starts
    }
    

    @Override
    public void onRoutingSuccess(PolylineOptions mPolyOptions, Route route) {
      PolylineOptions polyoptions = new PolylineOptions();
      polyoptions.color(Color.BLUE);
      polyoptions.width(10);
      polyoptions.addAll(mPolyOptions.getPoints());
      map.addPolyline(polyoptions);

      // Start marker
      MarkerOptions options = new MarkerOptions();
      options.position(start);
      options.icon(BitmapDescriptorFactory.fromResource(R.drawable.start_blue));
      map.addMarker(options);

      // End marker
      options = new MarkerOptions();
      options.position(end);
      options.icon(BitmapDescriptorFactory.fromResource(R.drawable.end_green));  
      map.addMarker(options);
    }
}
