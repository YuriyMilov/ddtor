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
		if (req.getQueryString() == null)
			stat.init(req, resp);
		else if (req.getQueryString().indexOf("p2=") > -1)
			doPost(req, resp);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		ServletOutputStream out = resp.getOutputStream();
		resp.setContentType("text/html; charset=UTF8");

		String sh = req.getScheme() + "://" + req.getServerName() + ":"
				+ req.getServerPort() + req.getContextPath();
		String s = req.getParameter("p2");

		if (s == null) {
			stat.init(req, resp);
			return;
		}
		s = s.trim();
		
		
		///////////////////////////////
		//
		//         команда
		//
		///////////////////////////////
			
		if (s.indexOf(" ") < 0) 				
		{
			stat.command(s, req, resp);
			return;
		}	

		///////////////////////////////
		//
		//         текст
		//
		///////////////////////////////
		
		if (s.indexOf("?") != s.length() - 1)
		{
			stat.text(s, req, resp);
			return;
		}
		else {
			
			///////////////////////////////
			//
			//         вопрос
			//
			///////////////////////////////

			String s5 = s;
			boolean bb = s5.indexOf("Кто ") == 0 || s5.indexOf("кто ") == 0
					|| s5.indexOf("Что ") == 0 || s5.indexOf("что ") == 0;
			if (bb) {

				s5 = s5.replace("Кто ", "").replace("кто ", "")
						.replace("Что ", "").replace("что ", "")
						.replace("?", "").trim();

				if (s5.indexOf(" ") == -1) {
					//stat.sowl = stat.get_owl(stat.sr);
					// s = wf("test.owl", stat.sowl);

					String surl = sh + "/qq5";
					String sowl = sh + "/qqr";
					String body = "p1=" + URLEncoder.encode(sowl, "UTF-8")
							+ "&p2=" + URLEncoder.encode(s5, "UTF-8");
					
					stat.stop = stat.stop + "<br> <b><i> - </i></b> " + s;
					
					s = stat.get_post(surl, body);

					stat.page(req, resp, s);
					return;
				}
				
				/////////////////////////////////////////////
				//
				//      вопрос больше чем два слова  - хелп
				//
				/////////////////////////////////////////////
				
				if (s5.indexOf(" ") > -1) {
					s = stat.rfu_utf(sh + "/hlp.txt");
					stat.page(req, resp, s.replace("\r\n", "<br>"));
					return;
				}
			}
						
			/////////////////////////////////////////////
			//
			//      вопрос без кто или что в начале - хелп
			//
			/////////////////////////////////////////////

			if (s5.indexOf("Кто ") != 0 && s5.indexOf("кто ") != 0) {
				s = stat.rfu_utf(sh + "/hlp.txt");
				stat.page(req, resp, s.replace("\r\n", "<br>"));
				return;
			}
		}
	}

	public void onModuleLoad() {
	}

	private static final long serialVersionUID = 1L;
}