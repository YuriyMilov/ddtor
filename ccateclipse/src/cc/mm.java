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
		String s = "";
		
		PrintWriter out = resp.getWriter();
		out.write(s);
		out.flush();
		out.close();
	}

	public void send_mail() throws Exception {

		Properties props = new Properties();
		Session session = Session.getDefaultInstance(props, null);

		String msgBody = "This notification has been scheduled for daily updates and generated authomaticly by qdbook mailing system based on the current project updates.";
		
		//msgBody=msgBody+"\r\n<br><br>"+rfu("http://code.google.com/p/ddtor/source/list");
		
		Message msg = new MimeMessage(session);
		msg.setFrom(new InternetAddress("ymdata@gmail.com", "Test"));
		msg.addRecipient(Message.RecipientType.TO, new InternetAddress(
				"cron_job_update@quicklydone.com", "Admin"));
		msg.setSubject("cron job " + new Date().toString());
		msg.setHeader("Content-type:","text/html;charset=ISO-8859-1");
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
