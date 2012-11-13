package edu.purdue.cs.iris.server;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;

public class PhoneSignInServlet extends HttpServlet {

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
		resp.setContentType("text/plain");
		PrintWriter writer = null;
		try {
			writer = resp.getWriter();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Map<String, String[]> params = req.getParameterMap();
		Set<String> keys = params.keySet();
		if(keys.size() == 0 || !keys.contains("id")) {
			writer.write("Send registration id as id=(id)\n");
			return;
		}
		
		// We want to save the id in the datastore
		Key datastoreKey = KeyFactory.createKey("Devices", 1);
		String gcmDeviceID = params.get("id")[0];
		Entity device = new Entity("Device", datastoreKey);
		device.setProperty("DeviceID", gcmDeviceID);
		
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		datastore.put(device);
	}
}
