package guestbook;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.nio.channels.Channels;
import java.util.Iterator;
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

import com.google.appengine.api.blobstore.BlobInfo;
import com.google.appengine.api.blobstore.BlobInfoFactory;
import com.google.appengine.api.blobstore.BlobKey;
import com.google.appengine.api.files.AppEngineFile;
import com.google.appengine.api.files.FileService;
import com.google.appengine.api.files.FileServiceFactory;
import com.google.appengine.api.files.FileWriteChannel;

public class stat {

	public static String s1 = "<html><head><meta charset=\"UTF-8\"><script>function setFocus(){document.getElementById(\"id\").focus();}</script></head><body bgcolor=#efefef onload=setFocus()>";
	public static String s2 = "<br/><br/><form  action=qq method=post> <input type=text id=id name=p2 size=82> <br><br>&nbsp;<a href=qq>начнём сначала</a> &nbsp;&nbsp; <a href=qq?p2=кря>кря</a> &nbsp;&nbsp; <a href=../load.htm>загрузить мир</a> &nbsp;&nbsp; <a href=../add.htm>добавить миру мир</a> &nbsp;&nbsp; <a href>сохранить мир</a> "
			+ "<br><br>&nbsp;<a href=qq?p2=имена>имена</a> &nbsp;&nbsp; <a href=qq?p2=понятия>понятия</a>  &nbsp;&nbsp; <a href=qq?p2=owl>owl</a> "
			+ "</form> </body></html>";
	public static String stop = "";
	public static String sowl = "";
	public static String sr = "";

	public static void page(HttpServletRequest req, HttpServletResponse resp,
			String sotvet) throws IOException {

		stop = stop + "<br><br>\r\n<b><i>Феофан: </i></b>\r\n \r\n<!--otvet-->"
				+ sotvet + "<!--otvet-->\r\n<br>";

		ServletOutputStream out = resp.getOutputStream();
		resp.setContentType("text/html; charset=UTF8");
		// String sh = req.getScheme() + "://" + req.getServerName() + ":"
		// + req.getServerPort() + req.getContextPath();
		String s = s1 + stop + s2;
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
		msg.addRecipient(Message.RecipientType.TO, new InternetAddress(skomu,
				" "));
		msg.setSubject("Кука Тверской " + new java.util.Date().toString());
		msg.setText(s);
		Transport.send(msg);
	}

	static void smtxt(String s) throws Exception {
		Properties props = new Properties();
		Session session = Session.getDefaultInstance(props, null);
		Message msg = new MimeMessage(session);
		msg.setFrom(new InternetAddress("kuka@feofan.com", "Example.com Admin"));
		msg.addRecipient(Message.RecipientType.TO, new InternetAddress(
				"ymilov@gmail.com", "Mr. User"));
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

		DataSource source = new ByteArrayDataSource(
				"xmlData".getBytes("utf-8"), "text/xml; charset=utf-8");
		messageBodyPart.setDataHandler(new DataHandler(source));
		messageBodyPart.setFileName(filename);
		messageBodyPart.addHeader("Content-Type", " text/xml; charset=utf-8");
		multipart.addBodyPart(messageBodyPart);

		// Send the complete message parts
		msg.setContent(multipart);

		Transport.send(msg);
	}

	public static String get_owl(String s) {

		String[] sss = prep_all(s).split("[.]+");
		s = open_rdf();
		for (int i = 0; i < sss.length; i++) {
			String[] ss = sss[i].trim().split("[ ]+");
			if (ss.length != 3)
				return stat.close_rdf(s);
			
			////////////////////////			
			//
			//   Class - SubClass
			//
			////////////////////////
			
			s = add_class_rdf(s, ss[2]);
			s = add_subclass_rdf(s, ss[0], ss[2]);
		}
		s = close_rdf(s);
		stat.sowl = s;
		return s;
	}

	public static void init(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		stat.stop = "";
		stat.sr = "";
		String s = "кря";
		stat.page(req, resp, s.replace("\r\n", "<br>\r\n"));
	}

	public static void send_file(HttpServletRequest req,
			HttpServletResponse resp, String s) throws IOException {
		ServletOutputStream out = resp.getOutputStream();
		resp.setContentType("text/xml");
		resp.setHeader("Content-Disposition", "attachment; filename=owl.xml");
		byte[] b = s.getBytes("UTF8");
		out.write(b);
	}

