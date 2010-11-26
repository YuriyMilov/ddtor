package cn;

import java.io.*;
import java.net.URL;

import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import javax.servlet.*;
import javax.servlet.http.*;

public class gmapd_app {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public static void main(String[] args) throws Exception {

		String s = rfu("http://maps.google.com/maps/nav?key=ABQIAAAACI6Ap2jcQ1oqProEJaR56RQK5EEH2t0xEwQ4uv9AyPUz7NEyYxQvir4iiXU1B5zMsah16FswrqdssA&output=txt&doflg=ptj&hl=en&gl=US&q=from:+42+Queen%20St.%20S.Mississauga%2C%20Ontario%20to%3A%20Toronto%2C%20On%2C%20Canada");

		System.out.println(s);

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
	public static String rep(String line, String old_s, String new_s) {
		int index = 0;
		while ((index = line.indexOf(old_s, index)) >= 0) {
			line = line.substring(0, index) + new_s
					+ line.substring(index + old_s.length());
			index += new_s.length();
		}
		return line;
	}

}
