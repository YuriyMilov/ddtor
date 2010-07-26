package qd.gmap;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.URL;
import java.util.Date;
import javax.jdo.PersistenceManager;

import javax.jdo.Query;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import com.google.gwt.maps.client.InfoWindowContent;
import com.google.gwt.maps.client.geocode.Geocoder;
import com.google.gwt.maps.client.geocode.LatLngCallback;

import com.google.gwt.maps.client.geom.LatLng;
import com.google.gwt.maps.client.overlay.Icon;
import com.google.gwt.maps.client.overlay.Marker;
import com.google.gwt.maps.client.overlay.MarkerOptions;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.RootPanel;

import java.util.List;

public class a4 extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static String[] s4 = null;
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		PrintWriter out = resp.getWriter();
		
		
		try{
		String str =req.getQueryString();
		String[] words = str.split("/");

		if(words==null)
			s4 = new String[]{"","50.0","-60.0","qqqq"};
		else
			s4 = words;
		
		for (int i=0; i < s4.length; i++)
			out.println (s4[i]);
		}		
		catch(Exception eee){}
		
		PersistenceManager pm = PMF.get().getPersistenceManager();
		Mrkr4 ns = new Mrkr4(new Date(),s4[0],s4[1],s4[2],s4[3]);
		pm.makePersistent(ns);
		
		//resp.sendRedirect("http://map.quicklydone.com");
		out.println("OK<br><br><a href=http://map.quicklydone.com>http://map.quicklydone.com</a>");


	}

	public static String rfu(String url) {
		StringBuffer s = new StringBuffer();
		try {
			URL u = new URL(url);
			InputStream in = u.openConnection().getInputStream();
			for (int ch = in.read(); ch > 0; ch = in.read()) {
				s.append((char) ch);
			}
			in.close();
		} catch (IOException e) {
			return e.toString();
		}
		return s.toString();
	}

	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		PrintWriter out = resp.getWriter();
		
		
		try{
		String str =req.getParameter("a");
		String[] words = str.split("\r\n");

		if(words==null)
			s4 = new String[]{"","50.0","-60.0","qqqq"};
		else
			s4 = words;
		
		//out.println("<a href=http://map.quicklydone.com>http://map.quicklydone.com</a><br><br>");
		
		for (int i=0; i < s4.length; i++)
				out.println (s4[i]);
		}
		catch(Exception eee){}
		//out.println("<br><a href=http://map.quicklydone.com>http://map.quicklydone.com</a><br><br>");

		PersistenceManager pm = PMF.get().getPersistenceManager();
		Mrkr4 ns = new Mrkr4(new Date(),s4[0],s4[1],s4[2],s4[3]);
		pm.makePersistent(ns);
		
		resp.sendRedirect("http://map.quicklydone.com");
	}

}