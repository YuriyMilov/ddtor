package gu.server;

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
import gu.client.dbService;
import gu.client.model.Consignee;
import gu.client.model.Shipper;
import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import com.google.gwt.maps.client.overlay.Marker;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * The server side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class dbServiceImpl extends RemoteServiceServlet implements dbService {
	UserService userService = UserServiceFactory.getUserService();
	User user = userService.getCurrentUser();

	public String[][] getUser(String input) {

		HttpServletRequest req = getThreadLocalRequest();
		String[][] sss = { { "b", "44", "-77", "INIT" } };

		UserService userService = UserServiceFactory.getUserService();
		User user = userService.getCurrentUser();
		if (user != null) {
			sss[0][0] = "<br>&nbsp;&nbsp;" + req.getUserPrincipal().getName()
					+ " | <b><a href=\"" + userService.createLogoutURL("/")
					+ "\">Logout</a></b><br><br>";
		} else {
			sss[0][0] = "<center><br><br><br><br><br><b><a href=\""
					+ userService.createLoginURL("/")
					+ "\">Sign In</a></b></crnter>";
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
						mk.getLongtitude(), "","", "", "", "", mk.getName(),
						mk.getCity(), mk.getProv(), mk.getPostalCode(),
						mk.getLatitude(),mk.getLongtitude(), mk.getId(), "Skids",
						"Comment is here", "", "5 765", "2 279",
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
						mk.getLongtitude(), "","", "", "", "", mk.getName(),
						mk.getCity(), mk.getProv(), mk.getPostalCode(),
						mk.getLatitude(),mk.getLongtitude(), mk.getId(), "Skids",
						"Comment is here", "", "5 765", "2 279",
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
			sss = getUser("");
			return sss;
		}

		if (input.equals("all")) {
			ar.clear();
			ar = getShippers(ar);
			ar = getConsignees(ar);
			sss = new String[ar.size()][ar.get(0).length];
			for (int n = 0; n < ar.size(); n++) {
				sss[n] = ar.get(n);
			}
		for (int n = 0; n < sss[0].length; n++) {
				System.out.print(sss[0][n] + " ");
			}
			return sss;
		}
		return sss;
	}
}
