package qd.gmap.server;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
//import java.text.DateFormat;
//import java.text.SimpleDateFormat;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import qd.gmap.client.GreetingService;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * The server side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class GreetingServiceImpl extends RemoteServiceServlet implements
		GreetingService {

	public String[] greetServer(String input) {
		
		String s[]= {"Toronto","r","time"};

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
			s[0]="Mississauga, On";
			if(Math.random()> 0.5)
				s[1]="r";
			else
				s[1]="g";
			}
		s[2]=getDateTime();
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
}
