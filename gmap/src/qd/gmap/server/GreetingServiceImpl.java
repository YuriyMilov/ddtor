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

import qd.gmap.client.GreetingService;
import qd.gmap.PMF;
import qd.gmap.Shipper;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * The server side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class GreetingServiceImpl extends RemoteServiceServlet implements
		GreetingService {

	public String[] greetServer(String input) {
		
		String s[]= {"Toronto","r","time",""};

		if ((int)(Math.random()*10+1.0) > 2)
			{
			s[0]="New York, US";
			if(Math.random()> 0.5)
				s[1]="r";
			else
				s[1]="g";
			}
		if ((int)(Math.random()*10)+1 > 4)
		{
			s[0]="Boston, US";
			if(Math.random()> 0.5)
				s[1]="r";
			else
				s[1]="g";
			}
		if ((int)(Math.random()*10)+1 > 6)
		{
			s[0]="Montreal, Canada";
			if(Math.random()> 0.5)
				s[1]="r";
			else
				s[1]="g";
			}
		if ((int)(Math.random()*10)+1 > 8)
		{
			s[0]="Ottawa, On";
			if(Math.random()> 0.5)
				s[1]="r";
			else
				s[1]="g";
			}
		s[2]=getDateTime();
		
		s[3]=get_ship();
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
	
//	public String det_ship(Long lid) {
	
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
}
