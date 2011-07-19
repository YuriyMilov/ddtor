package gu;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.jdo.PersistenceManager;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import gu.client.dbService;
import gu.client.model.Consignee;
import gu.client.model.Shipper;
import gu.client.model.Worder;
import gu.server.PMF;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import com.google.gwt.maps.client.overlay.Marker;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * The server side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class dbServiceImpl_ymd extends RemoteServiceServlet implements dbService {
	//UserService userService = UserServiceFactory.getUserService();
	//User user = userService.getCurrentUser();

	public String[][] getUser(String input) {

		HttpServletRequest req = getThreadLocalRequest();
		HttpServletResponse res = getThreadLocalResponse();

		String[][] sss = { { "b", "44", "-77", "INIT" },
				{ "b", "44", "-77", "INIT" } };

		UserService userService = UserServiceFactory.getUserService();

		User user = userService.getCurrentUser();
		if (user != null) {
			sss[0][0] = "<br>&nbsp;&nbsp;" + req.getUserPrincipal().getName()
					+ " | <b><a href=\"" + userService.createLogoutURL("/")
					+ "\">Logout</a></b><br><br>";
			return sss;
		} else {

			sss[0][0] = "<center><br><br><br><br><br><b><a href=\""
					+ userService.createLoginURL("/")
					+ "\">Sign In</a></b></center>";

		}
		return sss;

	}

	public ArrayList<String[]> getShippers(ArrayList<String[]> ar) {

		// ar.add(new String[] { "b", "48", "-77", "init" });

		try {

			PersistenceManager pm = PMF.get().getPersistenceManager();
			String query = "SELECT FROM " + Shipper.class.getName();// +
																	// " WHERE s5 == \""
																	// +
																	// getThreadLocalRequest().getUserPrincipal().getName()
																	// + "\"";

			@SuppressWarnings("unchecked")
			List<Shipper> results = (List<Shipper>) pm.newQuery(query)
					.execute();
			int i = 0;

			Shipper mk = null;
			int k = results.size();
			while (i < k) {
				mk = results.get(i++);
				ar.add(new String[] { "r", mk.getLatitude(),
						mk.getLongtitude(), "", "", "", "", "", mk.getName(),
						mk.getCity(), mk.getProv(), mk.getPostalCode(),
						mk.getLatitude(), mk.getLongtitude(), mk.getId(),
						"Skids", "Comment is here", "", "5 765", "2 279",
						"8/25/10 2:00 p.m.", "8/26/10 10:00 a.m." });
			}

			return ar;
		} catch (Exception e) {
			return ar;
		}
	}

	public ArrayList<String[]> getConsignees(ArrayList<String[]> ar) {

		// ar.add(new String[] { "b", "48", "-77", "init" });

		try {

			PersistenceManager pm = PMF.get().getPersistenceManager();
			String query = "SELECT FROM " + Consignee.class.getName();// +
																		// " WHERE s5 == \""
																		// +
																		// getThreadLocalRequest().getUserPrincipal().getName()
																		// +
																		// "\"";

			@SuppressWarnings("unchecked")
			List<Consignee> results = (List<Consignee>) pm.newQuery(query)
					.execute();
			int i = 0;

			Consignee mk = null;
			int k = results.size();
			// ar.clear();
			while (i < k) {
				mk = results.get(i++);
				ar.add(new String[] { "g", mk.getLatitude(),
						mk.getLongtitude(), "", "", "", "", "", mk.getName(),
						mk.getCity(), mk.getProv(), mk.getPostalCode(),
						mk.getLatitude(), mk.getLongtitude(), mk.getId(),
						"Skids", "Comment is here", "", "5 765", "2 279",
						"8/25/10 2:00 p.m.", "8/26/10 10:00 a.m." });
			}
			return ar;
		} catch (Exception e) {
			ar.clear();
			return ar;
		}
	}

	@SuppressWarnings("unused")
	private String getDateTime() {
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		return dateFormat.format(date);
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

	public String[][] getData(String input) {

		ArrayList<String[]> ar = new ArrayList<String[]>();
		String[][] sss = new String[][] { { "", "", "", "" },
				{ "", "", "", "" } };

		if (input.equals("login")) {
			//sss = getUser("");
			sss[0][0]="Logout";
			return sss;
		}

		if (input.equals("qq")) {
			ar.clear();
			ar = get_qq(ar);
			if (ar.size() > 0) {
				sss = new String[ar.size()][ar.get(0).length];
				for (int n = 0; n < ar.size(); n++) {
					sss[n] = ar.get(n);
				}
			}
			return sss;
		}

		if (input.equals("all")) {
			ar.clear();

			// ar=get_qq(ar);

			// ar = getShippers(ar);
			// ar = getConsignees(ar);

			ar = getWorders(ar);

			if (ar.size() > 0) {
				sss = new String[ar.size()][ar.get(0).length];
				for (int n = 0; n < ar.size(); n++) {
					sss[n] = ar.get(n);
				}
				// for (int n = 0; n < sss[0].length; n++) {
				// System.out.print(sss[0][n] + " ");
				// }
				return sss;
			}
			return sss;
		}
		return sss;
	}

	public ArrayList<String[]> getWorders(ArrayList<String[]> ar) {

		try {
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

			ar = new ArrayList<String[]>();
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

										"r",
										sh.getLatitude(),
										sh.getLongtitude(),
										"InfoWindow for shipper "
												+ sh.getName(),
										"g",
										cn.getLatitude(),
										cn.getLongtitude(),
										"InfoWindow for shipper "
												+ cn.getName(),
										ww.get_wo_number(), " ",
										sh.getCity() + ", " + sh.getProv(),
										" ",
										cn.getCity() + ", " + cn.getProv(),
										ww.getDescription(), ww.get_pieces(),
										ww.get_type(), ww.getDescription(),
										" ", ww.get_weight_lbs(),
										ww.get_weight_kgs(), ww.getpickup_dt(),
										ww.get_delivery_dt() };

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
			if (ar.size() != 0) {

				sss = new String[ar.size()][ar.get(0).length];
				for (int n = 0; n < ar.size(); n++) {
					sss[n] = ar.get(n);
				}
			}
			// if (sss != null)
			// for (int m = 0; m < ar.size(); m++) {
			// for (int n = 0; n < sss[0].length; n++) {
			// System.out.print(sss[m][n] + " ");
			// }
			// System.out.println();
			// }
			// //////////////////////////////////////////////////

			return ar;
		} catch (Exception e) {
			return ar;
		}
	}

	public ArrayList<String[]> get_qq(ArrayList<String[]> ar) {

		try {
			PersistenceManager pm = PMF.get().getPersistenceManager();
			@SuppressWarnings("unchecked")
			List<Worder> woList = (List<Worder>) pm.newQuery(
					"SELECT FROM " + Worder.class.getName()
							+ " ORDER BY wo_number").execute();

			Worder ww = null;
			Shipper sh = null;
			Consignee cn = null;

			// ////////////////////////////////////////////////////////

			ar = new ArrayList<String[]>();

			// ////////////////////////////////////////////////////////

			int iw = 0;
			int kw = woList.size();
			String[] zz = null;
			while (iw < kw) {

				ww = woList.get(iw++);
				String ship_id = ww.get_ship_id();
				String cons_id = ww.get_cons_id();

				@SuppressWarnings("unchecked")
				List<Shipper> shipList = (List<Shipper>) pm.newQuery(
						"SELECT FROM " + Shipper.class.getName()
								+ " WHERE sShipId == '" + ship_id + "'")
						.execute();

				@SuppressWarnings("unchecked")
				List<Consignee> consList = (List<Consignee>) pm.newQuery(
						"SELECT FROM " + Consignee.class.getName()
								+ " WHERE sConsId == '" + cons_id + "'")
						.execute();

				if (shipList == null || consList == null)
					break;

				String wo_number = "", ship_lat = "", ship_long = "", ship_prov = "", ship_city = "", cons_lat = "", cons_long = "", cons_prov = "", cons_city = "",

				sEquipmqnt = "", sPieces = "", sType = "", sDesc = "", slbs = "", skgs = "", sPickup = "", sDelivery = "";

				ship_lat = shipList.get(0).getLatitude();
				ship_long = shipList.get(0).getLongtitude();
				cons_lat = consList.get(0).getLatitude();
				cons_long = consList.get(0).getLongtitude();

				wo_number = ww.get_wo_number();
				ship_city = shipList.get(0).getCity();
				ship_prov = shipList.get(0).getProv();
				cons_city = consList.get(0).getCity();
				cons_prov = consList.get(0).getProv();

				sEquipmqnt = ww.getDescription();
				sPieces = ww.get_pieces();
				sType = ww.get_type();
				sDesc = ww.get_desc();
				slbs = ww.get_weight_lbs();
				skgs = ww.get_weight_kgs();
				sPickup = ww.getpickup_dt();
				sDelivery = ww.get_delivery_dt();

				zz = new String[] { ship_lat, ship_long, cons_lat, cons_long,
						wo_number, "", ship_city + ", " + ship_prov, "",
						cons_city + ", " + cons_prov, sEquipmqnt, sPieces,
						sType, sDesc, "",slbs, skgs, sPickup, sDelivery };

				ar.add(zz);
			}

			String[][] sss = null;
			if (ar.size() != 0) {

				sss = new String[ar.size()][ar.get(0).length];
				for (int n = 0; n < ar.size(); n++) {
					sss[n] = ar.get(n);
				}
			}
			return ar;
		} catch (Exception e) {
			return ar;
		}
	}
}
