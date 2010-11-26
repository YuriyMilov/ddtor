package cn;

import java.io.*;
import java.net.URL;
import java.util.Date;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import javax.servlet.*;
import javax.servlet.http.*;

import com.google.gdata.client.spreadsheet.SpreadsheetService;
import com.google.gdata.data.spreadsheet.ListEntry;
import com.google.gdata.data.spreadsheet.SpreadsheetEntry;
import com.google.gdata.data.spreadsheet.SpreadsheetFeed;
import com.google.gdata.data.spreadsheet.WorksheetEntry;

public class sg1 extends HttpServlet {

	private static final long serialVersionUID = 1L;

	static String s = "ok";

	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		ServletOutputStream out = res.getOutputStream();
		res.setContentType("text/html");
		String s = "ok";
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
			myEntry.getCustomElements().setValueLocal("name1", req.getParameter("name1"));
			myEntry.getCustomElements().setValueLocal("name2", req.getParameter("name2"));
			myService.insert(myWorksheet.getListFeedUrl(), myEntry);

		} catch (Exception e) {
			s=e.toString();
		}
		out.println(s);
		out.flush();
		out.close();
		System.out.println("insert name1: "+ req.getParameter("name1") + " " + getDateTime());
	}


    private String getDateTime() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        return dateFormat.format(date);
    }
}
