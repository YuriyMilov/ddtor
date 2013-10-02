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
			stq.init(req, resp);
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
			if (i > -1)
				s1 = s.substring(0, i);
			i = s1.indexOf(".");
			if (i > 0) {
				s1 = s1.substring(0, s1.lastIndexOf(".") + 1);
				stat.sr = "";
				stq.add_sr(s1, sh);
			}
			stat.sr = stat.sr + "";
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
		String s = req
				.getQueryString();
		stat.sh = sh;
		if (req.getQueryString() == null)
			stq.init(req, resp);
		else {
			if (s.indexOf("p2=") > -1)
				doPost(req, resp);

			else
				if (s.indexOf("w") == 0) {
					new Thread(new w("w","wwwwwwwwwwwwww")).start();
					stat.page(req, resp, "<a href="+sh+"/rff?w>"+sh+"/rff?w</a>");
				}

				else if (s.indexOf("mmm") == 0) {
					String ss = "Незнайка и Пончик - малыш. Кнопочка и Синеглазка это малышка. Если x дружитъс y, то y  дружит_с x. малыш  дружитъс 1 малышка. малышка  дружитъс 1 малыш. Пончик  дружитъс Синеглазка. СПРАШКЛ(Незнайка дружитъс ?кем)";
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