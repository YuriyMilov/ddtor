package cc;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.URL;
import java.util.Date;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class mm extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		String s = "OK";
		try {
			send_mail();
		} catch (Exception e) {
			s = e.toString();
		}
		PrintWriter out = resp.getWriter();
		out.write(s);
		out.flush();
		out.close();
	}

	public void send_mail() throws Exception {

		Properties props = new Properties();
		Session session = Session.getDefaultInstance(props, null);

		String msgBody = "";//"testing cron job at cron cron.xml in WEB-INF <?xml version=\"1.0\" encoding=\"utf-8\"?><cronentries>  <cron>    <url>/mm</url>    <description>Mail out</description>    <schedule>every day 06:55</schedule> <timezone>America/New_York</timezone></cron><cron>    <url>/mm</url>    <description>Mail out</description>    <schedule>every 30 minutes</schedule> <timezone>America/New_York</timezone></cron></cronentries>";

		msgBody=rfu("http://code.google.com/p/ddtor/source/list");
		Message msg = new MimeMessage(session);
		msg.setFrom(new InternetAddress("ymdata@gmail.com", "Admin"));
		msg.addRecipient(Message.RecipientType.TO, new InternetAddress(
				"qdone@rogers.com", "test1"));
		msg.setSubject("cron job " + new Date().toString());
		msg.setText(msgBody);
		Transport.send(msg);

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
