package cn;

import java.io.*;
import java.net.URL;
import java.text.*;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import javax.servlet.*;
import javax.servlet.http.*;

import com.google.gdata.client.spreadsheet.SpreadsheetService;
import com.google.gdata.data.spreadsheet.ListEntry;
import com.google.gdata.data.spreadsheet.SpreadsheetEntry;
import com.google.gdata.data.spreadsheet.SpreadsheetFeed;
import com.google.gdata.data.spreadsheet.WorksheetEntry;

public class sertest extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	static String kup = "", ace1 = "";

	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		ServletOutputStream out = res.getOutputStream();
		res.setContentType("text/html");
		String s = "";
		try {
			SpreadsheetService myService = new SpreadsheetService(
					"exampleCo-exampleApp-1");
			myService.setUserCredentials("33@quicklydone.com", "quicklydone");
			SpreadsheetFeed myFeed = myService
					.getFeed(
							new URL(
									"http://spreadsheets.google.com/feeds/spreadsheets/private/full"),
							SpreadsheetFeed.class);
			SpreadsheetEntry se = myFeed.getEntries().get(0);
			WorksheetEntry myWorksheet = se.getWorksheets().get(0);
			ListEntry myEntry = new ListEntry();
			//myEntry.getCustomElements().setValueLocal("name1", sta.rfu("http://localhost:8080/servlet/cn.sergoog1?com=get"));
			s = "ss";//sta.rfu("http://localhost:8080/servlet/cn.sersta");
			myEntry.getCustomElements().setValueLocal("name1", "test ss1");
			myEntry.getCustomElements().setValueLocal("name2", "test ss2");
			myService.insert(myWorksheet.getListFeedUrl(), myEntry);
			System.out.println("OK");
		} catch (Exception e) {
			s=e.toString();
		}

		out.println(s);
		out.flush();
		out.close();
	}


}
