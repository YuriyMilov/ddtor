package go;

import java.io.*;
import java.net.*;

import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;

import com.google.gdata.client.GoogleService;
import com.google.gdata.data.Entry;
import com.google.gdata.data.Feed;

public class _blogs_31 {
	public static _image15 im = null;

	public static String kk = "", ss = "", g2 = "", bb = "", ga3t = "",
			gg = "GoogleAccountsLocale_session=en;", id = "", rr = "",tt="",sf="",s2="";

	public static int k = 0, j = 0;
	
	public static boolean ok = true;

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
			System.out.println("----> New Google Account:  " + kk);
			_goog_32(kk);
			System.out.println("----> Confirm email notification? (press Enter)");
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
	
	public static void _goog_32(String sn) throws Exception {
		tt = sta
				.rfu("file:///C:/Documents and Settings/Administrator.QQ.000/Desktop/222.txt");
		tt = sn;
		
		int v = Integer.parseInt(tt);
		while (ok) {
			acc();
			proxy();
			System.out.println(v + " try ...");
		}
		System.out.println("New Google account " + v + " created (222.txt)");
		v++;
		sta.wtf(String.valueOf(v),
						"C:/Documents and Settings/Administrator.QQ.000/Desktop/222.txt");
		ok = true;

	}

	public static void proxy() throws Exception {
		ServerSocket serso = new ServerSocket(8080);
		Socket socket = null;
		int i = 1;
		boolean run = true;
		boolean post = false;
		while (run) {
			// System.out.println("--- running proxy() ---");
			String s = "qq", ss = "";
			socket = serso.accept();
			DataInputStream get_in = new DataInputStream(socket
					.getInputStream());
			BufferedReader br = new BufferedReader(
					new InputStreamReader(get_in));
			OutputStreamWriter w = new OutputStreamWriter(socket
					.getOutputStream());
			StringBuffer sr = new StringBuffer();
			try {
				int f = 1;
				while (!s.equals("")) {
					s = br.readLine();
					sr = sr.append(s + "\r\n");

					// System.out.println(s);

					if (s.indexOf("Content-Length:") == 0) {
						String cl = s.substring(16);
						f = Integer.parseInt(cl);
						// System.err.println(f);
						post = true;
					}
				}
				if (post) {
					while (f-- > 0) {
						char ch = (char) br.read();
						sr.append(ch);
					}
					post = false;
					// System.err.println(sr);
					s2 = nac(sr.toString());

					// continue=http%3A%2F%2Fwww.google.ca%2F&dsh=924635655480092870&hl=en&t=null&Email=test11%40quicklydone.com&Passwd=tverskoy&PasswdAgain=tverskoy&p=&rmShown=1&smhhk=1&nshk=1&loc=CA&newaccounttoken=Hm_mLLetsjr8gZkjhhcTh4_K4cPluLyNMX06uAjEsBwEZGPfQnADtZSzcgbtUilf%3A-5f6L3HLsWTqiOeSuf638A&newaccounturl=Captcha%3Fctoken%3DHm_mLLetsjr8gZkjhhcTh4_K4cPluLyNMX06uAjEsBwEZGPfQnADtZSzcgbtUilf%253A-5f6L3HLsWTqiOeSuf638A&newaccounttoken_audio=-VirjpMSMP3uQ-oTM6zl_qXFf-sh9KiK9j22K8V2DoBv9tnFv2oL3aRiUHxm6MXd%3Af4iQ14CI6V-lI1ko-fnHjQ&newaccounturl_audio=Captcha%3Fctoken%3D-VirjpMSMP3uQ-oTM6zl_qXFf-sh9KiK9j22K8V2DoBv9tnFv2oL3aRiUHxm6MXd%253Af4iQ14CI6V-lI1ko-fnHjQ&newaccountcaptcha=skowni&privacy_policy_url=http%3A%2F%2Fwww.google.com%2Fintl%2Fen%2Fprivacy.html&requested_tos_language=en&served_tos_location=US&served_tos_language=en&submitbutton=I+accept.+Create+my+account.

					//System.out.println("********* POST *********");
					ss = "HTTP/1.1 200 OK\r\nContent-Type: text/html\r\n\r\n";
					// w.write(ss + i++ + " <br> <p> <form
					// action=http://qq:8080><input type=submit></form>" + s2);
					w.write(ss + i++ + " <br>" + s2);
					w.flush();
					w.close();
					run = false;
				} else {
					// System.out.println("*** GET ***");
					ss = "HTTP/1.1 200 OK\r\nContent-Type: text/html\r\n\r\n";
					// s = sf;
					// s=sta.rfu("file:///C:/Documents and
					// Settings/Administrator.QQ.000/Desktop/1111.txt");
					// w.write(ss + i++ + " <br>***<br>" + sf);
					w.write(ss + sf);
					w.flush();
					w.close();
				}

			} catch (Exception e1) {
				System.err.println("proxy() exception:\r\n" + e1);
			}
		}
		serso.close();
	}

