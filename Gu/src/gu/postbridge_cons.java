package gu;

import gu.client.model.Consignee;
import gu.client.model.Worder;
import gu.server.PMF;

import java.io.IOException;
import java.io.PrintWriter;
import javax.jdo.PersistenceManager;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class postbridge_cons extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static String[] s4 = null;

	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		doPost(req, resp);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		PrintWriter out = resp.getWriter(); 
		String s1=req.getParameter("a1");
		String s2=req.getParameter("a2");
		String s3=req.getParameter("a3");
		String s4=req.getParameter("a4");
		String s5=req.getParameter("a5");
		String s6=req.getParameter("a6");
		String s7=req.getParameter("a7");
		String s8=req.getParameter("a8");
		String s9=req.getParameter("a9");
		String s10=req.getParameter("a10");
		String s11=req.getParameter("a11");

		
		 
		PersistenceManager pm = PMF.get().getPersistenceManager();
		Consignee tt = new Consignee(s1,s2,s3,s4,s5,s6,s7,s8,s9,s10,s11);
		pm.makePersistent(tt);
		
		out.println("ok");
	}

}