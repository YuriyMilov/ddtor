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
			// Calendar calendar = new GregorianCalendar();
			java.util.Date dd = new java.util.Date();
			String sd = dd.toString();

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
						s = bodyPart.getContent().toString(); // the changed
																// code
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
					ss = s1 + "\r\n------\r\n" + get_ans(sh,s4);
						mail_admins(sb, ss);
				}
				else
				{
					s1=stat.remove83(s1);
					s3 = stat.get_owl83(s1);

					ss = ss+"\r\nНе увидел вопроса. Сгенерил онтологию "
							+ " http://www.feofan.com/rff?83.owl\r\n-------------\r\n// ответ парсера get_owl83 - "
							+ s3;

					mail_admins(sb, ss);
				}
			}

			// ////////////////////////////////////

			/*
			 * s= s.split("\n")[0].replaceAll("\\<.*?>",""); s =
			 * stat.get_post(sh + "/qq", "p2=" + URLEncoder.encode(s, "UTF-8"));
			 * 
			 * String[] sss = s.split("<!--otvet-->"); String sall=""; int i =
			 * sss.length;
			 * 
			 * for(int k=2;k<i-1;k++){ sall=sall+sss[k]+"\r\n"; }
			 * 
			 * if (i > 1) s = sss[i - 2]; else s = "хмм... непонятно...";
			 * s=s.replaceAll("\\<.*?>","");
			 */

		} catch (Exception e) {
		}
	}

	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		doGet(req, resp);
	}

	public void mail_admins(String subject, String text) {
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

	public static String get_ans(String sh,String s) {

		s = s.replace("?", "");
		String[] ss = s.split("[ ]+");
		int i = ss.length;

		boolean bb = ss[0].toLowerCase().equals("кто")
				|| ss[0].toLowerCase().equals("что")
				|| ss[0].toLowerCase().equals("кого");

		if (bb) {

			// ///////////////////////////////////////////
			//
			// вопрос из 2-х слов
			//
			// ///////////////////////////////////////////

			if (i == 2) {
				OntModel mm = ModelFactory
						.createOntologyModel(PelletReasonerFactory.THE_SPEC);

				mm.read(sh+"/owl");

				s = stat.spref
				// +"SELECT ?кто  WHERE {?кто qq:любит qq:Миша}";
				// +"SELECT ?кто  WHERE {qq:Маша qq:любит ?кто}";
				// +"SELECT ?кто  WHERE {?кто qq:"+ss[1]+" qq:"+ss[2]+"}";

						+ "SELECT ?кто  WHERE {qq:" + ss[1] + " rdf:type ?кто}";

				Query qq = QueryFactory.create(s);
				ResultSet r = SparqlDLExecutionFactory.create(qq, mm)
						.execSelect();
				s = "";
				while (r.hasNext())
					s = s + r.next().toString();
				s = s.replace("http://owl.feofan.com/rff?83.owl#", "");

				s = s.replace("[Root]", "").trim();
				s = s.replace(">", "").trim();
				s = s.replace("<", "").trim();
				s = s.replace("-", "").trim();
				s = s.replace("(", "").trim();
				s = s.replace(")", "").trim();
				s = s.replace("=", "").trim();
				s = s.replace("?кто", "").trim();
				s = s.replace("owl:Thing", "").trim();

				
				String[] ss2 = s.split("[ ]+");
				i = ss.length;
				s="";
				int n=0;
				
					while (n < i)
						s = s + ss2[n++] + ", ";

					n=s.lastIndexOf(",");
					if(n>0)
						s=s.substring(0,n);

				s=ss[1] + " - " +s; 
			

			} else
			// ///////////////////////////////////////////
			//
			// вопрос из 3-х слов
			//
			// ///////////////////////////////////////////

			if (i == 3) {
				OntModel mm = ModelFactory
						.createOntologyModel(PelletReasonerFactory.THE_SPEC);
				
				mm.read(sh+"/owl");
				
				s = stat.spref
						// +"SELECT ?кто  WHERE {?кто qq:любит qq:Миша}";
						// +"SELECT ?кто  WHERE {qq:Маша qq:любит ?кто}";
						+ "SELECT ?кто  WHERE {?кто qq:" + ss[1] + " qq:"
						+ ss[2] + "}";

				Query qq = QueryFactory.create(s);
				ResultSet r = SparqlDLExecutionFactory.create(qq, mm)
						.execSelect();
				s = "";
				while (r.hasNext())
					s = s + r.next().toString();

				s = s.replace("( ?кто = <http://owl.feofan.com/rff?83.owl#", "")
						.replace("> ) -> [Root]", "");

			} else
				s = "Пока что вопрос должен начинается со слов Кто, Что, Кого и состоять из 2-х или 3-х слов.";
		}

		return s;

	}

	private static final long serialVersionUID = 1L;

}