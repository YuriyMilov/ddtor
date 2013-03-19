package dd;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

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



public class owl extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String s = "";
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		ServletOutputStream out = resp.getOutputStream();
		resp.setContentType("text/html; charset=utf-8");
		
		String s=req.getQueryString();
		if(s==null)
			s="no URL request found";
		else
			s=rfu(req.getQueryString());
		
		
		
		byte[] b=s.getBytes("UTF8");
		out.write(b);	
	}

	   public static String rfu(String url) {
	        StringBuffer s = new StringBuffer();
	        try {
	                URL u = new URL(url);
	                InputStream in = u.openConnection().getInputStream();
	                for (int ch = in.read(); ch > 0; ch = in.read()) {
	                        s.append((char) ch);
	                }
	                in.close();
	        } catch (IOException e) {
	                return e.toString();
	        }
	        return s.toString();
	}
    
}
