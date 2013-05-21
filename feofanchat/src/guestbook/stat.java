package guestbook;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class stat {

	public static String st="";
	public static String sr="Всякий человек смертен. Сократ - человек.";
	
	public static void page(HttpServletRequest req, HttpServletResponse resp, String s) throws IOException {
		
		ServletOutputStream out = resp.getOutputStream();
		resp.setContentType("text/html; charset=UTF8");
		String sh = req.getScheme() + "://" + req.getServerName() + ":"
				+ req.getServerPort() + req.getContextPath();
		
		s="<html><head><meta http-equiv=\"content-type\" content=\"text/html; charset=UTF-8\"/></head><body>"+s+"<form  action=qq method=post> <input type=text name=q >&nbsp;<input type=submit /></body></html>";
		
		byte[] b = s.getBytes("UTF8");
		out.write(b);		
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

	/*
	 * if (s5.indexOf(" ") == -1) {
					String[] stok = null;
					String phrase = null;
					String s8 = "[.]+";
					String delims = "[ ]+";
					s7 = st;
					s7 = s7.replace("Любой", "");
					s7 = s7.replace("Любая", "");
					s7 = s7.replace("Любое", "");
					s7 = s7.replace("Всякий", "");
					s7 = s7.replace("Всякая", "");
					s7 = s7.replace("Всякое", "");
					s7 = s7.replace("Каждый", "");
					s7 = s7.replace("Каждая", "");
					s7 = s7.replace("Каждое", "");
					s7 = s7.replace("есть", "");
					s7 = s7.replace("это", "-");
					String[] ss9 = s7.split(s8);
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
								
								
								
								
///////////////////////////////////////////////
								
								s = add_inividual_rdf(s, stok[0], stok[2]);
							
							//if(stok[1].equals("-"))
							//add_a_has_b(s,stok[0], stok[1], stok[2]);
							
							
							
							
							
							
			/////////////////////////////////////////////////				
							
							
						}
					}
					// s = Statik.close_owl(s);
					s = close_rdf(s);
					clear_blobstore();

					Statik.s = s;
					
					s = wf("test.owl", s);
	 * */
