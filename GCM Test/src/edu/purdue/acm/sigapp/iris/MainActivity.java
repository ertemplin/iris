package edu.purdue.acm.sigapp.iris;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.google.android.gcm.GCMRegistrar;

public class MainActivity extends Activity {

	public static String SENDER_ID = "274242247098";
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        Button registerButton = (Button) findViewById(R.id.buttonGCMRegister);
        Button deregisterButton = (Button) findViewById(R.id.buttonGCMUnregister);
        
        registerButton.setOnClickListener(new OnClickListener() {
			public void onClick(View arg0) {
				registerWithGCM();
			}
        });
        
        deregisterButton.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				unregisterWithGCM();
			}
        	
        });
    }
    
    public void registerWithGCM() {
    	GCMRegistrar.checkDevice(this);
    	GCMRegistrar.checkManifest(this);
    	final String regId = GCMRegistrar.getRegistrationId(this);
    	if(regId.equals("")) {
    		GCMRegistrar.register(this, SENDER_ID);
    	} else {
    		Log.v("GCM TAG", "Already registered");
    	}
    }
    
    public void unregisterWithGCM() {
    	GCMRegistrar.unregister(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
}
