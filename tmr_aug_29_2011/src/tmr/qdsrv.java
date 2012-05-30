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

public class qdsrv extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public static String s3 = "";

	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		PrintWriter out = resp.getWriter();
	//String s = shta.rff("index.html");
		Calendar cc = new GregorianCalendar();
		SimpleDateFormat sdf = new SimpleDateFormat("MMM'.'d',' yyyy");
		String sdate = sdf.format(cc.getTime());
		//s = s.replace("<!-- date -->", sdate);
		//out.println(s);
		 System.out.println(sdate);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		try {
//			PrintWriter out = resp.getWriter();
			String ss = "", s = "";

			DateFormat df = new SimpleDateFormat("yyyyMMddhhmmss");
			String sn = df.format(new Date());
			s = req.getParameter("cust");

			if (s == null)
				s = "";

			if (req.getParameter("att").equals("1")) {
				//resp.setContentType("text/plain");
				
				resp.setContentType("application/octet-stream");
				
				resp.setHeader("Content-Disposition", "attachment; filename="
						+ sn + ".txt");
				resp.getWriter().write(s);
			}
			if (req.getParameter("att").equals("2")) {
				resp.setContentType("application/pdf");
				resp.setHeader("Content-Disposition", "attachment; filename="
						+ sn + ".pdf");
				resp.getOutputStream().write(shta.getbb(s));

			}
			if (req.getParameter("att").equals("3")) 				
				shta.get_excel3(req, resp);
	
		} catch (Exception e) {
			resp.getWriter().print(e.toString());
		}
	} 
}
