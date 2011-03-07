package test;

import java.io.*;
import java.net.URL;
import java.text.*;
import java.util.StringTokenizer;

import javax.servlet.*;
import javax.servlet.http.*;

public class serg2 extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	static String kup = "", ace1 = "";

	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		boolean er = false;

		ServletOutputStream out = res.getOutputStream();
		res.setContentType("text/html");
		String from = req.getParameter("from");
		String to = req.getParameter("to");
		String via = req.getParameter("via");
		from = rep(from, " ", "%20");
		to = rep(to, " ", "%20");
		via = rep(via, " ", "%20");
		int idx = 0;
		int tokenCount;
		String c[] = new String[500];
		StringTokenizer st = new StringTokenizer(via, ";");
		tokenCount = st.countTokens();
		System.out.println("Number of tokens = " + tokenCount);
		while (st.hasMoreTokens()) // is there stuff to get?
		{
			c[idx] = st.nextToken();
			idx++;
		}
		String f = from;
		String t = c[0];
		double d = get_d(f, t);
		if (d < 0)
			er = true;
		for (idx = 0; idx < tokenCount; idx++) {
			f = c[idx];
			if (idx < tokenCount - 1)
				t = c[idx + 1];
			else
				t = to;
			double d2 = get_d(f, t);
			if (d2 < 0)
				er = true;
			d = d + d2;
		}

		d = d / 1609.344;
		DecimalFormat df = new DecimalFormat("0.#");
		String s = "0";
		if (!er)
			s=df.format(d);
		out.println(s);
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

	public static double get_d(String f, String t) {
		String s = "from:%20" + f + "%20to:%20" + t;
		s = rfu("http://maps.google.com/maps/nav?key=ABQIAAAACI6Ap2jcQ1oqProEJaR56RQK5EEH2t0xEwQ4uv9AyPUz7NEyYxQvir4iiXU1B5zMsah16FswrqdssA&output=txt&doflg=ptj&hl=en&gl=US&q="
				+ s);
		double d = 0;
		try {

			s = s.substring(s.indexOf("Distance") + 20);
			s = s.substring(0, s.indexOf(","));
			d = Double.parseDouble(s);
			// d = d / 1609.344;

		} catch (Exception ee) {
			d = -1;
		}

		return d;
	}

}