	public static void acc() throws Exception {
		// System.out.println("*** acc() ***");
		int kmo = 6;
		char[] c = new char[kmo + 1];
		char[] cc = new char[kmo + 1];

		String req = "GET https://www.google.com/accounts/NewAccount?continue=http%3A%2F%2Fwww.google.ca%2F&hl=en  HTTP/1.1"
				+ "\r\nHost: www.google.com" + "\r\nCookie: " + gg + "\r\n\r\n";
		// System.out.println(req);
		SSLSocketFactory sslsocketfactory = (SSLSocketFactory) SSLSocketFactory
				.getDefault();
		SSLSocket sslsocket = (SSLSocket) sslsocketfactory.createSocket(
				"www.google.com", 443);

		PrintStream outStream = new PrintStream(sslsocket.getOutputStream());
		outStream.println(req);
		outStream.flush();

		// read response

		DataInputStream inStream = new DataInputStream(sslsocket
				.getInputStream());
		StringBuffer stb = new StringBuffer();
		try {

			for (int ch = inStream.read(); ch > 0; ch = inStream.read()) {
				stb.append((char) ch);
				// System.out.print((char) ch);
				for (int n = kmo; n > 0; n--)
					c[n] = c[n - 1];
				c[0] = (char) ch;
				for (int n = kmo; n > -1; n--)
					cc[n] = c[kmo - n];
				req = String.valueOf(cc);
				if (req.indexOf("</html>") > -1)
					break;
			}
		} catch (Exception e) {
			System.err.println(e);
		}
		String s = stb.toString();

		// if(s.indexOf("please access your email")>-1)
		// System.err.println("**************************");

		s = s.substring(s.indexOf("<form"));
		s = s.substring(0, s.indexOf("</form>") + 7);
		String dsh = s.substring(s.indexOf("name=\"dsh"));
		dsh = dsh.substring(dsh.indexOf("value") + 7);
		dsh = dsh.substring(0, dsh.indexOf("\""));
		s = sta.rep(s, "src=\"Captcha?",
				"src=\"https://www.google.com/accounts/Captcha?");
		s = sta.rep(s, "https://www.google.com/accounts/CreateAccount",
				"http://qq:8080/accounts/CreateAccount");
		String s10 = "", s11 = "", s12 = "", s13 = "";
		s13 = s.substring(s.indexOf("<img"));
		s13 = s13.substring(0, s13.indexOf(">") + 1);
		s13 = sta.rep(s13, "\r", "");
		s13 = sta.rep(s13, "\n", "");

		//System.out.println(s13);
		
		System.out.println("---->  goto http://qq:8080");

		s11 = s.substring(0, s.indexOf(">") + 1);
		s11 = sta.rep(s11, "onsubmit=\"return(onPreCreateAccount());\"", "");
		while (s.indexOf("<input") > -1) {
			s = s.substring(s.indexOf("<input") + 1);
			s12 = s.substring(0, s.indexOf(">") + 1);
			if (s12.indexOf("newaccountcaptcha") > -1)
				s13 = s13 + "\r\n<br><" + s12 + "<br>===================<br>";
			else
				s10 = s10 + "\r\n<" + s12;
		}
		s11 = s11 + s13 + s10 + "\r\n</form>";
		s11 = "<body OnLoad=\"document.createaccount.newaccountcaptcha.focus();\">"
				+ s11 + "</body>";
		sf = s11;

		inStream.close();
		outStream.close();
		sslsocket.close();
	}

