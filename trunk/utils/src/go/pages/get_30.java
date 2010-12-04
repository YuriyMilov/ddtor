package go.pages;

import java.io.*;
import java.net.*;
import com.google.gdata.client.GoogleService;
import com.google.gdata.data.Entry;
import com.google.gdata.data.Feed;

public class get_30 {

	public static String toc = "";

	public static void main(String[] args) throws Exception {

		String s = "11@quicklydone.com";

		System.out.println("***********************");
		GoogleService ms = new GoogleService("pages", "");
		ms.setUserCredentials(s, "tverskoy");
		//"PAGES_SID="+
		toc = ms.getAuthToken(s, "tverskoy", "", "", "pages", "");
		s=get_page();
	}

	public static String get_page() throws Exception {
		String s = "POST http://pages.google.com/upload-file-simple/11qdone/?authtoken="+toc+" HTTP/1.1\r\n"+
			"Host: pages.google.com\r\n"+
			"User-Agent: Mozilla/5.0 (Windows; U; Windows NT 5.0; en-US; rv:1.8.1.1) Gecko/20061204 Firefox/2.0.0.1\r\n"+
			"Accept: text/xml,application/xml,application/xhtml+xml,text/html;q=0.9,text/plain;q=0.8,image/png,*/*;q=0.5\r\n"+
			"Accept-Language: en-us,en;q=0.5\r\n"+
			"Accept-Encoding: gzip,deflate\r\n"+
			"Accept-Charset: ISO-8859-1,utf-8;q=0.7,*;q=0.7\r\n"+
			"Keep-Alive: 300\r\n"+
			"Connection: keep-alive\r\n"+
			"Content-Type: multipart/form-data; boundary=---------------------------114782935826962\r\n"+
			"Content-Length: 192\r\n"+
			"-----------------------------114782935826962\r\n"+
			"Content-Disposition: form-data; name=\"file\"; filename=\"zz.txt\"\r\n"+
			"Content-Type: text/plain\r\n\r\n"+
			"zzzz\r\n";
		
		System.out.println(s);

		Socket js = new Socket("pages.google.com", 80);
		PrintStream outStream = new PrintStream(js.getOutputStream());
		outStream.println(s);
		outStream.flush();

		// read response

		DataInputStream inStream = new DataInputStream(js.getInputStream());
		StringBuffer stb = new StringBuffer();
		for (int ch = inStream.read(); ch > 0; ch = inStream.read()) {
			System.out.print((char) ch);
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
