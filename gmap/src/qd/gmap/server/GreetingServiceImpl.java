package qd.gmap.server;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
//import java.text.DateFormat;
//import java.text.SimpleDateFormat;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.servlet.http.HttpServletRequest;

import qd.gmap.client.GreetingService;
import qd.gmap.PMF;
import qd.gmap.Shipper;
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
	
	public String[] greetServer(String input) {
		String ss[]= {"Toronto, ON, Canada","Mississauga, ON, Canada","Ottawa, ON, Canada","London, ON, Canada","Kitchener, ON, Canada","Hamilton, ON, Canada","North Bay, ON, Canada","Barrie, ON, Canada","Montreal, QC, Canada","Calgary, AB, Canada","Winnipeg, MB, Canada","Peterborough, ON, Canada","Ajax, ON, Canada","Barrie, ON, Canada","Vancouver, BC, Canada","Miami, FL, USA","Los Angeles, CA, USA","Markham, ON, Canada","Burlington, ON, Canada","Barrie, ON, Canada","Kingston, ON, Canada","Toronto, ON, Canada","Barrie, ON,  Canada","Oakville, ON, Canada"} ;
		String s[]={"","","",""};
		//if(input.equals("add")){
		//for(int i=0;i<25;i++)
		//	s[0]=ss[(int)(Math.random()*25)];
		//s[1]="g";
		//s[2]=getDateTime();
		//s[3]=get_ship();
		//}
		//if(input.equals("preload"))
			//s=ss;
			s=aa.ss;
		//System.out.println("======="+ s.length +"========");
		
		//for(int i=0;i<s.length;i++)
		//	System.out.println(s[i]);
			
		return s;
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
	
	@SuppressWarnings("unchecked")
	public String get_ship() {
		String s = "";
		try {
			PersistenceManager pm = PMF.get().getPersistenceManager();
			String query = "select from " + Shipper.class.getName();//+ " where id == " + lid;
			
			List<Shipper> results = (List<Shipper>) pm.newQuery(query)
					.execute();

				Shipper sh = results.get(0);
				s = sh.get_company_name(); //+ " " + sh.get_address1() + " "	+ sh.get_city() + " " + sh.get_prov_state() + " "	+ sh.get_country() + " " + sh.get_postal_code()	+ "<br>Conact: " + sh.get_contact() + " " + sh.get_phone();
		

		} catch (Exception e) {
			s = e.toString();
		}

		return s;//.replaceAll("\\b\\s{2,}\\b", " ");
	}
	
	public String get_user(String input) {

		HttpServletRequest req = getThreadLocalRequest();
		String s = "qqqqqqqqqqqqq";

		UserService userService = UserServiceFactory.getUserService();
		User user = userService.getCurrentUser();
		if (user != null) {
			s = req.getUserPrincipal().getName() + " | <a href=\""
					+ userService.createLogoutURL("/") + "\">Logout</a>";
		} else {
			s = "<a href=\""
					+ userService.createLoginURL("/")
					+ "\">Login</a> <br/>   Test account: \"guest\", password: \"123456\"";
		}

		// if (input.equals("user"))
		// s=user.getNickname();
		return s;
	}
}
