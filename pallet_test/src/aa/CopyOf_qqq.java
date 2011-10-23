package aa;
import org.mindswap.pellet.PelletOptions;
import org.mindswap.pellet.jena.PelletReasonerFactory;
import org.semanticweb.owlapi.model.IRI;
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.model.OWLOntologyManager;
import com.clarkparsia.owlapiv3.OWL;
import com.clarkparsia.pellet.owlapiv3.PelletReasoner;
import com.clarkparsia.pellet.sparqldl.jena.SparqlDLExecutionFactory;
import com.hp.hpl.jena.ontology.Individual;
import com.hp.hpl.jena.ontology.ObjectProperty;
import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.query.Query;
import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.QueryExecutionFactory;
import com.hp.hpl.jena.query.QueryFactory;
import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.query.ResultSetFormatter;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.util.iterator.ExtendedIterator;

public class CopyOf_qqq {
	public static void main(String[] args) throws Exception {
		String s1 = "PREFIX rdf:  <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\r\n"
					+ "PREFIX wine: <http://www.w3.org/TR/2003/PR-owl-guide-20031209/wine#>\r\n"
				+ "\r\n" + "SELECT ?x ?y\r\n" + "WHERE {\r\n";
		String s2 = "}";
		String s = "file:fish.owl";
		s = "file:55.owl";
		s = "file:food.owl";

		OntModel model = ModelFactory
				.createOntologyModel(PelletReasonerFactory.THE_SPEC);
		model.read(s);
		//String sq = "   _:Wine wine:hasColor ?WineColor ";

		String sq = "   ?x wine:hasColor wine:Red";
		Query q = QueryFactory.create(s1 + sq + s2);
		ResultSet r = SparqlDLExecutionFactory.create(q, model).execSelect();
		ResultSetFormatter.out(System.out, r, q);
		
		sq = "    ?x wine:hasColor ?y ";
		q = QueryFactory.create(s1 + sq + s2);
		r = SparqlDLExecutionFactory.create(q, model).execSelect();
		ResultSetFormatter.out(System.out, r, q);
	}
}
