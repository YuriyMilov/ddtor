package cn;

import java.io.*;
import java.util.Date;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import javax.servlet.*;
import javax.servlet.http.*;

import com.google.gdata.client.spreadsheet.SpreadsheetService;

public class sergoogq extends HttpServlet {

	private static final long serialVersionUID = 1L;

	static String s = "ok";

	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		ServletOutputStream out = res.getOutputStream();
		res.setContentType("text/html");

		try {
				sta.s=req.getQueryString();
				Runtime.getRuntime().exec("java cn.sergoog2 \""+req.getParameter("name1")+"\" \""+req.getParameter("name2")+"\"");
System.out.println("run sergoog2 "+getDateTime());

		} catch (Exception e) {
			s = e.toString();
		}
		out.println(s);
		out.flush();
		out.close();
	}


    private String getDateTime() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        return dateFormat.format(date);
    }
}
