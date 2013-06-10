package guestbook;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;

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
import org.semanticweb.owlapi.model.OWLDataFactory;
import org.semanticweb.owlapi.model.OWLDifferentIndividualsAxiom;
import org.semanticweb.owlapi.model.OWLNamedIndividual;
import org.semanticweb.owlapi.model.OWLObjectExactCardinality;
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

public class qq6 extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		String sh = req.getScheme() + "://" + req.getServerName() + ":"
				+ req.getServerPort() + req.getContextPath();

		ServletOutputStream out = resp.getOutputStream();
		resp.setContentType("text/html; charset=UTF8");

		stat.sqq7 = maldev("");

		String s = stat.spref + "SELECT ?Who  WHERE {?Who qq:пара qq:3}";
		OntModel model = ModelFactory
				.createOntologyModel(PelletReasonerFactory.THE_SPEC);
		// model.read("file:zz.txt");
		model.read(sh + "/qq7");
		Query q = QueryFactory.create(s);
		ResultSet r = SparqlDLExecutionFactory.create(q, model).execSelect();
		s = r.nextBinding().toString();

		s = s.replace("( ?Who = <http://owl.feofan.com/1#", "").replace(
				"> ) -> [Root]", "");
		byte[] b = s.getBytes("UTF8");
		out.write(b);
	}

	public static String maldev(String s) {

		try {
			OWLClassAssertionAxiom caa = null;
			AddAxiom adax = null;
			OWLOntologyManager mm = OWLManager.createOWLOntologyManager();
			OWLDataFactory ff = mm.getOWLDataFactory();
			String base = "http://owl.feofan.com/1#";
			PrefixManager pre = new DefaultPrefixManager(base);
			OWLOntology oo;

			oo = mm.createOntology(IRI.create(base));

			// /////////////////////////

			// Классы

			// /////////////////////////

			OWLClass clam = ff.getOWLClass(":нечёт", pre);
			OWLClass clad = ff.getOWLClass(":чёт", pre);

			// ////////////////////////////

			// симметричная связь "пара"

			// ////////////////////////////

			OWLObjectProperty op = ff.getOWLObjectProperty(IRI.create(base
					+ "пара"));
			OWLSymmetricObjectPropertyAxiom obsym = ff
					.getOWLSymmetricObjectPropertyAxiom(op);
			adax = new AddAxiom(oo, obsym);
			mm.applyChange(adax);

			// /////////////////////////

			// Индивиды

			// /////////////////////////

			ArrayList<OWLNamedIndividual> nnm = new ArrayList<OWLNamedIndividual>();
			nnm.add(ff.getOWLNamedIndividual(":1", pre));
			nnm.add(ff.getOWLNamedIndividual(":3", pre));
			nnm.add(ff.getOWLNamedIndividual(":5", pre));

			ArrayList<OWLNamedIndividual> nnd = new ArrayList<OWLNamedIndividual>();
			nnd.add(ff.getOWLNamedIndividual(":2", pre));
			nnd.add(ff.getOWLNamedIndividual(":4", pre));
			nnd.add(ff.getOWLNamedIndividual(":6", pre));

			// /////////////////////////

			// Индивиды - члены класса

			// /////////////////////////

			for (int i = 0; i < nnm.size(); i++)
				mm.addAxiom(oo, ff.getOWLClassAssertionAxiom(clam, nnm.get(i)));

			for (int i = 0; i < nnm.size(); i++)
				mm.addAxiom(oo, ff.getOWLClassAssertionAxiom(clad, nnd.get(i)));

			
			
			// /////////////////////////

			//   Индивиды - разные 

			// /////////////////////////

			OWLDifferentIndividualsAxiom diffInds = ff
					.getOWLDifferentIndividualsAxiom(nnm.get(0),nnm.get(1), nnm.get(2), nnd.get(0),nnd.get(1), nnd.get(2));
			adax = new AddAxiom(oo, diffInds);
			mm.applyChange(adax);

			// /////////////////////////

			// 1 пара 2

			// /////////////////////////

			OWLObjectPropertyAssertionAxiom opaa = ff
					.getOWLObjectPropertyAssertionAxiom(op,
							ff.getOWLNamedIndividual(":1", pre),
							ff.getOWLNamedIndividual(":2", pre));

			adax = new AddAxiom(oo, opaa);
			mm.applyChange(adax);

			
			// /////////////////////////

			// 5 пара 6

			// /////////////////////////

			opaa = ff
					.getOWLObjectPropertyAssertionAxiom(op,
							ff.getOWLNamedIndividual(":3", pre),
							ff.getOWLNamedIndividual(":6", pre));

			adax = new AddAxiom(oo, opaa);
			mm.applyChange(adax);

			
			// /////////////////////////

			// {...} и пара только одному чёт (нечёт)

			// /////////////////////////

			OWLObjectExactCardinality exactly1m = ff
					.getOWLObjectExactCardinality(1, op, clam);
			OWLObjectExactCardinality exactly1d = ff
					.getOWLObjectExactCardinality(1, op, clad);

			OWLObjectOneOf nm = ff.getOWLObjectOneOf(nnm.get(0), nnm.get(1), nnm.get(2));
			OWLObjectOneOf nd = ff.getOWLObjectOneOf(nnd.get(0), nnd.get(1), nnd.get(2));

			OWLObjectIntersectionOf m_i_l_1_d = ff.getOWLObjectIntersectionOf(
					nm, exactly1d);
			OWLClassAxiom clax = ff.getOWLEquivalentClassesAxiom(clam,
					m_i_l_1_d);
			mm.addAxiom(oo, clax);

			OWLObjectIntersectionOf d_i_l_1_m = ff.getOWLObjectIntersectionOf(
					nd, exactly1m);
			OWLClassAxiom clax2 = ff.getOWLEquivalentClassesAxiom(clad,
					d_i_l_1_m);
			mm.addAxiom(oo, clax2);

			// /////////////////////////
			// /////////////////////////
			// /////////////////////////

			ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
			mm.saveOntology(oo, outputStream);
			s = new String(outputStream.toByteArray(), "UTF-8");

			return s;
		} catch (Exception e) {
			return e.toString();
		}
	}

	private static final long serialVersionUID = 1L;
}