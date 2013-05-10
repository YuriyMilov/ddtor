package ru;

import java.io.IOException;
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

public class qq5 extends HttpServlet  {

	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		ServletOutputStream out = resp.getOutputStream();
		resp.setContentType("text/html; charset=UTF8");	

		String s = get_q1();
		s=s.substring(s.indexOf("#"));
		s=s.substring(0,s.indexOf(">"));
		
		s="<html><body>Проба "+s+"</p></body><html>";
		byte[] b = s.getBytes("UTF8");
		out.write(b);
	}	
	
	public String get_q1() {
		String surl = "http://ru.feofan.com/qq/test5.owl";
		String s = "" +
				"PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>" +
				"PREFIX rdf:  <http://www.w3.org/1999/02/22-rdf-syntax-ns#>"
				+ "PREFIX : <http://test.feofan.com/rufish2.owl#>"
//				+ "SELECT ?ÐšÑ‚Ð¾  WHERE {?ÐšÑ‚Ð¾ :Ñ€Ð°Ð·Ð²Ð¾Ð´Ð¸Ñ‚ :Ñ€Ñ‹Ð±ÐºÐ¸}";
		+ "SELECT ?кто  WHERE {?кто a :смертен}";
	
		OntModel model = ModelFactory
				.createOntologyModel(PelletReasonerFactory.THE_SPEC);
		//model.read("file:test5.owl");
		model.read(surl);
		Query q = QueryFactory.create(s);
		ResultSet r = SparqlDLExecutionFactory.create(q, model).execSelect();
		s = r.nextBinding().toString();
		return s;
	}



	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		doPost(req, resp);
	}
	public void onModuleLoad() {
	}
	private static final long serialVersionUID = 1L;
}