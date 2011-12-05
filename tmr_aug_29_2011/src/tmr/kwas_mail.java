package tmr;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Enumeration;
import java.util.GregorianCalendar;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.Part;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.util.ByteArrayDataSource;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

public class kwas_mail extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public static String s3 = "";

	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		PrintWriter out = resp.getWriter();
		resp.setContentType("text/html");
		String s = "kwas get";
		try {
			send_mail("qdone@rogers.com" + req.getParameter("c104"),
					"Crates done " + new Date().toString(), s);
			s = "The confirmation email has been sent. <p><FORM><INPUT TYPE=\"button\" VALUE=\"Back\" onClick=\"history.go(-1);return true;\"></FORM>";
		} catch (Exception e) {
			s = e.toString();
			e.printStackTrace();
		}
		out.print(s);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		PrintWriter out = resp.getWriter();
		resp.setContentType("text/html");

		DateFormat df = new SimpleDateFormat("yyyyMMddhhmmss");
		String sn = df.format(new Date());
		String ss = "", s = req.getParameter("check");

		String s100 = "Trailer No: " + req.getParameter("c100");
		String s101 = "Seal No:    " + req.getParameter("c101");
		String s102 = "Vessel:     " + req.getParameter("c102");
		String s103 = "Date:       " + req.getParameter("c103");
		String s104 = "Email:      " + req.getParameter("c104");

		if (s == null)
			s = "";
		else
			ss = ss + s + "\r\n";

		if (s100 == null)
			s100 = "";
		else
			ss = ss + s100 + "\r\n";

		if (s101 == null)
			s101 = "";
		else
			ss = ss + s101 + "\r\n";

		if (s102 == null)
			s102 = "";
		else
			ss = ss + s102 + "\r\n";

		if (s103 == null || s103.trim().length() == 5) {
			Calendar cc = new GregorianCalendar();
			SimpleDateFormat sdf = new SimpleDateFormat("MMM'.'d',' yyyy");
			s103 = "Date:       " + sdf.format(cc.getTime());

		}

		ss = ss + s103 + "\r\n";
		ss = ss + s104 + "\r\n";

		for (int i = 1; i < 100; i++) {
			s = req.getParameter("c" + String.valueOf(i));

			if (s == null)
				s = "";
			else if (s.trim().length() == 0)
				s = "";
			else {
				if (s.length() > 6)
					s = s.substring(0, 6);
				if (i < 10)
					s = "  " + s;
				else if (i < 100)
					s = " " + s;
				ss = ss + "\r\nCrate No " + String.valueOf(i) + ":  " + s;
			}
		}

		try {
			String sd = df.format(new Date());
			byte[] ad = null;

			if (req.getParameter("c104").indexOf("@") > -1) {

				s104 = req.getParameter("c104").trim();
				// send_mail(s104, "Crates done ", ss);

				ad = getbb(ss);
				sm(ad, sd + ".pdf", "application/pdf", "ymdata@gmail.com",
						"Web UFOS", s104, "Recipients", "Crates done " + sd, ss);

				ss = "The data has been emailed to "+s104+" <p>"
						+ "<FORM>"
						+ "<INPUT TYPE=\"button\" VALUE=\"Back\" onClick=\"history.go(-1);return true;\" />"
						+ "</FORM> ";

			} else {
				ss = "Please check the email address <p>"
						+ "<FORM>"
						+ "<INPUT TYPE=\"button\" VALUE=\"Back\" onClick=\"history.go(-1);return true;\" />"
						+ "</FORM> ";
			}

		} catch (Exception e) {
			ss = e.toString();
			e.printStackTrace();
		}
		out.print(ss);
		s = "";
		ss = "";
	}

	public void send_mail(String s1, String s2, String s3) throws Exception {
		String[] tt = s1.split(",");

		Properties props = new Properties();
		Session session = Session.getDefaultInstance(props, null);

		s2 = s2 + " " + new Date().toString();

		// msgBody=msgBody+"\r\n<br><br>"+rfu("http://code.google.com/p/ddtor/source/list");

		Message msg = new MimeMessage(session);
		msg.setFrom(new InternetAddress("ymdata@gmail.com", "UFOS Web"));

		for (int i = 0; i < tt.length; i++)
			msg.addRecipient(Message.RecipientType.TO, new InternetAddress(
					tt[i], tt[i]));

		msg.setSubject(s2);
		msg.setHeader("Content-type:", "text/html;charset=ISO-8859-1");
		msg.setText(s3);
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

	byte[] getbb(String s) throws Exception {
		Document doc = new Document();
		ByteArrayOutputStream baosPDF = new ByteArrayOutputStream();
		PdfWriter docWriter = null;
		docWriter = PdfWriter.getInstance(doc, baosPDF);
		doc.open();

		doc.add(new Paragraph(s));

		// doc.add(new Paragraph("This is a pdf document."));
		// doc.add(new Paragraph("This document was created on "
		// + new java.util.Date()));
		doc.close();
		docWriter.close();
		return baosPDF.toByteArray();
	}
}
