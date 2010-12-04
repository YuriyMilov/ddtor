package go;

import java.io.*;
import java.net.*;
import com.google.gdata.client.GoogleService;
import com.google.gdata.data.Entry;
import com.google.gdata.data.Feed;

public class _blogs_32 {
	public static _image15 im = null;

	public static String kk = "", ss = "", g2 = "", bb = "", ga3t = "",
			gg = "GoogleAccountsLocale_session=en;", id = "", rr = "";

	public static int k = 0, j = 0;

	public static void main(String args[]) throws Exception {
		System.out.print("\r\nStart: ");
		String tt = sta.rfu("file:///C:/Documents and Settings/"
				+ "Administrator.QQ.000/Desktop/11.txt");
		int v = Integer.parseInt(tt);
		kk = String.valueOf(v);
		System.out.println(kk + "@quicklydone.com");

		GoogleService myService = new GoogleService("blogger", "");
		try {
			myService.setUserCredentials(kk + "@quicklydone.com", "tverskoy");
		} catch (Exception e) {
			System.out.println("----> New Google Account  " + kk);
			new _goog_32(kk);
			System.out.println("----> Confirm email notification?  ");
			BufferedReader br = new BufferedReader(new InputStreamReader(
					System.in));

			if (br.readLine().length() > -1)
				myService.setUserCredentials(kk + "@quicklydone.com",
						"tverskoy");
			else {
				System.out.println("----> ???  ");
				Thread.sleep(3000);
			}
		}
		Feed blogs = myService.getFeed(new URL(
				"http://www.blogger.com/feeds/default/blogs"), Feed.class);
		j = blogs.getEntries().size();
		System.out.println("\r\n          " + j + " blogs\r\n");
		k = j;
		while (j < 22) {
			rr = String.valueOf((new java.util.Date().getTime())).substring(9);
			login_g();
		}
		addr();
		sta.wtf(String.valueOf(v + 1), "C:/Documents and Settings/"
				+ "Administrator.QQ.000/Desktop/11.txt");
		System.out.println("");
		System.out.println("***********************");
		System.out.println("---- ALL DONE  -----");
		System.out.println("***********************");
	}

	public static void login_g() throws Exception {
		System.out.println("--- Login ");
		String req = "GET https://www.google.com/accounts/LoginAuth?continue=http%3A%2F%2Fwww.google.ca%2F&Email="
				+ kk
				+ "%40quicklydone.com&Passwd=tverskoy&PersistentCookie=yes&rmShown=1&signIn=Sign+in"
				+ " HTTP/1.1" + "\r\nHost: www.google.com\r\n\r\n";
		gg = sta.set_google_cookie(sta.get_ssl_header(req));
		box();
	}

	public static void box() throws Exception {
		System.out.println("--- Box ");
		String s = "GET https://www.google.com/accounts/ServiceLoginBox?service=blogger&amp;continue=http%3A%2F%2Fwww.blogger.com%2Floginz%3Fd%3D%252Fstart&amp;passive=true&amp;alwf=true&amp;uilel=3&amp;skipvpage=true&amp;rm=false&amp;naui=8&amp;showra=1&amp;fpui=2&amp;hl=en&amp;nui=8&amp;roeu=https%3A%2F%2Fwww.blogger.com%2Flogin.g  HTTP/1.1"
				+ "\r\nHost: www.google.com" + "\r\nCookie: " + gg + "\r\n\r\n";
		s = sta.get_ssl_header(s);
		ga3t = s.substring(s.indexOf("Set-Cookie:") + 12);
		ga3t = ga3t.substring(0, ga3t.indexOf("\r\n"));
		gg = ga3t + "; " + gg;
		ch();
	}

