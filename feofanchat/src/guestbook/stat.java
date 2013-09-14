package guestbook;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.nio.channels.Channels;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Properties;
import java.util.Set;

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

import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.model.AddAxiom;
import org.semanticweb.owlapi.model.IRI;
import org.semanticweb.owlapi.model.OWLAxiom;
import org.semanticweb.owlapi.model.OWLClass;
import org.semanticweb.owlapi.model.OWLClassAssertionAxiom;
import org.semanticweb.owlapi.model.OWLClassAxiom;
import org.semanticweb.owlapi.model.OWLClassExpression;
import org.semanticweb.owlapi.model.OWLDataFactory;
import org.semanticweb.owlapi.model.OWLDifferentIndividualsAxiom;
import org.semanticweb.owlapi.model.OWLEntity;
import org.semanticweb.owlapi.model.OWLIndividual;
import org.semanticweb.owlapi.model.OWLNamedIndividual;
import org.semanticweb.owlapi.model.OWLObjectExactCardinality;
import org.semanticweb.owlapi.model.OWLObjectIntersectionOf;
import org.semanticweb.owlapi.model.OWLObjectOneOf;
import org.semanticweb.owlapi.model.OWLObjectProperty;
import org.semanticweb.owlapi.model.OWLObjectPropertyAssertionAxiom;
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.model.OWLOntologyCreationException;
import org.semanticweb.owlapi.model.OWLOntologyManager;
import org.semanticweb.owlapi.model.OWLOntologyStorageException;
import org.semanticweb.owlapi.model.OWLSubClassOfAxiom;
import org.semanticweb.owlapi.model.OWLSymmetricObjectPropertyAxiom;
import org.semanticweb.owlapi.model.PrefixManager;
import org.semanticweb.owlapi.util.DefaultPrefixManager;

import para.st;

import com.clarkparsia.pellet.owlapiv3.PelletReasoner;
import com.clarkparsia.pellet.owlapiv3.PelletReasonerFactory;
import com.google.appengine.api.blobstore.BlobInfo;
import com.google.appengine.api.blobstore.BlobInfoFactory;
import com.google.appengine.api.blobstore.BlobKey;
import com.google.appengine.api.files.AppEngineFile;
import com.google.appengine.api.files.FileService;
import com.google.appengine.api.files.FileServiceFactory;
import com.google.appengine.api.files.FileWriteChannel;

public class stat {
	public static String sqq7 = "", blobkey="";

	public static String snach = "<html><head><meta charset=\"UTF-8\"><script>function setFocus(){document.getElementById(\"id\").focus();}</script></head><body bgcolor=#efefef onload=setFocus()>";
	public static String skon = "<form  action=qq method=post>"
			+ "<br><input type=text id=id name=p2 size=82>"
			+
			// "&nbsp;&nbsp; <a href=qq?p2=owl.txt>txt</a>" +
			" <br><br>&nbsp;<a href=qq>чист</a> &nbsp;&nbsp; <a href=qq?p2=загрузить>загрузить</a> &nbsp;&nbsp; <a href=qq?p2=добавить>добавить</a>"
			+ // &nbsp;&nbsp; <a href>сохранить мир</a>" +
			" &nbsp;&nbsp; <a href=qq?p2=кря>кря</a>  &nbsp;&nbsp; <a href=qq?p2=что>что</a>  &nbsp;&nbsp; <a href=owl>owl</a> &nbsp;&nbsp; <a href=qq?p2=мир>мир</a>  &nbsp;&nbsp; <a href=/donate.htm>Феофану на пропитание</a> "
			+ "<br>&nbsp;<br>&nbsp;<br>&nbsp;<br></form><br>&nbsp;<br>&nbsp;<br><br></html>";

	public static String siri = "http://owl.feofan.com/1#";

	public static String spref = "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>"
			+ "PREFIX owl: <http://www.w3.org/2002/07/owl#>"
			+ "PREFIX xsd: <http://www.w3.org/2001/XMLSchema#>"
			+ "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>"
			+ "PREFIX qq: <" + siri + ">";

