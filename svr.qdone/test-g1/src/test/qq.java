package test;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.servlet.http.*;

import java.util.Date;
import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;

public class qq extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		String s="OK";
		try {
		s=send_mail();
			
			//s=m1();
		} catch (Exception e) {
			s=e.toString();
		}
		PrintWriter out = resp.getWriter();
		out.write(s);
		out.flush();
		out.close();
	}
public String send_mail()throws Exception{


    Properties props = new Properties();
    Session session = Session.getDefaultInstance(props, null);

    String msgBody = new Date().toString();

        Message msg = new MimeMessage(session);
        msg.setFrom(new InternetAddress("ymdata@gmail.com", "Server"));
        msg.addRecipient(Message.RecipientType.TO,
                         new InternetAddress("qdone@rogers.com","Yuri"));
        msg.setSubject("Cron jobs "+msgBody);
        msg.setText(msgBody);
        Transport.send(msg);
   return msgBody;

   
}
	


public String m1() {
		String s = "", sa = "";
/*		try {
			s = "";
			Driver drv = (Driver) Class.forName("sun.jdbc.odbc.JdbcOdbcDriver")
					.newInstance();
			DriverManager.registerDriver(drv);
			Connection conn = DriverManager.getConnection("jdbc:odbc:dbFox");
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM \"country.dbf\"");
			while (rs.next())
				s = s + rs.getObject(2).toString().trim() + "  "
						+ rs.getObject(3).toString().trim() + "<br>";
			sa = s;
		} catch (Exception e) {
			sa = e.toString();
		}*/
		return sa;
	}

}