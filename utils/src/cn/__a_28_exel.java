package cn;

import java.net.*;
import com.google.gdata.data.*;
import com.google.gdata.client.spreadsheet.*;
import com.google.gdata.data.spreadsheet.*;

public class __a_28_exel {

	public static String qq = "";

	public static void main(String[] args) throws Exception {
		System.out.println(".");
		SpreadsheetService myService = new SpreadsheetService(
				"exampleCo-exampleApp-1");
		myService.setUserCredentials("11qdone@gmail.com", "tverskoy");

		SpreadsheetFeed myFeed = myService
				.getFeed(
						new URL(
								"http://spreadsheets.google.com/feeds/spreadsheets/private/full"),
						SpreadsheetFeed.class);

		SpreadsheetEntry se = myFeed.getEntries().get(0);
		WorksheetEntry myWorksheet = se.getWorksheets().get(0);

		ListEntry myEntry = new ListEntry();
		myEntry.getCustomElements().setValueLocal("name", "Yuri Milov");
		myEntry.getCustomElements().setValueLocal("hours", "4");
		myEntry.getCustomElements().setValueLocal("items", "40");
		//myEntry.getCustomElements().setValueLocal("IPM", "=Sum(B3,C3)");
		//myEntry.getCustomElements().setValueLocal("IPM", "FLOOR(R[0]C[-1]/(R[0]C[-2]*60),.0001)");
		//myEntry.getCustomElements().setValueLocal("IPM", "11");

		
		
		
		
		myService.insert(myWorksheet.getListFeedUrl(), myEntry);

		
		
		
		
		
		ListQuery myListQuery = new ListQuery(myWorksheet.getListFeedUrl());
		//myListQuery.setSpreadsheetQuery("ipm<4 and hours>40");
		myListQuery.setSpreadsheetQuery("hours>1");
		ListFeed pp = myService.query(myListQuery, ListFeed.class);
		myEntry =pp.getEntries().get(1);
		
		
		TextContent cc = (TextContent)myEntry.getContent();
		String ss= cc.getContent().getPlainText();

		System.out.println(ss);
		System.out.println("ok");
	}
}
