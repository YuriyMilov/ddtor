package tmr;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Writer;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashSet;
import java.util.List;
import java.util.Properties;

import javax.jdo.PersistenceManager;
import javax.mail.Flags;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Store;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//import qq.InboxReader;

import tmr.server.PMF;

public class pcmws extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		doGet(req,resp);
	}

	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		PrintWriter out = resp.getWriter();
		String s;
		try {
			s = postData2("EM","EM70271","EMDataCon_test","Toronto","ON","New York","NY");
		} catch (Exception e) {
			s=e.toString();
		}
/*
	       Properties props = System.getProperties();
	         
	        props.setProperty("mail.store.protocol", "imaps");
	            try {
	                Session session = Session.getDefaultInstance(props, null);
	                Store store = session.getStore("imaps");
	                store.connect("imap.gmail.com", "test@quicklydone.com", "89898999");
	                System.out.println(store);
	                int cout=0;
	 
	                Folder inbox = store.getFolder("Inbox");
	                inbox.open(Folder.READ_WRITE);
	                Message messages[] = inbox.getMessages();
	                for(Message message:messages) {
	                    mails=new HashSet<String>();
	                    System.out.println("Reading:"+ (messages.length-cout));
	                    cout++;
	                    InboxReader.storeAddresses(message);
	                dumpPart(message);
	                for(String temp:mails)
	                    System.out.println(temp);
	                //connecttoMySql();
	                message.setFlag(Flags.Flag.DELETED, true);
	            }	
		
		*/
		out.println(s);
	}
	
	public static String postData2(String sUserID,String sPassword,String sAccount,String soCity,String soState,String doCity,String doState) throws Exception {
		URL endpoint = new URL("http://pcmws.alk.com/service.asmx");
		String s = "<?xml version=\"1.0\" encoding=\"utf-8\"?><soap12:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap12=\"http://www.w3.org/2003/05/soap-envelope\">" +
				"<soap12:Body>" +
				"<PMWSGetReport xmlns=\"http://localhost/PCMilerWS/\">" +
				"<LoginInfo>" +
				//"<UserID>EM</UserID>" +
				//"<Password>EM70271</Password>" +
				//"<Account>EMDataCon_test</Account>" +
				"<UserID>"+sUserID+"</UserID>" +
				"<Password>"+sPassword+"</Password>" +
				"<Account>"+sAccount+"</Account>" +
				"</LoginInfo>" +
				"<TripOrigin>" +
				"<LocationInputType>" +
				//"<Zip>08540</Zip>" +
				"<City>"+soCity+"</City>" +
		        "<State>"+soState+"</State>" +
				"</LocationInputType>" +
				"</TripOrigin>" +
				"<TripDestination>" +
				"<LocationInputType>" +
				//"<Zip>46701</Zip>" +
				"<City>"+doCity+"</City>" +
		        "<State>"+doState+"</State>" +
				"</LocationInputType>" +
				"</TripDestination>" +
				"<ReportType>" +
//				"<string>d,m,s</string>" +
				"<string>m</string>" +
				"</ReportType></PMWSGetReport>" +
				"</soap12:Body></soap12:Envelope>";
		HttpURLConnection urlc = (HttpURLConnection) endpoint.openConnection();
		urlc.setRequestMethod("POST");
		urlc.setDoOutput(true);
		urlc.setDoInput(true);
		urlc.setUseCaches(false);
		urlc.setAllowUserInteraction(false);
		urlc.setRequestProperty("Content-type", "text/xml; charset=" + "UTF-8");
		OutputStream out = urlc.getOutputStream();
		Writer writer = new OutputStreamWriter(out, "UTF-8");
		writer.write(s);
		writer.close();
		InputStream in = urlc.getInputStream();
		BufferedReader br = new BufferedReader(new InputStreamReader(in));
		StringBuffer sb = new StringBuffer();
		s = "";
		while ((s = br.readLine()) != null) {
			sb.append(s);
			sb.append("\r\n");
		}
		br.close();
		return sb.toString();
	}
	
	public static String postData() throws Exception {
		URL endpoint = new URL("http://pcmws.alk.com/service.asmx");
		String s = "<?xml version=\"1.0\" encoding=\"utf-8\"?><soap12:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap12=\"http://www.w3.org/2003/05/soap-envelope\">  <soap12:Body>    <PMWSGetReport xmlns=\"http://localhost/PCMilerWS/\">      <LoginInfo>        <UserID>EM</UserID>        <Password>EM70271</Password>        <Account>EMDataCon_test</Account>       </LoginInfo>      <TripOrigin>        <LocationInputType>          <Zip>08540</Zip>       </LocationInputType>      </TripOrigin>      <TripDestination>        <LocationInputType>          <Zip>46701</Zip>        </LocationInputType>      </TripDestination>      <ReportType>        <string>d,m,s</string>      </ReportType></PMWSGetReport>  </soap12:Body></soap12:Envelope>";
		HttpURLConnection urlc = (HttpURLConnection) endpoint.openConnection();
		urlc.setRequestMethod("POST");
		urlc.setDoOutput(true);
		urlc.setDoInput(true);
		urlc.setUseCaches(false);
		urlc.setAllowUserInteraction(false);
		urlc.setRequestProperty("Content-type", "text/xml; charset=" + "UTF-8");
		OutputStream out = urlc.getOutputStream();
		Writer writer = new OutputStreamWriter(out, "UTF-8");
		writer.write(s);
		writer.close();
		InputStream in = urlc.getInputStream();
		BufferedReader br = new BufferedReader(new InputStreamReader(in));
		StringBuffer sb = new StringBuffer();
		s = "";
		while ((s = br.readLine()) != null) {
			sb.append(s);
			sb.append("\r\n");
		}
		br.close();
		return sb.toString();
	}
}