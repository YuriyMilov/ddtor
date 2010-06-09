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
		//String.valueOf(new Date().getTime()).substring(8);
		//rfu("http://quicklydone.com/webxr/Default.aspx");

		String s= "Toronto, On." ;
		if ((int)(Math.random()*10)+1 > 5)
			s="New York, US";
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
