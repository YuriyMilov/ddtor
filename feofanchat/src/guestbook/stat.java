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
	public static String s2 = "<br/><br/><form  action=qq method=post> <input type=text id=id name=p2 size=82> <br><br>&nbsp;<a href=qq>начнём сначала</a> &nbsp;&nbsp; <a href=qq?p2=кря>кря</a> &nbsp;&nbsp; <a href=../load.htm>загрузить мир</a> &nbsp;&nbsp; <a href=../add.htm>добавить миру мир</a> &nbsp;&nbsp; <a href>сохранить мир</a> " +
			"<br><br>&nbsp;<a href=qq?p2=имена>имена</a> &nbsp;&nbsp; <a href=qq?p2=понятия>понятия</a> " +
			"</form> </body></html>";
	public static String stop = "";
	public static String sowl = "";
	public static String sr = "";
	
	public static void page(HttpServletRequest req, HttpServletResponse resp,
			String sotvet) throws IOException {

		stop = stop + "<br><br>\r\n<b><i>Феофан:</i></b>\r\n \r\n<!--otvet-->" + sotvet+ "<!--otvet-->\r\n<br>";

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

	
	public static String get_owl(String s) {

		String[] stok = null;
		String phrase = null;
		String s8 = "[.]+";
		String delims = "[ ]+";

		s = prep_all(s);

		s = s.replace("есть", "");
		s = s.replace("это", "-");

		String[] ss9 = s.split(s8);
		s = open_rdf();
		{
			for (int i = 0; i < ss9.length; i++) {
				phrase = ss9[i].trim();
				stok = phrase.split(delims);

				if (stok.length == 2)
				// s = Statik.add_subclass(s, stok[1], stok[0]);
				{
					s = add_class_rdf(s, stok[1]);
					s = add_subclass_rdf(s, stok[0], stok[1]);
				}
				stok = phrase.split(delims);
				if (stok.length == 3)
					// s = Statik.add_classassertion(s, stok[2],
					// stok[0]);

					s = add_inividual_rdf(s, stok[0], stok[2]);
			}
		}
		// s = Statik.close_owl(s);
		s = close_rdf(s);

		// clear_blobstore();

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
	
	public static void send_file(HttpServletRequest req, HttpServletResponse resp, String s) 
			throws IOException {
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
				+ "<owl:Ontology rdf:about=\"http://feofan.com/contrus\"/>\r\n\r\n";

		return s;
	}

	public static String close_rdf(String s) {
		return s + "\r\n\r\n</rdf:RDF>\r\n";
	}

	public static String add_class_rdf(String s, String sclass) {
		return s + "\r\n<Class rdf:about=\"&qq;" + sclass + "\"/>\r\n";
	}

	public static String add_subclass_rdf(String s, String ssubclass,
			String sclass) {
		return s + "<Class rdf:about=\"&qq;" + ssubclass
				+ "\"><rdfs:subClassOf rdf:resource=\"&qq;" + sclass
				+ "\"/></Class> \r\n";
	}

	static String add_inividual_rdf(String s, String sind, String sclass) {
		return s + "<NamedIndividual rdf:about=\"&qq;" + sind
				+ "\"> <rdf:type rdf:resource=\"&qq;" + sclass
				+ "\"/> </NamedIndividual>\r\n";
	}

	public static String add_has(String s, String simeet) {
		s = s + "<owl:ObjectProperty rdf:about=\"&qq;" + simeet
				+ "\"/>\r\n\r\n";
		return s;
	}

	public static String add_a_has_b(String s, String skto, String simeet,
			String skogo) {

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

		s7 = s7.replace("Любой", "");
		s7 = s7.replace("Любая", "");
		s7 = s7.replace("Любое", "");
		s7 = s7.replace("Всякий", "");
		s7 = s7.replace("Всякая", "");
		s7 = s7.replace("Всякое", "");
		s7 = s7.replace("Каждый", "");
		s7 = s7.replace("Каждая", "");
		s7 = s7.replace("Каждое", "");

		s7 = s7.replace("любой", "");
		s7 = s7.replace("любая", "");
		s7 = s7.replace("любое", "");
		s7 = s7.replace("всякий", "");
		s7 = s7.replace("всякая", "");
		s7 = s7.replace("всякое", "");
		s7 = s7.replace("каждый", "");
		s7 = s7.replace("каждая", "");
		s7 = s7.replace("каждое", "");
		s7 = s7.replace("это", "-");
		s7 = s7.replace("\"", "");
		s7 = s7.replace("&", "");
		s7 = s7.replace("#", "");
		s7 = s7.replace("<", "");
		s7 = s7.replace(">", "");

		return s7.trim();
	}

}
