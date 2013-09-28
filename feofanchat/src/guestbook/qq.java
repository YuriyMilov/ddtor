package guestbook;

import java.io.IOException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.google.gwt.core.client.EntryPoint;

public class qq extends HttpServlet implements EntryPoint {


	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		resp.setContentType("text/html; charset=UTF8");
		String sh = req.getScheme() + "://" + req.getServerName() + ":"
				+ req.getServerPort() + req.getContextPath();
		stat.sh = sh;
		String s = req.getParameter("p2").trim();
		stat.stop = stat.stop + "<br> <b><i> - </i></b>" + s;
		
		if (s == null) {
			stat.init(req, resp);
			return;
		} else if (!s.contains("?")) {
			if (s.indexOf(" ") < 0) {
				stat.command(s, req, resp);
				return;
			} else {
				stq.add_sr(s, sh);
				s = stat.sr;
			}
		} else {
			int i = s.indexOf("?");			
			String s1 = "";			
			if(i>-1)
				s1=s.substring(0, i);			
			i = s1.indexOf(".");
			if (i > 0) {
				s1 = s1.substring(0, s1.lastIndexOf(".") + 1);
				stat.sr="";
				stq.add_sr(s1, sh);
			}
			s = stq.otvet(sh, s);
		}

		// ////////////////////////////////////////////

		stat.page(req, resp, s);
	}


	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		// BasicConfigurator.configure();
		String sh = req.getScheme() + "://" + req.getServerName() + ":"
				+ req.getServerPort() + req.getContextPath();
		stat.sh = sh;
		if (req.getQueryString() == null)
			stat.init(req, resp);
		else {
			if (req.getQueryString().indexOf("p2=") > -1)
				doPost(req, resp);
			else if (req.getQueryString().indexOf("mm") > -1) {
				String ss="Незнайка и Пончик - малыш. Кнопочка и Синеглазка это малышка. Если x дружитъс y, то y  дружит_с x. малыш  дружитъс 1 малышка. малышка  дружитъс 1 малыш. Пончик  дружитъс Синеглазка. СПАРКЛЯ(Незнайка дружитъс ?кем)";
				ss = stq.mm_get_otvet(sh, "sb", ss, "ymilov@gmail.com");
				stq.mail_admins("mailing test /qq?mm", ss);
				stat.page(req, resp, ss.replace("\r\n", "<br/>"));
			}
		}
	}
	public void onModuleLoad() {
	}

	private static final long serialVersionUID = 1L;
}