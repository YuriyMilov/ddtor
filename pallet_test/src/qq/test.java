package qq;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class test {

	
	public static void main(String[] args) {
		String s="";
		
		try{
			//s=post("a=qq","user","password","http://174.117.66.8/gu/gu.aspx");
			
	s=rfu("http://map.quicklydone.com/qq");	
	int i=1;
	while(i>0){
		i=s.indexOf("<country>");
		
		s=s.substring(i+1);
		String slat=s.substring(s.indexOf("<shiplat>")+9,s.indexOf("</shiplat>")).trim();
		String slng=s.substring(s.indexOf("<shiplng>")+9,s.indexOf("</shiplng>")).trim();
		
		
		System.out.println(slat+" " + slng);
		//System.out.println(i++);
		
	}
		
		}
		catch(Exception e){
			s=e.toString();
			}
//if(s.indexOf("<!-- UFOS server is running -->")!=0)		
//		s = rfu("http://beska.quicklydone.com/ds/test_data/supplyItem.data.xml");

		System.out.println(s);
		

	}
	
	
	public static String post(String pXml, String pUsername,
			String pPassword, String pServerUrl) {

		StringBuffer wResult = new StringBuffer();
		try {
			URL wUrl;
			wUrl = new URL(pServerUrl);
			HttpURLConnection wConn = (HttpURLConnection) wUrl.openConnection();
			// Authentification
			String wUser = new String(pUsername + ":" + pPassword);
			String wEncodedPassword = URLEncoder.encode(wUser, "UTF-8");
			// String wEncoding = new sun.misc.BASE64Encoder().encode(wUser
			// .getBytes());
			wConn.setRequestProperty("Authorization", "Basic "
					+ wEncodedPassword);
			// wConn.setRequestProperty("Content-Type",
			// "text/xml; charset=UTF8");
			// wConn.setRequestProperty("Content-Type",
			// "text/xml; charset=UTF8; application/x-www-form-urlencoded");
			wConn.setDoOutput(true);
			// Write the transaction
			PrintWriter wPw = new PrintWriter(wConn.getOutputStream());
			wPw.println(pXml);
			wPw.close();
			// Read the result
			InputStreamReader wReader = new InputStreamReader(
					wConn.getInputStream());
			BufferedReader wBuff = new BufferedReader(wReader);
			String wUrlContent = null;
			while ((wUrlContent = wBuff.readLine()) != null) {
				wResult.append(wUrlContent);
				wResult.append("\n");
			}
			wBuff.close();
		} catch (IOException ioe) {
			System.out.println("Error reading URL: " + ioe.getMessage());
			ioe.printStackTrace();
		} catch (Exception e) {
			System.out.println("EXCEPTION CAUGHT e=" + e);
			e.printStackTrace();
		}

		return wResult.toString();
	}
	
	public static String rfu(String url) {
		StringBuffer s = new StringBuffer();
		try {
			URL u = new URL(url);
			InputStream in = u.openConnection().getInputStream();
			for (int ch = in.read(); ch > 0; ch = in.read()) {
				s.append((char) ch);
			}
			in.close();
		} catch (IOException e) {
			return e.toString();
		}
		return s.toString();
	}

}
