package cn;

import java.io.*;
import java.net.*;
import java.util.Date;

import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;

import com.google.gdata.client.GoogleService;
import com.google.gdata.data.Entry;
import com.google.gdata.data.Feed;
import com.google.gdata.util.common.html.HtmlToText;

public class ads_29 {
	public static String gg = "", ku="";

	public static void main(String args[]) throws Exception {
		
		get1();
		//get2();
		//get3();
		get4();

		}
	
	public static void get1()throws Exception{

		String s2 = "username=agi1010&password=trace1010&url=&login-form-type=pwd";
		String s = "POST /pkmslogin.form HTTP/1.1"
				+ "\r\nHost: ecprod.cn.ca:443"
				+ "\r\nAccept: text/xml,application/xml,application/xhtml+xml,text/html;q=0.9,text/plain;q=0.8,image/png,*/*;q=0.5"
				+ "\r\nContent-Type: application/x-www-form-urlencoded"
				+ "\r\nContent-Length: "
				+ String.valueOf(s2.length()) + "\r\n\r\n" + s2;

		System.err.println(s+"\r\n");
		
		SSLSocketFactory sslsocketfactory = (SSLSocketFactory) SSLSocketFactory
				.getDefault();
		SSLSocket sslsocket = (SSLSocket) sslsocketfactory.createSocket(
				"ecprod.cn.ca", 443);
		PrintStream outStream = new PrintStream(sslsocket.getOutputStream());
		outStream.println(s);
		outStream.flush();

		// read response

		DataInputStream inStream = new DataInputStream(sslsocket
				.getInputStream());
		StringBuffer stb = new StringBuffer();

		int ch = ' ';
		for (ch = inStream.read(); ch > 0; ch = inStream.read()) {
			stb.append((char) ch);
			System.out.print((char) ch);
			if (stb.indexOf("</HTML>") > -1)
				break;
			if (stb.indexOf("</html>") > -1)
				break;
		}
		inStream.close();
		outStream.close();
		sslsocket.close();
		s = stb.toString();
		
		s = s.substring(s.indexOf("Set-Cookie:") + 12);
		gg = gg + s.substring(0, s.indexOf("\r\n")) + "; ";

		System.err.println("\r\n"+gg+"\r\n");
	}
	
	
	public static void get2() throws Exception {
		String s2="/velocity/MainMenu/HTML/english/main_menu_frameset.html";
			
			
		String s = "GET "
				+ s2
				+ " HTTP/1.1"
				+ "\r\nHost: ecprod.cn.ca:443"
				+ "\r\nAccept: text/xml,application/xml,application/xhtml+xml,text/html;q=0.9,text/plain;q=0.8,image/png,*/*;q=0.5"
				+ "\r\nContent-Type: application/x-www-form-urlencoded"
				+ "\r\nCookie: " + gg + "\r\n\r\n";
		
		
		
		System.err.println("\r\n ------------ \r\n\r\n"+s+"\r\n\r\n");
	
		
		

		SSLSocketFactory sslsocketfactory = (SSLSocketFactory) SSLSocketFactory
				.getDefault();
		SSLSocket sslsocket = (SSLSocket) sslsocketfactory.createSocket(
				"ecprod.cn.ca", 443);
		PrintStream outStream = new PrintStream(sslsocket.getOutputStream());
		outStream.println(s);
		outStream.flush();

		// read response

		DataInputStream inStream = new DataInputStream(sslsocket
				.getInputStream());
		StringBuffer stb = new StringBuffer();

		int ch = ' ';
		for (ch = inStream.read(); ch > 0; ch = inStream.read()) {
			stb.append((char) ch);
			System.out.print((char) ch);
			//if (stb.indexOf("</HTML>") > -1)
			//break;
			
			if (stb.indexOf("\r\n\r\n") > -1)
			break;
		}
		inStream.close();
		outStream.close();
		sslsocket.close();
		s = stb.toString();

		
		System.err.println("\r\n ------------ \r\n\r\n"+s+"\r\n\r\n");
		
		ku= s.substring(s.indexOf("Set-Cookie:"));
		ku=sta.rep(ku, "Set-Cookie: ", "Cookie: ");
		ku= ku.substring(0,ku.lastIndexOf("Secure")+6);
		//ku="Cookie: "+gg+"\r\n"+ku;
		System.out.println(">>>"+ku+"<<<");
		}
	
	
	public static void get3()throws Exception{

		String s2 = "LANGUAGE=E&version=SID-1.0&LAST_IS_LEGACY=false&REF_TYPE_CODE=HR&cars=mscu+892397&RESPONDBY=XLS&REQUEST=++Submit+++";
		
		String s = "POST /velocity/IMShipmentStatus/english/CFF_ImdShipmentStatus HTTP/1.1"
				+ "\r\nHost: ecprod.cn.ca:443"
				+ "\r\nAccept: text/xml,application/xml,application/xhtml+xml,text/html;q=0.9,text/plain;q=0.8,image/png,*/*;q=0.5"
				+ "\r\nContent-Type: application/x-www-form-urlencoded"
				//+ "\r\n" + ku 
				//+ "\r\nCookie: " + gg 
				+ "\r\nContent-Length: "
				+ String.valueOf(s2.length()) + "\r\n\r\n" + s2;

		System.err.println(s+"\r\n");
		
		SSLSocketFactory sslsocketfactory = (SSLSocketFactory) SSLSocketFactory
				.getDefault();
		SSLSocket sslsocket = (SSLSocket) sslsocketfactory.createSocket(
				"ecprod.cn.ca", 443);
		PrintStream outStream = new PrintStream(sslsocket.getOutputStream());
		outStream.println(s);
		outStream.flush();

		// read response

		DataInputStream inStream = new DataInputStream(sslsocket
				.getInputStream());
		StringBuffer stb = new StringBuffer();

		int ch = ' ';
		for (ch = inStream.read(); ch > 0; ch = inStream.read()) {
			stb.append((char) ch);
			System.out.print((char) ch);
			if (stb.indexOf("</HTML>") > -1)
			break;
			//if (stb.indexOf("\r\n\r\n") > -1)
			//break;
			
		}
		inStream.close();
		outStream.close();
		sslsocket.close();
		s = stb.toString();
		
		System.err.println("\r\n ------------ \r\n\r\n"+s+"\r\n\r\n");
		
		ku= s.substring(s.indexOf("Set-Cookie:"));
		ku=sta.rep(ku, "Set-Cookie: ", "Cookie: ");
		ku= ku.substring(0,ku.lastIndexOf("Secure"));
		System.err.println("\r\n-----------\r\n>>>"+ku+"<<<\r\n----------\r\n");

		System.out.println("\r\n"+s+"\r\n");
	}

	
	public static void get4()throws Exception{

		String s2 = "LANGUAGE=E&version=SID-1.0&LAST_IS_LEGACY=false&REF_TYPE_CODE=HR&cars=mscu+892397&RESPONDBY=HTTP&REQUEST=Submit";
		//https://ecprod.cn.ca:443/velocity/IMShipmentStatus/english/CFF_ImdShipmentStatus
		String s = "POST /velocity/IMShipmentStatus/english/CFF_ImdShipmentStatus HTTP/1.1"
				+ "\r\nHost: ecprod.cn.ca:443"
				+ "\r\nAccept: text/xml,application/xml,application/xhtml+xml,text/html;q=0.9,text/plain;q=0.8,image/png,*/*;q=0.5"
				+ "\r\nContent-Type: application/x-www-form-urlencoded"
				+ "\r\nUser-Agent: Mozilla/5.0 (Windows; U; Windows NT 5.0; en-US; rv:1.8.1.6) Gecko/20070725 Firefox/2.0.0.6"
				
				+ "\r\nAccept: */*"
				+ "User-Agent: Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.0; .NET CLR 1.1.4322; .NET CLR 2.0.50727)"
				
				//+ "\r\n" + ku 
				+ "\r\nCookie: " + gg 
				+ "\r\nContent-Length: "
				+ String.valueOf(s2.length()) + "\r\n\r\n" + s2;

		System.err.println(s+"\r\n");
		
		SSLSocketFactory sslsocketfactory = (SSLSocketFactory) SSLSocketFactory
				.getDefault();
		SSLSocket sslsocket = (SSLSocket) sslsocketfactory.createSocket(
				"ecprod.cn.ca", 443);
		PrintStream outStream = new PrintStream(sslsocket.getOutputStream());
		outStream.println(s);
		outStream.flush();

		// read response

		DataInputStream inStream = new DataInputStream(sslsocket
				.getInputStream());
		StringBuffer stb = new StringBuffer();

		int ch = ' ';
		for (ch = inStream.read(); ch > 0; ch = inStream.read()) {
			stb.append((char) ch);
			//System.out.print((char) ch);
			if (stb.indexOf("\r\n0\r\n") > -1)
			//if (stb.indexOf("</center>") > -1)
			break;
			
		}
		inStream.close();
		outStream.close();
		sslsocket.close();
		s = stb.toString();
		
		System.out.println("\r\n ------------ \r\n\r\n"+s+"\r\n\r\n");
		
		ku= s.substring(s.indexOf("Set-Cookie:"));
		ku=sta.rep(ku, "Set-Cookie: ", "Cookie: ");
		ku= ku.substring(0,ku.lastIndexOf("Secure")+6);
		System.err.println("\r\n\r\n444444444444\r\n\r\n>>>\r\n"+ku+"\r\n<<<");

		System.err.println("\r\n"+s+"\r\n");
	}
	
