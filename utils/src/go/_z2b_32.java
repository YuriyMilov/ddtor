package go;

import java.io.*;
import java.net.*;
import java.util.zip.*;
import java.util.*;
import com.google.gdata.client.GoogleService;
import com.google.gdata.data.Entry;
import com.google.gdata.data.Feed;
import com.google.gdata.data.HtmlTextConstruct;

public class _z2b_32 {
	public static void main(String[] args) throws Exception {
		String id = "";
		int k = 0;
		while (true) {
//			int bb = 11, bmax = 63;
			int bb = 63, bmax = 64;
			try {
				int n = 0;
				// BufferedReader br = new BufferedReader(new InputStreamReader(
				// new FileInputStream("_id.txt")));
				// while ((id = br.readLine()) != null) {
				while (bb < bmax) {
					id = String.valueOf(bb);
					System.out.print("\r\n\r\n " + k + "      ******  [  " + id
							+ "  ]  ******\r\n\r\n");
					GoogleService myService = new GoogleService("blogger", "");
					myService.setUserCredentials(id + "@quicklydone.com",
							"tverskoy");
					Feed blogs = myService.getFeed(new URL(
							"http://www.blogger.com/feeds/default/blogs"),
							Feed.class);
					for (int j = 0; j < blogs.getEntries().size(); j++) {
						Entry er = blogs.getEntries().get(j);
						long t = er.getUpdated().getValue();
						long t1 = new Date().getTime();
						int d = (int) ((t1 - t) / 60000);
						String title = er.getTitle().getPlainText();
						System.out.print(" "+ d + " ");
						if (d > 64) {

							System.out.print(title);
							String url = er.getHtmlLink().getHref();
							URL postUrl = new URL(url + "feeds/posts/default");
							er = new Entry();
							er.setTitle((new HtmlTextConstruct("")));
							er.setContent(new HtmlTextConstruct(zir("zips")));
							myService.insert(postUrl, er);
						}
					n++;
					}
				bb++;
				}
				// br.close();
				String d1 = new Date().toString().substring(11, 16);
				String d2 = new Date(300000 + new Date().getTime()).toString()
						.substring(11, 16);
				System.out.println("\r\n\r\n               Pause from " + d1
						+ "  to " + d2 + " \r\nn = " + n);
				Thread.sleep(300000);
			} catch (Exception e) {
				System.out.println("\r\n\r\n   Exception pause 1 min.  \r\n");
				System.out
						.println(com.google.gdata.util.common.html.HtmlToText
								.htmlToPlainText(e.toString()));
				System.out.println("\r\n\r\n   "
						+ new Date().toString().substring(11, 16) + "\r\n");
				Thread.sleep(60000);
			}
		}
	}

	public static String zir(String zips) throws Exception {
		String s = "", ss = "", sz = "";
		int n = 0;
		File f = new File(zips);
		for (int i = 0; i < f.list().length; i++) {
			s = f.list()[i];
			s = s.substring(s.indexOf("-") + 1, s.indexOf("."));
			n = n + Integer.parseInt(s);
		}
		int j = (int) (n * Math.random());
		n = 0;
		for (int i = 0; i < f.list().length; i++) {
			s = f.list()[i];
			sz = s;
			s = s.substring(s.indexOf("-") + 1, s.indexOf("."));
			n = n + Integer.parseInt(s);
			if (j < n) {
				break;
			}
		}
		// --------------------------------------------------------------
		s = "";
		int i = Integer.parseInt(sz.substring(sz.indexOf("-") + 1, sz
				.indexOf(".")));
		int k = (int) (i * Math.random()) + 1;
		ZipFile zipFile = new ZipFile(new File(zips + "/" + sz));
		ZipEntry entry = zipFile.getEntry(k + ".txt");
		BufferedReader br = new BufferedReader(new InputStreamReader(zipFile
				.getInputStream(entry), "UTF-8"));
		while ((ss = br.readLine()) != null) {
			s = s + ss;
		}
		br.close();
		zipFile.close();
		return s;
	}
}