	public static String stop = "";
	public static String sowl = "";
	public static String sr = "";

	public static String chto(String sir) {

		String s = "";

		try {
			OWLOntologyManager manager = OWLManager.createOWLOntologyManager();

			// IRI iri = IRI.create("http://test.feofan.com/rufish2.owl");
			IRI iri = IRI.create(sir);

			OWLOntology qw = manager.loadOntologyFromOntologyDocument(iri);

			String sa = "", sn = "", sc = "", sp = "";
			Object[] bb = null;
			Set<OWLEntity> set = qw.getSignature();
			int iset = set.size();
			if (iset < 1)
				return "Ничего тут еще нет.";

			bb = set.toArray();
			for (int i = 0; i < bb.length; i++) {
				sa = (qw.getDeclarationAxioms((OWLEntity) bb[i])).toString();

				if (sa.indexOf("Named") > -1)
					sn = sn
							+ sa.substring(sa.indexOf("#") + 1).replace(">))]",
									"") + " ";
				if (sa.indexOf("Class") > -1)
					sc = sc
							+ sa.substring(sa.indexOf("#") + 1).replace(">))]",
									"") + " ";
				if (sa.indexOf("Object") > -1)
					sp = sp
							+ sa.substring(sa.indexOf("#") + 1).replace(">))]",
									"") + " ";

				s = "<br><i>Сущности:</i> " + sn.trim().replace(" ", ", ")
						+ "<br><i>Понятия:</i> " + sc.trim().replace(" ", ", ")
						+ "<br><i>Связи:</i> " + sp.trim().replace(" ", ", ");

			}

			//

			// ByteArrayOutputStream bout = new ByteArrayOutputStream();
			// manager.saveOntology(oo, bout);
			// s = new String(bout.toByteArray(), "UTF-8");

		} catch (Exception e) {
			s = e.toString();
		}

		return s;

	}