	public static void ch() throws Exception {
		System.out.println("--- CheckCookie  ");
		String s = "GET https://www.google.com/accounts/"
				+ "CheckCookie?continue=http%3A%2F%2"
				+ "Fwww.blogger.com%2Floginz%3Fd%3D%252F"
				+ "start&service=blogger&hl=en&chtml="
				+ "LoginDoneHtml&skipvpage=true&naui=8  HTTP/1.1"
				+ "\r\nHost: www.google.com" + "\r\nCookie: " + gg
				+ "; GoogleAccountsLocale_session=en;" + "\r\n\r\n";
		s = sta.get_ssl_page(s);
		if (s.indexOf("LSID=blogger") > -1) {
			s = s
					.substring(s.indexOf("url='http://www.blogger.com/loginz") + 5);
			s = s.substring(0, s.indexOf("'"));
			s = s.substring(s.indexOf("auth") + 5);
			// System.err.println(s);
			homes(s);
		} else {
			String ss[] = new String[11];
			int i = 0;
			s = s.substring(s.indexOf("<form") + 1);
			String f = "<" + s.substring(0, s.indexOf(">") + 1);
			// System.out.println(f);
			while (s.indexOf("<input") > -1) {
				s = s.substring(s.indexOf("<input") + 1);
				ss[i] = "<" + s.substring(0, s.indexOf(">") + 1);
				// System.out.println(ss[i]);
				f = f + "\r\n" + ss[i];
			}
			f = f + "\r\n<form>";

			String dsh = f.substring(f.indexOf("dsh"));
			dsh = dsh.substring(dsh.indexOf("value=") + 7);
			dsh = dsh.substring(0, dsh.indexOf(">") - 1);
			// System.err.println(dsh);
			String timeStmp = f.substring(f.indexOf("timeStmp"));
			timeStmp = timeStmp.substring(timeStmp.indexOf("value=") + 7);
			timeStmp = timeStmp.substring(0, timeStmp.indexOf(">") - 1);
			// System.err.println(timeStmp);
			String secTok = f.substring(f.indexOf("secTok"));
			secTok = secTok.substring(secTok.indexOf("value=") + 7);
			secTok = secTok.substring(0, secTok.indexOf(">") - 1);
			// System.err.println(secTok);
			s = "continue=http%3A%2F%2Fwww.blogger.com%2Floginz%3Fd%3D%252Fstart&service=blogger&naui=8&skipvpage=true&dsh="
					+ dsh
					+ "&hl=en&timeStmp="
					+ timeStmp
					+ "&secTok="
					+ secTok
					+ "&displayname="
					+ kk
					+ "&termsofservice=1&submitbutton=Continue";
			dis_name(s);
		}
	}

	public static void dis_name(String rr) throws Exception {
		System.err.println("---- NEW BLOGGER SERVICE ");
		String s = "POST https://www.google.com/accounts/CreateServiceAccount?continue=http%3A%2F%2Fwww.blogger.com%2Floginz%3Fd%3D%252Fstart&service=blogger&hl=en&chtml=LoginDoneHtml&skipvpage=true&naui=8 HTTP/1.1"
				+ "\r\nHost: www.google.com"
				+ "\r\nAccept: text/xml,application/xml,application/xhtml+xml,text/html;q=0.9,text/plain;q=0.8,image/png,*/*;q=0.5"
				// + "\r\nKeep-Alive: 300"
				// + "\r\nConnection: keep-alive"
				+ "\r\nContent-Type: application/x-www-form-urlencoded"
				+ "\r\nCookie: "
				+ gg
				+ "\r\nContent-Length: "
				+ String.valueOf(rr.length()) + "\r\n\r\n" + rr;
		s = sta.get_ssl_page(s);
		// System.out.println(s);

		if (s.indexOf("LSID=blogger") > -1) {
			s = s
					.substring(s.indexOf("url='http://www.blogger.com/loginz") + 5);
			s = s.substring(0, s.indexOf("'"));
			s = s.substring(s.indexOf("auth") + 5);
			// System.err.println(s);
			homes(s);
		}

	}

