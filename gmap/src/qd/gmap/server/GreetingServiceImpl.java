package qd.gmap.server;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Date;

import qd.gmap.client.GreetingService;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * The server side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class GreetingServiceImpl extends RemoteServiceServlet implements
		GreetingService {

	public String greetServer(String input) {
		
		//return new java.util.Date().toString();
		//String s = rfu("http://www.bankofcanada.ca/en/markets/csv/exchange_eng.csv");
		//s=s.substring(s.indexOf("U.S. Dollar"));
		//s=s.substring(0,s.indexOf("\r\n"));
		//s=s.substring(s.lastIndexOf(",")+1);
		
		String s= String.valueOf(new Date().getTime()).substring(8);
		//String s= new java.util.Date().toString();
				//rfu("http://quicklydone.com/webxr/Default.aspx");
		
		return s;
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
