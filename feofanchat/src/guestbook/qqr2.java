package guestbook;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.util.Date;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.blobstore.BlobKey;
import com.google.appengine.api.blobstore.BlobstoreService;
import com.google.appengine.api.blobstore.BlobstoreServiceFactory;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Query.FilterOperator;
import com.google.appengine.api.files.AppEngineFile;
import com.google.appengine.api.files.FileReadChannel;
import com.google.appengine.api.files.FileService;
import com.google.appengine.api.files.FileServiceFactory;
import com.google.appengine.api.files.FileWriteChannel;

import com.google.appengine.api.blobstore.*;



public class qqr2 extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		String s = req.getQueryString();
		String sh = req.getScheme() + "://" + req.getServerName() + ":"
				+ req.getServerPort() + req.getContextPath();
		
		if(s==null)
			s=stat.posti(sh+"/qqw2","qqr2_null", rfu_utf(sh+"/n1.owl"));				
		else
			s = rf(stat.blobkey);
		  
		
		resp.setCharacterEncoding("UTF8");
		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();
		out.write(s);
		out.flush();
		out.close();
	}
	
	public static String rfu_utf(String s) {
		try {
			URL url = new URL(s);
			URLConnection conn = url.openConnection();
			BufferedReader br = new BufferedReader(new InputStreamReader(
					conn.getInputStream(), "utf8"));
			s = "";
			String thisLine = "";
			while ((thisLine = br.readLine()) != null) { 
				s = s + thisLine + "\r\n";
				}
			br.close();
			return s.toString();

		} catch (Exception e) {
			return e.toString();
		}
	}
	
	
	public String rf(String s) {

		try {
			FileService fileService = FileServiceFactory.getFileService();
			boolean lock = false;
			s=stat.blobkey;
			BlobKey blobKey = new BlobKey(s);				
			AppEngineFile file= fileService.getBlobFile(blobKey);
			FileReadChannel readChannel = fileService.openReadChannel(file,
					false);
			BufferedReader reader = new BufferedReader(Channels.newReader(
					readChannel, "UTF8"));
			s="";
			 String thisLine;
			 
			 while ((thisLine = reader.readLine()) != null)
			s = s + thisLine+" \r\n ";
			readChannel.close();
		} catch (Exception ee) {
			s = ee.toString();
		}
		return s;
	}
	
}