	public static void get33() throws Exception {
		//String s2="/velocity/IMShipmentStatus/english/CFF_ImdShipmentStatus";
		
		String s2 = "/velocity/IMShipmentStatus/english/CFF_ImdShipmentStatus";
		//  https://ecprod.cn.ca/velocity/IMShipmentStatus/english/CFF_ImdShipmentStatus
		
		String s = "GET "
				+ s2
				+ " HTTP/1.1"
				+ "\r\nHost: ecprod.cn.ca:443"
				+ "\r\nAccept: image/gif, image/x-xbitmap, image/jpeg, image/pjpeg, application/vnd.ms-powerpoint, application/vnd.ms-excel, application/msword, application/x-shockwave-flash, */*"
				+ "\r\nAccept-Language: en-us"
				+ "\r\nCookie: PD_STATEFUL_a27aef0a-36fc-11dc-9ac6-0002554f6131=%2Fvelocity; IV_JCT=%2Fvelocity; AMWEBJCT!%2Fvelocity!LtpaToken2=A+SW6wqEiG9wDCeTp2HGYWdhuNvAHuXnRq2nwTLS7elYg1qOQ5PguE9vgM+YcEaV79tgExOxao/9IsK2F1chcJ1lswHbk2CUaDVbKAYDPf9Ue+TxXcElX1vRQxq3/qmLYHroLX5a5S8STUQSfbsBjZvlfm/y0SgmX8AVXCL34FrmGIAYUAvOG2oAod7R2Nfj+6xhBOT6tAtomnlPuhA4NKMJ5Dq1rDSG0OoCDtUk8tagXgz6HOTp42iX6pHW6MJfX2SKWaRD2GJTP0H3KC555ew7cr9Ryv4/tiE8VS4WBoplcbWBLloyXY7djF8Y40i59cTK/5tEmLkn03fEdhOI1uBZaHSyumMkMP+cKoytyKXCiVxp4xh+W7zSlquOhYqT04rEUuE1Wl0l0mit+GmiI3EC5uOCg5ctmd4ga1oPYbUWBZzA3xpQYXZKpfwPb3ymvzQUeiV2Sv16cC/Ts6TEgMB7Na2ysDNAjsGXbd6cCMvgZ2eIw0L/D7/nfa33nwab+1ju29V+BP5ee5m0WTXOkL3r2a3bmsBeNA7uQk9t77FhqebkSIXY/17a3uaO/v3/ByYTGAZZlw/k7ouRDFimBj3G6HZLFaSefu+FkvKET73WCrifiLXJ17TW9eWyG0AmUMQvyGAVN3SJcdIzz9t4CVa0zilpDiHdWYcfq5bR5vtntD1SRns40s3T+GyHJKPW; AMWEBJCT!%2Fvelocity!LtpaToken=mr2KSsWa5MAaiy/h3quvtgt2LgzEl4vaa+QIahIchWG4o7Wv9WKV9MyGjf08hF3kJFMZWkr7RG7EDxIhhw2PMi1AGWSWg1rrySp5MM/51g+lDTLoPkadz49CJICA+dHQbhM8LtH7pwgPEYYVWSJ5F85zQG839C1ApgDHaIXvy7u4Kj69m3IokBolYdePk1twaSaM4LOi+O9pD1xwmg/VXL3QzXsQHCtnIgTbu7X+Ht3kXqA9X1nwyiDHjtgmZIQWulU3U1/DVWob9Sa47ZhyGlv1qAM/XWciDnUz1zQb0VFe683BQ5w2dh6Y1Rp5LzorfWSR88acvbwKH7bsCKw6Yc8fKtj7D9OL; JSESSIONID=0000pKQALmjL_Hkt2Wx8FcQE9sX:12ffjmd54:12h5lvok6; LAST_SESSION_CHANGE=0; LANGUAGE=en"
				+ "\r\nUser-Agent: Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.0; .NET CLR 1.1.4322; .NET CLR 2.0.50727)\r\n\r\n";

		
		
		
		System.err.println("\r\n ------------ \r\n\r\n"+s+"\r\n\r\n");
	
		
		

		SSLSocketFactory sslsocketfactory = (SSLSocketFactory) SSLSocketFactory
				.getDefault();
		SSLSocket sslsocket = (SSLSocket) sslsocketfactory.createSocket(
				"ecprod.cn.ca", 443);
		PrintStream outStream = new PrintStream(sslsocket.getOutputStream());
		outStream.println(s);
		outStream.flush();

		// read response

		DataInputStream inStream = new DataInputStream(sslsocket
				.getInputStream());
		StringBuffer stb = new StringBuffer();

		int ch = ' ';
		for (ch = inStream.read(); ch > 0; ch = inStream.read()) {
			stb.append((char) ch);
			System.out.print((char) ch);
			//if (stb.indexOf("\r\n\r\n") > -1)
			//	break;
		}
		inStream.close();
		outStream.close();
		sslsocket.close();
		s = stb.toString();

		
		System.err.println("\r\n ------------ \r\n\r\n"+s+"\r\n\r\n");
		
		ku= s.substring(s.indexOf("Set-Cookie:"));
		ku=sta.rep(ku, "Set-Cookie: ", "Cookie: ");
		ku= ku.substring(0,ku.lastIndexOf("Secure")+6);
		System.out.println(">>>"+ku+"<<<");
		
		}
	
	



