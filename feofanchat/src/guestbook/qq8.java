package guestbook;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URI;
import java.util.Set;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.mindswap.pellet.jena.PelletInfGraph;
import org.mindswap.pellet.jena.PelletReasonerFactory;
import org.mindswap.pellet.owlapi.Reasoner;
import org.semanticweb.owl.apibinding.OWLManager;
import org.semanticweb.owl.model.OWLIndividual;
import org.semanticweb.owl.model.OWLOntology;
import org.semanticweb.owl.model.OWLOntologyManager;
import org.semanticweb.owlapi.reasoner.OWLReasoner;
import org.semanticweb.owlapi.util.InferredOntologyGenerator;

import aterm.ATermAppl;

import com.hp.hpl.jena.graph.Graph;
import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.rdf.model.ModelFactory;




public class qq8 extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
	String s="";
		
		String sh = req.getScheme() + "://" + req.getServerName() + ":"
				+ req.getServerPort() + req.getContextPath();
		ServletOutputStream out = resp.getOutputStream();
		resp.setContentType("text/html; charset=UTF8");
		
		 try {
			 //stat.sqq7 = stat.para("");	
			 stat.sqq7 = stat.scrt("");	

		OntModel model = ModelFactory
				.createOntologyModel(PelletReasonerFactory.THE_SPEC);
		model.read(sh + "/qq7");
		
		
		
		
			// /////////////////////////
			// /////////////////////////
			// /////////////////////////

			ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
			model.writeAll(outputStream, "RDF/XML",stat.siri);	
			s = new String(outputStream.toByteArray(), "UTF-8");

		
			} catch (Exception e) {
				s=e.toString();
			}  
		byte[] b = s.getBytes("UTF8");
		out.write(b);
	}

	
private static final long serialVersionUID = 1L;
}