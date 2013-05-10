package ru;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.nio.channels.Channels;
import java.util.Iterator;

import com.google.appengine.api.blobstore.BlobInfo;
import com.google.appengine.api.blobstore.BlobInfoFactory;
import com.google.appengine.api.blobstore.BlobKey;
import com.google.appengine.api.files.AppEngineFile;
import com.google.appengine.api.files.FileReadChannel;
import com.google.appengine.api.files.FileService;
import com.google.appengine.api.files.FileServiceFactory;
import com.google.appengine.api.files.FileWriteChannel;

public class Statik {


	public static String init_owl() {
		return "<?xml version=\"1.0\" encoding=\"UTF8\"?>\r\n\r\n<Ontology\r\n   xml:base=\"http://www.w3.org/2002/07/owl#\"\r\n   xmlns=\"http://www.w3.org/2002/07/owl#\"\r\n   ontologyIRI=\"http://feofan.com/test\">\r\n";
	}

	public static String close_owl(String s) {
		return s + "</Ontology>\r\n";
	}

	
	public static String add_subclass(String sinit, String sclass,
			String ssubclass) {
		return sinit + "<SubClassOf>\r\n	<Class IRI=\"http://feofan.com/test#"
				+ ssubclass + "\"/>\r\n	<Class	IRI=\"http://feofan.com/test#"
				+ sclass + "\"/>\r\n</SubClassOf>\r\n";
	}

	public static String add_classassertion(String sinit, String sclass,
			String sind) {
		return sinit + "<ClassAssertion>   "
				+ "\r\n	<Class IRI=\"http://feofan.com/test#" + sclass + "\"/>"
				+ "\r\n	<NamedIndividual IRI=\"http://feofan.com/test#" + sind
				+ "\"/>\r\n" + "</ClassAssertion>\r\n";
	}





	public static String rf(String s) {

		try {
			// Get a file service
			FileService fileService = FileServiceFactory.getFileService();

			// Create a new Blob file with mime-type "text/plain"
			// AppEngineFile file = fileService.createNewBlobFile("text/plain");

			// Open a channel to write to it
			// boolean lock = false;
			// FileWriteChannel writeChannel =
			// fileService.openWriteChannel(file,
			// lock);

			// Different standard Java ways of writing to the channel
			// are possible. Here we use a PrintWriter:
			// PrintWriter out = new
			// PrintWriter(Channels.newWriter(writeChannel,
			// "UTF8"));

			// out.println("The woods are lovely dark and deep.");
			// out.println("But I have promises to keep.");

			// Close without finalizing and save the file path for writing later
			// out.close();
			// String path = file.getFullPath();

			// Write more to the file in a separate request:
			// file = new AppEngineFile(path);

			// This time lock because we intend to finalize
			// lock = true;
			// writeChannel = fileService.openWriteChannel(file, lock);

			// This time we write to the channel directly
			// writeChannel.write(ByteBuffer
			// .wrap("And miles to go before I sleep.".getBytes()));

			// Now finalize
			// writeChannel.closeFinally();

			// Later, read from the file using the file API
			// lock = false; // Let other people read at the same time

			BlobKey blobKey = new BlobKey(s);

			AppEngineFile file = new AppEngineFile(
					AppEngineFile.FileSystem.BLOBSTORE, blobKey.getKeyString());

			FileReadChannel readChannel = fileService.openReadChannel(file,
					false);

			// Again, different standard Java ways of reading from the channel.
			BufferedReader reader = new BufferedReader(Channels.newReader(
					readChannel, "UTF8"));
			s = "";
			String thisLine;

			while ((thisLine = reader.readLine()) != null)
				s = s + thisLine + " \r\n ";

			// line = "The woods are lovely dark and deep."

			readChannel.close();

			// Now read from the file using the Blobstore API

			// BlobKey blobKey = fileService.getBlobKey(file);
			// BlobstoreService blobStoreService = BlobstoreServiceFactory
			// .getBlobstoreService();
			// String s2 = new String(blobStoreService.fetchData(blobKey, 30,
			// 40));

		} catch (Exception ee) {
			s = ee.toString();
		}
		return s;

	}


	
	  public static String rfu_utf(String s) {
	        try {
		        URL url = new URL(s);
		        
	            URLConnection conn = url.openConnection();
	            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf8"));
	            s="";
	            String thisLine="";
	            while ((thisLine = br.readLine()) != null) { // while loop begins here
	                s=s+thisLine+"\r\n";
	              } 
	            br.close();
        return s.toString();
        
	        } catch (Exception e) {	            
	            return e.toString();
	        }
	    }
		public static String get_post(String surl,String body) {
			String s="";
			
	
			try {
			      
			     
				URL url = new URL(surl);
		        URLConnection  urlConnection = url.openConnection();
		        DataOutputStream outStream;
		 
		        // Build request body		       
		        // Create connection	       
		       
		        ((HttpURLConnection)urlConnection).setRequestMethod("POST");
		        urlConnection.setConnectTimeout(0);
		        urlConnection.setReadTimeout(0);
		        urlConnection.setDoInput(true);
		        urlConnection.setDoOutput(true);
		        urlConnection.setUseCaches(false);
		        urlConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
		        urlConnection.setRequestProperty("Content-Length", "" + body.length());
		 
		        // Create I/O streams
		        outStream = new DataOutputStream(urlConnection.getOutputStream());
		 
		        // Send request
		        outStream.writeBytes(body);
		        outStream.flush();
		        outStream.close();
		 
		        // Get Response
				 
	            BufferedReader br = new BufferedReader(new InputStreamReader(urlConnection.getInputStream(), "utf8"));
	            s="";
	            String thisLine="";
	            while ((thisLine = br.readLine()) != null) { 
	                s=s+thisLine+"\r\n";
	              } 
	            br.close();
   
	            
		    }
		    catch(Exception ex) {
		        s= ex.toString();
		    }
		       return s;
		}

}
