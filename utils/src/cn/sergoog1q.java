package cn;

import java.io.*;
import java.util.Date;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import javax.servlet.*;
import javax.servlet.http.*;
import java.net.*;

import com.google.gdata.data.Feed;

import com.google.gdata.data.*;
import com.google.gdata.client.calendar.*;
import com.google.gdata.client.spreadsheet.*;
import com.google.gdata.data.calendar.*;
import com.google.gdata.data.extensions.*;
import com.google.gdata.data.spreadsheet.*;

import com.google.gdata.client.spreadsheet.SpreadsheetService;

public class sergoog1q extends HttpServlet {

	private static final long serialVersionUID = 1L;

	static String s = "ok";

	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		ServletOutputStream out = res.getOutputStream();
		res.setContentType("text/html");

		try {
				sta.s=req.getQueryString();
				
				SpreadsheetService myService = new SpreadsheetService(
				"exampleCo-exampleApp-1");
				
				//Runtime.getRuntime().exec("java cn.sergoog2q \""+req.getQueryString());
System.out.println("run sergoog2qqqqqqqqqqqqq "+getDateTime());

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
