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

public class stq  {

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
				i = ss2.length;
				s="";
				int n=0;
				
					while (n < i)
						s = s + ss2[n++] + ", ";

					n=s.lastIndexOf(",");
					if(n>-1)
						s=s.substring(0,n);

					s=s.trim();
					if (s.length()==0)
						s="Не знаю. Расскажи мне об этом";
				s=ss[0] + " - " +ss[1] + "? - " +s+"."; 
			

			} else
			// ///////////////////////////////////////////
			//
			// вопрос из 3-х слов
			//
			// ///////////////////////////////////////////

			if (i == 3) {
				OntModel mm = ModelFactory
						.createOntologyModel(PelletReasonerFactory.THE_SPEC);

				mm.read(sh + "/owl");
				s = stat.sowl;

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

				if (s.trim().length() == 0) {
					s = stat.spref
							// +"SELECT ?кто  WHERE {?кто qq:любит qq:Миша}";
							// +"SELECT ?кто  WHERE {qq:Маша qq:любит ?кто}";
							// +"SELECT ?кто  WHERE {?кто qq:"+ss[1]+" qq:"+ss[2]+"}";
							+ "SELECT ?кто  WHERE {qq:" + ss[2] + " qq:"
							+ ss[1] + " ?кто}";

					qq = QueryFactory.create(s);
					r = SparqlDLExecutionFactory.create(qq, mm).execSelect();
					s = "";
					while (r.hasNext())
						s = s + r.next().toString();

					s = s.replace(
							"( ?кто = <http://owl.feofan.com/rff?83.owl#", "")
							.replace("> ) -> [Root]", "");
					s=s+"."; 
				}
			} else
				s = "Пока что вопрос должен начинается со слов Кто, Что, Кого и состоять из 2-х или 3-х слов.";
		}

		return s;

	}

}