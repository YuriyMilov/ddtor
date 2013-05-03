package ru;

import java.io.IOException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class qqr extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {		
		ServletOutputStream out = resp.getOutputStream();
		resp.setContentType("text/xml; charset=UTF8");
		resp.setCharacterEncoding("UTF8");
		String s=Statik.rf3("test.owl");
		byte[] b=s.getBytes("UTF8");
		out.write(b);	
		out.flush();
		out.close();
	}
}
