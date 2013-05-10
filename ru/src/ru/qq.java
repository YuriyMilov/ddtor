package ru;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.google.appengine.api.blobstore.BlobKey;
import com.google.appengine.api.files.AppEngineFile;
import com.google.appengine.api.files.FileService;
import com.google.appengine.api.files.FileServiceFactory;
import com.google.appengine.api.files.FileWriteChannel;
import com.google.appengine.api.files.GSFileOptions.GSFileOptionsBuilder;
import com.google.gwt.core.client.EntryPoint;

public class qq extends HttpServlet implements EntryPoint {

	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		ServletOutputStream out = resp.getOutputStream();
		resp.setContentType("text/html; charset=UTF8");	

		String[] stok=null;
		String phrase=null;
		
		String s8 = "[.]+";
		String delims = "[ ]+";		
		String s7 = req.getParameter("text");

		s7=s7.replace("\n", "");
		s7=s7.replace("\r", "");				
		s7=s7.replace("Любой", "");
		s7=s7.replace("Любая", "");
		s7=s7.replace("Всякий", "");
		s7=s7.replace("Всякая", "");
		s7=s7.replace("Каждый", "");
		s7=s7.replace("Каждая", "");
		s7=s7.replace("есть", "");
		s7=s7.replace("-", "");
		s7=s7.replace("это", "-");

		String[] ss9 = s7.split(s8);
		String s= Statik.init_owl();

		{
			for(int i=0;i<ss9.length;i++)
			{

				phrase=ss9[i].trim();
				stok = phrase.split(delims);			
				
				if (stok.length == 2)
					s = Statik.add_subclass(s, stok[1], stok[0]);
				//phrase = req.getParameter("S2");
				
				stok = phrase.split(delims);
				if (stok.length == 3)
					s = Statik.add_classassertion(s, stok[2], stok[0]);
			}			
		}
		s = Statik.close_owl(s);
		Statik.clear_blobstore();
		s = Statik.wf("test.owl", s);
		s = "<a href=contrus.owl>contrus.owl</a>";
		s="<html><body>OWL2 URL для загрузки в Протеже "+s+"</p></body><html>";
		byte[] b = s.getBytes("UTF8");
		out.write(b);
	}	
	

	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		doPost(req, resp);
	}
	public void onModuleLoad() {
	}
	private static final long serialVersionUID = 1L;
}