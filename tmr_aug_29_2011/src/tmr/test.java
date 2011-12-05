package tmr;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Enumeration;
import java.util.GregorianCalendar;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class test extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public static String s3 = "";

	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		PrintWriter out = resp.getWriter();
		resp.setContentType("text/html");
		String s = shta.rff("index.html");
		Calendar cc = new GregorianCalendar();
		SimpleDateFormat sdf = new SimpleDateFormat("MMM'.'d',' yyyy");
		String sdate = sdf.format(cc.getTime());
		s = s.replace("<!-- date -->", sdate);
		out.println(s);
		// System.out.println(date);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		PrintWriter out = resp.getWriter();
		DateFormat df = new SimpleDateFormat("yyyyMMddhhmmss");
		String sn = df.format(new Date());
		resp.setHeader("Content-Disposition", "attachment; filename=" + sn
				+ ".txt");
		String ss = "", s = req.getParameter("check");

		String s100 = "Trailer No: " + req.getParameter("c100");
		String s101 = "Seal No:    " + req.getParameter("c101");
		String s102 = "Vessel:     " + req.getParameter("c102");
		String s103 = "Date:       " + req.getParameter("c103");

		if (s == null)
			s = "";
		else
			ss = ss + s + "\r\n";

		if (s100 == null)
			s100 = "";
		else
			ss = ss + s100 + "\r\n";

		if (s101 == null)
			s101 = "";
		else
			ss = ss + s101 + "\r\n";

		if (s102 == null)
			s102 = "";
		else
			ss = ss + s102 + "\r\n";

		if (s103 == null || s103.trim().length() == 5) {
			Calendar cc = new GregorianCalendar();
			SimpleDateFormat sdf = new SimpleDateFormat("MMM'.'d',' yyyy");
			s103 = "Date:       " + sdf.format(cc.getTime());
			
		}
		ss = ss + s103 + "\r\n";

		for (int i = 1; i < 100; i++) {
			s = req.getParameter("c" + String.valueOf(i));

			if (s == null)
				s = "";
			else if (s.trim().length() == 0)
				s = "";
			else {
				if (s.length() > 6)
					s = s.substring(0, 6);
				if (i < 10)
					s = "  " + s;
				else if (i < 100)
					s = " " + s;
				ss = ss + "\r\nCrate No " + String.valueOf(i) + ":  " + s;
			}
		}

		out.print(ss);
		//System.out.println(ss);
		s = "";
		ss = "";
	}

	public String postData2(String sUserID, String sPassword, String sAccount,
			String soAddress1, String soCity, String soState,
			String sdAddress1, String sdCity, String sdState, String ssci1,
			String ssci2, String ssst1, String ssst2) throws Exception {
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
				+

				"<TripStops>"
				+

				"<ArrayOfLocationInputType>"
				+

				"<LocationInputType>"
				+
				// "<Address1>1000 Herrontown Rd</Address1>" +
				// "<City>Princeton</City>" +
				// "<State>NJ</State>" +

				"<City>"
				+ ssci1
				+ "</City>"
				+ "<State>"
				+ ssst1
				+ "</State>"
				+

				// "<Zip>08540</Zip>" +
				"</LocationInputType>"
				+

				"<LocationInputType>"
				+
				// "<City>Robbinsville</City>" +
				// "<State>NJ</State>" +
				"<City>"
				+ ssci2
				+ "</City>"
				+ "<State>"
				+ ssst2
				+ "</State>"
				+

				// "<Zip>08691</Zip>" +
				"</LocationInputType>"
				+ "</ArrayOfLocationInputType>"
				+

				"</TripStops>"
				+

				"<ReportType>"
				+
				// "<string>d,m,s</string>" +
				"<string>K</string>"
				// "<string>m,s</string>"
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

	DefaultHandler handler = new DefaultHandler() {
		boolean bstate = false;
		boolean blmiles = false;

		public void startElement(String uri, String localName, String qName,
				Attributes attributes) throws SAXException {
			// out.println(" start ");

			if (qName.equalsIgnoreCase("StCntry")) {
				bstate = true;
			}
			if (qName.equalsIgnoreCase("STotal")) {
				blmiles = true;
			}
		}

		public void endElement(String uri, String localName, String qName)
				throws SAXException {

			// out.println(" end ");

		}

		public String get_result() {
			return s3;
		};

		public void characters(char ch[], int start, int length)
				throws SAXException {
			String s1 = "";
			if (bstate) {
				s1 = (new String(ch, start, length));
				s3 = s3 + s1 + "-";
				bstate = false;
			}
			if (blmiles) {
				s1 = (new String(ch, start, length));
				s3 = s3 + s1 + ",";
				blmiles = false;
			}
		}
	};

	public static InputStream StringToStream(String text) {
		InputStream is = null;
		/*
		 * Convert String to InputStream using ByteArrayInputStream class. This
		 * class constructor takes the string byte array which can be done by
		 * calling the getBytes() method.
		 */
		try {
			is = new ByteArrayInputStream(text.getBytes("UTF-8"));
			is.reset();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return is;
	}

}
