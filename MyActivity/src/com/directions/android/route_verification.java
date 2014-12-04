package com.directions.android;

import android.app.Activity;
import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import android.location.Location;

import com.google.android.gms.maps.model.PolylineOptions;

public class route_verification extends Activity {
	
	String location0;
	String location1;
	String destination0;
	String destination1;
	String passNum; 
		
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.route_verification);
        
       Button mButton1 = (Button)findViewById(R.id.button1);
       Button mButton2 = (Button)findViewById(R.id.button2);
       final TextView tView = (TextView) findViewById(R.id.textView1);
       
	    passNum = getIntent().getStringExtra("passNum");
       
		location0 = getIntent().getStringExtra("location0");
	    location1 = getIntent().getStringExtra("location1");
	    destination0 = getIntent().getStringExtra("destination0");
	    destination1 = getIntent().getStringExtra("destination1");

        
       mButton1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				mapView();
			}
		});
       
       mButton2.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {

	            tView.setText(String.valueOf(dis()));
				//carpool();
			}
		}); 
		
    }
    
    private Double dis(){
        Location startLoc = new Location("point A");
        startLoc.setLatitude(Double.parseDouble(location0));
        startLoc.setLongitude(Double.parseDouble(location1));

        Location endLoc = new Location("point B");
        endLoc.setLatitude(Double.parseDouble(destination0));
        endLoc.setLongitude(Double.parseDouble(destination1));

        double distance =  endLoc.distanceTo(startLoc);
        return distance;
    }
    
    private void mapView() {
    	Intent MyActivity = new Intent(this, MyActivity.class);
    	
    	MyActivity.putExtra("location0", location0);
    	MyActivity.putExtra("location1", location1);
    	MyActivity.putExtra("destination0", destination0);
    	MyActivity.putExtra("destination1", destination1);

    	startActivity(MyActivity);
    }
    
    /*
    private void carpool() {
    	Intent carpool = new Intent(this, carpoolController.class);
    	
    	route_verification.putExtra("location", location);
    	route_verification.putExtra("destination", destination);
    	route_verification.putExtra("passNum", passNum);


    	startActivity(route_verification);
    }*/
}