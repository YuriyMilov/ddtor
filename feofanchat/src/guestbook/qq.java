package guestbook;

import java.io.IOException;
import java.net.URLEncoder;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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

		String sh = req.getScheme() + "://" + req.getServerName() + ":"
				+ req.getServerPort() + req.getContextPath();

		st.sh = sh;

		if (req.getQueryString() == null)
			stat.init(req, resp);
		else if (req.getQueryString().indexOf("p2=") > -1)
			doPost(req, resp);
		/*
		 * 
		 * 
		 * String ss=
		 * "Незнайка и Пончик - малыш. Кнопочка и Синеглазка это малышка. Если x любит y, то y любит x. Феофан любит Синеглазка. малыш любит 1 малышка. малышка любит 1 малыш. Пончик любит Синеглазка. Кого любит Кнопочка?"
		 * ; ss="человек смертен. Сократ - человек. Кто Сократ?"; String s1 =
		 * ss.trim(); String s="", s2 ="", s3 ="", s4 ="", s5 ="";
		 * 
		 * int i3 = ss.indexOf("?"); if (i3 > 0) { s4 = ss.substring(0, i3 + 1);
		 * i3 = ss.lastIndexOf(".");
		 * 
		 * if (i3 > 0) { s5 = ss.substring(0, i3 + 1).trim(); s4 =
		 * s4.substring(i3 + 1).trim(); }
		 * 
		 * s3 = stat.get_owl83(s5); ss = s1 + "\r\n------\r\n" + get_ans(sh,s4);
		 * } stat.page(req, resp, ss);
		 */
	}

	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {

		// ///////////////////

		ServletOutputStream out = resp.getOutputStream();
		resp.setContentType("text/html; charset=UTF8");

		String sh = req.getScheme() + "://" + req.getServerName() + ":"
				+ req.getServerPort() + req.getContextPath();

		st.sh = sh;

		String s = req.getParameter("p2");
		String s5 = s, s55 = "", s6 = "";
		String[] ss = null;

		if (s == null) {
			stat.init(req, resp);
			return;
		}
		s = s.trim();

		// /////////////////////////////
		//
		// команда
		//
		// /////////////////////////////

		if (s.indexOf(" ") < 0) {
			stat.command(s, req, resp);
			return;
		}

		// /////////////////////////////
		//
		// текст
		//
		// /////////////////////////////

		if (!s.contains("?")) {
			stat.add_sr(s, req, resp);
			stat.stop = stat.stop + "<br> <b><i> - </i></b>" +s;
			s= "Новый мир: "+stat.sr;
			stat.page(req, resp, s);
			return;
		}

		else
		// /////////////////////////////
		//
		// вопрос
		//
		// /////////////////////////////

		// if(stat.sr.trim().equals(""))
		// stat.sr=stat.rff("83.owl");
		{
			s6 = s;
			if (s5.contains(".")) {
				s55 = s5.substring(0, s5.lastIndexOf(".")).trim();
				stat.add_sr(s55, req, resp);				
				s5 = s5.substring(s5.lastIndexOf(".") + 1).trim();
			}
			s5 = s5.replace("?", "").trim();
			ss = s5.split("[ ]+");
			int i5 = ss.length;

			boolean bb = ss[0].toLowerCase().equals("кто")
					|| ss[0].toLowerCase().equals("что")
					|| ss[0].toLowerCase().equals("кого")
					|| ss[0].toLowerCase().equals("чего");

			if (bb)
			{
				s=stq.get_ans(sh, s5);
				stat.stop = stat.stop + "<br> <b><i> - </i></b> " + s6;
				stat.page(req, resp, s.replace("\r\n", "<br>"));
			}
				
				/*{

				
				 * 
				 * InfGraph graph = mm.getReasoner().bind(mm.getGraph());
				 * 
				 * InfModel im = ModelFactory.createInfModel(graph);
				 * 
				 * //<owl:onProperty
				 * rdf:resource="http://owl.feofan.com/rff?83.owl#любит"/>
				 * //ResIterator rit =
				 * im.listSubjectsWithProperty(im.getProperty("любит")); // bb=
				 * rit.hasNext();
				 * 
				 * 
				 * List<Statement> sts = im.listStatements().toList(); s=s
				 * +"\r\n<br>"+"\r\n<br>"; for(Statement st : sts) { String ss =
				 * st.toString(); // if(ss.indexOf("любит")>-1 ) {
				 * ss=ss.replace("http://owl.feofan.com/rff?83.owl#","");
				 * if(ss.indexOf("http")<0 ) {
				 * 
				 * s=s+ss+"\r\n<br>"; } } }
				 

				// stat.stop = stat.stop + "<br> <b><i> - " + s5 + "?</i></b>";

				// s5 = s5.replace("Кто ", "").replace("кто ", "")
				// .replace("Что ", "").replace("что ", "")
				// .replace("?", "").trim();

				if (ss.length == 2) {
					// stat.sowl = stat.get_owl(stat.sr);
					// s = wf("test.owl", stat.sowl);

					String surl = sh + "/qq5";
					String sowl = sh + "/qqr";
					String body = "p1=" + URLEncoder.encode(sowl, "UTF-8")
							+ "&p2=" + URLEncoder.encode(ss[1], "UTF-8");

					stat.stop = stat.stop + "<br> <b><i> - </i></b> " + s;

					s = stat.get_post(surl, body);
					if(s.trim().length()==0)
						s="Ещё не знаю. Расскажи мне об этом.";
					
					stat.page(req, resp, s);
					return;
				} else

				// ///////////////////////////////////////////
				//
				// вопрос больше чем два слова - пара и хелп
				//
				// ///////////////////////////////////////////

				if (i5 == 3) {

					OntModel mm = ModelFactory
							.createOntologyModel(PelletReasonerFactory.THE_SPEC);

					mm.read(sh + "/owl");
					s = stat.sowl;

					s = stat.spref
							// +"SELECT ?кто  WHERE {?кто qq:любит qq:Миша}";
							// +"SELECT ?кто  WHERE {qq:Маша qq:любит ?кто}";
							+ "SELECT ?кто  WHERE {?кто qq:" + ss[1] + " qq:"
							+ ss[2] + "}";

					Query qq = QueryFactory.create(s);
					ResultSet r = SparqlDLExecutionFactory.create(qq, mm)
							.execSelect();
					s = "";
					while (r.hasNext())
						s = s + r.next().toString();

					s = s.replace(
							"( ?кто = <http://owl.feofan.com/rff?83.owl#", "")
							.replace("> ) -> [Root]", "");

					if (s.trim().length() == 0) {
						s = stat.spref
								// +"SELECT ?кто  WHERE {?кто qq:любит qq:Миша}";
								// +"SELECT ?кто  WHERE {qq:Маша qq:любит ?кто}";
								// +"SELECT ?кто  WHERE {?кто qq:"+ss[1]+" qq:"+ss[2]+"}";
								+ "SELECT ?кто  WHERE {qq:" + ss[2] + " qq:"
								+ ss[1] + " ?кто}";

						qq = QueryFactory.create(s);
						r = SparqlDLExecutionFactory.create(qq, mm)
								.execSelect();
						s = "";
						while (r.hasNext())
							s = s + r.next().toString();

						s = s.replace(
								"( ?кто = <http://owl.feofan.com/rff?83.owl#",
								"").replace("> ) -> [Root]", "");
						if(s.trim().length()==0)
							s="Ещё не знаю. Расскажи мне об этом";
						s = s + ".";

					}
					stat.stop = stat.stop + "<br> <b><i> - </i></b> " + s6;
					stat.page(req, resp, s);
					return;
				} else if (i5 > 3) {

					if (ss.length == 4) {

						try {

							// /////////////////////////

							stat.sqq7 = stat.para("");

							// stat.sowl=stat.sqq7;

							s = stat.spref + "SELECT ?кто  WHERE {?кто qq:"
									+ ss[1] + " qq:" + ss[2] + "}";

							// /////////////////////////

							// stat.sqq7 = stat.scrt("");
							// s = stat.spref +
							// "SELECT ?кто  WHERE {qq:Сократ a ?кто}";

							// /////////////////////////

							OntModel model = ModelFactory
									.createOntologyModel(PelletReasonerFactory.THE_SPEC);
							model.read(sh + "/qq7");

							Query q = QueryFactory.create(s);
							ResultSet rs = SparqlDLExecutionFactory.create(q,
									model).execSelect();

							s = "";
							String sa = "";
							while (rs.hasNext()) {
								sa = rs.nextBinding().toString();
								if (sa.indexOf(stat.siri) > -1) {
									sa = sa.replace(stat.siri, "")
											.replace("( ?кто = <", "")
											.replace("> ) -> [Root]", "");
									s = s + " " + sa;
								}
							}
							s = s.trim();
							if (s.length() == 0)
								s = "Нет ответа...";
							else
								s = s.replace(" ", ", ");

						} catch (Exception e) {
							s = "не понял вопроса - см. №1 ";
						}

					} else
						s = "не понял вопроса - см. №2 ";
					stat.stop = stat.stop + "<br> <b><i> - </i></b> " + s6;
					stat.page(req, resp, s.replace("\r\n", "<br>"));
					return;
				}

			}

			// ///////////////////////////////////////////
			//
			// вопрос без кто или что в начале - хелп
			//
			// ///////////////////////////////////////////

			else {
				s = "Не понял вопроса: ожидал в начале вопроса слова Кто Что Кого Чего (см. описание КРЯ)";
				stat.page(req, resp, s.replace("\r\n", "<br>"));
				return;
			}*/
		}
	}

	public static String get_ans(String sh, String s) {

		s = s.replace("?", "");
		String[] ss = s.split("[ ]+");
		int i = ss.length;

		boolean bb = ss[0].toLowerCase().equals("кто")
				|| ss[0].toLowerCase().equals("что")
				|| ss[0].toLowerCase().equals("кого");

		if (bb) {

			// ///////////////////////////////////////////
			//
			// вопрос из 2-х слов
			//
			// ///////////////////////////////////////////

			if (i == 2) {
				OntModel mm = ModelFactory
						.createOntologyModel(PelletReasonerFactory.THE_SPEC);

				mm.read(sh + "/owl");

				s = stat.spref
				// +"SELECT ?кто  WHERE {?кто qq:любит qq:Миша}";
				// +"SELECT ?кто  WHERE {qq:Маша qq:любит ?кто}";
				// +"SELECT ?кто  WHERE {?кто qq:"+ss[1]+" qq:"+ss[2]+"}";

						+ "SELECT ?кто  WHERE {qq:" + ss[1] + " rdf:type ?кто}";

				Query qq = QueryFactory.create(s);
				ResultSet r = SparqlDLExecutionFactory.create(qq, mm)
						.execSelect();
				s = "";
				while (r.hasNext())
					s = s + r.next().toString();
				s = s.replace("http://owl.feofan.com/rff?83.owl#", "");

				s = s.replace("[Root]", "").trim();
				s = s.replace(">", "").trim();
				s = s.replace("<", "").trim();
				s = s.replace("-", "").trim();
				s = s.replace("(", "").trim();
				s = s.replace(")", "").trim();
				s = s.replace("=", "").trim();
				s = s.replace("?кто", "").trim();
				s = s.replace("owl:Thing", "").trim();

				String[] ss2 = s.split("[ ]+");
				i = ss.length;
				s = "";
				int n = 0;

				while (n < i)
					s = s + ss2[n++] + ", ";

				n = s.lastIndexOf(",");
				if (n > 0)
					s = s.substring(0, n);

				s = ss[1] + " - " + s;

			} else
			// ///////////////////////////////////////////
			//
			// вопрос из 3-х слов
			//
			// ///////////////////////////////////////////

			if (i == 3) {
				OntModel mm = ModelFactory
						.createOntologyModel(PelletReasonerFactory.THE_SPEC);

				mm.read(sh + "/owl");

				s = stat.spref
						// +"SELECT ?кто  WHERE {?кто qq:любит qq:Миша}";
						// +"SELECT ?кто  WHERE {qq:Маша qq:любит ?кто}";
						+ "SELECT ?кто  WHERE {?кто qq:" + ss[1] + " qq:"
						+ ss[2] + "}";

				Query qq = QueryFactory.create(s);
				ResultSet r = SparqlDLExecutionFactory.create(qq, mm)
						.execSelect();
				s = "";
				while (r.hasNext())
					s = s + r.next().toString();

				s = s.replace("( ?кто = <http://owl.feofan.com/rff?83.owl#", "")
						.replace("> ) -> [Root]", "");

			} else
				s = "Пока что вопрос должен начинается со слов Кто, Что, Кого и состоять из 2-х или 3-х слов.";
		}

		return s;

	}

	public void onModuleLoad() {
	}

	private static final long serialVersionUID = 1L;
}