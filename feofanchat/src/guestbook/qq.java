package guestbook;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.nio.channels.Channels;
import java.util.Iterator;
import java.util.Properties;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.mail.BodyPart;
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
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.google.appengine.api.blobstore.BlobInfo;
import com.google.appengine.api.blobstore.BlobInfoFactory;
import com.google.appengine.api.blobstore.BlobKey;
import com.google.appengine.api.files.AppEngineFile;
import com.google.appengine.api.files.FileService;
import com.google.appengine.api.files.FileServiceFactory;
import com.google.appengine.api.files.FileWriteChannel;
import com.google.gwt.core.client.EntryPoint;

public class qq extends HttpServlet implements EntryPoint {


	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		if(req.getQueryString()==null)
			stat.init(req, resp);
		else
			if(req.getQueryString().indexOf("p2=")>-1)
				doPost(req, resp);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		ServletOutputStream out = resp.getOutputStream();
		resp.setContentType("text/html; charset=UTF8");

		String sh = req.getScheme() + "://" + req.getServerName() + ":"
				+ req.getServerPort() + req.getContextPath();
		String s = req.getParameter("p2");
		
		if(s==null)
		{
			stat.init(req, resp);
			return;
		}
		s=s.trim();
		
		if (s.length() == 0) {
			s = stat.rfu_utf(sh + "/hlp.txt");
			stat.page(req, resp, s.replace("\r\n", "<br>\r\n"));
			return;
		}
		
		stat.stop = stat.stop + "<br> <b><i>Я: </i></b> " + s;
		if (s.indexOf(" ") < 0)
		{
			
		if (s.indexOf("чист") == 0) {
			stat.init(req, resp);
			return;
		}		
		if (s.indexOf("имена") == 0) {
			s = stat.get_owl(stat.sr);
			String[] ss = s.split("<NamedIndividual rdf:about=\"&qq;");
			String s2 = "";
			int i = 1;
			int k=0;
			while(i<ss.length)
				{
				s=ss[i++];
				k=s.indexOf("\"");
				s=s.substring(0, k);
				//System.out.println(s);
				if(s2.indexOf(s)<0)
				s2=s2+s+", ";
				}
			if(s2.length()>1)
				s2=s2.substring(0,s2.length()-2);
			else
				s2="Никого тут еще нет. Напиши чего-нибудь для начала или загрузи уже написанный мир.";
			stat.page(req, resp, s2);
			return;
		}
		if (s.equals("понятия")) {
			s = stat.get_owl(stat.sr);
			String[] ss = s.split("<Class rdf:about=\"&qq;");
			String s2 = "";
			int i = 1;
			int k=0;
			while(i<ss.length)
				{
				s=ss[i++];
				k=s.indexOf("\"");
				s=s.substring(0, k);
				//System.out.println(s);
				if(s2.indexOf(s)<0)
				s2=s2+s+", ";
				}
			if(s2.length()>1)
				s2=s2.substring(0,s2.length()-2);
			else
				s2="Никого тут еще нет. Напиши чего-нибудь для начала или загрузи уже написанный мир.";
			stat.page(req, resp, s2);
			return;
			}

		if (s.equals("кря")) {
			s = stat.rfu_utf(sh + "/kpz.txt");
			stat.page(req, resp, s.replace("\r\n", "<br>\r\n"));
			return;
		}
		if (s.equals("оул")){
			s = stat.get_owl(stat.sr);
			stat.send_file(req, resp, s);
			stat.page(req, resp, "готово");
			return;
		}
	}
		String sq2 = s, sq = s;
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
			sq2 = stat.prep_all(sq2);
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
					s = stat.rfu_utf(sh + "/hlp.txt");
					stat.page(req, resp, s.replace("\r\n", "<br>\r\n"));
					return;
				}

				// //////////////

				if (sq.lastIndexOf(".") != k) {
					sq2 = sq + ". ";
					sq = sq + ". ";
				} else
					sq2 = sq;

				if (iq3 < 3) {

					sq3 = " Всё, что называется " + ss3[0] + ", то " + ss3[1]
							+ ". ";

				} else {
					if (!ss3[1].equals("-") && !ss3[1].equals("-")) {
						s = stat.rfu_utf(sh + "/hlp.txt");
						stat.page(req, resp, s);
						return;
					} else {
						sq3 = ss3[0] + " это " + ss3[2] + ".";
					}
				}

				sq4 = sq4 + sq3;
			}

			s = "Понял. " + sq4;

			// ////////////////////////////////////////////////

			stat.sr = stat.sr + " " + sq;

			// /////////////////////////////////////////////////

			stat.page(req, resp, s);
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
					stat.sowl = stat.get_owl(stat.sr);
					// s = wf("test.owl", stat.sowl);

					String surl = sh + "/qq5";
					String sowl = sh + "/qqr";
					String body = "p1=" + URLEncoder.encode(sowl, "UTF-8")
							+ "&p2=" + URLEncoder.encode(s5, "UTF-8");
					s = stat.get_post(surl, body);

					stat.page(req, resp, s);
					return;
				}
				if (s5.indexOf(" ") > -1) {
					s = stat.rfu_utf(sh + "/hlp.txt");
					stat.page(req, resp, s);
					return;
				}
			}
			if (s5.indexOf("Кто ") != 0 && s5.indexOf("кто ") != 0) {
				s = stat.rfu_utf(sh + "/hlp.txt");
				stat.page(req, resp, s);
				return;
			}

		}

	}

	
	public void onModuleLoad() {
	}

	private static final long serialVersionUID = 1L;
}