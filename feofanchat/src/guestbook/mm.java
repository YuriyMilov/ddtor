package guestbook;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
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

public class mm extends HttpServlet {
	public static String slog = "";
	public static String slog1 = "";

	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {

		String sh = req.getScheme() + "://" + req.getServerName() + ":"
				+ req.getServerPort() + req.getContextPath();
		String s = "";

		try {
			Properties props = new Properties();
			Session session = Session.getDefaultInstance(props, null);
			MimeMessage ms1 = new MimeMessage(session, req.getInputStream());

			//s = ms1.getSubject();

			
			
			//s=ms1.getContent().toString();
			
			
			Object msgContent = ms1.getContent();

		    String content = "";             

		     /* Check if content is pure text/html or in parts */                     
		     if (msgContent instanceof Multipart) {

		         Multipart multipart = (Multipart) msgContent;

		        // Log.e("BodyPart", "MultiPartCount: "+multipart.getCount());

		         for (int j = 0; j < multipart.getCount(); j++) {

		          BodyPart bodyPart = multipart.getBodyPart(j);

		          String disposition = bodyPart.getDisposition();

		          if (disposition != null && (disposition.equalsIgnoreCase("ATTACHMENT"))) { 
		              //System.out.println("Mail have some attachment");

		              DataHandler handler = bodyPart.getDataHandler();
		             // System.out.println("file name : " + handler.getName());                                 
		            }
		          else { 
		                  content = bodyPart.getContent().toString();  // the changed code         
		            }
		        }
		     }
		     else                
		         content= ms1.getContent().toString();
			
		     s=content;
			     


s= s.split("\n")[0].replaceAll("\\<.*?>","");
		
			
			//s=s.substring(0,s.indexOf("\n"));
			

		    	 
		    	 s = stat.get_post(sh + "/qq", "p2=" + URLEncoder.encode(s, "UTF-8"));



			MimeMessage msg = new MimeMessage(session, req.getInputStream());
			msg.setFrom(new InternetAddress("feofan@feofan.com", "Феофан Кряк",
					"UTF-8"));
			//msg.addRecipient(Message.RecipientType.TO, new InternetAddress(
			//		"ymilov@gmail.com", " ", "UTF-8"));
			msg.addRecipient(Message.RecipientType.TO,ms1.getReplyTo()[0]);
			msg.setSubject( ms1.getSubject(), "UTF-8");
			

			
			
			
			
			
			
			
			
					
			String[] sss = s.split("<!--otvet-->");
			String sall="";
			int i = sss.length;
			
			for(int k=2;k<i-1;k++){
				sall=sall+sss[k]+"\r\n";
			}

			if (i > 1)
				s = sss[i - 2];
			else
				s = "хмм... непонятно...";

			
			
			
			//	s = "\r\n - "+content + s +"\r\n\r\n======  Лог  =======\r\n\r\n"+sall;
			
			
			s=s.replaceAll("\\<.*?>","");

			
			msg.setText(s);
		
			
			//msg.setText(ms1.getContent().toString());

			Transport.send(msg);
	
		} catch (Exception e) {
			s = e.toString();
		}
	}

	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		doGet(req, resp);
	}

	private static final long serialVersionUID = 1L;

}