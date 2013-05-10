package ru;

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

	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		ServletOutputStream out = resp.getOutputStream();
		resp.setContentType("text/html; charset=UTF8");	

		String sh = req.getScheme() + "://" + req.getServerName() + ":" + req.getServerPort() + req.getContextPath();
		String st = req.getParameter("text");
		String sq=req.getParameter("sq");


		String s5=sq;
	
		s5=s5.replace("Кто", "");
		s5=s5.replace("?", "").trim();

		String[] stok=null;
		String phrase=null;
		
		String s8 = "[.]+";
		String delims = "[ ]+";		

		String s7=st;
		s7=s7.replace("\n", "");
		s7=s7.replace("\r", "");				
		s7=s7.replace("Любой", "");
		s7=s7.replace("Любая", "");
		s7=s7.replace("Всякий", "");
		s7=s7.replace("Всякая", "");
		s7=s7.replace("Каждый", "");
		s7=s7.replace("Каждая", "");
		s7=s7.replace("есть", "");
		//s7=s7.replace("-", "");
		s7=s7.replace("это", "-");

		String[] ss9 = s7.split(s8);
		String s= open_rdf();

		{
			for(int i=0;i<ss9.length;i++)
			{
				phrase=ss9[i].trim();
				stok = phrase.split(delims);			
				
				if (stok.length == 2)
					//s = Statik.add_subclass(s, stok[1], stok[0]);
				{
					s = add_class_rdf(s, stok[1]);
					s = add_subclass_rdf(s, stok[0], stok[1]);
				}		
				stok = phrase.split(delims);
				if (stok.length == 3)
					//s = Statik.add_classassertion(s, stok[2], stok[0]);
					
					s = add_inividual_rdf(s, stok[0], stok[2]);
			}			
		}
		//s = Statik.close_owl(s);
		s = close_rdf(s);
		clear_blobstore();
		s = wf("test.owl", s);
		String surl=sh+"/qq5";
		String sowl = sh+"/qqr";
		String body ="p1="+ URLEncoder.encode(sowl, "UTF-8") + "&p2=" + URLEncoder.encode(s5, "UTF-8"); 
		String sotvet =Statik.get_post(surl,body);
		
		
		s=Statik.rfu_utf(sh+"/ru.txt");		
		s=s.replace("qq1>", "qq1>"+st);		
		s=s.replace("name=\"sq\" value=\"\"", "name=\"sq\" value=\""+sq+"\"");
		s= s.replace("<!--qq-->", sotvet);
		
		byte[] b = s.getBytes("UTF8");
		out.write(b);
	}	
	

	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		ServletOutputStream out = resp.getOutputStream();
		resp.setContentType("text/html; charset=UTF8");	
		String sh = req.getScheme() + "://" + req.getServerName() + ":" + req.getServerPort() + req.getContextPath();
		String s=Statik.rfu_utf(sh+"/ru.txt");
		String s1=Statik.rfu_utf(sh+"/text.txt");
		s=s.replace("qq1>", "qq1>"+s1);
		s1=Statik.rfu_utf(sh+"/vopros.txt");
		s=s.replace("name=\"sq\" value=\"\"", "name=\"sq\" value=\""+s1+"\"");

		
		byte[] b = s.getBytes("UTF8");
		out.write(b);
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

		return "<?xml version=\"1.0\"?>"
				+ "\r\n"
				+ "<!DOCTYPE rdf:RDF ["
				+ "\r\n"
				+ "<!ENTITY rdfs \"http://www.w3.org/2000/01/rdf-schema#\" >"
				+ "\r\n"
				+ "<!ENTITY rdf \"http://www.w3.org/1999/02/22-rdf-syntax-ns#\" >"
				+ "\r\n"
				+ "<!ENTITY xsd \"http://www.w3.org/2001/XMLSchema#\" >"
				+ "\r\n" + "<!ENTITY qq \"http://feofan.com/#\" >" + "\r\n"
				+ "]>" + "\r\n" + "<rdf:RDF" + "\r\n"
				+ "xmlns=\"http://www.w3.org/2002/07/owl#\"" + "\r\n"
				+ "xml:base=\"http://www.w3.org/2002/07/owl\"" + "\r\n"
				+ "xmlns:rdfs=\"http://www.w3.org/2000/01/rdf-schema#\""
				+ "\r\n"
				+ "xmlns:rdf=\"http://www.w3.org/1999/02/22-rdf-syntax-ns#\""
				+ "\r\n" + "xmlns:xsd=\"http://www.w3.org/2001/XMLSchema#\""
				+ "\r\n" + "xmlns:qq=\"http://feofan.com/#\">" + "\r\n"
				+ "<Ontology rdf:about=\"http://feofan.com/\"/>" + "\r\n";
	}
	
	String close_rdf(String s) {
		return s + "</rdf:RDF>\r\n";
	}

	String add_class_rdf(String s,String sclass) {
		return s + "<Class rdf:about=\"&qq;"+sclass+"\"/>\r\n";
	}

	String add_subclass_rdf(String s,String ssubclass, String sclass) {
		return s + "<Class rdf:about=\"&qq;"+ssubclass+"\"><rdfs:subClassOf rdf:resource=\"&qq;"+sclass+"\"/></Class> \r\n";
	}
	
	String add_inividual_rdf(String s, String sind, String sclass) {
		return s + "<NamedIndividual rdf:about=\"&qq;"+sind+"\"> <rdf:type rdf:resource=\"&qq;"+sclass+"\"/> </NamedIndividual>\r\n";
	}
	public void onModuleLoad() {
	}
	private static final long serialVersionUID = 1L;
}