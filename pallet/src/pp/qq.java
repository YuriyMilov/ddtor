package pp;

import java.io.IOException;
import java.io.PrintWriter;
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
		s = "";
		doPost(req, resp);

	}

	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		PrintWriter out = resp.getWriter();/*
		s = "file:fish.owl";

		try {
			String s1 = "PREFIX rdf:  <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\r\n"
					+ "PREFIX : <http://test.feofan.com/fish.owl#>\r\n"
					+ "\r\n" + "SELECT ?Who ?Beverage\r\n" + "WHERE {\r\n";
			String s2 = "}";

			OntModel model = ModelFactory
					.createOntologyModel(PelletReasonerFactory.THE_SPEC);
			model.read(s);

			Query q = QueryFactory.create(s1 + "?Who :owns :fish" + s2);
			ResultSet r = SparqlDLExecutionFactory.create(q, model)
					.execSelect();
			s = r.toString();
			s = s.substring(s.indexOf("#") + 1, s.indexOf("}"));


			// q = QueryFactory.create(s1 + "?Who :drinks ?Beverage" + s2);
			// r = SparqlDLExecutionFactory.create(q, model).execSelect();
			// ResultSetFormatter.out(System.out, r, q);

		} catch (Exception e) {
			s = e.toString();
		}*/
		s=get_q();
		s = s.substring(s.indexOf("#") + 1, s.indexOf("}"));
		out.println(s);
	}
	
	public String get_q(){
		String s = "";
		try{
			OntModel model = ModelFactory.createOntologyModel(PelletReasonerFactory.THE_SPEC);
			model.read("file:fish.owl");

			Query q = QueryFactory.create(

			  "PREFIX rdf:  <http://www.w3.org/1999/02/22-rdf-syntax-ns#>"
			+ "PREFIX : <http://test.feofan.com/fish.owl#>"
			+ "SELECT ?Who ?Beverage WHERE {?Who :owns :fish }"

			);

			ResultSet r = SparqlDLExecutionFactory.create(q, model).execSelect();
			s=r.toString();
		} catch (Exception e) {
			s = e.toString();
		}
		return s;}
}