package cc;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Properties;

import javax.mail.MessagingException;
import javax.mail.Part;
import javax.mail.Session;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.servlet.http.*;

public class MailHandlerServlet extends HttpServlet {
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		Properties props = new Properties();
		Session session = Session.getDefaultInstance(props, null);

		try {
			MimeMessage message = new MimeMessage(session, req.getInputStream());
			// String s=String.valueOf(message.getSize());
			String s = "qq";
			if (message.getContent() == null)
				s = "message.getContent()_IS_NULL";
			else
				s= "Subject_" + message.getSubject().toString()+  "_Body_" + get_body(message);

			shta.rfu("http://174.117.66.8/gu/mail.aspx?" + s);

		} catch (MessagingException e) {
			shta.rfu("http://174.117.66.8/gu/mail.aspx?" + e.toString());
		}
	}

	public static String get_body(Part p) {
		String s = "qq";
		try {
			if (p.isMimeType("text/plain")) {
				s = (String) p.getContent();
			} else {
				MimeMultipart mb = null;
				mb = (MimeMultipart) (p.getContent());
				MimeBodyPart mb1 = (MimeBodyPart) mb.getBodyPart(0);
				s = mb1.getContent().toString();
			}
		} catch (Exception e) {
			s = e.toString();
		}
		return s;
	}
}
