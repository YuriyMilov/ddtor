package cn;

import java.io.*;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import javax.servlet.*;
import javax.servlet.http.*;

public class servlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	

	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		ServletOutputStream out = res.getOutputStream();
		res.setContentType("text/html");
		String ku = "";

		try {

			ku = login();
			//mscu+892397
			String s=req.getQueryString();
			System.out.println(s);
			ku = get(s,ku);

			//ku = ku.substring(ku.indexOf("\r\nInit"));
			//ku = ku.substring(2, ku.indexOf("\r\n\r\n"));
			
			ku = ku.substring(ku.indexOf("\r\n\"Init"));
			ku = ku.substring(2, ku.indexOf("\r\n\r\n0"));

		} catch (Exception e) {
			ku = e.toString();
		}

		out.println(ku);
		out.flush();
		out.close();
	}

	public static String login() throws Exception {
		String ku = "";
		String s2 = "username=agi1010&password=trace1010&url=&login-form-type=pwd";
		String s = "POST /pkmslogin.form HTTP/1.1"
				+ "\r\nHost: ecprod.cn.ca:443"
				+ "\r\nAccept: text/xml,application/xml,application/xhtml+xml,text/html;q=0.9,text/plain;q=0.8,image/png,*/*;q=0.5"
				+ "\r\nContent-Type: application/x-www-form-urlencoded"
				+ "\r\nContent-Length: " + String.valueOf(s2.length())
				+ "\r\n\r\n" + s2;

		SSLSocketFactory sslsocketfactory = (SSLSocketFactory) SSLSocketFactory
				.getDefault();
		SSLSocket sslsocket = (SSLSocket) sslsocketfactory.createSocket(
				"ecprod.cn.ca", 443);
		PrintStream outStream = new PrintStream(sslsocket.getOutputStream());
		outStream.println(s);
		outStream.flush();

		// read response

		DataInputStream inStream = new DataInputStream(sslsocket
				.getInputStream());
		StringBuffer stb = new StringBuffer();

		int ch = ' ';
		for (ch = inStream.read(); ch > 0; ch = inStream.read()) {
			stb.append((char) ch);
			// System.out.print((char) ch);
			if (stb.indexOf("\r\n\r\n") > -1)
				break;
		}
		inStream.close();
		outStream.close();
		sslsocket.close();
		s = stb.toString();

		ku = s.substring(s.indexOf("Set-Cookie:"));
		ku = sta.rep(ku, "Set-Cookie: ", "Cookie: ");
		ku = ku.substring(0, ku.lastIndexOf("Secure") + 6);

		// System.err.println("\r\n"+ku+"\r\n");
		return ku;
	}

	public static String get(String req, String ku) throws Exception {
		//FTP
		//String s2 = "LANGUAGE=E&version=SID-1.0&LAST_IS_LEGACY=false&REF_TYPE_CODE=HR&cars="+req+"&RESPONDBY=FTP&REQUEST=Submit";
		//XLS
		String s2 = "LANGUAGE=E&version=SID-1.0&LAST_IS_LEGACY=false&REF_TYPE_CODE=HR&cars="+req+"&RESPONDBY=XLS&REQUEST=Submit";

		// https://ecprod.cn.ca:443/velocity/IMShipmentStatus/english/CFF_ImdShipmentStatus
		
		String s = "POST /velocity/IMShipmentStatus/english/CFF_ImdShipmentStatus HTTP/1.1"
				+ "\r\nHost: ecprod.cn.ca:443"
				+ "\r\nAccept: text/xml,application/xml,application/xhtml+xml,text/html;q=0.9,text/plain;q=0.8,image/png,*/*;q=0.5"
				+ "\r\nContent-Type: application/x-www-form-urlencoded"
				+ "\r\nUser-Agent: Mozilla/5.0 (Windows; U; Windows NT 5.0; en-US; rv:1.8.1.6) Gecko/20070725 Firefox/2.0.0.6"
				+ "\r\nAccept: */*"
				+ "User-Agent: Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.0; .NET CLR 1.1.4322; .NET CLR 2.0.50727)"
				+ "\r\n"
				+ ku
				+ "\r\nContent-Length: "
				+ String.valueOf(s2.length()) + "\r\n\r\n" + s2;

		// System.err.println(s+"\r\n");

		SSLSocketFactory sslsocketfactory = (SSLSocketFactory) SSLSocketFactory
				.getDefault();
		SSLSocket sslsocket = (SSLSocket) sslsocketfactory.createSocket(
				"ecprod.cn.ca", 443);
		PrintStream outStream = new PrintStream(sslsocket.getOutputStream());
		outStream.println(s);
		outStream.flush();

		// read response

		DataInputStream inStream = new DataInputStream(sslsocket
				.getInputStream());
		StringBuffer stb = new StringBuffer();

		int ch = ' ';
		for (ch = inStream.read(); ch > 0; ch = inStream.read()) {
			stb.append((char) ch);
			// System.out.print((char) ch);
			if (stb.indexOf("\r\n0\r\n") > -1)
				break;
		}
		inStream.close();
		outStream.close();
		sslsocket.close();
		s = stb.toString();

		ku = s.substring(s.indexOf("Set-Cookie:"));
		ku = sta.rep(ku, "Set-Cookie: ", "Cookie: ");
		ku = ku.substring(0, ku.lastIndexOf("Secure") + 6);
		// System.err.println("\r\n\r\n"+ku+"\r\n\r\n");
		return s;
	}

}
