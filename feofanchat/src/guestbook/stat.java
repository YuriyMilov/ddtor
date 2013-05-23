package guestbook;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.util.ByteArrayDataSource;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class stat {

	
	public static String s1 = "<script>function setFocus(){document.getElementById(\"id\").focus();}</script></head><body bgcolor=#efefef onload=setFocus()>";
	public static String s2 = "<br/><br/><form  action=qq method=post> <input type=text id=id name=q size=55></body></html>";
	public static String stop = "";
	public static String sr = "Всякий человек смертен. Сократ - человек.";
	public static String sowl = "";
	
	public static void page(HttpServletRequest req, HttpServletResponse resp,
			String sotvet) throws IOException {

		stop = stop + "<br><br>\r\n<b><i>Феофан:</i></b>\r\n <!--otvet--> \r\n" + sotvet+ "\r\n<!--otvet--><br>";

		ServletOutputStream out = resp.getOutputStream();
		resp.setContentType("text/html; charset=UTF8");
		// String sh = req.getScheme() + "://" + req.getServerName() + ":"
		// + req.getServerPort() + req.getContextPath();
		String s = s1+ stop+s2;
		byte[] b = s.getBytes("UTF8");
		out.write(b);
	}

	public static String rfu_utf(String s) {
		try {
			URL url = new URL(s);

			URLConnection conn = url.openConnection();
			BufferedReader br = new BufferedReader(new InputStreamReader(
					conn.getInputStream(), "utf8"));
			s = "";
			String thisLine = "";
			while ((thisLine = br.readLine()) != null) { // while loop begins
															// here
				s = s + thisLine + "\r\n";
			}
			br.close();
			return s.toString();

		} catch (Exception e) {
			return e.toString();
		}
	}

	
	public static String get_post(String surl, String body) {
		String s = "";

		try {

			URL url = new URL(surl);
			URLConnection urlConnection = url.openConnection();
			DataOutputStream outStream;

			// Build request body
			// Create connection

			((HttpURLConnection) urlConnection).setRequestMethod("POST");
			urlConnection.setConnectTimeout(0);
			urlConnection.setReadTimeout(0);
			urlConnection.setDoInput(true);
			urlConnection.setDoOutput(true);
			urlConnection.setUseCaches(false);
			urlConnection.setRequestProperty("Content-Type",
					"application/x-www-form-urlencoded");
			urlConnection.setRequestProperty("Content-Length",
					"" + body.length());

			// Create I/O streams
			outStream = new DataOutputStream(urlConnection.getOutputStream());

			// Send request
			outStream.writeBytes(body);
			outStream.flush();
			outStream.close();

			// Get Response

			BufferedReader br = new BufferedReader(new InputStreamReader(
					urlConnection.getInputStream(), "utf8"));
			s = "";
			String thisLine = "";
			while ((thisLine = br.readLine()) != null) {
				s = s + thisLine + "\r\n";
			}
			br.close();

		} catch (Exception ex) {
			s = ex.toString();
		}
		return s;
	}


	static void sm(String skomu, String s) throws Exception {
		Properties props = new Properties();
		Session session = Session.getDefaultInstance(props, null);
       Message msg = new MimeMessage(session);
            msg.setFrom(new InternetAddress("kuka@feofan.com", "Кука Тверской"));
            msg.addRecipient(Message.RecipientType.TO,
                             new InternetAddress(skomu, " "));
            msg.setSubject("Кука Тверской "+ new java.util.Date().toString());
            msg.setText(s);
            Transport.send(msg);
	}

	static void smtxt(String s) throws Exception {
		Properties props = new Properties();
		Session session = Session.getDefaultInstance(props, null);
       Message msg = new MimeMessage(session);
            msg.setFrom(new InternetAddress("kuka@feofan.com", "Example.com Admin"));
            msg.addRecipient(Message.RecipientType.TO,
                             new InternetAddress("ymilov@gmail.com", "Mr. User"));
            msg.setSubject("Your Example.com account has been activated");
            msg.setText("msgBody");
            
            
            // Create the message part 
	         BodyPart messageBodyPart = new MimeBodyPart();

	         // Fill the message
	         messageBodyPart.setText(s);
	         
	         // Create a multipar message
	         Multipart multipart = new MimeMultipart();

	         // Set text message part
	         multipart.addBodyPart(messageBodyPart);

	         // Part two is attachment
	         messageBodyPart = new MimeBodyPart();
	         String filename = "file.txt";
	         
	        
	         DataSource source = new ByteArrayDataSource("xmlData".getBytes("utf-8"),
                  "text/xml; charset=utf-8");
	         messageBodyPart.setDataHandler(new DataHandler(source));
	         messageBodyPart.setFileName(filename);
	         messageBodyPart.addHeader("Content-Type", " text/xml; charset=utf-8");
	         multipart.addBodyPart(messageBodyPart);

	         // Send the complete message parts
	         msg.setContent(multipart );
	         
	         
            Transport.send(msg);
	}

}
