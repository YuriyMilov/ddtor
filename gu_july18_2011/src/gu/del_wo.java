package gu;

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

public class del_wo extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static String[] s4 = null;

	@SuppressWarnings("unchecked")
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		PrintWriter out = resp.getWriter(); 
		String s="";
		try{
		PersistenceManager pm = PMF.get().getPersistenceManager();
		List<Worder> rd = (List<Worder>) pm.newQuery("SELECT FROM " + Worder.class.getName()+ " WHERE wo_number==\""+req.getParameter("wo_number")  +"\"").execute();
		//s=String.valueOf(rd.size());
		//for (int i = 0; i < rd.size(); i++)
		//{
		//Worder mr = pm.getObjectById(Worder.class, rd.get(0).getId());
		
		Worder mr = rd.get(0);
		pm.deletePersistent(mr);
		
		s=req.getParameter("wo_number")+" has been successfully deleted";
		
		//}
		}catch(Exception e){s=e.toString();}
		out.println(s);
	}
 
}