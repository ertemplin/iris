package edu.purdue.acm.sigapp.iris;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gcm.GCMBaseIntentService;

public class GCMIntentService extends GCMBaseIntentService {
	
	public GCMIntentService() {
		super(MainActivity.SENDER_ID);
	}

	@Override
	protected void onError(Context arg0, String arg1) {
		Log.e("GCM TAG", arg1);
	}

	@Override
	protected void onMessage(final Context arg0, final Intent arg1) {
		Log.v("GCM TAG", "Recieved message: " + arg1.getStringExtra("Key Test"));
				
		Handler h = new Handler(Looper.getMainLooper());
		h.post(new Runnable(){
			public void run() {
				Toast.makeText(arg0, "Recieved message: " + arg1.getStringExtra("Key Test"), Toast.LENGTH_SHORT).show();			}
		});
	}

	@Override
	protected void onRegistered(Context context, String regId) {
		// Send regId to server
		NotificationManager nm = (NotificationManager) context.getSystemService("notification");
		Notification n = new Notification();
		n.tickerText = "Registered: " + regId;
		nm.notify(5, n);
		Log.v("GCM TAG", "Registered: " + regId);
		try {
			URL toSend = new URL("http://pod2-3.cs.purdue.edu:8888/phoneSignIn?id=" + regId);
			try {
				InputStream is = toSend.openStream();
				while(is.read() != -1);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	protected void onUnregistered(Context context, String regId) {
		// TODO Auto-generated method stub

	}

}
