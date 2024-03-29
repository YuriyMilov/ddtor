package gu;

import gu.client.model.Consignee;
import gu.client.model.Shipper;
import gu.client.model.Worder;
import gu.server.PMF;

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

public class del extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static String[] s4 = null;

	@SuppressWarnings("unchecked")
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		PrintWriter out = resp.getWriter(); 
	
		
		String s=req.getQueryString(); 
		if(s.equals("word"))			
			s="Worders deleted " +deleteWorder("");
		if(s.equals("ship"))			
			s="Shippers deleted " +deleteShipper("");
		if(s.equals("cons"))			
			s="Consignees deleted " +deleteConsignee("");
		
		out.println(s);
	}
 
@SuppressWarnings("unchecked")
String deleteWorder (String s){
	
	try{PersistenceManager pm = PMF.get().getPersistenceManager();
	
	
	List<Worder> rd = (List<Worder>) pm.newQuery("SELECT FROM " + Worder.class.getName() ).execute();//+ " WHERE s5==\""+req.getQueryString()+"\""
	
	s= String.valueOf(rd.size());
	
	
	
	int i = 0;
	
		
		while(i<rd.size())
				pm.deletePersistent(rd.get(i++));
	
		return s;
	} catch ( Exception e){
		s=e.toString();
	}
	return s;
}	

String deleteShipper (String s){
	
	try{PersistenceManager pm = PMF.get().getPersistenceManager();
	@SuppressWarnings("unchecked")
	List<Shipper> rd = (List<Shipper>) pm.newQuery("SELECT FROM " + Shipper.class.getName() ).execute();//+ " WHERE s5==\""+req.getQueryString()+"\""
	s= String.valueOf(rd.size());
	int i = 0;
	
		
		while(i<rd.size())
				pm.deletePersistent(rd.get(i++));
	
		return s;
	} catch ( Exception e){
		s=e.toString();
	}
	return s;
}	
@SuppressWarnings("unchecked")
String deleteConsignee (String s){
	
	try{PersistenceManager pm = PMF.get().getPersistenceManager();
	List<Consignee> rd = (List<Consignee>) pm.newQuery("SELECT FROM " + Consignee.class.getName() ).execute();//+ " WHERE s5==\""+req.getQueryString()+"\""
	s= String.valueOf(rd.size());
	int i = 0;
	
		
		while(i<rd.size())
				pm.deletePersistent(rd.get(i++));
	
		return s;
	} catch ( Exception e){
		s=e.toString();
	}
	return s;
}	
	
	@SuppressWarnings("unchecked")
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		//UserService userService = UserServiceFactory.getUserService();
		//
		//User user = userService.getCurrentUser();
				//if ( user == null) {
				//	user=new User("test@quicklydone.com",
				//		"quicklydone.com","test");
				//}
			//
				
				
		PrintWriter out = resp.getWriter();
		int t = 9999;
		String s="ok";
		try {
			String str = req.getParameter("a");
			String id = req.getParameter("id");
			
			//user=new User(id, id.substring(id.indexOf("@")+1));
			
			
			String[] words = str.split("\r\n");
			PersistenceManager pm = PMF.get().getPersistenceManager();
			
			
			
			if (words == null)
				s4 = new String[] { "r", "50.0", "-60.0", "address","2", "text" };
			else
				s4 = words;
			
				List<Mrkr4> rd = (List<Mrkr4>) pm.newQuery("SELECT FROM " + Mrkr4.class.getName()+ " WHERE s5==\""+id+"\"").execute();
				s=String.valueOf(rd.size());
				for (int i = 0; i < rd.size(); i++)
				{
					Mrkr4 mr = pm.getObjectById(Mrkr4.class, rd.get(i).getId());
					pm.deletePersistent(mr);
				}
				for (int i = 0; i < s4.length; i = i + 6) {
				Mrkr4 ns = new Mrkr4(new Date(), s4[i], s4[i + 1],
						s4[i + 2], s4[i + 3], s4[i + 4], s4[i + 5]);
				pm.makePersistent(ns);
				}
				
				/*
			
			for (int i = 0; i < s4.length; i = i + 6) {
				String query = "SELECT FROM " + Mrkr4.class.getName()
						+ " WHERE s2 == \"" + s4[i + 1] + "\" && s3 == \""
						+ s4[i + 2] + "\"";
				List<Mrkr4> rr = (List<Mrkr4>) pm.newQuery(query).execute();
				t = rr.size();
				if (rr.size() == 0) {
					Mrkr4 ns = new Mrkr4(new Date(), s4[i], s4[i + 1],
							s4[i + 2], s4[i + 3], s4[i + 4], s4[i + 5], user);
					pm.makePersistent(ns);
				} else {

					Mrkr4 ns = pm.getObjectById(Mrkr4.class, rr.get(0).getId());
					pm.deletePersistent(ns);

					ns = new Mrkr4(new Date(), s4[i], s4[i + 1], s4[i + 2],
							s4[i + 3], s4[i + 4], s4[i + 5], user);
					pm.makePersistent(ns);
				}
				
				
			}
				*/
			
			
			
		} catch (Exception eee) {
			s=eee.toString();
		}
		//resp.sendRedirect("http://map.quicklydone.com");
		 out.println(s);
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
}