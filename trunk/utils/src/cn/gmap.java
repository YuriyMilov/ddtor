package cn;

import java.io.*;
import java.net.URL;

import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import javax.servlet.*;
import javax.servlet.http.*;

public class gmap extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	

	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		ServletOutputStream out = res.getOutputStream();
		res.setContentType("text/html");
		String s = rfu("http://localhost/gmap/temp.htm");
		String from=req.getParameter("from");
		String to=req.getParameter("to");
		s=rep(s,"42 Queen St. S.Mississauga, Ontario", from);
		s=rep(s,"Toronto, On, Canada",to);
		
		//System.out.println(from);

		out.println(s);
		out.flush();
		out.close();
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
