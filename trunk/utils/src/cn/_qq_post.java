package cn;
import java.io.*;
import java.net.*;

public class _qq_post {
	public static void main(String[] args) throws Exception {
		String s = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\r\n"
				+ "<ShippingStatus xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\">\r\n"
				+ "  qqqqqqqqqqqqqqqqqqqqqqqqqqqqq      \r\n</ShippingStatus>";
		System.out.println(sendTransaction(s, "xmlaxsun", "axsun321",
				"http://quicklydone.com/servlet/cn.axserv"));
	}

	public static String sendTransaction(String pXml, String pUsername,
			String pPassword, String pServerUrl) throws Exception {
		StringBuffer wResult = new StringBuffer();
		try {
			URL wUrl;
			wUrl = new URL(pServerUrl);
			HttpURLConnection wConn = (HttpURLConnection) wUrl.openConnection();
			// Authentification
			String wUser = new String(pUsername + ":" + pPassword);
			String wEncodedPassword = URLEncoder.encode(wUser);
			//String wEncoding = new sun.misc.BASE64Encoder().encode(wUser
			//		.getBytes());
			//wConn.setRequestProperty("Authorization", "Basic " + wEncoding);
			wConn.setRequestProperty("Content-Type", "text/xml; charset=UTF8");
			wConn.setDoOutput(true);
			// Write the transaction
			PrintWriter wPw = new PrintWriter(wConn.getOutputStream());
			wPw.println(pXml);
			wPw.close();
			// Read the result
			InputStreamReader wReader = new InputStreamReader(wConn
					.getInputStream());
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
}
