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

public class qq {

	public static void main(String[] args) throws Exception {
		//String s="http://www.blogger.com/";
		//https://www.google.com/accounts/ClientLogin
		//String s="https://www.google.com/accounts/ClientLogin";
		//URL url = new URL(s);
		//Entry myEntry = new Entry();
		//myEntry.setContent(new HtmlTextConstruct("qqqqqqqqqq"));
		//GoogleService myService = new GoogleService("wise", "");
		SpreadsheetService myService = new SpreadsheetService("exampleCo-exampleApp-1");
		
//		myService.setUserCredentials("kukash@gmail.com", "ymilov");
		myService.setUserCredentials("33@quicklydone.com", "tverskoy");
		String s=myService.getContentType().toString();
		System.out.println(s);
		URL metafeedUrl = new URL("http://spreadsheets.google.com/feeds/spreadsheets/private/full");
		SpreadsheetFeed feed = myService.getFeed(metafeedUrl, SpreadsheetFeed.class);
		List spreadsheets = feed.getEntries();
		System.out.println("------>   " + spreadsheets.size());
		
		for (int i = 0; i < spreadsheets.size(); i++) {
		  SpreadsheetEntry entry = (SpreadsheetEntry)spreadsheets.get(i);
		  System.out.println("\t" + entry.getTitle().getPlainText());
		  
		  
		  /////////
		  
		  List worksheets = entry.getWorksheets();
		  for (int ii = 0; ii < worksheets.size(); ii++) {
		    WorksheetEntry worksheet = (WorksheetEntry)worksheets.get(ii);
		    String title = worksheet.getTitle().getPlainText();
		    int rowCount = worksheet.getRowCount();
		    int colCount = worksheet.getColCount();
		    System.out.println("\t" + title + "- rows:" + rowCount + " cols: " + colCount);
		  }
		  
		}
		

		
		//myService.insert(url, myEntry);
        // Send the request and receive the response:
        //Entry insertedEntry = myService.insert(url, myEntry);
	}

}
