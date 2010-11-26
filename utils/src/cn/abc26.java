package cn;

import java.io.*;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;

public class abc26 {
	static String ku="";
	public static void main(String args[]) throws Exception {
		
		ku = login();
		ku = get();
		
		ku= ku.substring(ku.indexOf("\r\nInit"));
		ku= ku.substring(0,ku.lastIndexOf("\r\n\r\n"));
		
		
		System.out.println(ku);
		}
	
	
public String get_txt(){
		
		try{
		
		ku = login();
		ku = get();
		
		ku= ku.substring(ku.indexOf("\r\nInit"));
		ku= ku.substring(0,ku.lastIndexOf("\r\n\r\n"));
		
		}catch(Exception e){ku=e.toString();}
		return ku;
	}


	public static String login()throws Exception{
		String ku="";
		String s2 = "username=agi1010&password=trace1010&url=&login-form-type=pwd";
		String s = "POST /pkmslogin.form HTTP/1.1"
				+ "\r\nHost: ecprod.cn.ca:443"
				+ "\r\nAccept: text/xml,application/xml,application/xhtml+xml,text/html;q=0.9,text/plain;q=0.8,image/png,*/*;q=0.5"
				+ "\r\nContent-Type: application/x-www-form-urlencoded"
				+ "\r\nContent-Length: "
				+ String.valueOf(s2.length()) + "\r\n\r\n" + s2;

		
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
			if (stb.indexOf("\r\n\r\n") > -1)
				break;
		}
		inStream.close();
		outStream.close();
		sslsocket.close();
		s = stb.toString();
		
		ku= s.substring(s.indexOf("Set-Cookie:"));
		ku=sta.rep(ku, "Set-Cookie: ", "Cookie: ");
		ku= ku.substring(0,ku.lastIndexOf("Secure")+6);

		//System.err.println("\r\n"+ku+"\r\n");
		return ku;
	}
	
	
	
	public static String get()throws Exception{

		String s2 = "LANGUAGE=E&version=SID-1.0&LAST_IS_LEGACY=false&REF_TYPE_CODE=HR&cars=mscu+892397&RESPONDBY=FTP&REQUEST=Submit";
		//https://ecprod.cn.ca:443/velocity/IMShipmentStatus/english/CFF_ImdShipmentStatus
		String s = "POST /velocity/IMShipmentStatus/english/CFF_ImdShipmentStatus HTTP/1.1"
				+ "\r\nHost: ecprod.cn.ca:443"
				+ "\r\nAccept: text/xml,application/xml,application/xhtml+xml,text/html;q=0.9,text/plain;q=0.8,image/png,*/*;q=0.5"
				+ "\r\nContent-Type: application/x-www-form-urlencoded"
				+ "\r\nUser-Agent: Mozilla/5.0 (Windows; U; Windows NT 5.0; en-US; rv:1.8.1.6) Gecko/20070725 Firefox/2.0.0.6"
				+ "\r\nAccept: */*"
				+ "User-Agent: Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.0; .NET CLR 1.1.4322; .NET CLR 2.0.50727)"
				+ "\r\n" + ku 
				+ "\r\nContent-Length: "
				+ String.valueOf(s2.length()) + "\r\n\r\n" + s2;

		//System.err.println(s+"\r\n");
		
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
			break;
		}
		inStream.close();
		outStream.close();
		sslsocket.close();
		s = stb.toString();
		
		ku= s.substring(s.indexOf("Set-Cookie:"));
		ku=sta.rep(ku, "Set-Cookie: ", "Cookie: ");
		ku= ku.substring(0,ku.lastIndexOf("Secure")+6);
		//System.err.println("\r\n\r\n"+ku+"\r\n\r\n");
		return s;
	}
}
