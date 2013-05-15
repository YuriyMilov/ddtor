package ru;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.mindswap.pellet.jena.PelletReasonerFactory;
import com.clarkparsia.pellet.sparqldl.jena.SparqlDLExecutionFactory;
import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.query.Query;
import com.hp.hpl.jena.query.QueryFactory;
import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.rdf.model.ModelFactory;

public class qq_s extends HttpServlet  {

	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		ServletOutputStream out = resp.getOutputStream();
		resp.setContentType("text/html; charset=UTF8");	

		String sh = req.getScheme() + "://" + req.getServerName() + ":" + req.getServerPort() + req.getContextPath();
		//String s=sh+"/ru6.txt";
		String s=Statik.s;
		
		
		byte[] b = s.getBytes("UTF8");
		out.write(b);
	}	
	


	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		ServletOutputStream out = resp.getOutputStream();
		resp.setContentType("text/html; charset=UTF8");	

		String sh = req.getScheme() + "://" + req.getServerName() + ":" + req.getServerPort() + req.getContextPath();
		resp.setContentType("text/html; charset=UTF8");	
		String s=Statik.rfu_utf(sh+"/ru6.txt");
	
		
		byte[] b = s.getBytes("UTF8");
		out.write(b);
	}
	private static final long serialVersionUID = 1L;
}