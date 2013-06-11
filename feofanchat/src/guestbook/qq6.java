package guestbook;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashSet;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.mindswap.pellet.jena.PelletReasonerFactory;
import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.model.AddAxiom;
import org.semanticweb.owlapi.model.IRI;
import org.semanticweb.owlapi.model.OWLClass;
import org.semanticweb.owlapi.model.OWLClassAssertionAxiom;
import org.semanticweb.owlapi.model.OWLClassAxiom;
import org.semanticweb.owlapi.model.OWLClassExpressionVisitor;
import org.semanticweb.owlapi.model.OWLDataFactory;
import org.semanticweb.owlapi.model.OWLDifferentIndividualsAxiom;
import org.semanticweb.owlapi.model.OWLNamedIndividual;
import org.semanticweb.owlapi.model.OWLObject;
import org.semanticweb.owlapi.model.OWLObjectExactCardinality;
import org.semanticweb.owlapi.model.OWLObjectIntersectionOf;
import org.semanticweb.owlapi.model.OWLObjectOneOf;
import org.semanticweb.owlapi.model.OWLObjectProperty;
import org.semanticweb.owlapi.model.OWLObjectPropertyAssertionAxiom;
import org.semanticweb.owlapi.model.OWLObjectSomeValuesFrom;
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.model.OWLOntologyCreationException;
import org.semanticweb.owlapi.model.OWLOntologyManager;
import org.semanticweb.owlapi.model.OWLOntologyStorageException;
import org.semanticweb.owlapi.model.OWLSymmetricObjectPropertyAxiom;
import org.semanticweb.owlapi.model.PrefixManager;
import org.semanticweb.owlapi.util.DefaultPrefixManager;

import com.clarkparsia.pellet.sparqldl.jena.SparqlDLExecutionFactory;
import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.query.Query;
import com.hp.hpl.jena.query.QueryFactory;
import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.rdf.model.ModelFactory;

public class qq6 extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		String sh = req.getScheme() + "://" + req.getServerName() + ":"
				+ req.getServerPort() + req.getContextPath();
		ServletOutputStream out = resp.getOutputStream();
		resp.setContentType("text/html; charset=UTF8");
		//stat.sqq7 = stat.para("");		
		stat.sqq7 = scrt("");
		String s = stat.spref + "SELECT ?Who  WHERE {?Who qq:пара qq:4}";
		OntModel model = ModelFactory
				.createOntologyModel(PelletReasonerFactory.THE_SPEC);
		model.read(sh + "/qq7");
		Query q = QueryFactory.create(s);
		ResultSet r = SparqlDLExecutionFactory.create(q, model).execSelect();
		if(r.hasNext())		
		s = r.nextBinding().toString();
		else
			s="Нет ответа...";		
		s = s.replace("( ?Who = <http://owl.feofan.com/1#", "").replace(
				"> ) -> [Root]", "");
		byte[] b = s.getBytes("UTF8");
		out.write(b);
	}

	public static String scrt(String s) {
		try {
			OWLOntologyManager mm = OWLManager.createOWLOntologyManager();
			OWLDataFactory ff = mm.getOWLDataFactory();
			String base = "http://owl.feofan.com/1#";
			PrefixManager pre = new DefaultPrefixManager(base);
			OWLOntology онтология = mm.createOntology(IRI.create(base));

			OWLClass класс = ff.getOWLClass(":человек", pre);
			OWLNamedIndividual индивид = ff.getOWLNamedIndividual(":Сократ", pre);			
			OWLClassAssertionAxiom аксиома = ff.getOWLClassAssertionAxiom(класс, индивид);
			
			mm.addAxiom(онтология, аксиома);
	
			
			// /////////////////////////
			// /////////////////////////
			// /////////////////////////

			ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
			mm.saveOntology(онтология, outputStream);
			s = new String(outputStream.toByteArray(), "UTF-8");
			return s;
		} catch (Exception e) {
			return e.toString();
		}
	}

	private static final long serialVersionUID = 1L;
}