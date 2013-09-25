package guestbook;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.channels.Channels;
import java.nio.charset.Charset;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;
import javax.mail.util.ByteArrayDataSource;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jsoup.Jsoup;
import org.mindswap.pellet.jena.PelletReasonerFactory;
import org.semanticweb.owlapi.model.AddAxiom;
import org.semanticweb.owlapi.model.OWLClassAxiom;
import org.semanticweb.owlapi.model.OWLDataProperty;
import org.semanticweb.owlapi.model.OWLIndividual;

import para.st;

import com.clarkparsia.pellet.sparqldl.jena.SparqlDLExecutionFactory;
import com.google.appengine.api.blobstore.BlobInfo;
import com.google.appengine.api.blobstore.BlobInfoFactory;
import com.google.appengine.api.blobstore.BlobKey;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.FetchOptions;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query.FilterOperator;
import com.google.appengine.api.files.AppEngineFile;
import com.google.appengine.api.files.FileService;
import com.google.appengine.api.files.FileServiceFactory;
import com.google.appengine.api.files.FileWriteChannel;
import com.google.gwt.core.client.EntryPoint;
import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.query.Query;
import com.hp.hpl.jena.query.QueryFactory;
import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.rdf.model.ModelFactory;

public class stq {

	public static void mail_admins(String subject, String text) {
		Properties props = new Properties();
		Session session = Session.getDefaultInstance(props, null);
		try {
			MimeMessage msg = new MimeMessage(session);
			msg.setFrom(new InternetAddress("kuka@feofan.com", MimeUtility
					.encodeText("Феофан", "utf-8", "B")));
			msg.addRecipient(MimeMessage.RecipientType.TO, new InternetAddress(
					"admins"));
			msg.setSubject(MimeUtility.encodeText(subject, "utf-8", "B"));
			msg.setText(text);
			Transport.send(msg);
		} catch (Exception e) {
		}
	}

	public static String get_ans(String sh, String s, HttpServletRequest req,
			HttpServletResponse resp) {

		boolean ber = false;
		s = s.replace("?", "");
		String[] ss = s.split("[ ]+");
		int i = ss.length;
		String s3 = "";

		boolean bb = ss[0].toLowerCase().equals("кто")
				|| ss[0].toLowerCase().equals("что")
				|| ss[0].toLowerCase().equals("кого");

		if (bb) {

			// ///////////////////////////////////////////
			//
			// вопрос из 2-х слов - "кто Сократ?"
			//
			// ///////////////////////////////////////////

			if (i == 2) {
				
				OntModel mm = ModelFactory
						.createOntologyModel(PelletReasonerFactory.THE_SPEC);
				StringReader reader = new StringReader(stat.sowl);
				mm.read(reader, "");
				s = stat.sowl;
				
				String[] ssss = {
						//"SELECT ?кто  WHERE {?кто a qq:" + ss[1] + "}",
						"SELECT ?кто  WHERE {qq:" + ss[1] + " rdf:type ?кто}",
						"SELECT ?кто  WHERE {?кто rdf:type qq:" + ss[1] + "}" };

				for (String str : ssss) {
	
					s = stat.get_prefix() + str;

					Query qq = QueryFactory.create(s);
					s = stat.sowl;
					s = "";
					ResultSet r = null;
					
					try {
						r = SparqlDLExecutionFactory.create(qq, mm)
								.execSelect();
						while (r.hasNext())
						{
							s= r.next().toString();
							if(!s3.contains(s))
								s3=s3+s;
							}
					} catch (Exception er) {
						s = er.toString();
						ber = true;
					}
					s3 = s3 + s;
				}

				s = s3;
				if (s.length() == 0)
					s = "Не знаю. Расскажи мне об этом";
			}

			// ///////////////////////////////////////////
			//
			// вопрос из 3-х слов - "кто любит Кнопочку?"
			//
			// ///////////////////////////////////////////
			else

			if (i == 3) {
				OntModel mm = ModelFactory
						.createOntologyModel(PelletReasonerFactory.THE_SPEC);

				StringReader reader = new StringReader(stat.sowl);
				mm.read(reader, "");
				s = stat.sowl;

				String[] ssss = {
						"SELECT ?кто  WHERE {?кто qq:" + ss[1] + " qq:" + ss[2] + "}",
						"SELECT ?кто  WHERE { qq:" + ss[2] + " qq:" + ss[1] + " ?кто }" };

				for (String str : ssss) {
	
					s = stat.get_prefix() + str;

					Query qq = QueryFactory.create(s);
					s = stat.sowl;
					s = "";
					ResultSet r = null;
					
					try {
						r = SparqlDLExecutionFactory.create(qq, mm)
								.execSelect();
						while (r.hasNext())
						{
							s= r.next().toString();
							if(!s3.contains(s))
								s3=s3+s;
							}
					} catch (Exception er) {
						s = er.toString();
						ber = true;
					}
					s3 = s3 + s;
				}

				s = s3;
				if (s.length() == 0)
					s = "Не знаю :-(";
				
				
			} else
				s = "Пока что вопрос должен начинается со слов Кто, Что, Кого и состоять из 2-х или 3-х слов.";
		}

		if (ber) {
			stat.page(req, resp, s);
		}

	

		if (s.indexOf("#") > 0) {
			s = s.substring(s.indexOf("#") + 1);

			s = s.replaceAll("[<>]", "").replace("[", "");
			
			ss = s.split("[#]");
			s = "";
			s3=",";
			for (String sd : ss) {
				if (sd.indexOf(")") > 0)
				{
					s = sd.substring(0, sd.indexOf(")")).trim();
					if(!s3.contains(s))
						s3=s3+", "+s;
				
				}
				s = s3.replace(",," ,"");
			}
		} else
			s = "не знаю :-(";

		s = s + ".";
		return s;
	}

