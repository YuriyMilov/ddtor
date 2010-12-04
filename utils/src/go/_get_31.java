package go;

import java.io.*;
import java.net.*;
import com.google.gdata.client.GoogleService;
import com.google.gdata.data.Entry;
import com.google.gdata.data.Feed;

public class _get_31 {

	public static String toc = "";

	public static void main(String[] args) throws Exception {

		String s = "11@quicklydone.com";

		System.out.println("***********************");
		GoogleService ms = new GoogleService("pages", "");
		ms.setUserCredentials(s, "tverskoy");
		toc = "PAGES_SID="+ms.getAuthToken(s, "tverskoy", "", "",
						"pages", "");
		
		//s=get_page("http://pages.google.com/");
		s=get_page("http://pages.google.com/?pli=1");
		System.out.println(s);
		sta
		.wtf(s,
				"C:/Documents and Settings/Administrator.QQ.000/Desktop/page.htm");

	}

	public static String get_page(String s) throws Exception {
		s = "GET " + s + " HTTP/1.1" + "\r\nHost: www.google.com"
				+ "\r\nCookie: " + toc + "; " + "\r\n\r\n";

		Socket js = new Socket("pages.google.com", 80);
		PrintStream outStream = new PrintStream(js.getOutputStream());
		outStream.println(s);
		outStream.flush();

		// read response

		DataInputStream inStream = new DataInputStream(js.getInputStream());
		StringBuffer stb = new StringBuffer();
		for (int ch = inStream.read(); ch > 0; ch = inStream.read()) {
			//System.out.print((char) ch);
			stb.append((char) ch);
			// if (stb.indexOf("\r\n\r\n") > -1)
			if (stb.indexOf("</html>") > -1)
				break;
		}

		inStream.close();
		outStream.close();
		js.close();

		//

		s = stb.toString();
		return s;
	}

}
