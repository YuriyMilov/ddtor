package guestbook;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.nio.channels.Channels;

import java.util.Iterator;
import java.util.List;
import javax.jdo.PersistenceManager;
import javax.servlet.ServletException;
import javax.servlet.http.*;

import guestbook.PMF;
import guestbook.Greeting;

import com.google.appengine.api.blobstore.BlobInfo;
import com.google.appengine.api.blobstore.BlobInfoFactory;
import com.google.appengine.api.blobstore.BlobKey;
import com.google.appengine.api.files.AppEngineFile;
import com.google.appengine.api.files.FileService;
import com.google.appengine.api.files.FileServiceFactory;
import com.google.appengine.api.files.FileWriteChannel;
import com.google.appengine.api.xmpp.JID;
import com.google.appengine.api.xmpp.Message;
import com.google.appengine.api.xmpp.MessageBuilder;
import com.google.appengine.api.xmpp.MessageType;
import com.google.appengine.api.xmpp.SendResponse;
import com.google.appengine.api.xmpp.XMPPService;
import com.google.appengine.api.xmpp.XMPPServiceFactory;
import java.util.Properties;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException; 
import javax.mail.internet.InternetAddress; 
import javax.mail.internet.MimeMessage; 



@SuppressWarnings("serial")
public class GreetingChatbot extends HttpServlet {

    @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException,
      IOException {
		String sh = req.getScheme() + "://" + req.getServerName() + ":"
				+ req.getServerPort() + req.getContextPath();
		
    XMPPService xmpp = XMPPServiceFactory.getXMPPService();
    Message message = xmpp.parseMessage(req);
    JID id = message.getFromJid();
     
    String s = message.getBody();
    
    
	
	
	String sq2 = s,sq = s;
	sq = sq.replace("Кто тут?", "кто есть?");
	sq = sq.replace("Что тут?", "что есть?");
	sq = sq.replace("кто тут?", "кто есть?");
	sq = sq.replace("что тут?", "что есть?");
	sq = sq.replace("кто тут есть?", "Кто есть?");
	sq = sq.replace("что тут есть?", "Что есть?");
	sq = sq.replace("Кто тут есть?", "Кто есть?");
	sq = sq.replace("Что тут есть?", "Что есть?");

	int k = sq.length() - 1;
	if (sq.indexOf("?") != k)

	{
		sq2 = prep_all(sq2);
		String[] ss2 = sq2.split("[.]+");
		String[] ss3;
		String sq3 = "";
		String sq4 = "";

		int iq = ss2.length;
		int iq3 = 0;

		for (int i = 0; i < iq; i++) {
			sq3 = ss2[i].trim();
			ss3 = sq3.split("[ ]+");
			iq3 = ss3.length;

			if (iq3 < 2) {
				s = "В данной версии Кряка утверждения общего рода состоят из двух слов, " +
						"например, \"Человек смертен. Тигр хищник.\", или из трех слов, " +
						"первым из которых должны быть слова: Любой(-ая,-ое), Каждый(-ая,-ое), " +
						"Всякий(-ая,-ое), например, \"Всякий человек смертен. Любой тигр хищник.\", " +
						"или второе слово - \"это\", например, \"Васька это кот.\" или черточка - " +
						"Вася - мужик.\"";
				sm(id,s);
				return;
			}

			// //////////////

			if (sq.lastIndexOf(".") != k) {
				sq2 = sq + ". ";
				sq = sq + ". ";
			} else
				sq2 = sq;

			if (iq3 < 3) {

				sq3 = " всё, что называется " + ss3[0] + ", то "
						+ ss3[1] + ".";

			} else {
				if (!ss3[1].equals("-") && !ss3[1].equals("-")) {
					s = "В данной версии Кряка утверждения общего рода состоят из двух слов, например, \"Человек смертен. Тигр хищник.\", или из трех слов, первым из которых должны быть слова: Любой(-ая,-ое), Каждый(-ая,-ое), Всякий(-ая,-ое), например, \"Всякий человек смертен. Любой тигр хищник.\"";

					sm(id,s);
					return;
				} else {
					sq3 = ss3[0] + " это " + ss3[2] + ".";

				}
			}

			sq4 = sq4 + sq3;
		}

		s = "Понял так: " + sq4;
		
		//////////////////////////////////////////////////
		
		stat.sr = stat.sr + " " + sq;
		
		///////////////////////////////////////////////////
		
		
		sm(id,s);
		return;

	}

	else {
		String s5 = sq;
		boolean bb = s5.indexOf("Кто ") == 0 || s5.indexOf("кто ") == 0
				|| s5.indexOf("Что ") == 0 || s5.indexOf("что ") == 0;
		if (bb) {

			s5 = s5.replace("Кто ", "").replace("кто ", "")
					.replace("Что ", "").replace("что ", "")
					.replace("?", "").trim();

			if (s5.indexOf(" ") == -1) {
				String[] stok = null;
				String phrase = null;
				String s8 = "[.]+";
				String delims = "[ ]+";

				s = prep_all(stat.sr);

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
				
				
				
				//clear_blobstore();

				stat.sowl = s;

				//s = wf("test.owl", s);

				String surl = sh + "/qq5";
				String sowl = sh + "/qqr";
				String body = "p1=" + URLEncoder.encode(sowl, "UTF-8")
						+ "&p2=" + URLEncoder.encode(s5, "UTF-8");
				s = stat.get_post(surl, body);

				sm(id,s);
				return;
			}
			if (s5.indexOf(" ") > -1) {
				s = "В данной версии Кряка вопрос должен начинается словом 'Кто', потом идёт всего ОДНО слово по существу и знак вопроса";
				sm(id,s);
				return;
			}
		}
		if (s5.indexOf("Кто ") != 0 && s5.indexOf("кто ") != 0) {
			s="В данной версии Кряка вопрос должен начинается словами 'Кто','кто','Что','что', потом идёт одно слово по существу и знак вопроса";

			sm(id,s);
			return;
		}

	}

 }
  
  
void sm(JID fromJid, String s){
    // Send response..
    JID tojid = new JID(fromJid.getId());

    Message msg = new MessageBuilder().withRecipientJids(tojid).withBody(s).build();
    
    boolean messageSent = false;
    XMPPService xmpp = XMPPServiceFactory.getXMPPService();
    if (xmpp.getPresence(tojid).isAvailable()) {
      SendResponse status = xmpp.sendMessage(msg);
      messageSent = (status.getStatusMap().get(tojid) == SendResponse.Status.SUCCESS);
    }

}  
  
  
  
