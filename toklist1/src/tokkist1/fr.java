package tokkist1;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class fr extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {

		resp.setCharacterEncoding("UTF8");
		resp.setContentType("text/plain");
		PrintWriter out = resp.getWriter();
		out.write(stkl.blob_r(req.getQueryString()));
		out.flush();
		out.close();
	}
	
	

	
}
