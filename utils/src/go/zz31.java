package go;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Date;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import com.google.gdata.client.GoogleService;
import com.google.gdata.data.Entry;
import com.google.gdata.data.HtmlTextConstruct;
import com.google.gdata.util.common.html.HtmlToText;

public class zz31 {
	public static int fn;
	public static void main(String[] args) throws Exception {
		long pause = 3600000;
		// args = new String[3];
		// args[0]="servlet/test";args[1]="servlet/last.txt";args[2]="servlet/zips";
		String bdir = args[0], flast = args[1], zips = args[2];
		String sid = "";
		while (true) {
			fn=1;
			boolean last = false;
			if (sta.rffw(flast).equals(""))
				last = true;
			File f = new File(bdir);
			for (int i = 0; i < f.list().length; i++) {
				String s = sta.rffw(flast);
				sid = "";
				sid = f.list()[i];
				sid = sid.substring(0, sid.length() - 4);
				if (!last) {
					if (sid.equals(s))
						last = true;
				} else {
					sta.wtf(sid, flast);
					File f2 = new File(bdir + "/" + sid + ".txt");
					long p = new Date().getTime() - f2.lastModified();
					if (p < pause) {
						System.out
								.println("\r\n\r\n"	+ String.valueOf(new java.util.Date()
										.toString().substring(11, 16))
										+ "  >  pauza "
										+ String
												.valueOf((int) ((pause - p) / 60000) + 1)
										+ " min.");
						Thread.sleep(pause - p);
					}
					BufferedReader br = new BufferedReader(
							new InputStreamReader(new FileInputStream(f2)));
					System.out.print("\r\n " + sid + " - " + fn + " ");
					while ((s = br.readLine()) != null) {
						//ss = ss + s + "\r\n";

						try {
							post_zip(s, sid, zips);
						} catch (Exception e) {
							System.out.print("\r\n\r\n" + HtmlToText.htmlToPlainText(e
									.toString())+" \r\n\r\n  sleep 11 sec   ");
							Thread.sleep(11000);
						}
					}
					br.close();
					//if (ss.length() > 2)
					//	ss = ss.substring(0, ss.length() - 2);
					
					sta.wtf(sta.rffw(bdir + "/" + sid + ".txt"), bdir + "/" + sid + ".txt");
				}
			}
			last = false;
			sta.wtf("", flast);
		}

	}

	public static void wtb(String s, String blog, String id) throws Exception {

		URL postUrl = new URL("http://" + blog
				+ ".blogspot.com/feeds/posts/default");
		Entry myEntry = new Entry();
		myEntry.setContent(new HtmlTextConstruct(s));
		GoogleService myService = new GoogleService("blogger", "");
		myService.setUserCredentials(id + "@quicklydone.com", "tverskoy");
		myService.insert(postUrl, myEntry);
		System.out.print(".");
		fn++;
	}

	public static void post_zip(String blog, String id, String zips)
			throws Exception {
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
		// ////////////////////////////////////
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
		wtb(s, blog, id);
	}
}
