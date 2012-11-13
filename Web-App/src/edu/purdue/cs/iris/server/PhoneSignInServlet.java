package edu.purdue.cs.iris.server;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
		}
	}
}
