package tmr;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class kwas_index extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public static String s3 = "";

	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		PrintWriter out = resp.getWriter();
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
		try {
//			PrintWriter out = resp.getWriter();
			String ss = "", s = "";

			DateFormat df = new SimpleDateFormat("yyyyMMddhhmmss");
			String sn = df.format(new Date());
			s = req.getParameter("check");

			String s100 = "Trailer No: " + req.getParameter("c100");
			String s101 = "Seal No:    " + req.getParameter("c101");
			String s102 = "Vessel:     " + req.getParameter("c102");
			String s103 = "Date:       " + req.getParameter("c103");
			String s104 = "Email:      " + req.getParameter("c104");

			if (s == null)
				s = "";
			else
				ss = ss + s + "\r\n";

			ss = ss + s100 + "\r\n";

			ss = ss + s101 + "\r\n";

			ss = ss + s102 + "\r\n";

			if (s103 == null || s103.trim().length() == 5) {
				Calendar cc = new GregorianCalendar();
				SimpleDateFormat sdf = new SimpleDateFormat("MMM'.'d',' yyyy");
				s103 = "Date:       " + sdf.format(cc.getTime());

			}

			ss = ss + s103 + "\r\n";
			ss = ss + s104 + "\r\n";

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
			if (req.getParameter("att").equals("1")) {
				resp.setContentType("text/html");
				resp.setHeader("Content-Disposition", "attachment; filename="
						+ sn + ".txt");
				resp.getWriter().write(ss);
			}
			if (req.getParameter("att").equals("2")) {
				resp.setContentType("application/pdf");
				resp.setHeader("Content-Disposition", "attachment; filename="
						+ sn + ".pdf");
				resp.getOutputStream().write(shta.getbb(ss));

			}
			if (req.getParameter("att").equals("3")) 				
				shta.get_excel2(req, resp);
	
		} catch (Exception e) {
			resp.getWriter().print(e.toString());
		}
	}
}
