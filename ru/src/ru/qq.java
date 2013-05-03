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
	private static final long serialVersionUID = 1L;
	public void onModuleLoad() {
	}

	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		doPost(req, resp);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		ServletOutputStream out = resp.getOutputStream();
		resp.setContentType("text/html; charset=UTF8");		
		String phrase = req.getParameter("S1");
		// String phrase = "the music made   it   hard      to        concentrate";
		String delims = "[ ]+";
		String[] stok = phrase.split(delims);
		String s= init_owl();
		if (stok.length > 1)
			s = add_subclass(s, stok[1], stok[0]);
		// s = add_subclass(s, "смертен", "человек");
		phrase = req.getParameter("S2");
		stok = phrase.split(delims);
		if (stok.length > 2)
			s = add_classassertion(s, stok[2], stok[0]);
		// s = add_classassertion(s, "человек", "Сократ");
		s = close_owl(s);
		Statik.clear_blobstore();
		s = Statik.wf("test.owl", s);
		s = "<a href=contrus.owl>contrus.owl</a>";
		s="<html><body>OWL2 URI: "+s+"</p></body><html>";
		byte[] b = s.getBytes("UTF8");
		out.write(b);
	}

	static String init_owl() {
		return "<?xml version=\"1.0\" encoding=\"UTF8\"?>\r\n\r\n<Ontology\r\n   xml:base=\"http://www.w3.org/2002/07/owl#\"\r\n   xmlns=\"http://www.w3.org/2002/07/owl#\"\r\n   ontologyIRI=\"http://feofan.com/test\">\r\n";
	}

	static String close_owl(String s) {
		return s + "</Ontology>\r\n";
	}

	static String add_subclass(String sinit, String sclass, String ssubclass) {
		return sinit + "<SubClassOf>\r\n	<Class IRI=\"http://feofan.com/test#"
				+ ssubclass + "\"/>\r\n	<Class	IRI=\"http://feofan.com/test#"
				+ sclass + "\"/>\r\n</SubClassOf>\r\n";
	}

	static String add_classassertion(String sinit, String sclass, String sind) {
		return sinit + "<ClassAssertion>   "
				+ "\r\n	<Class IRI=\"http://feofan.com/test#" + sclass + "\"/>"
				+ "\r\n	<NamedIndividual IRI=\"http://feofan.com/test#" + sind
				+ "\"/>\r\n" + "</ClassAssertion>\r\n";
	}

	public String wfxxx(String sname, String s) {
		try {
			FileService fileService = FileServiceFactory.getFileService();
			AppEngineFile file = fileService.createNewBlobFile("text/plain",
					sname);
			boolean lock = false;
			FileWriteChannel writeChannel = fileService.openWriteChannel(file,
					lock);
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
		} catch (Exception ee) {
			s = ee.toString();
		}
		return s.substring(10).replace(">", "");
	}
}