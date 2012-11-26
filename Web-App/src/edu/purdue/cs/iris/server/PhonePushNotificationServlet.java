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
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;

public class PhonePushNotificationServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		Sender sender = new Sender("274242247098");
		
		Query allDevices = new Query("Device");
		PreparedQuery pq = datastore.prepare(allDevices);
		List<Entity> list = pq.asList(null);
		for(Entity e : list) {
			String deviceId = (String) e.getProperty("DeviceID");
			Message message = new Message.Builder().build();
			try {
				Result result = sender.send(message, deviceId, 5);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
}
