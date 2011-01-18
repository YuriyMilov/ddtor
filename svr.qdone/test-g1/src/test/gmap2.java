package test;

import java.io.*;
import java.net.URL;
import javax.servlet.*;
import javax.servlet.http.*;

public class gmap2 extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		ServletOutputStream out = res.getOutputStream();
		res.setContentType("text/html");
		String s = rff("6.htm");
		String from = req.getParameter("from");
		String to = req.getParameter("to");
		s = rep(s, "Fargo, ND", from);
		s = rep(s, "Salt Lake City, UT", to);

		// System.out.println(from);

		out.println(s);
		out.flush();
		out.close();
	}

	public static String rff(String file_name) {
		String s = "", str = "";
		try {
			BufferedReader in = new BufferedReader(new FileReader(file_name));
			while ((str = in.readLine()) != null) {
				s = s + str + "\r\n";
			}
			in.close();
		} catch (Exception e) {
			s = e.toString();
		}
		return s;
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
