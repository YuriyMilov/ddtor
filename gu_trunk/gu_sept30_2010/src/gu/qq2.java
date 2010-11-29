package gu;

import gu.server.PMF;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.URL;
import java.util.Date;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class qq2 extends HttpServlet {
	private static final long serialVersionUID = 1L;


	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		
		System.out.println(req.getQueryString());
		PrintWriter out = resp.getWriter();
		String s = rfu("http://localhost:8888/ds/test_data/supplyItem.data.xml");
		out.println(s);
		
		
		///////////////// adding work order 
		/*
		int t = 9999;
		String[] s4=null;
		try {
			String str = req.getParameter("a");
			String id = req.getParameter("id");
			
			//user=new User(id, id.substring(id.indexOf("@")+1));
			
			
			String[] words = str.split("\r\n");
			PersistenceManager pm = PMF.get().getPersistenceManager();
			
			
			
			if (words == null)
				s4 = new String[] { "r", "50.0", "-60.0", "address","2", "text" };
			else
				s4 = words;
			
				List<Mrkr4> rd = (List<Mrkr4>) pm.newQuery("SELECT FROM " + Mrkr4.class.getName()+ " WHERE s5==\""+id+"\"").execute();
				for (int i = 0; i < rd.size(); i++)
				{
					Mrkr4 mr = pm.getObjectById(Mrkr4.class, rd.get(i).getId());
					pm.deletePersistent(mr);
				}
				for (int i = 0; i < s4.length; i = i + 6) {
				Mrkr4 ns = new Mrkr4(new Date(), s4[i], s4[i + 1],
						s4[i + 2], s4[i + 3], s4[i + 4], s4[i + 5]);
				pm.makePersistent(ns);
				}
				
				
			
			for (int i = 0; i < s4.length; i = i + 6) {
				String query = "SELECT FROM " + Mrkr4.class.getName()
						+ " WHERE s2 == \"" + s4[i + 1] + "\" && s3 == \""
						+ s4[i + 2] + "\"";
				List<Mrkr4> rr = (List<Mrkr4>) pm.newQuery(query).execute();
				t = rr.size();
				if (rr.size() == 0) {
					Mrkr4 ns = new Mrkr4(new Date(), s4[i], s4[i + 1],
							s4[i + 2], s4[i + 3], s4[i + 4], s4[i + 5], user);
					pm.makePersistent(ns);
				} else {

					Mrkr4 ns = pm.getObjectById(Mrkr4.class, rr.get(0).getId());
					pm.deletePersistent(ns);

					ns = new Mrkr4(new Date(), s4[i], s4[i + 1], s4[i + 2],
							s4[i + 3], s4[i + 4], s4[i + 5], user);
					pm.makePersistent(ns);
				}
				
				
			}
			
			
		} catch (Exception eee) {
			s=eee.toString();
		}
				*/
		
	}

	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		System.out.println(2222);
		doGet(req,resp);
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