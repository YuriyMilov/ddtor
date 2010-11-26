package cn;

import com.google.gdata.client.spreadsheet.*;
import com.google.gdata.data.spreadsheet.*;
import java.net.URL;

public class sergoog2 extends Thread {

	public static String ss1="null",ss2="null";
	
	public static void main(String[] args) throws Exception {
		try{ss1=args[0];ss2=args[1];}catch(Exception e){}
		new sergoog2().run();
	}

		public void run() {
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
			String s = "ss";//sta.rfu("http://localhost:8080/servlet/cn.sersta");
			myEntry.getCustomElements().setValueLocal("name1", ss1);
			myEntry.getCustomElements().setValueLocal("name2", ss2);
			myService.insert(myWorksheet.getListFeedUrl(), myEntry);

		} catch (Exception e) {
			System.err.println(e.toString());
		}
	}
}
