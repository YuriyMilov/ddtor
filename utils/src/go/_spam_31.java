package go;

import java.io.*;
import java.net.*;
import java.util.zip.*;
import java.util.*;
import com.google.gdata.client.GoogleService;
import com.google.gdata.data.Entry;
import com.google.gdata.data.Feed;
import com.google.gdata.data.HtmlTextConstruct;

public class _spam_31 {

	public static String bb = "";

	public static void main(String[] args) throws Exception {

		for (int i = 113; i < 140; i++) {
			String id = String.valueOf(i);
			System.out.println("***********  " + id + "  *************");
			GoogleService ms = new GoogleService("blogger", "");
			ms.setUserCredentials(id + "@quicklydone.com", "tverskoy");
			bb = "blogger_SID="
					+ ms.getAuthToken(id + "@quicklydone.com", "tverskoy", "",
							"", "blogger", "");

			Feed blogs = ms.getFeed(new URL(
					"http://www.blogger.com/feeds/default/blogs"), Feed.class);
			for (int j = 0; j < blogs.getEntries().size(); j++) {
				Entry er = blogs.getEntries().get(j);
				String title = er.getTitle().getPlainText();
				String s = er.getId().toString();
				s = s.substring(s.indexOf("blog-") + 5);
				s=get_spam("http://www.blogger.com/post-create.g?blogID="
						+ s)+ " " + id + " "+ title;
				sta.wtft(s, "C:/Documents and Settings/Administrator.QQ.000/Desktop/spam.txt");
				System.out.println(s);
			}
		}
	}

	public static boolean get_spam(String s) throws Exception {
		s = "GET " + s + " HTTP/1.1" + "\r\nHost: www.blogger.com"
				+ "\r\nCookie: " + bb + "; " + "\r\n\r\n";

		Socket js = new Socket("www.blogger.com", 80);
		PrintStream outStream = new PrintStream(js.getOutputStream());
		outStream.println(s);
		outStream.flush();

		// read response

		DataInputStream inStream = new DataInputStream(js.getInputStream());
		StringBuffer stb = new StringBuffer();
		try {
			for (int ch = inStream.read(); ch > 0; ch = inStream.read()) {
				// System.out.print((char) ch);
				stb.append((char) ch);
				// if (stb.indexOf("\r\n\r\n") > -1)
				if (stb.indexOf("spam") > -1 || stb.indexOf("</html>") > -1)
					break;
			}
			s = stb.toString();
			// String g2 = s.substring(s.indexOf("S=blogger"));
			// g2 = g2.substring(0, g2.indexOf(";") + 1);
			// bb=g2 + " "+ bb;
			// System.err.println(bb);

			if (stb.indexOf("spam") > -1) {
				inStream.close();
				outStream.close();
				js.close();
				return true;
			}

			// System.out.println(s);

		} catch (Exception e) {
			System.err.println(e);
		}
		inStream.close();
		outStream.close();
		js.close();
		return false;
	}

	public static void homes(String s) throws Exception {
		System.out.println("--- Blogger Home ");
		String bb = "blogger_SID=" + s + "; ";
		String req = "GET http://www.blogger.com/home HTTP/1.1"
				+ "\r\nHost: www.blogger.com" + "\r\nCookie: " + bb
				+ "\r\n\r\n";

		System.out.println(req);
		Socket js = new Socket("www.blogger.com", 80);
		PrintStream outStream = new PrintStream(js.getOutputStream());
		outStream.println(req);
		outStream.flush();

		// read response

		DataInputStream inStream = new DataInputStream(js.getInputStream());
		StringBuffer stb = new StringBuffer();
		try {
			for (int ch = inStream.read(); ch > 0; ch = inStream.read()) {
				System.out.print((char) ch);
				stb.append((char) ch);
				// if (stb.indexOf("\r\n\r\n") > -1)
				if (stb.indexOf("</html>") > -1)
					break;
			}
			s = stb.toString();
			String g2 = s.substring(s.indexOf("S=blogger"));
			g2 = g2.substring(0, g2.indexOf(";") + 1);

			System.out.println(s);

			System.err.println(bb);

		} catch (Exception e) {
			System.err.println(e);
		}
		inStream.close();
		outStream.close();
		js.close();

	}

}