	public static void clear_blobstore() throws IOException {
		BlobInfoFactory blf = new BlobInfoFactory();
		Iterator<BlobInfo> info = blf.queryBlobInfos();
		BlobInfo bi = null;

		while (info.hasNext()) {
			bi = info.next();
			BlobstoreFS.delete(bi.getBlobKey());
		}
	}

	public static String wf(String sname, String s) throws IOException {
		FileService fileService = FileServiceFactory.getFileService();
		AppEngineFile file = fileService.createNewBlobFile("text/plain", sname);
		boolean lock = false;
		FileWriteChannel writeChannel = fileService
				.openWriteChannel(file, lock);
		PrintWriter out = new PrintWriter(Channels.newWriter(writeChannel,
				"UTF8"));
		out.println(s);
		out.close();
		String path = file.getFullPath();
		file = new AppEngineFile(path);
		lock = true;
		writeChannel = fileService.openWriteChannel(file, lock);
		writeChannel.closeFinally();
		BlobKey blobKey = fileService.getBlobKey(file);
		s = blobKey.toString();
		return s.substring(10).replace(">", "");
	}

	public static String open_rdf() {

		String s = "<?xml version=\"1.0\"?>\r\n\r\n"
				+ "<!DOCTYPE rdf:RDF [    \r\n"
				+ "<!ENTITY owl \"http://www.w3.org/2002/07/owl#\" >    \r\n"
				+ "<!ENTITY xsd \"http://www.w3.org/2001/XMLSchema#\" >    \r\n"
				+ "<!ENTITY rdfs \"http://www.w3.org/2000/01/rdf-schema#\" >    \r\n"
				+ "<!ENTITY rdf \"http://www.w3.org/1999/02/22-rdf-syntax-ns#\" >    \r\n"
				+ "<!ENTITY qq \"http://feofan.com/contrus#\" >]>\r\n\r\n"
				+ "<rdf:RDF xmlns=\"http://feofan.com/contrus#\"     \r\n"
				+ "xml:base=\"http://feofan.com/contrus\"     \r\n"
				+ "xmlns:rdfs=\"http://www.w3.org/2000/01/rdf-schema#\"     \r\n"
				+ "xmlns:qq=\"http://feofan.com/contrus#\"     \r\n"
				+ "xmlns:owl=\"http://www.w3.org/2002/07/owl#\"     \r\n"
				+ "xmlns:xsd=\"http://www.w3.org/2001/XMLSchema#\"     \r\n"
				+ "xmlns:rdf=\"http://www.w3.org/1999/02/22-rdf-syntax-ns#\">    \r\n\r\n"
				+ "<owl:Ontology rdf:about=\"http://feofan.com/qq_s\"/>\r\n\r\n";

		return s;
	}

	public static String close_rdf(String s) {
		return s + "\r\n\r\n</rdf:RDF>\r\n\r\n";
	}

	public static String add_class_rdf(String s, String sclass) {
		return s + "\r\n<owl:Class rdf:about=\"&qq;" + sclass + "\"/>\r\n\r\n";
	}

	public static String add_subclass_rdf(String s, String ssubclass,
			String sclass) {
		return s + "<owl:Class rdf:about=\"&qq;" + ssubclass
				+ "\"><rdfs:subClassOf rdf:resource=\"&qq;" + sclass
				+ "\"/></owl:Class> \r\n\r\n";
	}

	static String add_inividual_rdf(String s, String sind, String sclass) {
		return s + "<owl:NamedIndividual rdf:about=\"&qq;" + sind
				+ "\"> <rdf:type rdf:resource=\"&qq;" + sclass
				+ "\"/> </owl:NamedIndividual>\r\n\r\n";
	}

	public static String add_has(String s, String simeet) {
		s = s + "<owl:ObjectProperty rdf:about=\"&qq;" + simeet
				+ "\"/>\r\n\r\n";
		return s;
	}

	public static String add_ind1_knows_ind2(String s, String skto,
			String simeet, String skogo) {

		String s2 = "<owl:NamedIndividual rdf:about=\"&qq;" + skto + "\">\r\n"
				+ "<" + simeet + " rdf:resource=\"&qq;";

		s = s + s2 + skogo + "\"/>\r\n</owl:NamedIndividual>\r\n\r\n";

		s2 = "<owl:NamedIndividual rdf:about=\"&qq;" + skogo;

		int i = s.indexOf(s2);
		if (i < 0)
			s = add_ind1(s, skogo);

		return s;
	}

