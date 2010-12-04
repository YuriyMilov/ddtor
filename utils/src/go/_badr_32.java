package go;

import java.net.*;
import com.google.gdata.client.GoogleService;
import com.google.gdata.data.Entry;
import com.google.gdata.data.Feed;

public class _badr_32 {
	public static void main(String[] args) throws Exception {
		String id = "";
		int bb = 11, bmax = 175;
		//int bb = Integer.parseInt(sta.rfu("file:///C:/Documents and Settings/"
		//		+ "Administrator.QQ.000/Desktop/11.txt")), bmax = bb+1;
		while (bb < bmax) {
			id = String.valueOf(bb);
			System.out.println("\r\n***   " + id + "   ***\r\n");
			GoogleService myService = new GoogleService("blogger", "");
			myService.setUserCredentials(id + "@quicklydone.com", "tverskoy");
			Feed blogs = myService.getFeed(new URL(
					"http://www.blogger.com/feeds/default/blogs"), Feed.class);
			String sf="C:/Documents and Settings/"
				+ "Administrator.QQ.000/Desktop/test/"
				+ id + ".txt";
				sta.wtf("", sf);
			for (int j = 0; j < blogs.getEntries().size(); j++) {
				Entry er = blogs.getEntries().get(j);
				//String ss =er.getSummary().getPlainText();
				String h = er.getHtmlLink().getHref();
				h = h.substring(7);
				h = h.substring(0, h.indexOf("."));
				sta.wtft(h, sf);
				System.out.println(h);
			}
			bb++;
		}
	}

}