	public static void gc1() throws Exception {
		String s = "GET https://www.google.com/adsense/login/en_US/ HTTP/1.1"
				+ "\r\nHost: www.google.com\r\n\r\n";
		s = gh(s);
		while (s.indexOf("Set-Cookie:") > -1) {
			s = s.substring(s.indexOf("Set-Cookie:") + 12);
			gg = gg + s.substring(0, s.indexOf(";") + 1) + " ";
		}
 s = "GET https://www.google.com/accounts/ServiceLoginBox?service=adsense&ltmpl=login&ifr=true&rm=hide&fpui=3&nui=15&alwf=true&passive=true&continue=https%3A%2F%2Fwww.google.com%2Fadsense%2Flogin-box-gaiaauth&followup=https%3A%2F%2Fwww.google.com%2Fadsense%2Flogin-box-gaiaauth&hl=en_US HTTP/1.1"
				+ "\r\nHost: www.google.com\r\n\r\n";
		s = gh(s);
		while (s.indexOf("Set-Cookie:") > -1) {
			s = s.substring(s.indexOf("Set-Cookie:") + 12);
			gg = gg + s.substring(0, s.indexOf("\r\n")) + "; ";
		}
	}

	public static String gh(String s) throws Exception {
		System.out.print("-");

		SSLSocketFactory sslsocketfactory = (SSLSocketFactory) SSLSocketFactory
				.getDefault();
		SSLSocket sslsocket = (SSLSocket) sslsocketfactory.createSocket(
				"www.google.com", 443);
		PrintStream outStream = new PrintStream(sslsocket.getOutputStream());
		
		//s="";
		
		
		outStream.println(s);
		outStream.flush();

		// read response

		DataInputStream inStream = new DataInputStream(sslsocket
				.getInputStream());
		StringBuffer stb = new StringBuffer();

		int ch = ' ';
		for (ch = inStream.read(); ch > 0; ch = inStream.read()) {
			stb.append((char) ch);
			if (stb.indexOf("\r\n\r\n") > -1)
				break;
		}
		inStream.close();
		outStream.close();
		sslsocket.close();
		return stb.toString();
	}




