package com.directions.android;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
    }
    
    public void routeInput(View view) {
    	Intent route_input = new Intent(this, route_input.class);

    	startActivity(route_input);
    	finish();
    }
}