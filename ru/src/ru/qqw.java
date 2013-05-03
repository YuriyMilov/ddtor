package ru;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class qqw extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		String s = req.getQueryString();
		Statik.clear_blobstore();
		 s=Statik.wf("test.owl",s);
		 s="<a href=qqr?"+s+">"+s+"</a>";		 
		PrintWriter out = resp.getWriter();
		out.write(s);
		out.flush();
		out.close();
	}
}
