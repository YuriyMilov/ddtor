package dd;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.URL;
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

public class qqr extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		String s = rf(req.getQueryString());
		
		
	
		PrintWriter out = resp.getWriter();
		out.write(s);
		out.flush();
		out.close();
	}
	public String rf(String s) {

		try {
			// Get a file service
			FileService fileService = FileServiceFactory.getFileService();

			// Create a new Blob file with mime-type "text/plain"
			//AppEngineFile file = fileService.createNewBlobFile("text/plain");

			// Open a channel to write to it
			boolean lock = false;
			//FileWriteChannel writeChannel = fileService.openWriteChannel(file,
			//		lock);

			// Different standard Java ways of writing to the channel
			// are possible. Here we use a PrintWriter:
			//PrintWriter out = new PrintWriter(Channels.newWriter(writeChannel,
			//		"UTF8"));

			//out.println("The woods are lovely dark and deep.");
			//out.println("But I have promises to keep.");

			// Close without finalizing and save the file path for writing later
			//out.close();
			//String path = file.getFullPath();

			// Write more to the file in a separate request:
			//file = new AppEngineFile(path);

			// This time lock because we intend to finalize
			//lock = true;
			//writeChannel = fileService.openWriteChannel(file, lock);

			// This time we write to the channel directly
			//writeChannel.write(ByteBuffer
			//		.wrap("And miles to go before I sleep.".getBytes()));

			// Now finalize
			//writeChannel.closeFinally();

			// Later, read from the file using the file API
			//lock = false; // Let other people read at the same time
			
			
			BlobKey blobKey = new BlobKey(s);			
			
		  
			
			AppEngineFile file = new AppEngineFile(AppEngineFile.FileSystem.BLOBSTORE, blobKey.getKeyString());
			
			FileReadChannel readChannel = fileService.openReadChannel(file,
					false);

			// Again, different standard Java ways of reading from the channel.
			BufferedReader reader = new BufferedReader(Channels.newReader(
					readChannel, "UTF8"));
			s="";
			 String thisLine;
			 
			 while ((thisLine = reader.readLine()) != null)
			s = s + thisLine+" \r\n ";
			
			// line = "The woods are lovely dark and deep."

			readChannel.close();

			// Now read from the file using the Blobstore API
			
			//BlobKey blobKey = fileService.getBlobKey(file);
			//BlobstoreService blobStoreService = BlobstoreServiceFactory
			//		.getBlobstoreService();
			//String s2 = new String(blobStoreService.fetchData(blobKey, 30,
			//		40));

		} catch (Exception ee) {
			s = ee.toString();
		}
		return s;

	}


}
