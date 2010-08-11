package qd.gmap.server;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL; //import java.text.DateFormat;
//import java.text.SimpleDateFormat;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
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

	public String[] greetServer(String input) {
		String s[] = { "", "", "", "" };
		
		if (input.equals("aa"))
			s = aa.ss;
		if (input.equals("a2"))
			s = a2.s2;

		if (input.equals("a4"))
				s = det_mrkr();

		if (input.equals("login"))
			s=get_user("");
		return s;
	}

	@SuppressWarnings("unchecked")
	public String[] det_mrkr() {
		try {
			PersistenceManager pm = PMF.get().getPersistenceManager();
			// String query = "select from " + Mrkr4.class.getName()
			// + " where id == " + lid;

			
			
			String query = "SELECT FROM " + Mrkr4.class.getName() + " WHERE s5 == \""+getThreadLocalRequest().getUserPrincipal().getName() +"\"";
			
//			String query = "SELECT FROM " + Mrkr4.class.getName() + " WHERE s5 == \"1\"";
			
//	+ " WHERE u = USER('test@quicklydone.com')";

			
			
			//String query = "SELECT FROM " + Mrkr4.class.getName()
			//		+ " WHERE s5 == \"1\"";
			//+ " WHERE u == USER(\"test@quicklydone.com\")";
			
					//+" WHERE u==USER(\"test@quicklydone.com\")";// ORDER BY date DESC  order by s2

			//"+user.getEmail()+"
			//SELECT * FROM Mrkr4 where date > date('2010-06-26') order by date
			
			// u= USER('test@quicklydone.com')
			
			
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
		String s[] = {"init"};

		UserService userService = UserServiceFactory.getUserService();
		User user = userService.getCurrentUser();
		if (user != null) {
			s[0] = "<br>&nbsp;&nbsp;"+req.getUserPrincipal().getName() + " | <b><a href=\""
					+ userService.createLogoutURL("/") + "\">Logout</a></b><br><br>";
		} else {
			s[0] = "<center><br><br><br><br><br><b><a href=\""+ userService.createLoginURL("/")
					+ "\">Sign In</a></b></crnter>";
		} 

		// if (input.equals("user"))
		// s=user.getNickname();
		return s;
	}
}
