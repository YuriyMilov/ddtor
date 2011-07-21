package gu;

import gu.client.model.Consignee;
import gu.client.model.Shipper;
import gu.client.model.Worder;
import gu.server.PMF;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.URL;
import java.util.ArrayList;
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
import com.smartgwt.client.data.fields.DataSourceIntegerField;
import com.smartgwt.client.data.fields.DataSourceTextField;

import java.util.List;

public class qq extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static String[] s4 = null;
	String s = "";

	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		doPost(req,resp);

	}

	@SuppressWarnings("unchecked")
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		PrintWriter out = resp.getWriter();

		String user = (String) req.getSession().getAttribute("name");

		PersistenceManager pm = PMF.get().getPersistenceManager();
		List<Worder> woList = (List<Worder>) pm.newQuery(
				"SELECT FROM " + Worder.class.getName()).execute();
		List<Shipper> shipList = (List<Shipper>) pm.newQuery(
				"SELECT FROM " + Shipper.class.getName()).execute();
		List<Consignee> consList = (List<Consignee>) pm.newQuery(
				"SELECT FROM " + Consignee.class.getName()).execute();
		Worder ww = null;
		Shipper sh = null;
		Consignee cn = null;

		ArrayList<String[]> ar = new ArrayList<String[]>();
		int is = 0;
		int ks = shipList.size();
		int wo_int=0;
		while (is < ks) {
			sh = shipList.get(is++);
			int iw = 0;
			int kw = woList.size();
			String[] zz = null;
			while (iw < kw) {
				ww = woList.get(iw++);
				String ship_id = ww.get_ship_id();
				if (ship_id.equals(sh.get_ship_id())) {					
					wo_int++;					
					String cons_id = ww.get_cons_id();
					int ic = 0;
					int kc = consList.size();
					while (ic < kc) {
						cn = consList.get(ic++);
						if (cons_id.equals(cn.get_ConsId())) {							
							zz = new String[] {
							"r", sh.getLatitude(), sh.getLongtitude(),
									"InfoWindow for shipper " + sh.getName(),
									"g", cn.getLatitude(), cn.getLongtitude(),
									"InfoWindow for shipper " + cn.getName(),
									ww.get_wo_number(), " ",
									sh.getCity() + ", " + sh.getProv(), " ",
									cn.getCity() + ", " + cn.getProv(),
									ww.getDescription(), ww.get_pieces(),
									ww.get_type(), ww.get_desc(), " ",
									ww.get_weight_lbs(), ww.get_weight_kgs(),
									ww.getpickup_dt(), ww.get_delivery_dt() 
									
									};
						}
					}

					if (zz != null)
						ar.add(zz);
					System.out.println(wo_int);
				}
			}
		}
		String[][] sss = null;
		s = "";
		if (ar.size() != 0) {
			sss = new String[ar.size()][ar.get(0).length];
			for (int n = 0; n < ar.size(); n++) {
				sss[n] = ar.get(n);
				String ssh = ar.get(n)[20].trim();
				String sdd = ar.get(n)[21].trim();
				if (sdd.length() > 7)
					sdd = "\r\n<dd>" + "20" + sdd.substring(6, 8) + "-"
							+ sdd.substring(0, 2) + "-" + sdd.substring(3, 5)
							+ "</dd>";
				else
					sdd = "";

				if (ssh.length() > 7)
					ssh = "\r\n<shd>" + "20" + ssh.substring(6, 8) + "-"
							+ ssh.substring(0, 2) + "-" + ssh.substring(3, 5)
							+ "</shd>";
				else
					ssh = "";

				String from1 = "", from2 = "";
				from1 = ar.get(n)[10];
				if (from1.indexOf(",") > -1) {
					from2 = from1.substring(from1.indexOf(","))
							.replace(",", "");
					from1 = from1.substring(0, from1.indexOf(","));
				} else {
					from1 = "Nowhere";
					from2 = "??";
				}

				String to1 = "", to2 = "";
				to1 = ar.get(n)[12];
				if (to1.indexOf(",") > -1) {
					to2 = to1.substring(to1.indexOf(",")).replace(",", "");
					to1 = to1.substring(0, to1.indexOf(","));
				} else {
					to1 = "Nowhere";
					to2 = "??";
				}				
				String prefix = ar.get(n)[8].substring(0, 3);
				if(user.equals(prefix))
				s = s + "\r\n<country>" + "\r\n<shiplat>" + ar.get(n)[1]
						+ "</shiplat>" + "\r\n<shiplng>" + ar.get(n)[2]
						+ "</shiplng>" + "\r\n<conslat>" + ar.get(n)[5]
						+ "</conslat>" + "\r\n<conslng>" + ar.get(n)[6]
						+ "</conslng>" + "\r\n<prefix>" + prefix + "</prefix>"
						//+ "\r\n<nord>" + wo_int + "</nord>"
						+ "\r\n<woNumber>" + ar.get(n)[8].substring(4)
						+ "</woNumber>" + "\r\n<from1>" + from1 + "</from1>"
						+ "\r\n<from2>" + from2 + "</from2>" + "\r\n<to1>"
						+ to1 + "</to1>" + "\r\n<to2>" + to2 + "</to2>" + sdd
						+ ssh + "\r\n<equipment>" + ar.get(n)[13]
						+ "</equipment>" + "\r\n<pieces>" + ar.get(n)[14]
						+ "</pieces>" + "\r\n<type>" + ar.get(n)[15]
						+ "</type>" + "\r\n<description>"+ ar.get(n)[16]
						+ "</description>" + "\r\n<lbs>" + ar.get(n)[18]
						+ "</lbs>" + "\r\n<kgs>" + ar.get(n)[19] + "</kgs>"
						+ "\r\n</country>\r\n";
			}
		}
		s = "<List>" + s + "</List>";
		s = s.replaceAll("\\s+", " ");
		out.println(s);
		System.out.println(s);
	}
}