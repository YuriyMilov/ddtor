package tkl;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;
import java.util.TimeZone;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.mail.internet.*;
import javax.mail.util.ByteArrayDataSource;

public class mh extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		String s = "OK";
		try {
			//send_mail("ymilov@gmail.com", "test", "test");
			//send_mail("admins", "test1111111", "test11111");
			//send_mail("ymilov", "test22222", "test22222");
			
			//send_mail("ymilov@gmail.com", "test333", "test33333333");
			
			send_admin("UFOS Daily Activity", "UFOS Daily Activity");
			
		} catch (Exception e) {
			s = e.toString();
		}
		PrintWriter out = resp.getWriter();
		out.write(s);
		out.flush();
		out.close();
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
	throws IOException {
		String s = "OK";
		
		String s1 = req.getParameter("a1");
		String s2 = req.getParameter("a2");
		String s3 = req.getParameter("a3");
		String s4 = req.getParameter("a4");
try {
	
	if(s4.equals("admins"))
		send_admin(s2,s3);
	else
		send_mail(s1,s2,s3);
	
} catch (Exception e) {
	s = e.toString();
}
PrintWriter out = resp.getWriter();
out.write(s);
out.flush();
out.close();
}

	public void send_mail(String s1, String s2, String s3) throws Exception {
		String[] tt = s1.split(",");

		Properties props = new Properties();
		Session session = Session.getDefaultInstance(props, null);

		
		
		String sdt=""; 
		TimeZone tz=TimeZone.getTimeZone("America/Montreal");
		SimpleDateFormat parser = new SimpleDateFormat(
		"MMM dd, yyyy 'at' HH:mm:ss z");
		//Date d = parser.parse("Oct 25, 2007 at 18:35:07 EDT");
		Date d = Calendar.getInstance(TimeZone.getTimeZone("EST")).getTime();
		SimpleDateFormat formatter = new SimpleDateFormat(
		"yyyy/MM/dd  HH:mm:ss z'('Z')'");
		formatter.setTimeZone(tz);
		sdt=formatter.format(d);
		
		//s2 = s2 + " "+ new Date().toString();
		
		s2 = s2 + " "+ sdt;
		
		//msgBody=msgBody+"\r\n<br><br>"+rfu("http://code.google.com/p/ddtor/source/list");
		
		Message msg = new MimeMessage(session);
		msg.setFrom(new InternetAddress("ymilov@gmail.com", "Yuriy Milov"));
		//msg.setFrom(new InternetAddress("lowrisk.terryfoxfoundation@gmail.com", "LowRisk Admin"));
		
		for (int i = 0; i < tt.length; i++)
		msg.addRecipient(Message.RecipientType.TO, new InternetAddress(
				tt[i], tt[i]));
		
		msg.setSubject(s2);
		msg.setHeader("Content-type:","text/html;charset=ISO-8859-1");
		msg.setText(s3);
		Transport.send(msg);

	}

	private void send_admin (String subject, String body) throws Exception {
	    Properties props = new Properties();
	    Session session = Session.getDefaultInstance(props, null);
	        Message msg = new MimeMessage(session);
	        msg.setFrom(new InternetAddress("ymilov@gmail.com", "UFOS Daily Activity"));
	        msg.addRecipient(Message.RecipientType.TO, new InternetAddress("admins"));
	        msg.setSubject(subject);
	        msg.setText(body);
	        
	    	Multipart mp = new MimeMultipart();

			MimeBodyPart textPart = new MimeBodyPart();
			textPart.setContent("textBody", "text/plain");
			mp.addBodyPart(textPart);

			MimeBodyPart attachment = new MimeBodyPart();
			String fileName = "UFOS_Daily.txt";
			String filename = URLEncoder.encode(fileName, "UTF-8");
			attachment.setFileName(filename);
			attachment.setDisposition(Part.ATTACHMENT);

			// DataSource src = new ByteArrayDataSource(spreadSheetData,
			// "application/x-ms-excel");
			DataSource src = new ByteArrayDataSource(body.getBytes(), "plain/text");
			DataHandler handler = new DataHandler(src);
			attachment.setDataHandler(handler);
			mp.addBodyPart(attachment);

			msg.setContent(mp);
			//msg.setSubject(String.format(subj));

			//Transport.send(msg);
	        
	        Transport.send(msg);
	       
 
	}
	
	private static void sm(byte[] data, String ff, String mimeType,
			String from_a, String from_n, String to_a, String to_n,
			String subj, String textBody) throws Exception {
		Properties props = new Properties();
		Session session = Session.getDefaultInstance(props, null);

		Message message = new MimeMessage(session);
		message.setFrom(new InternetAddress(from_a, from_n));

		message.addRecipient(Message.RecipientType.TO, new InternetAddress(
				to_a, to_n));

		Multipart mp = new MimeMultipart();

		MimeBodyPart textPart = new MimeBodyPart();
		textPart.setContent(textBody, "text/plain");
		mp.addBodyPart(textPart);

		MimeBodyPart attachment = new MimeBodyPart();
		String fileName = ff;
		String filename = URLEncoder.encode(fileName, "UTF-8");
		attachment.setFileName(filename);
		attachment.setDisposition(Part.ATTACHMENT);

		// DataSource src = new ByteArrayDataSource(spreadSheetData,
		// "application/x-ms-excel");
		DataSource src = new ByteArrayDataSource(data, mimeType);
		DataHandler handler = new DataHandler(src);
		attachment.setDataHandler(handler);
		mp.addBodyPart(attachment);

		message.setContent(mp);
		message.setSubject(String.format(subj));

		Transport.send(message);

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
