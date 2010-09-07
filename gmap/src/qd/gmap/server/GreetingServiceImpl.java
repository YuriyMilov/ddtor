package qd.gmap.server;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL; //import java.text.DateFormat;
//import java.text.SimpleDateFormat;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.servlet.http.HttpServletRequest;

import qd.gmap.client.GreetingService;
import qd.gmap.PMF;
import qd.gmap.Mrkr4;
import qd.gmap.a2;
import qd.gmap.a4;
import qd.gmap.aa;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * The server side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class GreetingServiceImpl extends RemoteServiceServlet implements
		GreetingService {

	UserService userService = UserServiceFactory.getUserService();
	User user = userService.getCurrentUser();

	public String[] get_r4(String s1, String s2) {
		String ss[] = { "b", "44", "-70", s1 + "<br>" + s2 };

		ss = de2_mrkr(s1, s2);

		return ss;
	}

	public String get(String s) {
		// s=rfu("http://map.quicklydone.com/geo?"+s);
		return s;
	}

	@SuppressWarnings("unchecked")
	public String[] de2_mrkr(String s1, String s2) {
		try {
			double z = 2222;
			String[] ss2 = { "70", "-88", "init" };
			ss2 = get_LL(s2);
			PersistenceManager pm = PMF.get().getPersistenceManager();
			String query = "SELECT FROM " + Mrkr4.class.getName()
					+ " WHERE s5 == \""
					+ getThreadLocalRequest().getUserPrincipal().getName()
					+ "\"";
			List<Mrkr4> results = (List<Mrkr4>) pm.newQuery(query).execute();
			int i = 0, n = 4, k = results.size();
			String[] ss = new String[k * 4 + 4];

			Mrkr4 mk = null;
			ArrayList<Integer> ar = new ArrayList<Integer>();
			double d1 = Double.parseDouble(ss2[0]);
			double d2 = Double.parseDouble(ss2[1]);

			while (i < k) {
				mk = results.get(i++);
				ss[n++] = mk.get_s1();
				ss[n++] = mk.get_s2();
				ss[n++] = mk.get_s3();
				ss[n++] = mk.get_s6();

				double d3 = Double.parseDouble(ss[n - 3]);
				double d4 = Double.parseDouble(ss[n - 2]);
				double d5 = Math.pow(d1 - d3, 2) + Math.pow(d2 - d4, 2);
				z = 2222;

				if (s1.equals("100 km"))
					z = 0.2;
				if (s1.equals("50 km"))
					z = 0.08;
				if (s1.equals("20 km"))
					z = 0.04;
				if (s1.equals("10 km"))
					z = 0.02;
				if (d5 < z)
					ar.add(i);
			}
			ss[0] = "b";
			ss[1] = ss2[0];
			ss[2] = ss2[1];
			ss[3] = "aaaaaaaaaaaaa";//"Destination: " + s2 + "<br>Radius: " + s1					+ "<br>Array size: " + ar.size() + "<br>Z: "					+ String.valueOf(z) + "<br>Exc.: " + ss2[2];
			
			
			
			k=ar.size() * 4;
			String[] ss3 = new String[k];
			ss3[0] = "b";
			ss3[1] = ss2[0];
			ss3[2] = ss2[1];
			ss3[3] = "Destination: " + s2 + "<br>Radius: " + s1
					+ "<br>Array size: " + ar.size();// + "<br>Z: "	+ String.valueOf(z) + "<br>Exc.: " + ss2[2];
			ss3[3]=String.valueOf(k);
			int j=4;
			n = 4;
			i = 1;
			while (n < k-3) {
				ss3[n++] = ss[j];
				ss3[n++] = ss[j+1];
				ss3[n++] = ss[j+2];
				ss3[n++] = ss[j+3];
				//j=j+4;
				//ss3[3]=ss3[3]+" j="+String.valueOf(j)+" "+" i="+String.valueOf(i)+" ";
				j=4*ar.get(i++);
			}
			
			return ss3;
			
		} catch (Exception e) {
			return new String[] { "b", "44", "-70", e.toString() };
		}

	}

	@SuppressWarnings("unchecked")
	public String[] det_mrkr() {
		try {
			PersistenceManager pm = PMF.get().getPersistenceManager();
			String query = "SELECT FROM " + Mrkr4.class.getName()
					+ " WHERE s5 == \""
					+ getThreadLocalRequest().getUserPrincipal().getName()
					+ "\"";
			List<Mrkr4> results = (List<Mrkr4>) pm.newQuery(query).execute();
			int i = 0, n = 0, k = results.size();
			String[] ss = new String[k * 4];
			Mrkr4 mk = null;
			while (i < k) {
				mk = results.get(i++);
				ss[n++] = mk.get_s1();
				ss[n++] = mk.get_s2();
				ss[n++] = mk.get_s3();
				ss[n++] = mk.get_s6();
			}
			return ss;
		} catch (Exception e) {
			return null;
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

	public String[] get_user(String input) {

		HttpServletRequest req = getThreadLocalRequest();
		String s[] = { "init" };

		UserService userService = UserServiceFactory.getUserService();
		User user = userService.getCurrentUser();
		if (user != null) {
			s[0] = "<br>&nbsp;&nbsp;" + req.getUserPrincipal().getName()
					+ " | <b><a href=\"" + userService.createLogoutURL("/")
					+ "\">Logout</a></b><br><br>";
		} else {
			s[0] = "<center><br><br><br><br><br><b><a href=\""
					+ userService.createLoginURL("/")
					+ "\">Sign In</a></b></crnter>";
		}

		// if (input.equals("user"))
		// s=user.getNickname();
		return s;
	}

	public String[] greetServer(String input) {
		String s[] = { "", "", "", "" };

		if (input.equals("aa"))
			s = aa.ss;
		if (input.equals("a2"))
			s = a2.s2;

		if (input.equals("a4"))
			s = det_mrkr();

		if (input.equals("login"))
			s = get_user("");
		return s;
	}

	public String[] get_LL(String s) {
		String ss[] = { "70", "-88", "init" };
		s = s.replace(' ', '+');
		try {
			s = rfu("http://maps.google.com/maps/api/geocode/xml?address=" + s
					+ "&sensor=true");
			if (s.indexOf("</location>") > s.indexOf("<location>")) {
				s = s.substring(s.indexOf("<location>") + 10,
						s.indexOf("</location>"));
				if (s.indexOf("</lat>") > s.indexOf("<lat>")
						&& s.indexOf("</lng>") > s.indexOf("<lng>")) {
					ss[0] = s.substring(s.indexOf("<lat>") + 5,
							s.indexOf("</lat>"));
					ss[1] = s.substring(s.indexOf("<lng>") + 5,
							s.indexOf("</lng>"));
				}
			}

		} catch (Exception e) {
			ss = new String[] { "70", "-88", e.toString() };
		}

		return ss;
	}
}