	void clear_blobstore() throws IOException {
		BlobInfoFactory blf = new BlobInfoFactory();
		Iterator<BlobInfo> info = blf.queryBlobInfos();
		BlobInfo bi = null;

		while (info.hasNext()) {
			bi = info.next();
			BlobstoreFS.delete(bi.getBlobKey());
		}
	}

	String wf(String sname, String s) throws IOException {
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

	String add_inividual_rdf(String s, String sind, String sclass) {
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

		return s7.trim();
	}
  
  public void sendEmail(String recipient, String subject, String text) {
	    Properties props = new Properties();
	    Session session = Session.getDefaultInstance(props, null);
	    try {
	      MimeMessage msg = new MimeMessage(session);
	      msg.setFrom(new InternetAddress("YOUREMAILGOESHERE"));
	      msg.addRecipient(MimeMessage.RecipientType.TO,
	                             new InternetAddress(recipient));
	      msg.setSubject(subject);
	      msg.setText(text);
	      Transport.send(msg);
	    } catch (AddressException e) {
	         // ...
	    } catch (MessagingException e) {
	         // ...
	    }
	  }  

  public String getGreetingList() {

    PersistenceManager pm = PMF.get().getPersistenceManager();

    String query = "";
    query = "select from " + Greeting.class.getName();
    List<Greeting> greetings = (List<Greeting>) pm.newQuery(query).execute();

    String greetinglist = "Greetings list:";

    for (Greeting g : greetings) {
      greetinglist += "\n" + g.getAuthor() + " posted:\n";
      greetinglist += g.getContent()
          + "\n";
    }
    pm.close();
    return greetinglist;
  }
}  