package guestbook;

import java.io.IOException;
import java.io.StringReader;
import java.net.URLEncoder;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.BasicConfigurator;
import org.mindswap.pellet.jena.PelletReasonerFactory;
import com.clarkparsia.pellet.sparqldl.jena.SparqlDLExecutionFactory;
import com.google.gwt.core.client.EntryPoint;
import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.query.Query;
import com.hp.hpl.jena.query.QueryFactory;
import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import para.st;

public class qq extends HttpServlet implements EntryPoint {

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
				String ss = "(Феофану)Незнайка и Пончик - малыш. Кнопочка и Синеглазка это малышка. Если x любит y, то y любит x. Феофан любит Синеглазка. малыш любит 1 малышка. малышка любит 1 малыш. Пончик любит Синеглазка. Кого любит Кнопочка?";
				// ss="человек смертен. Сократ - человек. Кто Сократ?";
				ss = "qqqqqqqqq (Феофану) человек смертен. Сократ - человек. Кто Сократ?";
				ss = stat.rfu_utf(sh + "/qq.txt");

				ss = stq.get_mm(sh, "mailing test /qq?mm", ss, null, null);
				stat.page(req, resp, ss.replace("\r\n", "<br/>"));
			}
		}
	}

	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {

		// ///////////////////

		ServletOutputStream out = resp.getOutputStream();
		resp.setContentType("text/html; charset=UTF8");

		String sh = req.getScheme() + "://" + req.getServerName() + ":"
				+ req.getServerPort() + req.getContextPath();
		stat.sh = sh;

		String s = req.getParameter("p2").trim();
		String s5 = s, s55 = "";
		String[] ss = null;

		if (s == null) {
			stat.init(req, resp);
			return;
		} else if (s.toLowerCase().contains("спаркля(")) {
			s = stq.sparql(s, req, resp);
			stat.page(req, resp, s);
			return;
		} else if (s.contains("sparql")) {
			s55 = req.getParameter("txt");
			if (s55 != null)
				if (s55.toLowerCase().contains("спаркля(")) {
					s = stq.sparql(s55, req, resp);
					stat.page(req, resp, s);
					return;
				}
			if (stat.sowl == null)
				stat.sowl = "";
			stq.add_sr(stat.sr, sh);

			s = stat.get_prefix(sh)
					+ s55.replace(":", "qq:").replace("НАЙТИ", "select")
							.replace("ГДЕ", "where");

			OntModel mm = ModelFactory
					.createOntologyModel(PelletReasonerFactory.THE_SPEC);

			mm.read(new StringReader(stat.sowl), "");

			try {
				Query qq = QueryFactory.create(s);
				s = "";
				ResultSet r = SparqlDLExecutionFactory.create(qq, mm)
						.execSelect();
				while (r.hasNext())
					s = s + r.next().toString();

			} catch (Exception ee) {
				s = ee.toString();
			}
			stat.stop = stat.stop + "<br> <b><i> - </i></b>" + s55;

			s = s.replace("<", "[").replace(">", "]")
					.replace(sh + "/rff?83.owl#", "");
			s = s.replace("[Root]", "<br/>");

			if (s.trim().length() == 0)
				s = "ответа нет.<br/>мир можно дописать, загрузить или добавить.";

			stat.page(req, resp, "\r\n" + s);
			return;
		} else if (!s.contains("?")) {
			// /////////////////////////////
			//
			// текст
			//
			// /////////////////////////////
			if (s.indexOf(" ") < 0) {
				// /////////////////////////////
				//
				// команда
				//
				// /////////////////////////////
				stat.command(s, req, resp);
				return;
			} else {
				stq.add_sr(s, sh);
				stat.stop = stat.stop + "<br> <b><i> - </i></b>" + s;
				s = stat.sr;
				stat.page(
						req,
						resp,
						"\r\n<i>"
								+ s
								+ "</i>\r\nВопросы на КРЯ пока не больше трёх слов и начинаются на Что Кто Кого. Можно задать более сложный вопрос на Спаркле.");
				return;
			}
		} else {
			// /////////////////////////////
			//
			// вопрос
			//
			// /////////////////////////////

			String s6 = s;
			if (s5.contains(".")) {
				s55 = s5.substring(0, s5.lastIndexOf(".")).trim();

				s55 = stat.prep_all(s55);
				stq.add_sr(s55, sh);
				s5 = s5.substring(s5.lastIndexOf(".") + 1).trim();
			}
			s5 = s5.replace("?", "").trim();
			ss = s5.split("[ ]+");
			int i5 = ss.length;

			boolean bb = ss[0].toLowerCase().equals("кто")
					|| ss[0].toLowerCase().equals("что")
					|| ss[0].toLowerCase().equals("кого")
					|| ss[0].toLowerCase().equals("чего");

			if (bb) {
				s = stq.get_ans(sh, s5, req, resp);
				stat.stop = stat.stop + "<br> <b><i> - </i></b> " + s6;
				stat.page(req, resp, s.replace("\r\n", "<br>"));
			} else {
				stat.stop = stat.stop + "<br> <b><i> - </i></b> " + s6;
				stat.page(
						req,
						resp,
						("Вопросы на КРЯ пока не больше трёх слов и начинаются на Что Кто Кого. Можно задать более сложный вопрос на Спаркле. См. описане КРЯ и Спаркля"));
			}
		}
	}

	public void onModuleLoad() {
	}

	private static final long serialVersionUID = 1L;
}