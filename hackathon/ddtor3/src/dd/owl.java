package dd;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.mindswap.pellet.jena.PelletReasonerFactory;
import com.clarkparsia.pellet.sparqldl.jena.SparqlDLExecutionFactory;
import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.query.Query;
import com.hp.hpl.jena.query.QueryFactory;
import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.rdf.model.ModelFactory;

public class owl extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String s = "";

	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		ServletOutputStream out = resp.getOutputStream();
		resp.setContentType("text/xml; charset=utf-8");

		String s = req.getParameter("url");
		if (s == null)
			s = "no URL request found";
		else
			s = rfu(req.getParameter("url"));
		int i = s.indexOf("(Semantic Web begin)") + 20;
		if (i > -1)
			s = s.substring(i);
		i = s.indexOf("(Semantic Web end)");
		if (i > -1)
			s = s.substring(0, i);

		// (Semantic Web end)
		
		String s1=get_level_1(s);
		
		s=s1+"\r\n\r\n"+s;

		s = "text=" + s + "&solo=owlxml";
		s = get_ape("http://attempto.ifi.uzh.ch/ws/ape/apews.perl?", s);

		
		
		
	
		
		
		byte[] b = s.getBytes("UTF8");
		out.write(b);
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

	public String get_ape(String location, String s) {

		String charEncoding = "iso-8859-1";

		try {
			HttpURLConnection conn;
			conn = (HttpURLConnection) new URL(location).openConnection();
			conn.setDoOutput(true);
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-Length",
					Integer.toString(s.length()));
			conn.getOutputStream().write(s.getBytes(charEncoding));
			conn.connect();
			conn.getResponseCode();
			InputStream is = conn.getInputStream();
			s = get_is(is);

		} catch (Exception e) {
			s = e.toString();
		}
		return s;
	}

	public static String post(String cc, String s) {

		try {
			// Construct data
			// s = URLEncoder.encode("fName", "UTF-8") + "=" +
			// URLEncoder.encode("value1", "UTF-8");
			// s += "&" + URLEncoder.encode("lName", "UTF-8") + "=" +
			// URLEncoder.encode("value2", "UTF-8");

			// Send data
			URL url = new URL(cc);
			URLConnection conn = url.openConnection();
			conn.setDoOutput(true);
			OutputStreamWriter wr = new OutputStreamWriter(
					conn.getOutputStream());
			wr.write(s);
			wr.flush();

			// Get the response
			BufferedReader rd = new BufferedReader(new InputStreamReader(
					conn.getInputStream()));
			String line;
			while ((line = rd.readLine()) != null) {
				s = s + line + "\r\n";
			}
			wr.close();
			rd.close();
		} catch (Exception e) {
			s = e.toString();
		}

		return s;

	};

	static String get_is(InputStream is) throws Exception {
		StringBuilder sb = new StringBuilder();
		int i = 0;
		while ((i = is.read()) != -1)
			sb.append((char) i);
		return sb.toString();
	}

	static String get_level_1(String s) {
		int i = s.indexOf("http://");
		if (i > -1)
			s = s.substring(i);
		else
			return "";
		i = s.indexOf(" ");
		if (i > 0)
			s = s.substring(0, i).replace("\"", "");
		else
			return "";
		s = rfu(s);
		return s;
	};
}
