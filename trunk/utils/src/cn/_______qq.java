package cn;

import com.google.gdata.client.spreadsheet.*;
import com.google.gdata.data.*;
import com.google.gdata.data.spreadsheet.*;
import com.google.gdata.util.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.io.*;
import java.net.*;
import com.google.gdata.client.http.*;
import com.google.gdata.client.batch.*;
import com.google.gdata.client.calendar.*;
import com.google.gdata.client.codesearch.*;
import com.google.gdata.data.*;
import com.google.gdata.data.batch.*;
import com.google.gdata.data.calendar.*;
import com.google.gdata.data.codesearch.*;
import com.google.gdata.data.extensions.*;
import com.google.gdata.data.introspection.*;
import com.google.gdata.util.*;
import com.google.gdata.util.common.util.*;
import com.google.gdata.util.common.base.*;
import com.google.gdata.util.common.html.*;
import com.google.gdata.util.common.xml.*;

public class _______qq {

	public static void main(String[] args) throws Exception {
test();
	}
	
	public static void test() throws Exception {
		SpreadsheetService service = new SpreadsheetService(
				"exampleCo-exampleApp-1");

		service.setUserCredentials("33@quicklydone.com", "quicklydone");
		URL metafeedUrl = new URL("http://spreadsheets.google.com/feeds/spreadsheets/private/full");
		SpreadsheetFeed feed = service.getFeed(metafeedUrl, SpreadsheetFeed.class);
		List spreadsheets = feed.getEntries();
		System.out.println("> "+spreadsheets.size());
		for (int i = 0; i < spreadsheets.size(); i++) {
		  SpreadsheetEntry spreadsheetEntry = (SpreadsheetEntry)spreadsheets.get(0);
		  System.out.println(">> \t-" + spreadsheetEntry.getTitle().getPlainText());
		  List worksheets = spreadsheetEntry.getWorksheets();
		  WorksheetEntry worksheet = (WorksheetEntry)worksheets.get(0);
		  ListEntry newEntry = new ListEntry();
		  newEntry.getCustomElements().setValueLocal("name1", "aaaa");
		 // ListEntry insertedRow = service.insert(feedUrl, newEntry);
		 service.insert(worksheet.getListFeedUrl(), newEntry);
		}
	}
}