	public static String nac(String s) throws Exception {
		// System.out.println("*** nac() ***");
		int kmo = 6;
		char[] c = new char[kmo + 1];
		char[] cc = new char[kmo + 1];
		s = sta.rep(s, "\r\nAccept-Encoding: gzip,deflate", "");
		s = sta.rep(s, "\r\nReferer: http://qq:8080/", "");
		s = sta.rep(s, "Host: qq:8080", "Host: www.google.com");
		s = sta.rep(s, "POST /", "POST https://www.google.com/");

		s = sta.rep(s, "Email=&Passwd=&PasswdAgain=", "Email=" + tt
				+ "%40quicklydone.com&Passwd=tverskoy&PasswdAgain=tverskoy");
		s = sta.rep(s, "PersistentCookie=yes", "PersistentCookie=no");
		s = sta.rep(s, "smhck=1&", "");

		SSLSocketFactory sslsocketfactory = (SSLSocketFactory) SSLSocketFactory
				.getDefault();
		SSLSocket sslsocket = (SSLSocket) sslsocketfactory.createSocket(
				"www.google.com", 443);

		PrintStream outStream = new PrintStream(sslsocket.getOutputStream());
		outStream.println(s);
		outStream.flush();

		// read response

		// System.out.println("\r\n********* response nac() *********\r\n");
		DataInputStream inStream = new DataInputStream(sslsocket
				.getInputStream());
		StringBuffer stb = new StringBuffer();
		try {

			for (int ch = inStream.read(); ch > 0; ch = inStream.read()) {
				stb.append((char) ch);
				// System.out.print((char) ch);
				for (int n = kmo; n > 0; n--)
					c[n] = c[n - 1];
				c[0] = (char) ch;
				for (int n = kmo; n > -1; n--)
					cc[n] = c[kmo - n];
				s = String.valueOf(cc);

				// if (s.indexOf("create") > -1)
				// ok=false;
				// else
				// ok=true;
				if (s.indexOf("</html>") > -1)
					break;
			}
		} catch (Exception e) {
			System.err.println(e);
		}
		s = stb.toString();

		if (s.indexOf("please access your email") > -1)
			ok = false;
		else if (s.indexOf("you already have") > -1)
			ok = true;

		inStream.close();
		outStream.close();
		sslsocket.close();

		return s;

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
			s = s.substring(s.indexOf("url='http://www.blogger.com/loginz") + 5);
			//s = s.substring(s.indexOf("loginz"));
			s = s.substring(0, s.indexOf("'"));
			s = s.substring(s.indexOf("auth") + 5);
			//s = s.substring(s.indexOf("auth") + 7);
			
			
		//	System.err.println(s);
			
			
			homes(s);
			//System.exit(0);
			
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
		 //System.out.println(s);

		if (s.indexOf("LSID=blogger") > -1) {
			s = s.substring(s.indexOf("url='http://www.blogger.com/loginz") + 5);
			s = s.substring(0, s.indexOf("'"));
			s = s.substring(s.indexOf("auth") + 5);
			 //System.err.println(s);
			 
			homes(s);
			 
		}

	}

	public static void homes(String s) throws Exception{
		System.out.println("--- Blogger Home ");
		bb = "blogger_SID=" + s + "; ";
		String req = "GET http://www.blogger.com/home HTTP/1.1"
				+ "\r\nHost: www.blogger.com" + "\r\nCookie: " + bb
				+ "\r\n\r\n";

		//System.out.println(req);
		try{
		Socket js = new Socket("www.blogger.com", 80);
		PrintStream outStream = new PrintStream(js.getOutputStream());
		outStream.println(req);
		outStream.flush();
		
		// read response

		DataInputStream inStream = new DataInputStream(js.getInputStream());
		StringBuffer stb = new StringBuffer();
		
		for (int ch = inStream.read(); ch > 0; ch = inStream.read()) {
				//System.out.print((char) ch);
				stb.append((char) ch);
				if (stb.indexOf("\r\n\r\n") > -1)
					break;
			}
			s = stb.toString();
			g2 = s.substring(s.indexOf("S=blogger"));
			g2 = g2.substring(0, g2.indexOf(";") + 1);
			bb = g2 + " " + bb;
			//System.err.println(s);

		inStream.close();
		outStream.close();
		js.close();

		
		} catch (Exception e) {
			System.err.println(e);
		}

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