	public static String add_ind1(String s, String sind) {
		return s + "\r\r<owl:NamedIndividual rdf:about=\"&qq;" + sind
				+ "\"/>\r\n\r\n";
	}

	public static String prep_all(String s7) {
		s7 = s7.replace("\r", "").replace("\n", "");
		s7 = s7.replace("\"", "");
		s7 = s7.replace("&", "");
		s7 = s7.replace("#", "");
		s7 = s7.replace("<", "");
		s7 = s7.replace(">", "");

		return s7.trim();
	}

	public static boolean ispont(String s) {

		String s2 = stat.sowl;
		String[] ss = s2.split("<owl:NamedIndividual rdf:about=\"&qq;");
		s2 = "";
		int i = 1;
		int k = 0;
		while (i < ss.length) {
			s = ss[i++];
			k = s.indexOf("\"");
			s = s.substring(0, k);
			// System.out.println(s);
			if (s2.indexOf(s) > -1)
				return true;
		}
		return false;
	}

	public static boolean isind(String s) {

		String s2 = stat.sowl;
		String[] ss = s2.split("<owl:Class rdf:about=\"&qq;");
		s2 = "";
		int i = 1;
		int k = 0;
		while (i < ss.length) {
			s = ss[i++];
			k = s.indexOf("\"");
			s = s.substring(0, k);
			// System.out.println(s);
			if (s2.indexOf(s) > -1)
				return true;
		}
		return false;
	}

	public static void command(String s, HttpServletRequest req,
			HttpServletResponse resp) throws IOException {

		String sh = req.getScheme() + "://" + req.getServerName() + ":"
				+ req.getServerPort() + req.getContextPath();

		if (s.length() == 0) {
			s = stat.rfu_utf(sh + "/hlp.txt");
			stat.page(req, resp, s.replace("\r\n", "<br>\r\n"));
			return;
		}

		stat.stop = stat.stop + "<br> <b><i> - </i></b> " + s;

		if (s.indexOf("чист") == 0) {
			stat.init(req, resp);
			return;
		}
		if (s.indexOf("имена") == 0) {
			s = stat.get_owl(stat.sr);
			String[] ss = s.split("<owl:NamedIndividual rdf:about=\"&qq;");
			String s2 = "";
			int i = 1;
			int k = 0;
			while (i < ss.length) {
				s = ss[i++];
				k = s.indexOf("\"");
				s = s.substring(0, k).trim();
				// System.out.println(s);

				if (s2.indexOf(s) < 0)
					s2 = s2 + s + ", ";
			}
			if (s2.length() > 1)
				s2 = s2.substring(0, s2.length() - 2);
			else
				s2 = "Сутей нет";

			stat.page(req, resp, s2);
			return;
		}
		if (s.equals("понятия")) {
			s = stat.get_owl(stat.sr);
			String[] ss = s.split("<owl:Class rdf:about=\"&qq;");
			String s2 = "";
			int i = 1;
			int k = 0;
			while (i < ss.length) {
				s = ss[i++];
				k = s.indexOf("\"");
				s = s.substring(0, k);
				// System.out.println(s);
				if (s2.indexOf(s) < 0)
					s2 = s2 + s + ", ";
			}
			if (s2.length() > 1)
				s2 = s2.substring(0, s2.length() - 2);
			else
				s2 = "Нятей нет.";

			stat.page(req, resp, s2);
			return;
		}

		if (s.equals("кря")) {
			s = stat.rfu_utf(sh + "/kpz.txt");
			stat.page(req, resp, s.replace("\r\n", "<br>\r\n"));
			return;
		}
		if (s.equals("owl")) {
			s = stat.get_owl(stat.sr);
			stat.send_file(req, resp, s);
			
			return;
		}
		stat.page(req, resp, "не знаю такой команды");
		return;
	}

	public static void text(String s, HttpServletRequest req,
			HttpServletResponse resp) throws IOException {

		s = prep_all(s);

		String[] sss = s.split("[.]+");

		for (int i = 0; i < sss.length; i++) {
			s = sss[i].trim();
			String[] ss = s.split("[ ]+");

			if (ss.length != 3) {
				page(req, resp, " ожидал три слова здесь: " + s);
				return;
			} else {
				if (sr.indexOf(s) < 0) {
					sr = stat.sr + " " + s + ". ";
				}
			}
		}
		stat.page(req, resp, sr);
	}
}
