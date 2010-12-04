package go;

import java.net.*;
import java.io.*;

import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;

public class _goog_33 {
	public _goog_33() {
		super();
		// TODO Auto-generated constructor stub
	}


	public static String gg = "GoogleAccountsLocale_session=en;";
	public static String s2 = "";
	public static String sf = "";
	public static String tt = "";
	public static boolean ok=true;

	public static void main(String args[]) throws Exception {
		System.out.println("--- start ---");
//we have sent an email message
		//23-27
		tt="";
		
		while(true)	{
			tt=sta.rfu("file:///C:/Documents and Settings/Administrator.QQ.000/Desktop/222.txt");
			int v=Integer.parseInt(tt);
			System.out.println(v+"@quicklydone.com");
			acc();
			proxy();
			if(ok)sta.wtf(String.valueOf(v+1), "C:/Documents and Settings/Administrator.QQ.000/Desktop/222.txt");
		}
	}

	public static void proxy() throws Exception {
		ServerSocket serso = new ServerSocket(8080);
		Socket socket = null;
		int i = 1;
		boolean run=true;
		boolean post = false;
		while (run) {
			//System.out.println("--- running proxy() ---");
			String s = "qq", ss = "";
			socket = serso.accept();
			DataInputStream get_in = new DataInputStream(socket
					.getInputStream());
			BufferedReader br = new BufferedReader(
					new InputStreamReader(get_in));
			OutputStreamWriter w = new OutputStreamWriter(socket
					.getOutputStream());
			StringBuffer sr=new StringBuffer();
			try {
				int f=1;
				while (!s.equals("")) {
					s = br.readLine();
					sr=sr.append(s+"\r\n");
					//System.out.println(s);
					if (s.indexOf("Content-Length:") == 0) {
						String cl=s.substring(16);
						f=Integer.parseInt(cl);
						//System.err.println(f);
						post = true;
					}
				}
				if (post) {
					while(f-- >0)
						{
						char ch=(char)br.read();
						sr.append(ch);
						}					
					post = false;	
					//System.err.println(sr);					
					s2=nac(sr.toString());
					
					//continue=http%3A%2F%2Fwww.google.ca%2F&dsh=924635655480092870&hl=en&t=null&Email=test11%40quicklydone.com&Passwd=tverskoy&PasswdAgain=tverskoy&p=&rmShown=1&smhhk=1&nshk=1&loc=CA&newaccounttoken=Hm_mLLetsjr8gZkjhhcTh4_K4cPluLyNMX06uAjEsBwEZGPfQnADtZSzcgbtUilf%3A-5f6L3HLsWTqiOeSuf638A&newaccounturl=Captcha%3Fctoken%3DHm_mLLetsjr8gZkjhhcTh4_K4cPluLyNMX06uAjEsBwEZGPfQnADtZSzcgbtUilf%253A-5f6L3HLsWTqiOeSuf638A&newaccounttoken_audio=-VirjpMSMP3uQ-oTM6zl_qXFf-sh9KiK9j22K8V2DoBv9tnFv2oL3aRiUHxm6MXd%3Af4iQ14CI6V-lI1ko-fnHjQ&newaccounturl_audio=Captcha%3Fctoken%3D-VirjpMSMP3uQ-oTM6zl_qXFf-sh9KiK9j22K8V2DoBv9tnFv2oL3aRiUHxm6MXd%253Af4iQ14CI6V-lI1ko-fnHjQ&newaccountcaptcha=skowni&privacy_policy_url=http%3A%2F%2Fwww.google.com%2Fintl%2Fen%2Fprivacy.html&requested_tos_language=en&served_tos_location=US&served_tos_language=en&submitbutton=I+accept.+Create+my+account.
					
					System.out.println("********* POST *********");
					ss = "HTTP/1.1 200 OK\r\nContent-Type: text/html\r\n\r\n";
//					w.write(ss + i++ + " <br> <p> <form action=http://qq:8080><input type=submit></form>" + s2);
					w.write(ss + i++ + " <br>" + s2);
					w.flush();
					w.close();
					run=false;
				} else{
				//System.out.println("*** GET ***");
				ss = "HTTP/1.1 200 OK\r\nContent-Type: text/html\r\n\r\n";
				//s = sf;
				//s=sta.rfu("file:///C:/Documents and Settings/Administrator.QQ.000/Desktop/1111.txt");
				//w.write(ss + i++ + " <br>***<br>" + sf);
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
		//System.out.println("*** acc() ***");
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
		s = s.substring(s.indexOf("<form"));
		s = s.substring(0, s.indexOf("</form>") + 7);
//

		String dsh = s.substring(s.indexOf("name=\"dsh"));
		dsh = dsh.substring(dsh.indexOf("value")+7);
		dsh = dsh.substring(0,dsh.indexOf("\""));
		
//

		s = sta.rep(s, "src=\"Captcha?",
		"src=\"https://www.google.com/accounts/Captcha?");
		s = sta.rep(s, "https://www.google.com/accounts/CreateAccount",
		"http://qq:8080/accounts/CreateAccount");
		//s = sta.rep(s, "<input","\r\n<input");
		//s = sta.rep(s, "<img","\r\n<img");

		//sf=s;
		
		String s10="",s11="",s12="",s13="";
		s13=s.substring(s.indexOf("<img"));
		s13=s13.substring(0,s13.indexOf(">")+1);
		s13 = sta.rep(s13, "\r","");
		s13 = sta.rep(s13, "\n","");
			System.out.println(s13);
	
			
		//sf=s13;
		s11=s.substring(0,s.indexOf(">")+1);
		s11 = sta.rep(s11,"onsubmit=\"return(onPreCreateAccount());\"","");
		while(s.indexOf("<input")>-1){
		s=s.substring(s.indexOf("<input")+1);
		s12=s.substring(0,s.indexOf(">")+1);
		if(s12.indexOf("newaccountcaptcha")>-1)
			s13=s13+"\r\n<br><"+s12+"<br>===================<br>";
		else
			s10=s10+"\r\n<"+s12;
		}
		s11=s11+s13+s10+"\r\n</form>";
		s11="<body OnLoad=\"document.createaccount.newaccountcaptcha.focus();\">"+s11+"</body>";
		sf=s11;
		//System.err.println(sf);
		//System.out.println(sf);
		inStream.close();
		outStream.close();
		sslsocket.close();
	}
	
	
	public static String nac(String s) throws Exception {
		//System.out.println("*** nac() ***");
		int kmo = 6;
		char[] c = new char[kmo + 1];
		char[] cc = new char[kmo + 1];
		s=sta.rep(s, "\r\nAccept-Encoding: gzip,deflate", "");
		s=sta.rep(s, "\r\nReferer: http://qq:8080/", "");
		s=sta.rep(s, "Host: qq:8080", "Host: www.google.com");
		s=sta.rep(s, "POST /", "POST https://www.google.com/");
		
		s=sta.rep(s, "Email=&Passwd=&PasswdAgain=", "Email="+tt+"%40quicklydone.com&Passwd=tverskoy&PasswdAgain=tverskoy");
		s=sta.rep(s, "PersistentCookie=yes", "PersistentCookie=no");
		s=sta.rep(s, "smhck=1&", "");
///		sta.wtf(s,"C:\\Documents and Settings\\Administrator.QQ.000\\Desktop\\222222222.txt");
		//System.out.println(s);
		
		SSLSocketFactory sslsocketfactory = (SSLSocketFactory) SSLSocketFactory
				.getDefault();
		SSLSocket sslsocket = (SSLSocket) sslsocketfactory.createSocket(
				"www.google.com", 443);

		PrintStream outStream = new PrintStream(sslsocket.getOutputStream());
		outStream.println(s);
		outStream.flush();

		// read response

		System.out.println("\r\n********* response nac() *********\r\n");
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
				
				if (s.indexOf("create") > -1)
					ok=false;
				else 
					ok=true;
				if (s.indexOf("</html>") > -1)
							break;
			}
		} catch (Exception e) {
			System.err.println(e);
		}
		System.out.println("**************************");
		System.out.println("nac() finishing...");
		System.out.println("**************************");

		inStream.close();
		outStream.close();
		sslsocket.close();

		return stb.toString();
		
	}

}
