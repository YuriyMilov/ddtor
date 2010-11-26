package cn;

import java.io.*;

import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import javax.servlet.*;
import javax.servlet.http.*;

public class serp extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	static String kup="",ace1=""; 

	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		String s = "";
		ServletOutputStream out = res.getOutputStream();
		res.setContentType("text/html");
		try {
			s=m2(m());
		} catch (Exception e) {
			s = e.toString();
		}
		out.println(s);
		out.flush();
		out.close();
	}

	public static String m2(String s) throws Exception {
		
		String ss="";
		
		s = s.substring(s.indexOf("<table class=\"etaResultsTable"));
		s = s.substring(0, s.indexOf("</table") + 9);
		// System.out.println("************\r\n" + s + "\r\n==============");
		s = s.substring(s.indexOf("taResultsTableRow"));
		s = s.substring(s.indexOf("<"));

		// sta.wtf(s,"C:\\Documents and
		// Settings\\Administrator.QQ.000\\Desktop\\qqqq_2.htm");

		s = sta.rep(s, "<", "\r\n<");
		s = sta.rep(s, "<tr", "\r\n--------------\r\n<");
		s = s.substring(s.indexOf("\r\n<") + 2);

		int i = 0;

		while (true) {
			try {
				String z = s.substring(0, s.indexOf("\r\n"));
				z = z.substring(z.indexOf(">") + 1);
				s = s.substring(s.indexOf("\r\n<") + 2);
				i++;
				if(i % 2 != 0)
				{
				ss=ss+z+"\r\n";
				//System.out.println(i + "   " + z);
				}
				if (i > 41) {
					i = 0;
					ss=ss+"***\r\n";
					//System.out
					//		.println("\r\n*********************************\r\n");
				}

			} catch (Exception e) {
				//System.out.println("================\r\n" + i + 
				//"\r\n==============");
				
				ss=ss.substring(0,ss.lastIndexOf("***\r\n"))+"***end***";
				
				break;
			}
		}
		System.out.println(ss);
		return ss;
	}	
	
	public static String m() throws Exception {

		kup = login();
		kup = get("/portal/portal/tp.asp?sppt=235&k=66265930", kup);
		kup = get2("/portal/portal/default.asp", kup);
		kup = get3("/portal/portal/SecuredLoginValidate.asp", kup);
		String s = get4("/portal/portal/tp.asp?sppt=235&k=66265930", kup);
		s = get5(s, kup);
		sta
				.wtf(s,
						"C:\\Documents and Settings\\Administrator.QQ.000\\Desktop\\qqqq.htm");
		return s;
	}

	public static String get4(String req, String ku) throws Exception {

		String s = "GET "
				+ req
				+ " HTTP/1.1"
				+ "\r\nHost: www8.cpr.ca:443"
				+ "\r\nAccept: */*"
				+ "User-Agent: Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.0; .NET CLR 1.1.4322; .NET CLR 2.0.50727)"
				+ "\r\n" + ku + "\r\n\r\n";

		// System.out.println("************\r\n"+s+"\r\n==============");

		SSLSocketFactory sslsocketfactory = (SSLSocketFactory) SSLSocketFactory
				.getDefault();
		SSLSocket sslsocket = (SSLSocket) sslsocketfactory.createSocket(
				"www8.cpr.ca", 443);
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
			if (stb.indexOf("\r\n0\r\n\r\n") > -1)
				break;
		}
		inStream.close();
		outStream.close();
		sslsocket.close();
		s = stb.toString();

		ku = s.substring(s.indexOf("Set-Cookie:"));
		ku = ku.substring(0, ku.indexOf("\r\n\r\n"));
		ku = sta.rep(ku, "Set-Cookie: ", "Cookie: ");
		System.err.println("\r\n-----   KU  ---\r\n" + ku + "\r\n-------\r\n");

		ace1 = s.substring(0, s.lastIndexOf("ace1</a>"));
		ace1 = ace1.substring(ace1.lastIndexOf("/inet58/"));
		ace1 = ace1.substring(0, ace1.indexOf("\""));
		ace1 = sta.rep(ace1, "amp;", "");

		System.err.println("\r\n******  ace1  ******\r\n\r\n" + ace1
				+ "\r\n\r\n************\r\n\r\n");
		return ace1;
	}

	public static String get5(String req, String kuk) throws Exception {

		String s = "GET "
				+ req
				+ " HTTP/1.1"
				+ "\r\nHost: www8.cpr.ca:443"
				+ "\r\nAccept: */*"
				+ "User-Agent: Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.0; .NET CLR 1.1.4322; .NET CLR 2.0.50727)"
				+ "\r\n" + kuk + "\r\n\r\n";

		// System.out.println("************\r\n"+s+"\r\n==============");

		SSLSocketFactory sslsocketfactory = (SSLSocketFactory) SSLSocketFactory
				.getDefault();
		SSLSocket sslsocket = (SSLSocket) sslsocketfactory.createSocket(
				"www8.cpr.ca", 443);
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
			// System.err.print((char) ch);
			// if (stb.indexOf("\r\n\r\n") > -1)
			// break;
			if (stb.indexOf("\r\n0\r\n\r\n") > -1)
				break;
		}
		inStream.close();
		outStream.close();
		sslsocket.close();
		s = stb.toString();

		return s;
	}

	public static String login() throws Exception {
		String ku = "";
		// String s2 =
		// "username=agi1010&password=trace1010&url=&login-form-type=pwd";
		String s2 = "username=acetrack&password=Trucking2&url=&login-form-type=pwd";
		String s = "POST /pkmslogin.form HTTP/1.1"
				+ "\r\nHost: www8.cpr.ca:443"
				+ "\r\nAccept: text/xml,application/xml,application/xhtml+xml,text/html;q=0.9,text/plain;q=0.8,image/png,*/*;q=0.5"
				+ "\r\nContent-Type: application/x-www-form-urlencoded"
				+ "\r\nContent-Length: " + String.valueOf(s2.length())
				+ "\r\n\r\n" + s2;

		SSLSocketFactory sslsocketfactory = (SSLSocketFactory) SSLSocketFactory
				.getDefault();
		SSLSocket sslsocket = (SSLSocket) sslsocketfactory.createSocket(
				"www8.cpr.ca", 443);
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

		// System.err.println(ku);
		return ku;
	}

	public static String get(String req, String ku) throws Exception {

		// https://www8.cpr.ca/portal/portal/tp.asp?sppt=235&k=66265930
		// https://www8.cpr.ca:443/portal/portal/default.asp
		// https://www8.cpr.ca/portal/portal/SecuredLoginValidate.asp

		// String s = "POST
		// /velocity/IMShipmentStatus/english/CFF_ImdShipmentStatus HTTP/1.1"
		String s = "GET "
				+ req
				+ " HTTP/1.1"
				// + "\r\nHost: ecprod.cn.ca:443"
				+ "\r\nHost: www8.cpr.ca:443"
				+ "\r\nAccept: text/xml,application/xml,application/xhtml+xml,text/html;q=0.9,text/plain;q=0.8,image/png,*/*;q=0.5"
				+ "\r\nContent-Type: application/x-www-form-urlencoded"
				+ "\r\nUser-Agent: Mozilla/5.0 (Windows; U; Windows NT 5.0; en-US; rv:1.8.1.6) Gecko/20070725 Firefox/2.0.0.6"
				+ "\r\nAccept: */*"
				+ "User-Agent: Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.0; .NET CLR 1.1.4322; .NET CLR 2.0.50727)"
				+ "\r\n" + ku + "\r\n\r\n";

		// + "\r\nContent-Length: " + String.valueOf(s2.length())
		// + "\r\n\r\n\r\n";// + s2;

		// System.err.println(s+"\r\n");

		SSLSocketFactory sslsocketfactory = (SSLSocketFactory) SSLSocketFactory
				.getDefault();
		SSLSocket sslsocket = (SSLSocket) sslsocketfactory.createSocket(
				"www8.cpr.ca", 443);
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

		// ku = ku.substring(0, ku.lastIndexOf("Secure") + 6);

		ku = ku.substring(0, ku.lastIndexOf("\r\n\r\n"));
		ku = sta.rep(ku, "Set-Cookie: ", "Cookie: ");
		// System.err.println("\r\n-----\r\n"+ku+"\r\n-------\r\n");
		return ku;
	}

	public static String get2(String req, String kuk) throws Exception {

		String s = "GET "
				+ req
				+ " HTTP/1.1"
				+ "\r\nHost: www8.cpr.ca:443"
				// + "\r\nAccept:
				// text/xml,application/xml,application/xhtml+xml,text/html;q=0.9,text/plain;q=0.8,image/png,*/*;q=0.5"
				// + "\r\nContent-Type: application/x-www-form-urlencoded"
				// + "\r\nUser-Agent: Mozilla/5.0 (Windows; U; Windows NT 5.0;
				// en-US; rv:1.8.1.6) Gecko/20070725 Firefox/2.0.0.6"
				+ "\r\nAccept: */*"
				+ "User-Agent: Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.0; .NET CLR 1.1.4322; .NET CLR 2.0.50727)"
				+ "\r\n" + kuk + "\r\n\r\n";

		// System.out.println("************\r\n"+s+"\r\n==============");

		SSLSocketFactory sslsocketfactory = (SSLSocketFactory) SSLSocketFactory
				.getDefault();
		SSLSocket sslsocket = (SSLSocket) sslsocketfactory.createSocket(
				"www8.cpr.ca", 443);
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
			// System.err.print((char) ch);
			if (stb.indexOf("\r\n\r\n") > -1)
				break;
		}
		inStream.close();
		outStream.close();
		sslsocket.close();
		s = stb.toString();

		kuk = s.substring(s.indexOf("Set-Cookie:"));

		// ku = ku.substring(0, ku.lastIndexOf("Secure") + 6);

		kuk = kuk.substring(0, kuk.lastIndexOf("\r\n\r\n"));
		kuk = sta.rep(kuk, "Set-Cookie: ", "Cookie: ");
		// System.err.println("\r\n-----\r\n"+kuk+"\r\n-------\r\n");

		return kuk;
	}

	public static String get3(String req, String ku) throws Exception {

		String s = "GET "
				+ req
				+ " HTTP/1.1"
				+ "\r\nHost: www8.cpr.ca:443"
				// + "\r\nAccept:
				// text/xml,application/xml,application/xhtml+xml,text/html;q=0.9,text/plain;q=0.8,image/png,*/*;q=0.5"
				// + "\r\nContent-Type: application/x-www-form-urlencoded"
				// + "\r\nUser-Agent: Mozilla/5.0 (Windows; U; Windows NT 5.0;
				// en-US; rv:1.8.1.6) Gecko/20070725 Firefox/2.0.0.6"
				+ "\r\nAccept: */*"
				+ "User-Agent: Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.0; .NET CLR 1.1.4322; .NET CLR 2.0.50727)"
				+ "\r\n" + ku + "\r\n\r\n";

		// System.out.println("************\r\n"+s+"\r\n==============");

		SSLSocketFactory sslsocketfactory = (SSLSocketFactory) SSLSocketFactory
				.getDefault();
		SSLSocket sslsocket = (SSLSocket) sslsocketfactory.createSocket(
				"www8.cpr.ca", 443);
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
			// System.err.print((char) ch);
			if (stb.indexOf("\r\n\r\n") > -1)
				break;
		}
		inStream.close();
		outStream.close();
		sslsocket.close();
		s = stb.toString();

		// System.out.println("\r\n-----\r\n"+s+"\r\n\r\n-------\r\n\r\n");

		ku = s.substring(s.indexOf("Set-Cookie:"));
		ku = ku.substring(0, ku.indexOf("\r\n\r\n"));
		ku = sta.rep(ku, "Set-Cookie: ", "Cookie: ");

		return ku;
	}

	// https://www8.cpr.ca/portal/portal/tp.asp?sppt=235&k=66265930

}
