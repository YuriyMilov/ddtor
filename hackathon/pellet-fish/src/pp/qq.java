package pp;

import java.io.IOException;
//import java.io.PrintWriter;
//import java.io.UnsupportedEncodingException;

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

public class qq extends HttpServlet {
	private static final long serialVersionUID = 1L;
	String s = "";
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		doPost(req, resp);

	}

	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		ServletOutputStream out = resp.getOutputStream();
		resp.setContentType("text/html; charset=utf-8");
		
		s = get_q();
		if(s.indexOf("#")>0)
			s=s.substring(s.indexOf("#")+1);
		if(s.indexOf(")")>0)
			s=s.substring(0,s.indexOf(")")-1);
		
		s=s.replace("<", "");
		s=s.replace(">", "");
		 s="<html><meta http-equiv=\"content-type\" content=\"text/html; charset=UTF-8\"><body>"+s+"</body></html>";
		
		byte[] b=s.getBytes("UTF8");
		out.write(b);	
	}

	public String get_q() {
		
		//String s = "PREFIX rdf:  <http://www.w3.org/1999/02/22-rdf-syntax-ns#>"
		//		+ "PREFIX : <http://test.feofan.com/rufish2.owl#>"
		//		+ "SELECT ?Кто  WHERE {?Кто :разводит :рыбки}";
		
		
		String s = "PREFIX rdf:  <http://www.w3.org/1999/02/22-rdf-syntax-ns#>"
				+ "PREFIX : <http://test.feofan.com/fish.owl#>"
				+ "SELECT ?Who  WHERE {?Who :owns :fish}";
		
		OntModel model = ModelFactory
				.createOntologyModel(PelletReasonerFactory.THE_SPEC);
		//model.read("file:rufish2.owl");
		model.read("file:fish.owl");
		Query q = QueryFactory.create(s);
		ResultSet r = SparqlDLExecutionFactory.create(q, model).execSelect();
		s = r.nextBinding().toString();
		return s;
	}
	
}