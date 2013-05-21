package guestbook;

import java.io.IOException;
import java.net.URLEncoder;

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

public class qq6 extends HttpServlet  {

	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		ServletOutputStream out = resp.getOutputStream();
		resp.setContentType("text/html; charset=UTF8");	

		String sh = req.getScheme() + "://" + req.getServerName() + ":" + req.getServerPort() + req.getContextPath();
		String sru6=stat.rfu_utf(sh+"/ru6.txt");
		
		String s = req.getParameter("text").trim();
		
		
		String[] ss = s.split("[.]+");
		String s1 = "";
		String s2 = "";
		s="";
		for(int i=0;i<ss.length;i++)
		{
			s=ss[i].replace("Все", "").trim();
			s2=s;
			int f=s.indexOf(" ");
			String slovo=s.substring(0,f);
			s=s.substring(0,f);
			s2=s2.substring(f)+".";
			
			int k=slovo.lastIndexOf("и")+1;
			int m=slovo.length();
			
			if(k==m)
				slovo=slovo.substring(0,m-1);
			
				s="Всякий "+slovo+s2;
				
				s1=s1+s+"<br/>";
			
		}
		
		s1=s1+stat.rfu_utf(sh+"/yesno.txt");
		
		
		s=sru6.replace("<!--qq-->", s1);
		String syn=req.getParameter("yesno");
		if(syn==null)
			syn="";
		else
		{
		if(syn.equals("да"))
			syn="отлично";
		if(syn.equals("нет"))
			syn="надо разобраться, в чем проблема";
		}
		s=s+syn;
		
		byte[] b = s.getBytes("UTF8");
		out.write(b);
	}	
	


	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		ServletOutputStream out = resp.getOutputStream();
		resp.setContentType("text/html; charset=UTF8");	

		String sh = req.getScheme() + "://" + req.getServerName() + ":" + req.getServerPort() + req.getContextPath();
		resp.setContentType("text/html; charset=UTF8");	
		String s=stat.rfu_utf(sh+"/ru6.txt");
	
		
		byte[] b = s.getBytes("UTF8");
		out.write(b);
	}
	private static final long serialVersionUID = 1L;
}