package edu.purdue.cs.iris.server;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.android.gcm.server.Message;
import com.google.android.gcm.server.MulticastResult;
import com.google.android.gcm.server.Result;
import com.google.android.gcm.server.Sender;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.FetchOptions;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;

public class PhonePushNotificationServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		Sender sender = new Sender("AIzaSyA60cBwRw0eVB63s06KebBvI2MhjZaWO0I");
		
		Query allDevices = new Query("Device");
		//PreparedQuery pq = datastore.prepare(allDevices);
		//List<Entity> list = pq.asList(null);
		
		// Apparently asList() requires a fetchoptions thingy to limit the number of results.
		// The API recommends that we use an iterator if we want to work on larger datasets
		List<Entity> list = datastore.prepare(allDevices).asList(FetchOptions.Builder.withLimit(10));
		
		for(Entity e : list) {
			String deviceId = (String) e.getProperty("DeviceID");
			Message message = new Message.Builder().addData("Key Test", Double.toString(Math.random())).build();
			try {
				Result result = sender.send(message, deviceId, 5);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}
}
