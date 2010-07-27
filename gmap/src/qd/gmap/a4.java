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

		try {
			String str = req.getQueryString();
			String[] words = str.split("/");

			if (words == null)
				s4 = new String[] { "r", "50.0", "-60.0", "qqqq", "2", "2" };
			else
				s4 = words;

			for (int i = 0; i < s4.length; i++)
				out.println(s4[i]);
		} catch (Exception eee) {
		}

		PersistenceManager pm = PMF.get().getPersistenceManager();
		Mrkr4 ns = new Mrkr4(new Date(), s4[0], s4[1], s4[2], s4[3], s4[4],
				s4[5]);
		pm.makePersistent(ns);

		// resp.sendRedirect("http://map.quicklydone.com");
		out
				.println("OK<br><br><a href=http://map.quicklydone.com>http://map.quicklydone.com</a>");

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

	@SuppressWarnings("unchecked")
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		PrintWriter out = resp.getWriter();
		int t = 9999;
		try {

			String str = req.getParameter("a");
			String[] words = str.split("\r\n");

			PersistenceManager pm = PMF.get().getPersistenceManager();

			// Mrkr4 mk = null;

			/*
			 * while (i < k) { mk = rr.get(i++); s = mk.get_s1(); ss[n++] =
			 * mk.get_s2(); ss[n++] = mk.get_s3(); ss[n++] = mk.get_s4() +
			 * " <br>Location_ID: " + mk.get_s6();
			 * 
			 * }
			 */

			if (words == null)
				s4 = new String[] { "", "50.0", "-60.0", "qqqq", "2", "2" };
			else
				s4 = words;

			for (int i = 0; i < s4.length; i = i + 6) {
				// out.println(s4[i]);
				String query = "SELECT FROM " + Mrkr4.class.getName()
						+ " WHERE s2 == \"" + s4[i + 1] + "\" && s3 == \""
						+ s4[i + 2] + "\"";
				
				List<Mrkr4> rr = (List<Mrkr4>) pm.newQuery(query).execute();

				t = rr.size();
				if (rr.size() == 0) {
					Mrkr4 ns = new Mrkr4(new Date(), s4[i], s4[i + 1],
							s4[i + 2], s4[i + 3], s4[i + 4], s4[i + 5]);
					pm.makePersistent(ns);
				} else {
					
					Mrkr4 ns = pm.getObjectById(Mrkr4.class, rr.get(0).getId());
					pm.deletePersistent(ns);
					
					ns = new Mrkr4(new Date(), s4[i], s4[i + 1],
							s4[i + 2], s4[i + 3], s4[i + 4], s4[i + 5]);
					pm.makePersistent(ns);
				}
			}
		} catch (Exception eee) {
			out.println(eee.toString());
		}
		// out.println("<br><a href=http://map.quicklydone.com>http://map.quicklydone.com</a><br><br>");
		 resp.sendRedirect("http://map.quicklydone.com");

		//out.println(t);

	}

}