	public static String get_mm(String sh, String sb, String s,
			HttpServletRequest req, HttpServletResponse resp) {

		String s1 = s, s3 = "", svopros = "", sotvet = "";

		int i1 = s.toLowerCase().indexOf("йй");
		int i2 = s.toLowerCase().indexOf("ййй");
		int i3 = 0;

		boolean bkrk = i2 > i1 && i1 > -1;

		if (bkrk) {
			s = s.substring(i1 + 2, i2);
			s = stat.prep_all(s);

			i3 = s.indexOf("?");
			if (i3 > 0) {

				svopros = s.substring(s.lastIndexOf(".") + 1).trim();

				s = s.substring(0, s.lastIndexOf(".") + 1).trim();
				add_sr(s, sh);

				// s3 = stat.get_owl83(s1);
				// System.err.println("get_mm_s3 "+s3);

				try {
					sotvet = stq.get_ans(sh, svopros, req, resp);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				s = "\r\n" + s + "\r\n \r\n- " + svopros + "\r\n \r\n- "
						+ sotvet + "";

				System.err.println(s);

				// stq.sm("ymilov@gmail.com", "stq.get_mm", s);

			} else {

				s3 = stat.get_owl83(s1, sh);

				s = s
						+ "\r\n Не увидел вопроса. Сгенерил онтологию "
						+ " http://www.feofan.com/rff?83.owl \r\n-------------\r\n// ответ парсера get_owl83: \r\n---\r\n"
						+ s3;
			}

			stq.mail_admins(sb, s);
			System.err.println("------------------> sent to the forum");
		} else
			System.err.println("------------------> NOT sent");

		return s;
	}

	public static void add_sr(String s, String sh) {

		String[] ss = s.split("[.]");
		try {
			for (String s7 : ss)
				if (!stat.sr.contains(s7))
					stat.sr = stat.sr.trim() + " " + s7.trim() + ".";

			stat.get_owl83(stat.sr, sh);
			stat.w2f("83.owl", stat.sowl);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	static void sm(String skomu, String sbj, String s) {
		Properties props = new Properties();
		Session session = Session.getDefaultInstance(props, null);
		Message msg = new MimeMessage(session);
		try {
			msg.setFrom(new InternetAddress("kuka@feofan.com", MimeUtility
					.encodeText("Феофан", "utf-8", "B")));

			msg.addRecipient(Message.RecipientType.TO, new InternetAddress(
					skomu, " "));
			msg.setSubject(sbj);
			msg.setText(s);
			Transport.send(msg);
		} catch (Exception e) {

			e.printStackTrace();
		}
	}

	static void smtxt(String s) {
		Properties props = new Properties();
		Session session = Session.getDefaultInstance(props, null);
		Message msg = new MimeMessage(session);
		try {
			msg.setFrom(new InternetAddress("kuka@feofan.com",
					"Example.com Admin"));

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
			messageBodyPart.addHeader("Content-Type",
					" text/xml; charset=utf-8");
			multipart.addBodyPart(messageBodyPart);

			// Send the complete message parts
			msg.setContent(multipart);

			Transport.send(msg);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}