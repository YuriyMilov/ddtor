package gu;

import gu.client.model.Consignee;
import gu.client.model.Shipper;
import gu.client.model.Worder;
import gu.server.PMF;
import java.io.IOException;
import java.io.PrintWriter;
//import java.util.ArrayList;
import javax.jdo.PersistenceManager;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class qq_july22 extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static String[] s4 = null;
	String s = "";

	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		doPost(req, resp);

	}

	@SuppressWarnings("unchecked")
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		PrintWriter out = resp.getWriter();

		String user = (String) req.getSession().getAttribute("name");

		PersistenceManager pm = PMF.get().getPersistenceManager();
		List<Worder> woList = (List<Worder>) pm.newQuery(
				"SELECT FROM " + Worder.class.getName()).execute();
		List<Shipper> shipList = (List<Shipper>) pm.newQuery(
				"SELECT FROM " + Shipper.class.getName()).execute();
		List<Consignee> consList = (List<Consignee>) pm.newQuery(
				"SELECT FROM " + Consignee.class.getName()).execute();
		Worder ww = null;
		Shipper sh = null;
		Consignee cn = null;

		//ArrayList<String[]> ar = new ArrayList<String[]>();
		int iw = 0;
		while (iw < woList.size()) {
			ww = woList.get(iw++);
			int is = 0;
			while (is < shipList.size()) {
				sh = shipList.get(is++);
	
				if (ww.get_ship_id().equals(sh.get_ship_id())) {
					String cons_id = ww.get_cons_id();
					int ic = 0;
					int kc = consList.size();
					while (ic < kc ) {
						cn = consList.get(ic++);

						if (cons_id.equals(cn.get_ConsId())) {
							System.out.println("ww: "+iw);
							System.out.println("sh: "+is);
							System.out.println("cn: "+ic);

							s = s + "\r\n<country>" +
							  "\r\n<shiplat>44.0</shiplat>" +
							  "\r\n<shiplng>-77.0</shiplng>" +
							  "\r\n<conslat>40.0</conslat>" +
							  "\r\n<conslng>-88.0</conslng>" +
							  "\r\n<prefix>QQQ</prefix>"+ 
							  "\r\n<woNumber>789</woNumber>" + 
							  "\r\n<from1>from1</from1>" + 
							  "\r\n<from2>from2</from2>" +
							  "\r\n<to1>to1</to1>" + 
							  "\r\n<to2>to2</to2>" +
							  "\r\n<dd>2011-07-25</dd>" + 
							  "\r\n<shd>2011-08-29</shd>" + 
							  "\r\n<equipment>equipment</equipment>"+ 
							  "\r\n<pieces>11</pieces>"+
							  "\r\n<type>type</type>" +
							  "\r\n<description>description</description>" +
							 "\r\n<lbs>22</lbs>" + 
							 "\r\n<kgs>11</kgs>" + 
							 "\r\n</country>\r\n";							
						}
					}

				}
			}
		}

		s = "<List>" + s + "</List>";
		//s = s.replaceAll("\\s+", " ");
		out.println(s);
		System.out.println(s);

				/*
				 * if(user.equals(prefix)) s = s + "\r\n<country>" +
				 * "\r\n<shiplat>" + ar.get(n)[1] + "</shiplat>" +
				 * "\r\n<shiplng>" + ar.get(n)[2] + "</shiplng>" +
				 * "\r\n<conslat>" + ar.get(n)[5] + "</conslat>" +
				 * "\r\n<conslng>" + ar.get(n)[6] + "</conslng>" +
				 * "\r\n<prefix>" + prefix + "</prefix>" //+ "\r\n<nord>" +
				 * wo_int + "</nord>" + "\r\n<woNumber>" +
				 * ar.get(n)[8].substring(4) + "</woNumber>" + "\r\n<from1>" +
				 * from1 + "</from1>" + "\r\n<from2>" + from2 + "</from2>" +
				 * "\r\n<to1>" + to1 + "</to1>" + "\r\n<to2>" + to2 + "</to2>" +
				 * sdd + ssh + "\r\n<equipment>" + ar.get(n)[13] +
				 * "</equipment>" + "\r\n<pieces>" + ar.get(n)[14] + "</pieces>"
				 * + "\r\n<type>" + ar.get(n)[15] + "</type>" +
				 * "\r\n<description>"+ ar.get(n)[16] + "</description>" +
				 * "\r\n<lbs>" + ar.get(n)[18] + "</lbs>" + "\r\n<kgs>" +
				 * ar.get(n)[19] + "</kgs>" + "\r\n</country>\r\n";
				 */

			
		

	}
}