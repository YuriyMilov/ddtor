package tokkist1;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@SuppressWarnings("serial")
public class fw extends HttpServlet {

	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		PrintWriter out = resp.getWriter();
		
		out.write(stkl.blob_w(req.getParameter("f"),req.getParameter("s")));
		out.flush();
		out.close();
	}

	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		
		PrintWriter out = resp.getWriter();
		out.write(stkl.blob_w(req.getParameter("f"),req.getParameter("s")));
		out.flush();
		out.close();
	}

}
