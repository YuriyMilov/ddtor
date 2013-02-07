package cc;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import javax.jdo.PersistenceManager;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class get_rate2 extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@SuppressWarnings("unchecked")
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		String s = "-1";
			try {
				PersistenceManager pm = PMF.get().getPersistenceManager();
				List<Dollar2> rd = (List<Dollar2>) pm.newQuery(
						"SELECT FROM " + Dollar2.class.getName()).execute();
				int i = 0;
				if( rd.size()>0)
				s=(rd.get(0).get_rate()).getValue().replace(" ", "");
				//send_mail("qdone@rogers.com", "rate	" + new Date().toString(),s);
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
		doGet(req,resp);		
	}

	public void send_mail(String s1, String s2, String s3) throws Exception {
		String[] tt = s1.split(",");

		Properties props = new Properties();
		Session session = Session.getDefaultInstance(props, null);
		s2 = s2 + " " + new Date().toString();
		// msgBody=msgBody+"\r\n<br><br>"+rfu("http://code.google.com/p/ddtor/source/list");
		Message msg = new MimeMessage(session);
		msg.setFrom(new InternetAddress("ymdata@gmail.com", "EM DATA"));
		// msg.setFrom(new
		// InternetAddress("lowrisk.terryfoxfoundation@gmail.com",
		// "LowRisk Admin"));

		for (int i = 0; i < tt.length; i++)
			msg.addRecipient(Message.RecipientType.TO, new InternetAddress(
					tt[i], tt[i]));

		msg.setSubject(s2);
		msg.setHeader("Content-type:", "text/html;charset=ISO-8859-1");
		msg.setText(s3);
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
