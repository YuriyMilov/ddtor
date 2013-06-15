package guestbook;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class qq_s extends HttpServlet {

	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		ServletOutputStream out = resp.getOutputStream();
		resp.setContentType("text/xml; charset=UTF8");
		resp.setCharacterEncoding("UTF8");
		String sh = req.getScheme() + "://" + req.getServerName() + ":"
				+ req.getServerPort() + req.getContextPath();
		String s3 = req.getParameter("p3");
		String s4 = req.getParameter("p4");
		String s = "";
		if (s3 == null && s4 == null) {
			stat.sowl = stat.get_owl8(stat.sr);
			s = stat.sowl;
			byte[] b = s.getBytes("UTF8");
			out.write(b);
			return;
		}

		if (s3.equals("load")) {
			stat.stop = stat.stop + "<br> <b><i> - </i></b>" + s4;
			s = stat.rfu_utf(sh + "/" + URLEncoder.encode(s4, "UTF-8"));
			if (s.indexOf("Ъ") > -1 && s.length() > s.indexOf("Ъ"))
				s = s.substring(s.indexOf("Ъ") + 1);
			stat.text_new(s, req, resp);
			return;
		}

		if (s3.equals("add")) {
			stat.stop = stat.stop + "<br> <b><i> - </i></b>" + s4;
			s = stat.rfu_utf(sh + "/" + URLEncoder.encode(s4, "UTF-8"));
			if (s.indexOf("Ъ") > -1 && s.length() > s.indexOf("Ъ"))
				s = s.substring(s.indexOf("Ъ") + 1);
			stat.text(s, req, resp);
			return;
		}
	}

	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		doPost(req, resp);
	}

	private static final long serialVersionUID = 1L;
}