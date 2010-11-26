package cn;

import java.io.*;
import java.net.*;
import java.util.*;

import com.google.gdata.data.Feed;

import com.google.gdata.data.*;
import com.google.gdata.client.calendar.*;
import com.google.gdata.client.spreadsheet.*;
import com.google.gdata.data.calendar.*;
import com.google.gdata.data.extensions.*;
import com.google.gdata.data.spreadsheet.*;

public class a_28_exel {

	public static String qq = "";

	public static void main(String[] args) throws Exception {
		System.out.println(".");
		SpreadsheetService myService = new SpreadsheetService(
				"exampleCo-exampleApp-1");
		myService.setUserCredentials("33@quicklydone.com", "quicklydone");
		SpreadsheetFeed myFeed = myService
				.getFeed(
						new URL(
								"http://spreadsheets.google.com/feeds/spreadsheets/private/full"),
						SpreadsheetFeed.class);

		System.out.println("> "+myFeed.getEntries().size());

		SpreadsheetEntry se = myFeed.getEntries().get(0);
		WorksheetEntry myWorksheet = se.getWorksheets().get(0);

		ListEntry myEntry = new ListEntry();
		myEntry.getCustomElements().setValueLocal("name1", "10");
		myEntry.getCustomElements().setValueLocal("name2", "100");
		myEntry.getCustomElements().setValueLocal("name3", "1100");
		myService.insert(myWorksheet.getListFeedUrl(), myEntry);
		
		
		//myEntry.getCustomElements().setValueLocal("hours", "4");
		//myEntry.getCustomElements().setValueLocal("items", "40");
		//myEntry.getCustomElements().setValueLocal("IPM", "=Sum(B3,C3)");
		//myEntry.getCustomElements().setValueLocal("IPM", "FLOOR(R[0]C[-1]/(R[0]C[-2]*60),.0001)");
		//myEntry.getCustomElements().setValueLocal("IPM", "11");
		//myService.insert(myWorksheet.getListFeedUrl(), myEntry);	


		ListQuery myListQuery = new ListQuery(myWorksheet.getListFeedUrl());
		//myListQuery.setSpreadsheetQuery("name1<4 and name2>40");		
		myListQuery.setSpreadsheetQuery("name1>4 and name3<4");		
		ListFeed pp = myService.query(myListQuery, ListFeed.class);
		System.out.println("Entrie size = "+pp.getEntries().size());
		myEntry =pp.getEntries().get(0);		
		TextContent cc = (TextContent)myEntry.getContent();
		String ss= cc.getContent().getPlainText();		
		System.out.println(ss);


		//ListQuery myListQuery = new ListQuery(myWorksheet.getListFeedUrl());
		//myListQuery.setSpreadsheetQuery("ipm<4 and hours>40");
		//myListQuery.setSpreadsheetQuery("hours>1");
		//ListFeed pp = myService.query(myListQuery, ListFeed.class);
		//myEntry =pp.getEntries().get(1);
		//TextContent cc = (TextContent)myEntry.getContent();
		//String ss= cc.getContent().getPlainText();
		//System.out.println(ss);
		
		System.out.println("ok");
	}
}