	public static void get_rep() throws Exception {
		System.out.print("-> ");
		String s = "GET https://www.google.com/adsense/report/overview HTTP/1.1"
				+ "\r\nHost: www.google.com"
				+ "\r\nAccept: text/xml,application/xml,application/xhtml+xml,text/html;q=0.9,text/plain;q=0.8,image/png,*/*;q=0.5"
				+ "\r\nContent-Type: application/x-www-form-urlencoded"
				+ "\r\nCookie: " + gg + "\r\n\r\n";

		SSLSocketFactory sslsocketfactory = (SSLSocketFactory) SSLSocketFactory
				.getDefault();
		SSLSocket sslsocket = (SSLSocket) sslsocketfactory.createSocket(
				"www.google.com", 443);
		PrintStream outStream = new PrintStream(sslsocket.getOutputStream());
		outStream.println(s);
		outStream.flush();

		// read response

		DataInputStream inStream = new DataInputStream(sslsocket
				.getInputStream());
		StringBuffer stb = new StringBuffer();

		int ch = ' ';
		for (ch = inStream.read(); ch > 0; ch = inStream.read()) {
			stb.append((char) ch);
			// System.out.print((char) ch);
			if (stb.indexOf("view all AdSense") > -1)
				break;
		}
		inStream.close();
		outStream.close();
		sslsocket.close();

		s = stb.toString();
		s = s.substring(s.indexOf(" AdSense for Content ")+21);	 
		s = s.substring(0, s.indexOf("view all AdSense"));
		s=HtmlToText.htmlToPlainText(s);
		//sta.wtft(new Date().toString().substring(11,19)+"  "+ s +"\r\n", "C:/Documents and Settings/"
		//		 + "Administrator.QQ.000/Desktop/_qqqqqq_!.txt");

		System.out.println(s+"\r\n\r\n");
	}

}
