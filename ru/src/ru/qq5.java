package ru;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.mindswap.pellet.jena.PelletReasonerFactory;
import com.clarkparsia.pellet.sparqldl.jena.SparqlDLExecutionFactory;
import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.query.Query;
import com.hp.hpl.jena.query.QueryFactory;
import com.hp.hpl.jena.query.QuerySolution;
import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.rdf.model.ModelFactory;

public class qq5 extends HttpServlet  {

	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		ServletOutputStream out = resp.getOutputStream();
		resp.setContentType("text/html; charset=UTF8");		
		
		String sowl =  "http://ru.feofan.com/qq/test5.owl";		
		String sq = "смертен";
		//sowl="http://127.0.0.1:8888/qqr";
		
		sowl =  req.getParameter("p1");		
		sq = req.getParameter("p2");
		
		String s=get_q1(sowl,sq,req);

		s=s+" "+get_q2(sowl,sq,req);
	
		if(s.trim().length()==0)
			s="не знаю";
		//s=s.substring(s.indexOf("#")+1);
		//s=s.substring(0,s.indexOf(">"));
		
		
		//s="<html><body>"+s+"</p></body><html>";
		byte[] b = s.getBytes("UTF8");
		out.write(b);
	}	
	
	public String get_q1(String sowl, String  sq, HttpServletRequest req) {
		
		String s = "" +
				"PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>" +
				"PREFIX rdf:  <http://www.w3.org/1999/02/22-rdf-syntax-ns#>" +
				"PREFIX : <http://feofan.com/#> \r\n\r\n "
				//+"SELECT ?кто  WHERE {?кто a :"+sq+"}";
				+"SELECT ?кто  WHERE {:"+sq+" rdf:type ?кто}";
		

	
		OntModel model = ModelFactory
				.createOntologyModel(PelletReasonerFactory.THE_SPEC);

		String sh = req.getScheme() + "://" + req.getServerName() + ":" + req.getServerPort() + req.getContextPath();
		sh=sh+"/qq_s";
		
		//if(Statik.s.length()>0)		
		//	model.read(sh);
		//else
			model.read(sowl);
		
		Query q = QueryFactory.create(s);
		ResultSet r = SparqlDLExecutionFactory.create(q, model).execSelect();
		s="";
		while(r.hasNext())
		s=s+r.next().toString();
		
		String s2="";
				String[] ss = s.split("#");
				
				int i = ss.length;
				int k = 1;
				while(k <i)
				{
					s=ss[k++];
					int m = s.indexOf(">");
					s=s.substring(0,m);
					s2=s2+" "+s;
					
				}
		
		return s2.trim().replace(" ", " и ");
	}
	
	public String get_q2(String sowl, String  sq, HttpServletRequest req) {
		
		String s = "" +
				"PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>" +
				"PREFIX rdf:  <http://www.w3.org/1999/02/22-rdf-syntax-ns#>" +
				"PREFIX : <http://feofan.com/#> \r\n\r\n "
				+"SELECT ?кто  WHERE {?кто a :"+sq+"}";
				//+"SELECT ?кто  WHERE {:"+sq+" rdf:type ?кто}";
		

	
		OntModel model = ModelFactory
				.createOntologyModel(PelletReasonerFactory.THE_SPEC);

		String sh = req.getScheme() + "://" + req.getServerName() + ":" + req.getServerPort() + req.getContextPath();
		sh=sh+"/qq_s";
		
		//if(Statik.s.length()>0)		
		//	model.read(sh);
		//else
			model.read(sowl);
		
		Query q = QueryFactory.create(s);
		ResultSet r = SparqlDLExecutionFactory.create(q, model).execSelect();
		s="";
		while(r.hasNext())
		s=s+r.next().toString();
		
		String s2="";
				String[] ss = s.split("#");
				
				int i = ss.length;
				int k = 1;
				while(k <i)
				{
					s=ss[k++];
					int m = s.indexOf(">");
					s=s.substring(0,m);
					s2=s2+" "+s;
					
				}
		
		return s2.trim().replace(" ", " и ");
	}

	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		doPost(req, resp);
	}
	private static final long serialVersionUID = 1L;
}