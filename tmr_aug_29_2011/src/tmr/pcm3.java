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
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class pcm3 extends HttpServlet {
	private static final long serialVersionUID = 1L;
	String s = "";

	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		doGet(req, resp);
	}

	void parse_xml(String s, PrintWriter out) {
		// s = shta.rff("3.txt");
		try {
			new pcmSAX(s, out);
		} catch (Exception e) {
			out.println(e.toString());
		}
	}

	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		PrintWriter out = resp.getWriter();
		String s2 = "";
		try {
			String oadr = "";
			if (req.getParameter("oadr") != null)
				oadr = req.getParameter("oadr");
			String dadr = "";
			if (req.getParameter("dadr") != null)
				dadr = req.getParameter("dadr");
			
	String s5="";
			for (int i=1;i<51;i++){
				if (req.getParameter("ssci"+String.valueOf(i)) != null && req.getParameter("ssst"+String.valueOf(i)) != null)
					s5=s5+"<LocationInputType><City>"+req.getParameter("ssci"+String.valueOf(i))+"</City><State>"+req.getParameter("ssst"+String.valueOf(i))+"</State></LocationInputType>";
			}
	

			s2 = postData2(req.getParameter("user"), req.getParameter("pass"),
					req.getParameter("acc"), oadr, req.getParameter("ocity"),
					req.getParameter("ostate"), dadr,
					req.getParameter("dcity"), req.getParameter("dstate"),
					s5);
			s2 = s2.trim();
			//System.out.println("->" + s2 + "<-");

			if (s2.length() == 0) {
				out.println("ERROR: PC-Miler returned nothing :(");
			} else 
				if (s2.indexOf("<ErrorDesc>") > -1) {
					out.println("ERROR: " + s2.substring(s2.indexOf("<ErrorDesc>")+11,s2.indexOf("</ErrorDesc>")));
			} else
				parse_xml(s2, out);

		} catch (Exception e) {
			s2 = e.toString();
			out.println("ERROR: "+s2);
		}

	}

	private String postData2(String sUserID, String sPassword, String sAccount,
			String soAddress1, String soCity, String soState,
			String sdAddress1, String sdCity, String sdState, String s5) throws Exception {
		URL endpoint = new URL("http://pcmws.alk.com/service.asmx");

		String s = "<?xml version=\"1.0\" encoding=\"utf-8\"?><soap12:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap12=\"http://www.w3.org/2003/05/soap-envelope\">"
				+ "<soap12:Body>"
				+ "<PMWSGetReport xmlns=\"http://localhost/PCMilerWS/\">"
				+ "<LoginInfo>" +
				// "<UserID>EM</UserID>" +
				// "<Password>EM70271</Password>" +
				// "<Account>EMDataCon_test</Account>" +
				"<UserID>"
				+ sUserID
				+ "</UserID>"
				+ "<Password>"
				+ sPassword
				+ "</Password>"
				+ "<Account>"
				+ sAccount
				+ "</Account>"
				+ "</LoginInfo>"
				+ "<TripOrigin>"
				+ "<LocationInputType>"
				+
				// "<Zip>08540</Zip>" +

				"<Address1>"
				+ soAddress1
				+ "</Address1>"
				+ "<City>"
				+ soCity
				+ "</City>"
				+ "<State>"
				+ soState
				+ "</State>"
				+ "</LocationInputType>"
				+ "</TripOrigin>"
				+ "<TripDestination>"
				+ "<LocationInputType>"
				+

				// "<Zip>46701</Zip>" +
				"<Address1>"
				+ sdAddress1
				+ "</Address1>"
				+ "<City>"
				+ sdCity
				+ "</City>"
				+ "<State>"
				+ sdState
				+ "</State>"
				+ "</LocationInputType>"
				+ "</TripDestination>"
				+"<TripStops>"
				+"<ArrayOfLocationInputType>"
				
				
				
				+ s5 +
				
				//"<LocationInputType>" +
				// "<Address1>1000 Herrontown Rd</Address1>" +
				// "<City>Princeton</City>" +
				// "<State>NJ</State>" +
				// "<Zip>08540</Zip>" +
				//"</LocationInputType>"+
				
				//"<LocationInputType>" +
				// "<City>Robbinsville</City>" +
				// "<State>NJ</State>" +
				// "<Zip>08691</Zip>"	+
				//"</LocationInputType>"
				
				
				"</ArrayOfLocationInputType>"+
				"</TripStops>"+
				"<ReportType>"+
				// "<string>d,m,s</string>" +
				// "<string>m</string>" +
				"<string>m,s</string>"
				+ "</ReportType></PMWSGetReport>"
				+ "</soap12:Body></soap12:Envelope>";
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
		s = sb.toString().replace("><", ">\r\n<");

		// String sm="";
		// int i=s.lastIndexOf("<LMiles>");
		// if(i>-1)
		// sm=s.substring(i);

		// i=sm.indexOf("</LMiles>");
		// if(i>-1)
		// sm=sm.substring(8,i);

		// s=
		// "<html><body>"+sm+"<br/><br/>-----------<br/><xmp>"+s+"</xmp></body><html>";

		return s;

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
