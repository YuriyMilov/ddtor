package go;

import java.io.*;
import java.net.*;

import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;

import com.google.gdata.client.GoogleService;
import com.google.gdata.data.Entry;
import com.google.gdata.data.Feed;

public class _ads_31 {
	public static String gg = "";

	public static void main(String args[]) throws Exception {
		System.out.print("Today's Earnings:  ");
		gc1();
		post();
		get_rep();
	}

	public static void gc1() throws Exception {
		String s = "GET https://www.google.com/adsense/login/en_US/ HTTP/1.1"
				+ "\r\nHost: www.google.com\r\n\r\n";
		s = gh(s);
		while (s.indexOf("Set-Cookie:") > -1) {
			s = s.substring(s.indexOf("Set-Cookie:") + 12);
			gg = gg + s.substring(0, s.indexOf(";") + 1) + " ";
		}
 s = "GET https://www.google.com/accounts/ServiceLoginBox?service=adsense&ltmpl=login&ifr=true&rm=hide&fpui=3&nui=15&alwf=true&passive=true&continue=https%3A%2F%2Fwww.google.com%2Fadsense%2Flogin-box-gaiaauth&followup=https%3A%2F%2Fwww.google.com%2Fadsense%2Flogin-box-gaiaauth&hl=en_US HTTP/1.1"
				+ "\r\nHost: www.google.com\r\n\r\n";
		s = gh(s);
		while (s.indexOf("Set-Cookie:") > -1) {
			s = s.substring(s.indexOf("Set-Cookie:") + 12);
			gg = gg + s.substring(0, s.indexOf("\r\n")) + "; ";
		}
	}

	public static String gh(String s) throws Exception {
		SSLSocketFactory sslsocketfactory = (SSLSocketFactory) SSLSocketFactory
				.getDefault();
		SSLSocket sslsocket = (SSLSocket) sslsocketfactory.createSocket(
				"www.google.com", 443);
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
			if (stb.indexOf("\r\n\r\n") > -1)
				break;
		}
		inStream.close();
		outStream.close();
		sslsocket.close();
		return stb.toString();
	}

	public static void post() throws Exception {
		String s2 = "continue=https%3A%2F%2Fwww.google.com%2Fadsense%2Flogin-box-gaiaauth&followup=https%3A%2F%2Fwww.google.com%2Fadsense%2Flogin-box-gaiaauth&service=adsense&nui=15&fpui=3&ifr=true&rm=hide&ltmpl=login&hl=en_US&alwf=true&GA3T=GnN2YaQdoDk&Email=kukash@gmail.com&Passwd=2ymilov&null=Sign+in";
		String s = "POST https://www.google.com/accounts/ServiceLoginBoxAuth"
				+ " HTTP/1.1"
				+ "\r\nHost: www.google.com"
				+ "\r\nAccept: text/xml,application/xml,application/xhtml+xml,text/html;q=0.9,text/plain;q=0.8,image/png,*/*;q=0.5"
				+ "\r\nContent-Type: application/x-www-form-urlencoded"
				+ "\r\nCookie: " + gg + "\r\nContent-Length: "
				+ String.valueOf(s2.length()) + "\r\n\r\n" + s2;

		SSLSocketFactory sslsocketfactory = (SSLSocketFactory) SSLSocketFactory
				.getDefault();
		SSLSocket sslsocket = (SSLSocket) sslsocketfactory.createSocket(
				"www.google.com", 443);
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
			if (stb.indexOf("</html>") > -1)
				break;
		}
		inStream.close();
		outStream.close();
		sslsocket.close();
		s = stb.toString();
		s = s.substring(s.indexOf("https://www.google.com/adsense/adsauth"));
		s = s.substring(0, s.indexOf("\""));

		get(s);

	}

	public static void get(String s2) throws Exception {
		String s = "GET "
				+ s2
				+ " HTTP/1.1"
				+ "\r\nHost: www.google.com"
				+ "\r\nAccept: text/xml,application/xml,application/xhtml+xml,text/html;q=0.9,text/plain;q=0.8,image/png,*/*;q=0.5"
				+ "\r\nContent-Type: application/x-www-form-urlencoded"
				+ "\r\nCookie: " + gg + "\r\n\r\n";

		SSLSocketFactory sslsocketfactory = (SSLSocketFactory) SSLSocketFactory
				.getDefault();
		SSLSocket sslsocket = (SSLSocket) sslsocketfactory.createSocket(
				"www.google.com", 443);
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
		s = s.substring(s.indexOf("Set-Cookie: I="));
		s = s.substring(0, s.indexOf(";") + 1);
		gg = gg + s;
	}

	public static void get_rep() throws Exception {
		String s = "GET https://www.google.com/adsense/report/overview HTTP/1.1"
				+ "\r\nHost: www.google.com"
				+ "\r\nAccept: text/xml,application/xml,application/xhtml+xml,text/html;q=0.9,text/plain;q=0.8,image/png,*/*;q=0.5"
				+ "\r\nContent-Type: application/x-www-form-urlencoded"
				+ "\r\nCookie: " + gg + "\r\n\r\n";

		SSLSocketFactory sslsocketfactory = (SSLSocketFactory) SSLSocketFactory
				.getDefault();
		SSLSocket sslsocket = (SSLSocket) sslsocketfactory.createSocket(
				"www.google.com", 443);
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
			if (stb.indexOf("Quick Reports") > -1)
				break;
		}
		inStream.close();
		outStream.close();
		sslsocket.close();

		s = stb.toString();
		s = s.substring(s.indexOf("Today's Earnings:"));
		s = s.substring(0, s.indexOf("</"));

		s = s.substring(s.indexOf(">") + 1);
		s = s.substring(0, s.indexOf("&"));

		System.out.println(s);

	}

}
