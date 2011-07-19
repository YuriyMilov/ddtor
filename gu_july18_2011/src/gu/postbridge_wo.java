package gu;

import gu.client.model.Worder;
import gu.server.PMF;

import java.io.IOException;
import java.io.PrintWriter;
import javax.jdo.PersistenceManager;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class postbridge_wo extends HttpServlet {
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
		String s12=req.getParameter("a12");
		String s13=req.getParameter("a13");
		String s14=req.getParameter("a14");
		String s15=req.getParameter("a15");
		String s16=req.getParameter("a16");
		
		boolean bb=true;
		while(bb)
			try{
		 
		PersistenceManager pm = PMF.get().getPersistenceManager();
		Worder tt = new Worder(s1,s2,s3,s4,s5,s6,s7,s8,s9,s10,s11,s12,s13,s14,s15,s16);
		pm.makePersistent(tt);
		bb=false;
/*
		System.out.println("--------------------");
		System.out.println(s1);
		System.out.println(s2);
		System.out.println(s3);
		System.out.println(s4);
		System.out.println(s5);
		System.out.println(s6);
		System.out.println(s7);
		System.out.println("--------------------");
*/
			
			}catch(Exception e){
				try {
					Thread.sleep(111);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				out.println(e.toString());
				}
		
			
			out.println("ok");
	}

}