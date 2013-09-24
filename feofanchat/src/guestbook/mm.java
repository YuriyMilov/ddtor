package guestbook;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeUtility;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.BasicConfigurator;
import org.jsoup.Jsoup;
import org.mindswap.pellet.jena.PelletReasonerFactory;

import para.st;

import com.clarkparsia.pellet.sparqldl.jena.SparqlDLExecutionFactory;
import com.google.appengine.api.blobstore.BlobInfo;
import com.google.appengine.api.blobstore.BlobInfoFactory;
import com.google.appengine.api.blobstore.BlobKey;
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

public class mm extends HttpServlet {
	public static String slog = "";
	public static String slog1 = "";

	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		try {
			BasicConfigurator.configure();
			String sh = req.getScheme() + "://" + req.getServerName() + ":"
					+ req.getServerPort() + req.getContextPath();
			Properties props = new Properties();
			Session session = Session.getDefaultInstance(props, null);
			MimeMessage ms1 = new MimeMessage(session, req.getInputStream());
			Object msgContent = ms1.getContent();

			String s = "";
			if (msgContent instanceof Multipart) {

				Multipart multipart = (Multipart) msgContent;
				for (int j = 0; j < multipart.getCount(); j++) {
					BodyPart bodyPart = multipart.getBodyPart(j);
					String disposition = bodyPart.getDisposition();
					if (disposition != null
							&& (disposition.equalsIgnoreCase("ATTACHMENT"))) {
						DataHandler handler = bodyPart.getDataHandler();
					} else {
						s = bodyPart.getContent().toString(); 
					}
				}
			} else
				s = ms1.getContent().toString();

			String sbj = ms1.getSubject();
			System.err.println( ms1.getSender().toString() + " ------> "+ sbj);
			System.err.println( " encoding ------> "+ ms1.getEncoding());
			String ss = Jsoup.parse(s).body().text();
			ss=MimeUtility.encodeText(ss, "utf-8", "B");
			
			stat.sr="";
			
			stq.get_mm(sh,sbj,ss);		

		} catch (Exception e) {
			System.err.println(e.toString());
		}
	}

	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		doGet(req, resp);
	}


	
	
	private static final long serialVersionUID = 1L;

}