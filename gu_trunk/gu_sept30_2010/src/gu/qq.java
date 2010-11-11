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
	String s = rfu("http://localhost:8888/ds/test_data/world.data.xml");

	@SuppressWarnings("unchecked")
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		PrintWriter out = resp.getWriter();

		PersistenceManager pm = PMF.get().getPersistenceManager();
		@SuppressWarnings("unchecked")
		List<Worder> woList = (List<Worder>) pm.newQuery(
				"SELECT FROM " + Worder.class.getName()).execute();
		@SuppressWarnings("unchecked")
		List<Shipper> shipList = (List<Shipper>) pm.newQuery(
				"SELECT FROM " + Shipper.class.getName()).execute();
		@SuppressWarnings("unchecked")
		List<Consignee> consList = (List<Consignee>) pm.newQuery(
				"SELECT FROM " + Consignee.class.getName()).execute();
		Worder ww = null;
		Shipper sh = null;
		Consignee cn = null;

		// ////////////////////////////////////////////////////////

		ArrayList<String[]> ar = new ArrayList<String[]>();
		int is = 0;
		int ks = shipList.size();
		while (is < ks) {
			sh = shipList.get(is++);

			// ////////////////////////////////////////////////////////

			int iw = 0;
			int kw = woList.size();
			String[] zz = null;
			while (iw < kw) {

				ww = woList.get(iw++);
				String ship_id = ww.get_ship_id();
				if (ship_id.equals(sh.get_ship_id())) {
					String cons_id = ww.get_cons_id();

					// ////////////////////

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
									ww.getpickup_dt(), ww.get_delivery_dt() };

						}
					}

					// //////////////////

					// ar.add(new String[] { "r", sh.getLatitude(),
					// sh.getLongtitude(),"numbers of WOs",
					// "----WO----->",ww.get_customer_name(),
					// "8/25/10 2:00 p.m.", "8/26/10 10:00 a.m." });

				}

			}

			// //////////////////////////////////////////////////
			if (zz != null)
				ar.add(zz);
		}
		String[][] sss = null;
		s = "";
		if (ar.size() != 0) {

			sss = new String[ar.size()][ar.get(0).length];
			for (int n = 0; n < ar.size(); n++) {
				sss[n] = ar.get(n);

				String ssh = ar.get(n)[20].trim();
				String sdd = ar.get(n)[21].trim();
				// 09/16/10
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

				s = s + "\r\n<country>" + "\r\n<shiplat>" + ar.get(n)[1]
						+ "</shiplat>" + "\r\n<shiplng>" + ar.get(n)[2]
						+ "</shiplng>" + "\r\n<conslat>" + ar.get(n)[5]
						+ "</conslat>" + "\r\n<conslng>" + ar.get(n)[6]
						+ "</conslng>" + "\r\n<prefix>"
						+ ar.get(n)[8].substring(0, 3) + "</prefix>"
						+ "\r\n<woNumber>" + ar.get(n)[8].substring(4)
						+ "</woNumber>" + "\r\n<from1>" + from1 + "</from1>"
						+ "\r\n<from2>" + from2 + "</from2>" + "\r\n<to1>"
						+ to1 + "</to1>" + "\r\n<to2>" + to2 + "</to2>" + sdd
						+ ssh + "\r\n<equipment>" + ar.get(n)[13]
						+ "</equipment>" + "\r\n<pieces>" + ar.get(n)[14]
						+ "</pieces>" + "\r\n<type>" + ar.get(n)[15]
						+ "</type>" + "\r\n<description>" + ar.get(n)[16]
						+ "</description>" + "\r\n<lbs>" + ar.get(n)[18]
						+ "</lbs>" + "\r\n<kgs>" + ar.get(n)[19] + "</kgs>"
						+ "\r\n</country>\r\n";
			}
		}
		s = "<List>" + s + "</List>";

		s = s.replaceAll("\\s+", " ");
		out.println(s);
		// equipment 13 (16)
		// pieces 14
		// type 15
		// description 17
		// lbs 18

	}

	@SuppressWarnings("unchecked")
	String deleteWorder(String s) {

		try {
			PersistenceManager pm = PMF.get().getPersistenceManager();

			List<Worder> rd = (List<Worder>) pm.newQuery(
					"SELECT FROM " + Worder.class.getName()).execute();// +
																		// " WHERE s5==\""+req.getQueryString()+"\""

			s = String.valueOf(rd.size());

			int i = 0;

			while (i < rd.size())
				pm.deletePersistent(rd.get(i++));

			return s;
		} catch (Exception e) {
			s = e.toString();
		}
		return s;
	}

	String deleteShipper(String s) {

		try {
			PersistenceManager pm = PMF.get().getPersistenceManager();
			@SuppressWarnings("unchecked")
			List<Shipper> rd = (List<Shipper>) pm.newQuery(
					"SELECT FROM " + Shipper.class.getName()).execute();// +
																		// " WHERE s5==\""+req.getQueryString()+"\""
			s = String.valueOf(rd.size());
			int i = 0;

			while (i < rd.size())
				pm.deletePersistent(rd.get(i++));

			return s;
		} catch (Exception e) {
			s = e.toString();
		}
		return s;
	}

	@SuppressWarnings("unchecked")
	String deleteConsignee(String s) {

		try {
			PersistenceManager pm = PMF.get().getPersistenceManager();
			List<Consignee> rd = (List<Consignee>) pm.newQuery(
					"SELECT FROM " + Consignee.class.getName()).execute();// +
																			// " WHERE s5==\""+req.getQueryString()+"\""
			s = String.valueOf(rd.size());
			int i = 0;

			while (i < rd.size())
				pm.deletePersistent(rd.get(i++));

			return s;
		} catch (Exception e) {
			s = e.toString();
		}
		return s;
	}

	@SuppressWarnings("unchecked")
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		// UserService userService = UserServiceFactory.getUserService();
		//
		// User user = userService.getCurrentUser();
		// if ( user == null) {
		// user=new User("test@quicklydone.com",
		// "quicklydone.com","test");
		// }
		//

		PrintWriter out = resp.getWriter();
		int t = 9999;
		String s = "ok";
		try {
			String str = req.getParameter("a");
			String id = req.getParameter("id");

			// user=new User(id, id.substring(id.indexOf("@")+1));

			String[] words = str.split("\r\n");
			PersistenceManager pm = PMF.get().getPersistenceManager();

			if (words == null)
				s4 = new String[] { "r", "50.0", "-60.0", "address", "2",
						"text" };
			else
				s4 = words;

			List<Mrkr4> rd = (List<Mrkr4>) pm.newQuery(
					"SELECT FROM " + Mrkr4.class.getName() + " WHERE s5==\""
							+ id + "\"").execute();
			s = String.valueOf(rd.size());
			for (int i = 0; i < rd.size(); i++) {
				Mrkr4 mr = pm.getObjectById(Mrkr4.class, rd.get(i).getId());
				pm.deletePersistent(mr);
			}
			for (int i = 0; i < s4.length; i = i + 6) {
				Mrkr4 ns = new Mrkr4(new Date(), s4[i], s4[i + 1], s4[i + 2],
						s4[i + 3], s4[i + 4], s4[i + 5]);
				pm.makePersistent(ns);
			}

			/*
			 * 
			 * for (int i = 0; i < s4.length; i = i + 6) { String query =
			 * "SELECT FROM " + Mrkr4.class.getName() + " WHERE s2 == \"" + s4[i
			 * + 1] + "\" && s3 == \"" + s4[i + 2] + "\""; List<Mrkr4> rr =
			 * (List<Mrkr4>) pm.newQuery(query).execute(); t = rr.size(); if
			 * (rr.size() == 0) { Mrkr4 ns = new Mrkr4(new Date(), s4[i], s4[i +
			 * 1], s4[i + 2], s4[i + 3], s4[i + 4], s4[i + 5], user);
			 * pm.makePersistent(ns); } else {
			 * 
			 * Mrkr4 ns = pm.getObjectById(Mrkr4.class, rr.get(0).getId());
			 * pm.deletePersistent(ns);
			 * 
			 * ns = new Mrkr4(new Date(), s4[i], s4[i + 1], s4[i + 2], s4[i +
			 * 3], s4[i + 4], s4[i + 5], user); pm.makePersistent(ns); }
			 * 
			 * 
			 * }
			 */

		} catch (Exception eee) {
			s = eee.toString();
		}
		// resp.sendRedirect("http://map.quicklydone.com");
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