	public static void homes(String s) throws Exception {
		System.out.println("--- Blogger Home ");
		bb = "blogger_SID=" + s + "; ";
		String req = "GET http://www.blogger.com/home HTTP/1.1"
				+ "\r\nHost: www.blogger.com" + "\r\nCookie: " + bb
				+ "\r\n\r\n";

		// System.out.println(req);
		Socket js = new Socket("www.blogger.com", 80);
		PrintStream outStream = new PrintStream(js.getOutputStream());
		outStream.println(req);
		outStream.flush();

		// read response

		DataInputStream inStream = new DataInputStream(js.getInputStream());
		StringBuffer stb = new StringBuffer();
		try {
			for (int ch = inStream.read(); ch > 0; ch = inStream.read()) {
				// System.out.print((char) ch);
				stb.append((char) ch);
				if (stb.indexOf("\r\n\r\n") > -1)
					break;
			}
			s = stb.toString();
			g2 = s.substring(s.indexOf("S=blogger"));
			g2 = g2.substring(0, g2.indexOf(";") + 1);
			bb = g2 + " " + bb;
			// System.err.println(bb);
		} catch (Exception e) {
			System.err.println(e);
		}
		inStream.close();
		outStream.close();
		js.close();
		create_blog("", "");
	}

	public static void create_blog(String title, String address)
			throws Exception {
		System.out.println("--- Blog ");

		String s = "GET http://www.blogger.com/create-blog.g HTTP/1.1"
				+ "\r\nHost: www.blogger.com" + "\r\nCookie: " + bb + "; "
				+ "\r\n\r\n";

		// System.out.println(s);

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
				// break;
				if (stb.indexOf("</html>") > -1 || stb.indexOf("</HTML>") > -1)
					break;

			}
		} catch (Exception e) {
			System.err.println(e);
		}
		inStream.close();
		outStream.close();
		js.close();

		s = stb.toString();

		s = s.substring(s.indexOf("<form"));
		s = s.substring(0, s.indexOf("</form") + 7);
		s = sta.rep(s, "<input", "\r\n<input");

		String st = s.substring(s.indexOf("securityToken") + 7);
		st = st.substring(st.indexOf("value") + 7);
		st = st.substring(0, st.indexOf(">") - 1);
		st = sta.rep(st, "=:", "%3D%3A");

		String sz = sta.zir("zir");
		sz = sz.substring(0, sz.indexOf(" ")).toUpperCase();
		// System.out.println("**********\r\nTitle "+sz);

		String bn = sz
				+ String.valueOf((new java.util.Date().getTime())).substring(9);

		s = "reqId=&blogID=&securityToken=" + st + "&blogtitle=" + sz
				+ "&blogspotname=" + bn + "&submit=Continue";

		create_blog2(s);

		// crecap(s);
	}

	public static void create_blog2(String s) throws Exception {
		System.out.println("--- Blog 2 ");
		s = "POST http://www.blogger.com/create-blog.do HTTP/1.1"
				+ "\r\nHost: www.blogger.com"
				+ "\r\nAccept: text/xml,application/xml,application/xhtml+xml,text/html;q=0.9,text/plain;q=0.8,image/png,*/*;q=0.5"
				+ "\r\nContent-Type: application/x-www-form-urlencoded"
				+ "\r\nCookie: " + bb + "\r\nContent-Length: "
				+ String.valueOf(s.length()) + "\r\n\r\n" + s;

		// System.out.println(s);

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
				// break;
				if (stb.indexOf("</html>") > -1 || stb.indexOf("</HTML>") > -1)
					break;

			}
		} catch (Exception e) {
			System.err.println(e);
		}
		inStream.close();
		outStream.close();
		js.close();

		s = stb.toString();
		// System.out.println(s);

		if (s.indexOf("Location:") > -1) {
			s = s.substring(s.indexOf("Location:"));
			s = s.substring(10, s.indexOf("\r\n"));
			System.err.println(s);
			// create_first(s);
			templ(s);
		} else {
			// System.err.println("\r\n Word Verification ? \r\n");

			if (s.indexOf("src=\"/captcha") == -1)
				ss = "http://www.blogger.com/img/captcha.jpg";
			else {
				s = sta.rep(s, "src=\"/captcha",
						"src=\"http://www.blogger.com/captcha");
				g2 = s;

				// System.out.println(ss);
				// sta.wtf(s, "C:/Documents and Settings/"
				// + "Administrator.QQ.000/Desktop/vvvvvvvvvv.htm");

				s = s.substring(s.indexOf("Visual verification"));
				s = s.substring(0, s.indexOf(">"));
				s = s.substring(s.indexOf("http"));
				ss = s.substring(0, s.indexOf("\""));
				ss = sta.rep(ss, "&amp;", "&");
				// System.out.println(s);
			}
			captcha();

		}
	}

	public static void create_first(String s) throws Exception {
		System.out.println("--- First blog \r\n");
		s = "GET "
				+ s
				+ " HTTP/1.1"
				+ "\r\nHost: www.blogger.com"
				+ "\r\nAccept: text/xml,application/xml,application/xhtml+xml,text/html;q=0.9,text/plain;q=0.8,image/png,*/*;q=0.5"
				+ "\r\nContent-Type: application/x-www-form-urlencoded"
				+ "\r\nCookie: " + bb + "\r\n\r\n";

		// System.out.println(s);

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
				// break;
				if (stb.indexOf("</html>") > -1 || stb.indexOf("</HTML>") > -1)
					break;

			}
		} catch (Exception e) {
			System.err.println(e);
		}
		inStream.close();
		outStream.close();
		js.close();

		s = stb.toString();
		// System.out.println(222);
		// sta.wtf(stb.toString(), "C:/Documents and Settings/"
		// + "Administrator.QQ.000/Desktop/zzzzzzzzzz.htm");

	}

	public static void captcha() throws Exception {
		// ServerSocket serso = new ServerSocket(8082);
		// Socket socket = null;
		// while (run) {
		System.out.println("--- Captcha ");
		String s = "qq";
		// socket = serso.accept();

		try {
			// System.out.println("");
			// System.out.println("--- GET IMAGE CAPTCHA ---");

			s = "GET "
					+ ss
					+ " HTTP/1.1"
					+ "\r\nHost: www.blogger.com"
					+ "User-Agent: Mozilla/5.0 (Windows; U; Windows NT 5.0; en-US; rv:1.8.1.1) Gecko/20061204 Firefox/2.0.0.1"
					+ "\r\nAccept: image/png,*/*;q=0.5"
					+ "\r\nAccept-Encoding: gzip,deflate" + "\r\nCookie: " + bb
					+ "; " + "\r\n\r\n";

			// System.out.println(s);

			Socket js = new Socket("www.blogger.com", 80);
			PrintStream outStream = new PrintStream(js.getOutputStream());
			outStream.println(s);
			outStream.flush();

			// System.out.println("=== read response ===");
			BufferedInputStream bis = new BufferedInputStream(js
					.getInputStream());
			// BufferedOutputStream bos = new BufferedOutputStream(socket
			// .getOutputStream());

			OutputStream bos2 = new FileOutputStream("captcha/" + rr + ".jpg");

			int b;
			boolean b13 = false;
			StringBuffer sbuf = new StringBuffer();
			try {
				int iv = 10000;
				while (iv-- > 0 && (b = bis.read()) != -1) {
					// System.out.print((char)b);
					// bos.write(b);
					// bos.flush();
					if (b13)
						bos2.write(b);
					sbuf.append((char) b);
					if (!b13 && sbuf.indexOf("\r\n\r\n") > -1) {
						b13 = true;
						String v = sbuf.toString();
						v = v.substring(v.indexOf("Content-Length: "));
						v = v.substring(16, v.indexOf("\r\n"));
						iv = Integer.parseInt(v);
						// System.out.println(iv);
						// System.out.println("+++++++++++ CAPCHA 2
						// +++++++++++");
					}
					// System.err.println(iv);
				}

			} catch (Exception e) {
				System.err.println(e);
			}
			// System.err.println(sbuf);
			bis.close();
			// bos.close();
			bos2.flush();
			bos2.close();
			js.close();
			outStream.close();
		} catch (Exception e1) {
			System.err.println("proxy() exception:\r\n" + e1);
		}
		// }
		// serso.close();

		im = new _image15("captcha/" + rr + ".jpg");

		g2 = g2.substring(g2.indexOf("<form"));
		g2 = g2.substring(0, g2.indexOf("</form") + 7);
		g2 = sta.rep(g2, "<input", "\r\n<input");
		// System.out.println(g2);
		// sta.wtf(g2.toString(), "C:/Documents and Settings/"
		// + "Administrator.QQ.000/Desktop/zzzzzzzzzz.htm");

		s = g2;
		String sz = sta.zir("zir");
		sz = sz.substring(0, sz.indexOf(" ")).toUpperCase();
		// System.err.println("Title "+sz);

		String blogspotname = sz + rr;

		String st = s.substring(s.indexOf("securityToken") + 7);
		st = st.substring(st.indexOf("value") + 7);
		st = st.substring(0, st.indexOf(">") - 1);
		st = sta.rep(st, "=:", "%3D%3A");
		// System.err.println("securityToken: " + st);

		String ck = s.substring(s.indexOf("captchaKey") + 11);
		ck = ck.substring(ck.indexOf("value") + 7);
		ck = ck.substring(0, ck.indexOf(">") - 1);
		// System.out.println("captchaKey: " + ck);

		System.out.println("*****************");
		System.out.println("     CAPTCHA " + k + " -  ?   ");
		System.out.println("*****************\r\n");

		String captchaAnswer = new BufferedReader((new InputStreamReader(
				System.in))).readLine();

		s = "reqId=&blogID=&securityToken=" + st + "&blogtitle=" + sz
				+ "&blogspotname=" + blogspotname + "&captchaKey=" + ck
				+ "&captchaAnswer=" + captchaAnswer + "&submit=Continue";

		// System.out.println("OK " + captchaAnswer);

		crecap(s);

	}

	public static void crecap(String s) throws Exception {
		// System.out.println("\r\n--- Captcha done");
		s = "POST http://www.blogger.com/create-blog.do HTTP/1.1"
				+ "\r\nHost: www.blogger.com"
				+ "\r\nAccept: text/xml,application/xml,application/xhtml+xml,text/html;q=0.9,text/plain;q=0.8,image/png,*/*;q=0.5"
				+ "\r\nContent-Type: application/x-www-form-urlencoded"
				+ "\r\nCookie: " + bb + "\r\nContent-Length: "
				+ String.valueOf(s.length()) + "\r\n\r\n" + s;

		// System.out.println(s);

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
				// break;
				if (stb.indexOf("</html>") > -1 || stb.indexOf("</HTML>") > -1)
					break;

			}
		} catch (Exception e) {
			System.err.println(e);
		}
		inStream.close();
		outStream.close();
		js.close();

		s = stb.toString();
		// sta.wtf(s.toString(), "C:/Documents and Settings/"
		// + "Administrator.QQ.000/Desktop/zzzzzzzzzz.htm");
		// System.out.println(111);

		if (im != null)
			im.dispose();

		if (s.indexOf("Location:") > -1) {
			s = s.substring(s.indexOf("Location:"));
			s = s.substring(10, s.indexOf("\r\n"));
			// System.err.println(s);
			templ(s);
		} else {
			System.out.println("\r\n\r\n********************");
			System.err.println("\r\n Error... \r\n");
			System.out.println("********************\r\n\r\n");
		}

	}

	public static void templ(String s) throws Exception {
		System.out.println("\r\n********************");
		System.out.println("       Captcha  " + k + "  -  OK    ");
		System.out.println("********************\r\n");
		s = "GET "
				+ s
				+ " HTTP/1.1"
				+ "\r\nHost: www.blogger.com"
				+ "\r\nAccept: text/xml,application/xml,application/xhtml+xml,text/html;q=0.9,text/plain;q=0.8,image/png,*/*;q=0.5"
				+ "\r\nContent-Type: application/x-www-form-urlencoded"
				+ "\r\nCookie: " + bb + "\r\n\r\n";

		// System.out.println(s);

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
				// break;
				if (stb.indexOf("</html>") > -1 || stb.indexOf("</HTML>") > -1)
					break;

			}
		} catch (Exception e) {
			System.err.println(e);
		}
		inStream.close();
		outStream.close();
		js.close();

		s = stb.toString();
		// System.out.println(333);
		// sta.wtf(stb.toString(), "C:/Documents and Settings/"
		// + "Administrator.QQ.000/Desktop/zzzzzzzzzz.htm");

		String st = s.substring(s.indexOf("securityToken") + 7);
		st = st.substring(st.indexOf("value") + 7);
		st = st.substring(0, st.indexOf(">") - 1);
		st = sta.rep(st, "=:", "%3D%3A");
		// System.out.println("securityToken: " + st);

		id = s.substring(s.indexOf("blogID") + 8);
		id = id.substring(id.indexOf("value") + 7);
		id = id.substring(0, id.indexOf(">") - 1);
		// System.out.println("blogID: " + id);

		s = "blogID=" + id + "&reqId=&securityToken=" + st
				+ "&template=minima&submit=Continue";
		// System.out.println(s);

		temp2(s);

	}

	public static void temp2(String s) throws Exception {
		System.out.println("--- Template ");
		s = "POST http://www.blogger.com/choose-template-new.do HTTP/1.1"
				+ "\r\nHost: www.blogger.com"
				+ "\r\nAccept: text/xml,application/xml,application/xhtml+xml,text/html;q=0.9,text/plain;q=0.8,image/png,*/*;q=0.5"
				+ "\r\nContent-Type: application/x-www-form-urlencoded"
				+ "\r\nCookie: " + bb + "; B2I=" + kk + "; "
				+ "\r\nContent-Length: " + String.valueOf(s.length())
				+ "\r\n\r\n" + s;

		// System.err.println(s);

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
				// break;
				if (stb.indexOf("</html>") > -1 || stb.indexOf("</HTML>") > -1)
					break;

			}
		} catch (Exception e) {
			System.err.println(e);
		}
		inStream.close();
		outStream.close();
		js.close();

		s = stb.toString();
		// sta.wtf(s.toString(), "C:/Documents and Settings/"
		// + "Administrator.QQ.000/Desktop/zzzzzzzzzz.htm");
		// System.out.println(444);

		s = "http://www.blogger.com/rearrange?blogID=" + id
				+ "&action=editWidget&sectionId=sidebar&widgetType=HTML";

		get_js(s);

	}

	public static void get_js(String s) throws Exception {
		System.out.println("--- Get JS  ");

		s = "GET "
				+ s
				+ " HTTP/1.1"
				+ "\r\nHost: www.blogger.com"
				+ "\r\nAccept: text/xml,application/xml,application/xhtml+xml,text/html;q=0.9,text/plain;q=0.8,image/png,*/*;q=0.5"
				+ "\r\nContent-Type: application/x-www-form-urlencoded"
				+ "\r\nCookie: " + bb + "\r\n\r\n";

		// System.err.println(s);

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
				// break;
				if (stb.indexOf("</html>") > -1 || stb.indexOf("</HTML>") > -1)
					break;
			}
		} catch (Exception e) {
			System.err.println(e);
		}

		inStream.close();
		outStream.close();
		js.close();

		s = stb.toString();
		String st = "";

		if (s.indexOf("Set-Cookie: S=blogger=") > -1) {
			st = s.substring(s.indexOf("Set-Cookie: S=blogger=") + 12);
			st = st.substring(0, st.indexOf(";") + 1);
			bb = bb + st;
			// System.out.println("Set-Cookie: " + bb);
		}
		String sp = "";

		if (s.indexOf("_SetPageActionUrl") > -1) {
			sp = s.substring(s.indexOf("_SetPageActionUrl"));
			sp = sp.substring(sp.indexOf(","));
			sp = sp.substring(0, sp.indexOf(")") - 1);
			sp = sp.substring(sp.indexOf("'") + 1);
			sp = sta.rep(sp, "=:", "%3D%3A");

			// System.out.println("pageToken: " +sp);
		}

		// sta.wtf(s.toString(), "C:/Documents and Settings/"
		// + "Administrator.QQ.000/Desktop/zzzzzzzzzz.htm");

		if (s.indexOf("securityToken") > -1) {
			st = s.substring(s.indexOf("securityToken") + 7);
			st = st.substring(st.indexOf("value") + 7);
			st = st.substring(0, st.indexOf(">") - 1);
			st = sta.rep(st, "=:", "%3D%3A");
			// System.out.println("securityToken: " + st);
		} else
			System.err.println("No securityToken.....");
		String cont = "%3Cscript%20type%3D%22text%2Fjavascript%22%3E%3C!--%0Agoogle_ad_client%20%3D%20%22pub-7319282935903431%22%3B%0Agoogle_ad_width%20%3D%20120%3B%0Agoogle_ad_height%20%3D%2090%3B%0Agoogle_ad_format%20%3D%20%22120x90_0ads_al%22%3B%0Agoogle_ad_channel%20%3D%20%22%22%3B%0A%2F%2F--%3E%0A%3C%2Fscript%3E%0A%3Cscript%20src%3D%22http%3A%2F%2Fpagead2.googlesyndication.com%2Fpagead%2Fshow_ads.js%22%20%0A%20type%3D%22text%2Fjavascript%22%3E%0A%3C%2Fscript%3E%0A%3Cbr%2F%3E%0A%3Cbr%2F%3E%0A%3Cscript%20type%3D%22text%2Fjavascript%22%3E%3C!--%0Agoogle_ad_client%20%3D%20%22pub-7319282935903431%22%3B%0Agoogle_ad_width%20%3D%20160%3B%0Agoogle_ad_height%20%3D%20600%3B%0Agoogle_ad_format%20%3D%20%22160x600_as%22%3B%0Agoogle_ad_type%20%3D%20%22text%22%3B%0Agoogle_ad_channel%20%3D%20%22%22%3B%0A%2F%2F--%3E%0A%3C%2Fscript%3E%0A%3Cscript%20src%3D%22http%3A%2F%2Fpagead2.googlesyndication.com%2Fpagead%2Fshow_ads.js%22%20%0A%20type%3D%22text%2Fjavascript%22%3E%0A%3C%2Fscript%3E";
		// action=addnew&widgetId=&widgetType=HTML&responseType=js&content=2222222222&title=&securityToken=g2uRjFqS4nLze7qEw54Xm12W8L8%3D%3A1183041841487&sectionid=sidebar&pageToken=qlywaCMXPyrB5ZcZ0Wrs0mlv720%3D%3A1183041841464===
		// read response ===
		s = "action=addnew&widgetId=&widgetType=HTML&responseType=js&content="
				+ cont + "&title=&securityToken=" + st
				+ "&sectionid=sidebar&pageToken=" + sp;
		// System.err.println(s);
		post_js(s);

	}

	public static void post_js(String s2) throws Exception {
		System.out.println("--- POST JS ");
		String s = "POST http://www.blogger.com/display?blogID="
				+ id
				+ " HTTP/1.1"
				+ "\r\nHost: www.blogger.com"
				+ "\r\nAccept: text/xml,application/xml,application/xhtml+xml,text/html;q=0.9,text/plain;q=0.8,image/png,*/*;q=0.5"
				+ "\r\nContent-Type: application/x-www-form-urlencoded"
				+ "\r\nCookie: " + bb + "\r\nContent-Length: "
				+ String.valueOf(s2.length()) + "\r\n\r\n" + s2;

		// System.out.println(s);

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
				if (stb.indexOf("\r\n\r\n") > -1)
					break;
				// if (stb.indexOf("</html>") > -1 || stb.indexOf("</HTML>") >
				// -1)
				// break;
			}
		} catch (Exception e) {
			System.err.println(e);
			k--;
		}
		inStream.close();
		outStream.close();
		js.close();
		k++;
		j = k;

		// sta.wtf(s.toString(), "C:/Documents and Settings/"
		// + "Administrator.QQ.000/Desktop/zzzzzzzzzz.htm");
		// System.out.println("JS - OK");

	}

	public static void addr() throws Exception {
		String id = "";
		// int bb = 11, bmax = 63;
		int bb = Integer.parseInt(sta.rfu("file:///C:/Documents and Settings/"
				+ "Administrator.QQ.000/Desktop/11.txt")), bmax = bb + 1;
		while (bb < bmax) {
			id = String.valueOf(bb);
			System.out.println("\r\n***   " + id + "   ***\r\n");
			GoogleService myService = new GoogleService("blogger", "");
			myService.setUserCredentials(id + "@quicklydone.com", "tverskoy");
			Feed blogs = myService.getFeed(new URL(
					"http://www.blogger.com/feeds/default/blogs"), Feed.class);
			String sf = "C:/Documents and Settings/"
					+ "Administrator.QQ.000/Desktop/" + id + ".txt";
			sta.wtf("", sf);
			for (int j = 0; j < blogs.getEntries().size(); j++) {
				Entry er = blogs.getEntries().get(j);
				// String ss =er.getSummary().getPlainText();
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
