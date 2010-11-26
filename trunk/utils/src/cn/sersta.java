package cn;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

import com.google.gdata.client.spreadsheet.SpreadsheetService;

public class sersta extends HttpServlet {

	private static final long serialVersionUID = 1L;

	static String s = "ok";

	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		ServletOutputStream out = res.getOutputStream();
		res.setContentType("text/html");

		out.println(sta.s);
		out.flush();
		out.close();
	}
}
