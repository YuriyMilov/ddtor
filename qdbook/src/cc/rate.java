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

public class rate extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@SuppressWarnings("unchecked")
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		String s = rfu("http://www.bankofcanada.ca/en/markets/csv/exchange_eng.csv");
		s = s.substring(s.indexOf("USD"));
		s = s.substring(3, 111);

		int n = s.indexOf("U");
		s = s.substring(0, n);

		s = s.substring(s.lastIndexOf(",") + 1);
		s = s.replace("\r", "");
		s = s.replace("\n", "");
		if (s.length() > 0)
			try {
				PersistenceManager pm = PMF.get().getPersistenceManager();
				List<Dollar> rd = (List<Dollar>) pm.newQuery(
						"SELECT FROM " + Dollar.class.getName()).execute();
				int i = 0;
				while (i < rd.size())
					pm.deletePersistent(rd.get(i++));
				pm.makePersistent(new Dollar(s, new Date()));
				send_mail("qdone@rogers.com", "rate	" + new Date().toString(),
						s);
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
		try {

			send_mail(s1, s2, s3);
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
