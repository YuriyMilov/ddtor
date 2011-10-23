package aa;
import org.mindswap.pellet.jena.PelletReasonerFactory;
import com.clarkparsia.pellet.sparqldl.jena.SparqlDLExecutionFactory;
import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.query.Query;
import com.hp.hpl.jena.query.QueryFactory;
import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.query.ResultSetFormatter;
import com.hp.hpl.jena.rdf.model.ModelFactory;

public class _qqq {
	public static void main(String[] args) throws Exception {
		String s1 = "PREFIX rdf:  <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\r\n"
				+ "PREFIX : <http://test.feofan.com/fish.owl#>\r\n"
				+ "\r\n"
				+ "SELECT ?Who ?Beverage\r\n" + "WHERE {\r\n";
		String s2 = "}";
		String s = "file:fish.owl";

		OntModel model = ModelFactory
				.createOntologyModel(PelletReasonerFactory.THE_SPEC);
		model.read(s);

		Query q = QueryFactory.create(s1 + "?Who :owns :fish" + s2);
		ResultSet r = SparqlDLExecutionFactory.create(q, model).execSelect();
		s = r.toString();
		s=s.substring(s.indexOf("#")+1,s.indexOf("}"));
		System.out.println(s);

		// q = QueryFactory.create(s1 + "?Who :drinks ?Beverage" + s2);
		// r = SparqlDLExecutionFactory.create(q, model).execSelect();
		// ResultSetFormatter.out(System.out, r, q);
	}
}
