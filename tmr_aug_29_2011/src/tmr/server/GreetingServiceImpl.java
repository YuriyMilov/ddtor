package tmr.server;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import tmr.client.GreetingService;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

@SuppressWarnings("serial")
public class GreetingServiceImpl extends RemoteServiceServlet implements
		GreetingService {

	public String greetServer(String input) throws IllegalArgumentException {

		String s=rfu("http://beska.quicklydone.com/qq");
				System.out.println(s);
				
				return 		s;
				
		
		//return rfu("http://174.117.66.8/1.txt");
		//return rfu("http://"+getThreadLocalRequest().getRemoteAddr().toString()+"/1.txt");
		//return rfu("http://174.117.66.8/gu/gu.aspx");
		//return String.valueOf(Math.round(Math.random()*10));
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
