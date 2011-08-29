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

public class qq extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static String[] s4 = null;
	String s = "";

	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		s="";
		doPost(req, resp);

	}

	@SuppressWarnings("unchecked")
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		PrintWriter out = resp.getWriter();
		s="";
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
							//System.out.println("ww: "+iw);
							//System.out.println("sh: "+is);
							//System.out.println("cn: "+ic);

							String prefix=(ww.get_wo_number()).substring(0,3);
							
							//if(user.equals(prefix))
								
							s = s + "\r\n<country>" +
							  "\r\n<shiplat>"+sh.getLatitude()+"</shiplat>" +
							  "\r\n<shiplng>"+sh.getLongtitude()+"</shiplng>" +
							  "\r\n<conslat>"+cn.getLatitude()+"</conslat>" +
							  "\r\n<conslng>"+cn.getLongtitude()+"</conslng>" +
							  "\r\n<prefix>"+prefix+"</prefix>"+ 
							  "\r\n<woNumber>"+(ww.get_wo_number()).substring(4)+"</woNumber>" + 
							  "\r\n<from1>"+sh.getCity()+"</from1>" + 
							  "\r\n<from2>"+sh.getProv()+"</from2>" +
							  "\r\n<to1>"+cn.getCity()+"</to1>" + 
							  "\r\n<to2>"+cn.getProv()+"</to2>" +
							  "\r\n<dd>"+ww.get_delivery_dt()+"</dd>" + 
							  "\r\n<shd>"+ww.getpickup_dt()+"</shd>" + 
							  "\r\n<equipment>"+ww.getDescription()+"</equipment>"+ 
							  "\r\n<pieces>"+ww.get_pieces()+"</pieces>"+
							  "\r\n<type>"+ww.get_type()+"</type>" +
							  "\r\n<description>"+ww.get_desc()+"</description>" +
							 "\r\n<lbs>"+ww.get_weight_lbs()+"</lbs>" + 
							 "\r\n<kgs>"+ww.get_weight_kgs()+"</kgs>" + 
							 "\r\n</country>\r\n";							
						}
					}

				}
			}
		}

		s = "<List>" + s + "</List>";
		//s = s.replaceAll("\\s+", " ");
		out.println(s);
		//System.out.println(s);

	}
}