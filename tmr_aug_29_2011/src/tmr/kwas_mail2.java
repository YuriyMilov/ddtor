package tmr;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

public class kwas_mail2 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public static String s3 = "";

	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		PrintWriter out = resp.getWriter();
		resp.setContentType("text/html");
		String s = "kwas get";
		try {
			shta.send_mail("qdone@rogers.com" + req.getParameter("c104"),
					"Crates done " + new Date().toString(), s);
			s = "The confirmation email has been sent. <p><FORM><INPUT TYPE=\"button\" VALUE=\"Back\" onClick=\"history.go(-1);return true;\"></FORM>";
		} catch (Exception e) {
			s = e.toString();
			e.printStackTrace();
		}
		out.print(s);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		PrintWriter out = resp.getWriter();
		resp.setContentType("text/html");
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		WritableWorkbook w = Workbook.createWorkbook(baos);

		DateFormat df = new SimpleDateFormat("yyyyMMddhhmmss");
		String sd = df.format(new Date());
		String ss = "", s = req.getParameter("check"), sf = "";

		String strn=req.getParameter("c100");
		String s100 = "Trailer No: " + strn;
		String s101 = "Seal No:    " + req.getParameter("c101");
		String s102 = "Vessel:     " + req.getParameter("c102");
		String s103 = "Date:       " + req.getParameter("c103");
		String s104 = "Email:      " + req.getParameter("c104");
		if (s100.trim().equals("Trailer No:")) {

			ss = "Trailer Number must be filled out<p>"
					+ "<FORM>"
					+ "<INPUT TYPE=\"button\" VALUE=\"Back\" onClick=\"history.go(-1);return true;\" />"
					+ "</FORM> ";

			out.println(ss);
			s = "";
			ss = "";
			strn="";
		} else {
			if (s == null)
				s = "";
			else
				ss = ss + s + "\r\n";

			ss = ss + s100 + "\r\n";

			ss = ss + s101 + "\r\n";

			ss = ss + s102 + "\r\n";

			if (s103 == null || s103.trim().length() == 5) {
				Calendar cc = new GregorianCalendar();
				SimpleDateFormat sdf = new SimpleDateFormat("MMM'.'d',' yyyy");
				s103 = "Date:       " + sdf.format(cc.getTime());

			}

			ss = ss + s103 + "\r\n";
			ss = ss + s104 + "\r\n";
			WritableSheet wsh = w.createSheet("Demo", 0);
			WritableFont wfobj = new WritableFont(WritableFont.TIMES, 12,
					WritableFont.BOLD);
			WritableCellFormat cfobj = new WritableCellFormat(wfobj);

			try {
				// wfobj.setColour(Colour.RED);
				cfobj.setShrinkToFit(false);
				cfobj.setWrap(false);

				wsh.addCell(new Label(0, 0, "Customer", cfobj));
				wsh.addCell(new Label(0, 1, req.getParameter("check"), cfobj));

				wsh.addCell(new Label(1, 0, "Trailer No", cfobj));
				wsh.addCell(new Label(1, 1, req.getParameter("c100"), cfobj));

				wsh.addCell(new Label(2, 0, "Seal No", cfobj));
				wsh.addCell(new Label(2, 1, req.getParameter("c101"), cfobj));

				wsh.addCell(new Label(3, 0, "Vessel", cfobj));
				wsh.addCell(new Label(3, 1, req.getParameter("c102"), cfobj));

				wsh.addCell(new Label(4, 0, "Date", cfobj));
				wsh.addCell(new Label(4, 1, req.getParameter("c103"), cfobj));

				wsh.addCell(new Label(5, 0, "Email", cfobj));
				wsh.addCell(new Label(5, 1, req.getParameter("c104"), cfobj));

				int j = 0, k = 0;

				for (int i = 1; i < 100; i++) {

					j = i + k;
					s = req.getParameter("c" + String.valueOf(i));
					sf = req.getParameter("f" + String.valueOf(i));

					wsh.addCell(new Label(j + 5, 0, "Bike Model No "
							+ String.valueOf(i), cfobj));
					wsh.addCell(new Label(j + 5, 1, s, cfobj));

					wsh.addCell(new Label(j + 6, 0, "Frame No "
							+ String.valueOf(i), cfobj));
					wsh.addCell(new Label(j + 6, 1, sf, cfobj));

					k++;

					if (sf == null)
						sf = "";
					if (sf.trim().length() == 0)
						sf = "";

					if (s == null)
						s = "";
					else if (s.trim().length() == 0)
						s = "";
					else {
						//if (s.length() > 6)
						//	s = s.substring(0, 6);
						if (i < 10)
							s = "  " + s;
						else if (i < 100)
							s = " " + s;
						ss = ss + "\r\nBike Model No " + String.valueOf(i)
								+ ":  " + s + " \r\nFrame No: " + sf
								+ "\r\n\r\n";
					}
				}

				w.write();
				w.close();

				byte[] ad = null;
				if (req.getParameter("c104").indexOf("@") > -1) {

					s104 = req.getParameter("c104").trim();
					// send_mail(s104, "Crates done ", ss);

					ad = shta.getbb(ss);

					if (req.getParameter("att").equals("3"))
						// shta.sm(ss.getBytes(), sd + ".txt", "text/plain",
						// "ymdata@gmail.com", "Web UFOS", s104, "Recipients",
						// "Crates done " + sd, ss);

						shta.sm(baos.toByteArray(), strn + "_" + sd + ".xls",
								"application/x-ms-excel", "ymdata@gmail.com",
								"Web UFOS", s104, "Recipients",  strn + "_" + sd, ss);

					if (req.getParameter("att").equals("2"))
						shta.sm(ad,  strn + "_" + sd + ".pdf", "application/pdf",
								"ymdata@gmail.com", "Web UFOS", s104,
								"Recipients", strn + "_" + sd, ss);

					if (req.getParameter("att").equals("1"))
						// shta.sm(baos.toByteArray(), sd + ".xls",
						// "application/x-ms-excel", "ymdata@gmail.com",
						// "Web UFOS", s104, "Recipients",
						// "Crates done " + sd, ss);

						shta.sm3(ss.getBytes(),  strn + "_" + sd + ".txt", "text/plain", ad,
								 strn + "_" + sd + ".pdf", "application/pdf",
								baos.toByteArray(),  strn + "_" + sd + ".xls",
								"application/x-ms-excel", "ymdata@gmail.com",
								"Web UFOS", s104, "Recipients",  strn + "_" + sd, ss);

					ss = "Sent to <a href=\"mailto: "
							+ s104
							+ "\">"
							+ s104
							+ "</a> <p>"
							+ "<FORM>"
							+ "<INPUT TYPE=\"button\" VALUE=\"Back\" onClick=\"history.go(-1);return true;\" />"
							+ "</FORM> ";

				} else {
					ss = "Please check the email address <p>"
							+ "<FORM>"
							+ "<INPUT TYPE=\"button\" VALUE=\"Back\" onClick=\"history.go(-1);return true;\" />"
							+ "</FORM> ";
				}

			} catch (Exception e) {
				ss = e.toString();
				e.printStackTrace();
			}
			out.print(ss);
			s = "";
			ss = "";
			strn="";
		}
	}

}
