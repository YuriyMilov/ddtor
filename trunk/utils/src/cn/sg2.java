package cn;

import java.io.*;
import java.net.URL;
import java.util.Date;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import javax.servlet.*;
import javax.servlet.http.*;

import com.google.gdata.client.spreadsheet.ListQuery;
import com.google.gdata.client.spreadsheet.SpreadsheetService;
import com.google.gdata.data.TextContent;
import com.google.gdata.data.spreadsheet.ListEntry;
import com.google.gdata.data.spreadsheet.ListFeed;
import com.google.gdata.data.spreadsheet.SpreadsheetEntry;
import com.google.gdata.data.spreadsheet.SpreadsheetFeed;
import com.google.gdata.data.spreadsheet.WorksheetEntry;

public class sg2 extends HttpServlet {

	private static final long serialVersionUID = 1L;

	static String s = "ok";

	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		ServletOutputStream out = res.getOutputStream();
		res.setContentType("text/html");
		String s = req.getQueryString();
		System.out.println(s);
		s=sta.rep(s, "%3E", ">");
		s=sta.rep(s, "%3C", "<");
		//s="name1>4";
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
			ListQuery myListQuery = new ListQuery(myWorksheet.getListFeedUrl());
	
			myListQuery.setSpreadsheetQuery(s);		
			ListFeed pp = myService.query(myListQuery, ListFeed.class);
			System.out.println("Entrie size = "+pp.getEntries().size());
			
			ListEntry myEntry = new ListEntry();
			myEntry =pp.getEntries().get(0);		
			TextContent cc = (TextContent)myEntry.getContent();
			s= cc.getContent().getPlainText();		
			System.out.println(s);


		} catch (Exception e) {
			s=e.toString();
			System.out.println(s + " " + getDateTime());		
			s="Nothing found, try again...";
		}
		out.println(s);
		out.flush();
		out.close();
		System.out.println(req.getQueryString() + " " + getDateTime());
	}


    private String getDateTime() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        return dateFormat.format(date);
    }
}
