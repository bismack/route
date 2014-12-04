package com.directions.android;

import java.io.IOException;
import java.util.List;

import com.google.android.gms.maps.model.LatLng;
import com.google.android.maps.GeoPoint;

import android.app.Activity;
import android.content.Intent;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

public class route_input extends Activity {
	
	private String loc0;
	private String loc1;
	private String des0;
	private String des1;
	private String passNum = "1"; 
	
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.route_input);

        Button mButton1 = (Button)findViewById(R.id.button1);
        Button mButton2 = (Button)findViewById(R.id.button2);
        Button mButton3 = (Button)findViewById(R.id.button3);
        final EditText mEdit1   = (EditText)findViewById(R.id.editText1);
        final EditText mEdit2   = (EditText)findViewById(R.id.EditText01);
        final EditText mEdit3   = (EditText)findViewById(R.id.EditText02);
        final CheckBox checkBox = (CheckBox) findViewById(R.id.checkBox1);
        final TextView tView = (TextView) findViewById(R.id.TextView02);
        
        checkBox.setOnClickListener(new View.OnClickListener(){
        	@Override
		    public void onClick(View v){
		        if (checkBox.isChecked()) {
		            mEdit3.setVisibility(View.INVISIBLE);
		            tView.setVisibility(View.INVISIBLE);
		            mEdit3.setText("1");
		        }else{
		            mEdit3.setVisibility(View.VISIBLE);
		            tView.setVisibility(View.VISIBLE);
		        }
        	}
        });

        mButton1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
					if ((mEdit1.getText().toString().length() < 3) || (mEdit2.getText().toString().length() < 3)){
						tView.setText("try again");
					}else{
			        Double[] location = getLocationFromAddress(mEdit1.getText().toString());
			        loc0 = location[0].toString();
			        loc1 = location[1].toString();
			        Double[] destination = getLocationFromAddress(mEdit2.getText().toString());
			        des0 = destination[0].toString();
			        des1 = destination[1].toString();
			        passNum = mEdit3.getText().toString();
			        tView.setText(loc0+" "+loc1+" "+des0+" "+des1);		
			        routeVerification();
				}
			}
		});
        
        mButton3.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
		        String switch1 = new String(mEdit1.getText().toString());
		        String switch2 = new String(mEdit2.getText().toString());
		        mEdit1.setText(switch2);
		        mEdit2.setText(switch1);
			}
		});
        
        mButton2.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
		        Double[] location = getLocation();
		        loc0 = location[0].toString();
		        loc1 = location[1].toString();
		        mEdit1.setText(location[0].toString()+" "+location[1].toString());
			}
		});
    }
    
    private void routeVerification() {
    	Intent route_verification = new Intent(this, route_verification.class);
    	
    	route_verification.putExtra("location0", loc0);
    	route_verification.putExtra("location1", loc1);
    	route_verification.putExtra("destination0", des0);
    	route_verification.putExtra("destination1", des1);
    	route_verification.putExtra("passNum", passNum);


    	startActivity(route_verification);
    }
    
    public Double[] getLocation()
    {
     LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
     Criteria criteria = new Criteria();
     String bestProvider = locationManager.getBestProvider(criteria, false);
     Location location = locationManager.getLastKnownLocation(bestProvider);
     Double lat,lon;
     try {
	   Double[] p = {location.getLatitude(),location.getLongitude()};
	   return p;
     }
     catch (NullPointerException e){
         e.printStackTrace();
       return null;
     }
    }
    
    public Double[] getLocationFromAddress(String strAddress){

    	Geocoder coder = new Geocoder(this);
    	List<Address> address = null;
    	
    	   try {
				address = coder.getFromLocationName(strAddress,5);
				if (address == null) {
	    	        return null;
	    	    	}
			} catch (IOException e) {
				e.printStackTrace();
			}
    	
    	    Address location = address.get(0);

    	    Double[] p = {location.getLatitude(),location.getLongitude()};
    	    return p;
    }
}
