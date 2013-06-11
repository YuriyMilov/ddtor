package guestbook;

import static org.semanticweb.owlapi.vocab.OWLFacet.MAX_EXCLUSIVE;
import static org.semanticweb.owlapi.vocab.OWLFacet.MIN_INCLUSIVE;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.coode.owlapi.manchesterowlsyntax.ManchesterOWLSyntaxOntologyFormat;
import org.mindswap.pellet.jena.PelletReasonerFactory;
import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.model.AddAxiom;
import org.semanticweb.owlapi.model.IRI;
import org.semanticweb.owlapi.model.OWLAxiom;
import org.semanticweb.owlapi.model.OWLClass;
import org.semanticweb.owlapi.model.OWLClassAssertionAxiom;
import org.semanticweb.owlapi.model.OWLClassAxiom;
import org.semanticweb.owlapi.model.OWLClassExpression;
import org.semanticweb.owlapi.model.OWLDataFactory;
import org.semanticweb.owlapi.model.OWLDataProperty;
import org.semanticweb.owlapi.model.OWLDataRange;
import org.semanticweb.owlapi.model.OWLDataSomeValuesFrom;
import org.semanticweb.owlapi.model.OWLDatatype;
import org.semanticweb.owlapi.model.OWLDifferentIndividualsAxiom;
import org.semanticweb.owlapi.model.OWLDisjointClassesAxiom;
import org.semanticweb.owlapi.model.OWLEquivalentClassesAxiom;
import org.semanticweb.owlapi.model.OWLFacetRestriction;
import org.semanticweb.owlapi.model.OWLIndividual;
import org.semanticweb.owlapi.model.OWLNamedIndividual;
import org.semanticweb.owlapi.model.OWLObjectAllValuesFrom;
import org.semanticweb.owlapi.model.OWLObjectExactCardinality;
import org.semanticweb.owlapi.model.OWLObjectHasValue;
import org.semanticweb.owlapi.model.OWLObjectIntersectionOf;
import org.semanticweb.owlapi.model.OWLObjectOneOf;
import org.semanticweb.owlapi.model.OWLObjectProperty;
import org.semanticweb.owlapi.model.OWLObjectPropertyAssertionAxiom;
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

public class qq7 extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		ServletOutputStream out = resp.getOutputStream();
		resp.setContentType("text/xml; charset=UTF8");

		String s = test("");
		
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
	

			ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
			mm.saveOntology(онтология, outputStream);
			s = new String(outputStream.toByteArray(), "UTF-8");
			return s;
		} catch (Exception e) {
			return e.toString();
		}
	}
	
	public static String test(String s) {
		try {
			Owl2Model qq= new Owl2Model("http://owl.feofan.com/1");
			OWLIndividual Socrat = qq.getIndividual("Socrat");
			OWLClass man = qq.getOwlClass("man");
			OWLClass mortal = qq.getOwlClass("mortal");
			qq.hasClass(Socrat, man);
			
			qq.isSubClassOf(man, mortal);
			s= qq.sowl();
			
			return s;
		} catch (Exception e) {
			return e.toString();
		}
	}
	
private static final long serialVersionUID = 1L;
}