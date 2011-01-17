package test;

import java.io.*;
import java.net.URL;
import java.text.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class serg extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	static String kup = "", ace1 = "";

	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		String s = "";
		ServletOutputStream out = res.getOutputStream();
		res.setContentType("text/html");
		String from = req.getParameter("from");
		String to = req.getParameter("to");
		from = rep(from, " ", "%20");
		to = rep(to, " ", "%20");
		s = "from:%20" + from + "%20to:%20" + to;
		System.out.println(rep(s, "%20", " ") + "\r\n\r\n");
		s = rfu("http://maps.google.com/maps/nav?key=ABQIAAAACI6Ap2jcQ1oqProEJaR56RQK5EEH2t0xEwQ4uv9AyPUz7NEyYxQvir4iiXU1B5zMsah16FswrqdssA&output=txt&doflg=ptj&hl=en&gl=US&q="
				+ s);
		String str = "";
		try {

			s = s.substring(s.indexOf("Distance") + 20);
			s = s.substring(0, s.indexOf(","));
			double dd = Double.parseDouble(s);
			dd = dd / 1609.344;
			// String.Format("{0:0.00}", 123.4567); // "123.46"

			// String fmt = "0.00#";
			String fmt = "0.#";
			DecimalFormat df = new DecimalFormat(fmt);
			str = df.format(dd);
		} catch (Exception ee) {
			str = "0";
		}
		out.println(str);
		out.flush();
		out.close();
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