	public static void page(HttpServletRequest req, HttpServletResponse resp,
			String sotvet) throws IOException {

		if (sotvet.indexOf("Server Error") > -1)
			sotvet = "какая-то проблема на сервере";

		stop = stop + "<br>\r\n<b><i>Феофан: </i></b>\r\n \r\n<!--otvet-->"
				+ sotvet + "<!--otvet-->\r\n";

		ServletOutputStream out = resp.getOutputStream();
		resp.setContentType("text/html; charset=UTF8");
		// String sh = req.getScheme() + "://" + req.getServerName() + ":"
		// + req.getServerPort() + req.getContextPath();
		String s = snach + stop + skon;
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

	public static String get_owl(String s1) {
		String s = "";

		try {

			OWLOntologyManager mm = OWLManager.createOWLOntologyManager();
			OWLDataFactory ff = mm.getOWLDataFactory();
			mm = OWLManager.createOWLOntologyManager();
			String base = siri;// "http://www.feofan.com/test/";
			PrefixManager pm = new DefaultPrefixManager(base);

			OWLOntology oo = mm.createOntology(IRI.create(base));
			OWLAxiom axiom = null;
			OWLClassAssertionAxiom classAssertion = null;
			OWLObjectProperty obp = null;

			String[] sss = prep_all(s1).split("[.]+");

			for (int i = 0; i < sss.length; i++) {
				String[] ss = sss[i].trim().split("[ ]+");
				if (ss.length != 3) {
					ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
					mm.saveOntology(oo, outputStream);
					s = new String(outputStream.toByteArray(), "UTF-8");
					return s;

				}

				if (ss[1].equals("-")) {
					axiom = ff.getOWLSubClassOfAxiom(
							ff.getOWLClass(":" + ss[0], pm),
							ff.getOWLClass(":" + ss[2], pm));
					mm.addAxiom(oo, axiom);

				} else

				if (ss[1].equals("это")) {
					classAssertion = ff.getOWLClassAssertionAxiom(
							ff.getOWLClass(":" + ss[2], pm),
							ff.getOWLNamedIndividual(":" + ss[0], pm));
					mm.addAxiom(oo, classAssertion);
				} else {
					obp = ff.getOWLObjectProperty(IRI.create(base + ss[1]));
					OWLSymmetricObjectPropertyAxiom obsym = ff
							.getOWLSymmetricObjectPropertyAxiom(obp);
					AddAxiom addAxiomChange1 = new AddAxiom(oo, obsym);
					mm.applyChange(addAxiomChange1);

					if (socrat(ss[0]) && socrat(ss[2])) {
						OWLObjectPropertyAssertionAxiom aa = ff
								.getOWLObjectPropertyAssertionAxiom(obp, ff
										.getOWLNamedIndividual(IRI.create(base
												+ ss[0])), ff
										.getOWLNamedIndividual(IRI.create(base
												+ ss[2])));

						AddAxiom addAxiomChange2 = new AddAxiom(oo, aa);
						mm.applyChange(addAxiomChange2);
					}
					if (!socrat(ss[0]) && !socrat(ss[2]))

					{

						OWLObjectProperty hasPart = ff.getOWLObjectProperty(IRI
								.create(base + ss[1]));
						OWLClass nose = ff
								.getOWLClass(IRI.create(base + ss[2]));

						// Now create a restriction to describe the class of
						// individuals that have at least one
						// part that is a kind of nose

						OWLClassExpression hasPartSomeNose = ff
								.getOWLObjectSomeValuesFrom(hasPart, nose);

						// Obtain a reference to the Head class so that we can
						// specify that Heads have noses
						OWLClass head = ff
								.getOWLClass(IRI.create(base + ss[0]));

						OWLSubClassOfAxiom ax2 = ff.getOWLSubClassOfAxiom(head,
								hasPartSomeNose);
						AddAxiom addAx = new AddAxiom(oo, ax2);
						mm.applyChange(addAx);

						// ///////////////////////
						// ///////////////////////
						// ///////////////////////
						// ///////////////////////
						// ///////////////////////
						// ///////////////////////

					}
				}
			}

			// /////////////////////////
			// /////////////////////////
			// /////////////////////////

			ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
			mm.saveOntology(oo, outputStream);
			s = new String(outputStream.toByteArray(), "UTF-8");

		} catch (Exception e) {
			s = e.toString();
		}

		return s;
	}

	public static String get_owl7(String s) {

		Owl2Model qq = new Owl2Model("http://owl.feofan.com/1");

		try {
			// OWLIndividual Сократ = qq.getIndividual("Сократ");
			// OWLClass человек = qq.getOwlClass("человек");
			// OWLClass смертен = qq.getOwlClass("смертен");
			// qq.hasClass(Сократ, человек);
			// qq.isSubClassOf(человек, смертен);

			String[] sss = prep_all(s).split("[.]+");
 
			for (int i = 0; i < sss.length; i++) {

				String[] ss = sss[i].trim().split("[ ]+");

				if (ss.length != 3) {
					;
				} else if (ss[1].equals("-")) {
					qq.isSubClassOf(qq.getOwlClass(ss[0]),
							qq.getOwlClass(ss[2]));
				} else if (ss[1].equals("это")) {
					qq.hasClass(qq.getIndividual(ss[0]), qq.getOwlClass(ss[2]));
				}

				else if (socrat(ss[0]) && socrat(ss[2])) {
					qq.assertFact(ss[1], ss[0], ss[2]);
				}

				else if (!socrat(ss[0]) && !socrat(ss[2])) {
					qq.assertDomainAndRange(qq.getProperty(ss[1]),
							qq.getOwlClass(ss[0]), qq.getOwlClass(ss[2]));
				}
			}

			s = qq.sowl();
		} catch (Exception e) {
			s = qq.sowl();
		}

		return s;
	}
	
	
	public static String get_owl8(String s) {

		Owl2Model qq = new Owl2Model("http://owl.feofan.com/1");

		try {

			String[] sss = prep_all(s).split("[.]+");
 
			for (int i = 0; i < sss.length; i++) {

				String[] ss = sss[i].trim().split("[ ]+");

				if (ss.length != 3) {
					;
				} else if (ss[1].equals("-")) {
					qq.isSubClassOf(qq.getOwlClass(ss[0]),
							qq.getOwlClass(ss[2]));
				} else if (ss[1].equals("это")) {
					qq.hasClass(qq.getIndividual(ss[0]), qq.getOwlClass(ss[2]));
				}

				else if (socrat(ss[0]) && socrat(ss[2])) {
					//qq.getProperty(ss[1]);
					qq.assertFact(ss[1], ss[0], ss[2]);
				}

				else if (!socrat(ss[0]) && !socrat(ss[2])) {
					qq.assertDomainAndRange(qq.getProperty(ss[1]),
							qq.getOwlClass(ss[0]), qq.getOwlClass(ss[2]));
				}
				else if (socrat(ss[0]) && !socrat(ss[2])) {
					
					qq.hasClass(qq.getIndividual(ss[0]), qq.getOwlClass(ss[2]));
					qq.assertRange(qq.getProperty(ss[1]), qq.getOwlClass(ss[2]));					
					
				}
			}

			s = qq.sowl();
			
		} catch (Exception e) {
			s = qq.sowl();
		}

		String s33=s;
		
		///////////////////////////////////
		
		try {
			
			//st.ontologyIRI = IRI.create(st.sh+"/owl");
			st.ontologyIRI = IRI.create(st.sh+"/qqr?1111");
		
			try {
		st.ontology = st.manager.loadOntology(st.ontologyIRI);	
			}
		catch(Exception e1){s=e1.toString();}
		
		st.factory = st.manager.getOWLDataFactory();
		
		OWLClass человек=st.что("человек");
		OWLClass дурак=st.что("дурак");
		OWLIndividual Вася=st.кто("Вася");
		st.что_что(человек, дурак);
		st.кто_что(Вася, дурак);
		byte [] b = st.get_bowl();
		
			s= new String(b, "UTF-8");
			
			
			posti(st.sh+"/qqw2","qq_вася_qq", s);
			
		} catch (Exception e2) {
			s=e2.toString();
		}
		
		///////////////////////////////////
		
		
		return s33;
	}
	

   
   

	public static void init(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		stat.stop = "";
		stat.sr = "";
		String s = "кря";
		stat.page(req, resp, s);
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

	public static String prep_all(String s7) {
		s7 = s7.replace("\r", "").replace("\n", "");
		s7 = s7.replace("\"", "");
		s7 = s7.replace("&", "");
		s7 = s7.replace("#", "");
		s7 = s7.replace("<", "");
		s7 = s7.replace(">", "");

		return s7.trim();
	}

	public static void command(String s, HttpServletRequest req,
			HttpServletResponse resp) throws IOException {

		String sh = req.getScheme() + "://" + req.getServerName() + ":"
				+ req.getServerPort() + req.getContextPath();

		if (s.length() == 0) {
			s = rfu_utf(sh + "/cmd.txt");
			stat.page(req, resp, s.replace("\r\n", "<br>"));
			return;
		}

		stat.stop = stat.stop + "<br> <b><i> - </i></b> " + s;

		if (s.indexOf("чист") == 0) {
			stat.init(req, resp);
			return;
		}

		if (s.equals("загрузить")) {
			s = rfu_utf(sh + "/load.txt");

			ServletOutputStream out = resp.getOutputStream();
			resp.setContentType("text/html; charset=UTF-8");
			s = s.replace("\r\n", "<br>");
			byte[] b = s.getBytes("UTF8");
			out.write(b);

			return;
		}

		if (s.equals("добавить")) {
			s = rfu_utf(sh + "/add.txt");
			ServletOutputStream out = resp.getOutputStream();
			resp.setContentType("text/html; charset=UTF-8");
			s = s.replace("\r\n", "<br>");
			byte[] b = s.getBytes("UTF8");
			out.write(b);

			return;
		}

		if (s.equals("кря")) {
			// s = rfu_utf(sh+"/qq?p2="+URLEncoder.encode("дом", "UTF-8"));
			// stat.page(req, resp, s);

			s = stat.rfu_utf(sh + "/dom.txt");
			ServletOutputStream out = resp.getOutputStream();
			resp.setContentType("text/html; charset=UTF-8");
			s = s.replace("\r\n", "<br>");
			byte[] b = s.getBytes("UTF8");
			out.write(b);

			return;
		}

		if (s.equals("что")) {
			s = chto(sh + "/qq_s");

			stat.page(req, resp, s);

			// ServletOutputStream out = resp.getOutputStream();
			// resp.setContentType("text/xml; charset=UTF8");
			// resp.setCharacterEncoding("UTF8");
			// byte[] b = s.getBytes("UTF8");
			// out.write(b);
			return;
		}

		if (s.equals("фео")) {
			stat.page(req, resp, "кря");
			return;
		}
		if (s.equals("мир")) {
		s = rfu_utf(sh + "/qq7");
			stat.page(req, resp, s);
			return;
		}

	
		
		
		stat.page(req, resp, "не знаю такой команды");
		return;
	}



	public static void text8(String s, HttpServletRequest req,
			HttpServletResponse resp) throws IOException {

		s = prep_all(s);

		String[] sss = s.split("[.]+");

		for (int i = 0; i < sss.length; i++) {
			s = sss[i].trim();
			String[] ss = s.split("[ ]+");

			
			if (ss.length != 3) {
				
				stat.stop = stat.stop + "<br> <b><i> - </i></b> " + s;
		
				
				s=" ожидал три слова здесь: " +s;
				page(req, resp, s);
				return;
			} 
			else
			if (Character.isDigit(ss[1].charAt(0)))
			{
				stat.stop = stat.stop + "<br> <b><i> - </i></b> " + s;
				
				s="Отношение (-"+ss[1]+"->) в высказывании (\""+s+"\") в данной версии Феофана не может начинаться с числа";
				page(req, resp, s);
				return;
			}
				
			else {
				
				///////////////////////////////////
				// комментарий - пока добавляется мир
				///////////////////////////////////
				
				stat.stop = stat.stop + "<br> <b><i> - </i></b> добавить: " + s;

				
				if (sr.indexOf(s) < 0) {
					sr = sr + " " + s + ". ";

					// sowl = get_owl(sr);
					//sowl = get_owl7(sr);
				}
			}
		}
		
		sowl = get_owl8(sr);
		stat.page(req, resp, " Новый мир: \"" + stat.sr.trim()+"\"");
	}

	public static boolean socrat(String s) {

		if (s.length() > 0)
			if (s.substring(0, 1).toUpperCase().equals(s.substring(0, 1)))
				return true;
			else
				return false;
		else
			return false;
	}

	
	
	
	public static String para8(String s) {

		try {
			OWLClassAssertionAxiom caa = null;
			AddAxiom adax = null;
			OWLOntologyManager mm = OWLManager.createOWLOntologyManager();
			OWLDataFactory ff = mm.getOWLDataFactory();
			String base = "http://owl.feofan.com/1#";
			
			PrefixManager pre = new DefaultPrefixManager(base);
			OWLOntology oo = mm.createOntology(IRI.create(base));

		
			
			
			////////////////////////////////
			
			
			
			

			// /////////////////////////
			// Классы
			// /////////////////////////

			OWLClass clam = ff.getOWLClass(":нечёт", pre);
			OWLClass clad = ff.getOWLClass(":чёт", pre);

			// ////////////////////////////
			// Cимметричная связь
			// ////////////////////////////

			OWLObjectProperty op = ff.getOWLObjectProperty(IRI.create(base
					+ "пара"));
			OWLSymmetricObjectPropertyAxiom obsym = ff
					.getOWLSymmetricObjectPropertyAxiom(op);
			adax = new AddAxiom(oo, obsym);
			mm.applyChange(adax);

			// /////////////////////////
			// Индивиды
			// /////////////////////////

			ArrayList<OWLNamedIndividual> nnm = new ArrayList<OWLNamedIndividual>();
			ArrayList<OWLNamedIndividual> nnd = new ArrayList<OWLNamedIndividual>();

			nnm.add(ff.getOWLNamedIndividual(":1", pre));
			nnm.add(ff.getOWLNamedIndividual(":3", pre));
			nnm.add(ff.getOWLNamedIndividual(":5", pre));
			nnm.add(ff.getOWLNamedIndividual(":7", pre));

			nnd.add(ff.getOWLNamedIndividual(":2", pre));
			nnd.add(ff.getOWLNamedIndividual(":4", pre));
			nnd.add(ff.getOWLNamedIndividual(":6", pre));
			nnd.add(ff.getOWLNamedIndividual(":8", pre));

			// /////////////////////////
			// Индивиды - члены класса
			// /////////////////////////

			for (int i = 0; i < nnm.size(); i++)
				mm.addAxiom(oo, ff.getOWLClassAssertionAxiom(clam, nnm.get(i)));

			for (int i = 0; i < nnm.size(); i++)
				mm.addAxiom(oo, ff.getOWLClassAssertionAxiom(clad, nnd.get(i)));

			// /////////////////////////
			// 1 пара 2
			// /////////////////////////

			OWLObjectPropertyAssertionAxiom opaa = ff
					.getOWLObjectPropertyAssertionAxiom(op,
							ff.getOWLNamedIndividual(":1", pre),
							ff.getOWLNamedIndividual(":2", pre));

			adax = new AddAxiom(oo, opaa);
			mm.applyChange(adax);

			// /////////////////////////
			// 5 пара 6
			// /////////////////////////

			opaa = ff.getOWLObjectPropertyAssertionAxiom(op,
					ff.getOWLNamedIndividual(":5", pre),
					ff.getOWLNamedIndividual(":6", pre));

			adax = new AddAxiom(oo, opaa);
			mm.applyChange(adax);

			// /////////////////////////
			// 3 пара 8
			// /////////////////////////

			opaa = ff.getOWLObjectPropertyAssertionAxiom(op,
					ff.getOWLNamedIndividual(":3", pre),
					ff.getOWLNamedIndividual(":4", pre));

			adax = new AddAxiom(oo, opaa);
			mm.applyChange(adax);

			// ///////////////////////////////////////
			// {...} и пара только одному индивиду
			// ///////////////////////////////////////

			OWLObjectExactCardinality exactly1m = ff
					.getOWLObjectExactCardinality(1, op, clam);
			OWLObjectExactCardinality exactly1d = ff
					.getOWLObjectExactCardinality(1, op, clad);

			OWLObjectOneOf nm = ff.getOWLObjectOneOf((OWLNamedIndividual[]) nnm
					.toArray(new OWLNamedIndividual[nnm.size()]));
			OWLObjectOneOf nd = ff.getOWLObjectOneOf((OWLNamedIndividual[]) nnd
					.toArray(new OWLNamedIndividual[nnd.size()]));

			OWLObjectIntersectionOf m_i_l_1_d = ff.getOWLObjectIntersectionOf(
					nm, exactly1d);
			OWLClassAxiom clax = ff.getOWLEquivalentClassesAxiom(clam,
					m_i_l_1_d);
			mm.addAxiom(oo, clax);

			OWLObjectIntersectionOf d_i_l_1_m = ff.getOWLObjectIntersectionOf(
					nd, exactly1m);
			OWLClassAxiom clax2 = ff.getOWLEquivalentClassesAxiom(clad,
					d_i_l_1_m);
			mm.addAxiom(oo, clax2);

			// /////////////////////////
			// Индивиды - разные
			// /////////////////////////

			ArrayList<OWLNamedIndividual> nnmd = new ArrayList<OWLNamedIndividual>();
			nnmd.addAll(nnm);
			nnmd.addAll(nnd);

			OWLDifferentIndividualsAxiom diffInds = ff
					.getOWLDifferentIndividualsAxiom((OWLNamedIndividual[]) nnmd
							.toArray(new OWLNamedIndividual[nnmd.size()]));
			mm.addAxiom(oo, diffInds);

			// /////////////////////////
			// /////////////////////////
			// /////////////////////////

			ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
			mm.saveOntology(oo, outputStream);
			s = new String(outputStream.toByteArray(), "UTF-8");

			return s;
		} catch (Exception e) {
			return e.toString();
		}
	}
		
	public static String para(String s) {

		try {
			OWLClassAssertionAxiom caa = null;
			AddAxiom adax = null;
			OWLOntologyManager mm = OWLManager.createOWLOntologyManager();
			OWLDataFactory ff = mm.getOWLDataFactory();
			String base = "http://owl.feofan.com/1#";
			PrefixManager pre = new DefaultPrefixManager(base);
			OWLOntology oo;

			oo = mm.createOntology(IRI.create(base));

			// /////////////////////////
			// Классы
			// /////////////////////////

			OWLClass clam = ff.getOWLClass(":нечёт", pre);
			OWLClass clad = ff.getOWLClass(":чёт", pre);

			// ////////////////////////////
			// Cимметричная связь
			// ////////////////////////////

			OWLObjectProperty op = ff.getOWLObjectProperty(IRI.create(base
					+ "пара"));
			OWLSymmetricObjectPropertyAxiom obsym = ff
					.getOWLSymmetricObjectPropertyAxiom(op);
			adax = new AddAxiom(oo, obsym);
			mm.applyChange(adax);

			// /////////////////////////
			// Индивиды
			// /////////////////////////

			ArrayList<OWLNamedIndividual> nnm = new ArrayList<OWLNamedIndividual>();
			ArrayList<OWLNamedIndividual> nnd = new ArrayList<OWLNamedIndividual>();

			nnm.add(ff.getOWLNamedIndividual(":1", pre));
			nnm.add(ff.getOWLNamedIndividual(":3", pre));
			nnm.add(ff.getOWLNamedIndividual(":5", pre));
			nnm.add(ff.getOWLNamedIndividual(":7", pre));

			nnd.add(ff.getOWLNamedIndividual(":2", pre));
			nnd.add(ff.getOWLNamedIndividual(":4", pre));
			nnd.add(ff.getOWLNamedIndividual(":6", pre));
			nnd.add(ff.getOWLNamedIndividual(":8", pre));

			// /////////////////////////
			// Индивиды - члены класса
			// /////////////////////////

			for (int i = 0; i < nnm.size(); i++)
				mm.addAxiom(oo, ff.getOWLClassAssertionAxiom(clam, nnm.get(i)));

			for (int i = 0; i < nnm.size(); i++)
				mm.addAxiom(oo, ff.getOWLClassAssertionAxiom(clad, nnd.get(i)));

			// /////////////////////////
			// 1 пара 2
			// /////////////////////////

			OWLObjectPropertyAssertionAxiom opaa = ff
					.getOWLObjectPropertyAssertionAxiom(op,
							ff.getOWLNamedIndividual(":1", pre),
							ff.getOWLNamedIndividual(":2", pre));

			adax = new AddAxiom(oo, opaa);
			mm.applyChange(adax);

			// /////////////////////////
			// 5 пара 6
			// /////////////////////////

			opaa = ff.getOWLObjectPropertyAssertionAxiom(op,
					ff.getOWLNamedIndividual(":5", pre),
					ff.getOWLNamedIndividual(":6", pre));

			adax = new AddAxiom(oo, opaa);
			mm.applyChange(adax);

			// /////////////////////////
			// 3 пара 8
			// /////////////////////////

			opaa = ff.getOWLObjectPropertyAssertionAxiom(op,
					ff.getOWLNamedIndividual(":3", pre),
					ff.getOWLNamedIndividual(":4", pre));

			adax = new AddAxiom(oo, opaa);
			mm.applyChange(adax);

			// ///////////////////////////////////////
			// {...} и пара только одному индивиду
			// ///////////////////////////////////////

			OWLObjectExactCardinality exactly1m = ff
					.getOWLObjectExactCardinality(1, op, clam);
			OWLObjectExactCardinality exactly1d = ff
					.getOWLObjectExactCardinality(1, op, clad);

			OWLObjectOneOf nm = ff.getOWLObjectOneOf((OWLNamedIndividual[]) nnm
					.toArray(new OWLNamedIndividual[nnm.size()]));
			OWLObjectOneOf nd = ff.getOWLObjectOneOf((OWLNamedIndividual[]) nnd
					.toArray(new OWLNamedIndividual[nnd.size()]));

			OWLObjectIntersectionOf m_i_l_1_d = ff.getOWLObjectIntersectionOf(
					nm, exactly1d);
			OWLClassAxiom clax = ff.getOWLEquivalentClassesAxiom(clam,
					m_i_l_1_d);
			mm.addAxiom(oo, clax);

			OWLObjectIntersectionOf d_i_l_1_m = ff.getOWLObjectIntersectionOf(
					nd, exactly1m);
			OWLClassAxiom clax2 = ff.getOWLEquivalentClassesAxiom(clad,
					d_i_l_1_m);
			mm.addAxiom(oo, clax2);

			// /////////////////////////
			// Индивиды - разные
			// /////////////////////////

			ArrayList<OWLNamedIndividual> nnmd = new ArrayList<OWLNamedIndividual>();
			nnmd.addAll(nnm);
			nnmd.addAll(nnd);

			OWLDifferentIndividualsAxiom diffInds = ff
					.getOWLDifferentIndividualsAxiom((OWLNamedIndividual[]) nnmd
							.toArray(new OWLNamedIndividual[nnmd.size()]));
			mm.addAxiom(oo, diffInds);

			// /////////////////////////
			// /////////////////////////
			// /////////////////////////

			ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
			mm.saveOntology(oo, outputStream);
			s = new String(outputStream.toByteArray(), "UTF-8");

			return s;
		} catch (Exception e) {
			return e.toString();
		}
	}
	
	public static String scrt(String s) {
		try {
			OWLOntologyManager начальник = OWLManager.createOWLOntologyManager();
            OWLDataFactory работник = начальник.getOWLDataFactory();
            String где = "http://owl.feofan.com/1#";
            PrefixManager приставка= new DefaultPrefixManager(где);
            OWLOntology онтология = начальник.createOntology(IRI.create(где));
           
            OWLClass человек = работник.getOWLClass(":человек", приставка);
            OWLClass смертен = работник.getOWLClass(":смертен", приставка);
            OWLNamedIndividual Сократ = работник.getOWLNamedIndividual(":Сократ", приставка);           
           
            OWLClassAssertionAxiom Сократ_это_человек = работник.getOWLClassAssertionAxiom(человек, Сократ);           
            начальник.addAxiom(онтология, Сократ_это_человек);
           
            OWLSubClassOfAxiom человек_смертен = работник.getOWLSubClassOfAxiom(человек,смертен);       
            начальник.addAxiom(онтология, человек_смертен);
	
			
			// /////////////////////////
			// /////////////////////////
			// /////////////////////////

			ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
			начальник.saveOntology(онтология, outputStream);
			s = new String(outputStream.toByteArray(), "UTF-8");
			return s;
		} catch (Exception e) {
			return e.toString();
		}
	}
	
	
	  public static String posti(String surl, String sname, String scontent) throws IOException {
	       // Encode the query
		  String postData = "name="+ URLEncoder.encode(sname, "UTF-8")+"&content=" + URLEncoder.encode(scontent, "UTF-8");
	       
		   URL url = new URL(surl);
	       HttpURLConnection connection = (HttpURLConnection) url.openConnection();
	       connection.setDoOutput(true);
	       connection.setRequestMethod("POST");
	       connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
	       connection.setRequestProperty("Content-Length",  String.valueOf(postData.length()));
	        
	       // Write data
	       OutputStream os = connection.getOutputStream();
	       os.write(postData.getBytes());
	        
	       // Read response
	       StringBuilder responseSB = new StringBuilder();
	       BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
	         
	       String line;
	       while ( (line = br.readLine()) != null)
	           responseSB.append(line);
	                
	       // Close streams
	       br.close();
	       os.close();
	        
	       return responseSB.toString();
	        
	   }
}
