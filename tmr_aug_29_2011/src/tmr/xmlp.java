package tmr;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class xmlp extends HttpServlet {
	private static final long serialVersionUID = 1L;

	void parse_xml(String s, PrintWriter out ){
		s=shta.rff("2.txt");
		try {
			new SAX(s, out);
		} catch (Exception e) {
			out.println(e.toString());
		}
	}

	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		PrintWriter out = resp.getWriter();
		parse_xml("",out);
	}
	 
	}
