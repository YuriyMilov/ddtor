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

			String ss = Jsoup.parse(s).body().text();

			String sb = ms1.getSubject();
			String s1 = "", s3 = "", s4 = "", s5 = "";

			int i1 = ss.toLowerCase().indexOf("кря");
			int i2 = ss.toLowerCase().indexOf("кряк");
			int i3 = 0;

			if (i2 > i1 && i1 > -1) {
				ss = ss.substring(i1 + 3, i2);

				s1 = ss.trim();

				i3 = ss.indexOf("?");
				if (i3 > 0) {
					s4 = ss.substring(0, i3 + 1);
					i3 = ss.lastIndexOf(".");

					if (i3 > 0) {
						s5 = ss.substring(0, i3 + 1).trim();
						s4 = s4.substring(i3 + 1).trim();
					}
					s3 = stat.get_owl83(s5);
					ss = s1 + "\r\n------\r\n" + stq.get_ans(sh,s4);
						stq.mail_admins(sb, ss);
				}
				else
				{
					s1=stat.remove83(s1);
					s3 = stat.get_owl83(s1);

					ss = ss+"\r\nНе увидел вопроса. Сгенерил онтологию "
							+ " http://www.feofan.com/rff?83.owl\r\n-------------\r\n// ответ парсера get_owl83 - "
							+ s3;

					stq.mail_admins(sb, ss);
				}
			}

		} catch (Exception e) {
		}
	}

	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		doGet(req, resp);
	}

	private static final long serialVersionUID = 1L;

}