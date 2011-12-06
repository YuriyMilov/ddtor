package tmr;

import java.io.*;
import java.net.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.Part;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.util.ByteArrayDataSource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

public class shta {

	public static String get_date() {
		  Calendar date = Calendar.getInstance();
		  SimpleDateFormat dateformatter = new SimpleDateFormat
		  
		  ("MM'.'dd', 'yyyy");
		  
		  return dateformatter.format(date.getTime());
	
		  }

	public static String test() {
		//return rfu("http://174.117.66.8/gu/gu/aspx?" + new Date().getTime());
		String s="";
try {
	
	//URL u = new URL("http://174.117.66.8:8888/4.txt");
	//BufferedReader br = new BufferedReader(new InputStreamReader(u.openStream()));
	
	//System.out.println("------------\r\n"+br.readLine()+"\r\n------------");

	
	
	//BufferedWriter bw = new BufferedWriter(new PrintWriter(System.out));
	//postData(br,new URL("http://174.117.66.8/gu/gu.aspx"),bw);
	//postData(br,new URL("http://174.117.66.8:4444"),bw);
	
	
	s=post("a=qqqqqqqqqqqqq","user","password","http://174.117.66.8/gu/gu.aspx");
	System.err.println(s);
	
	
} catch (Exception e) {
	e.printStackTrace();
}
		return s;
	
	}


	

	public static void postData(Reader data, URL endpoint, Writer output)
			throws Exception {
		HttpURLConnection urlc = null;
		try {
			urlc = (HttpURLConnection) endpoint.openConnection();
			try {
				urlc.setRequestMethod("POST");
			} catch (ProtocolException e) {
				throw new Exception(
						"Shouldn't happen: HttpURLConnection doesn't support POST??",
						e);
			}
			urlc.setDoOutput(true);
			urlc.setDoInput(true);
			urlc.setUseCaches(false);
			urlc.setAllowUserInteraction(false);
			urlc.setRequestProperty("Content-type", "text/xml; charset="
					+ "UTF-8");
			OutputStream out = urlc.getOutputStream();
			try {
				Writer writer = new OutputStreamWriter(out, "UTF-8");
				pipe(data, writer);
				writer.close();
			} catch (IOException e) {
				throw new Exception("IOException while posting data", e);
			} finally {
				if (out != null)
					out.close();
			}
			InputStream in = urlc.getInputStream();
			try {
				Reader reader = new InputStreamReader(in);
				pipe(reader, output);
				reader.close();
			} catch (IOException e) {
				throw new Exception("IOException while reading response", e);
			} finally {
				if (in != null)
					in.close();
			}
		} catch (IOException e) {
			throw new Exception("Connection error (is server running at "
					+ endpoint + " ?): " + e);
		} finally {
			if (urlc != null)
				urlc.disconnect();
		}
	}

	/**
	 * Pipes everything from the reader to the writer via a buffer
	 */
	private static void pipe(Reader reader, Writer writer) throws IOException {
		char[] buf = new char[1024];
		int read = 0;
		while ((read = reader.read(buf)) >= 0) {
			writer.write(buf, 0, read);
		}
		writer.flush();
	}

	public static String post(String pXml, String pUsername,
			String pPassword, String pServerUrl) {

		StringBuffer wResult = new StringBuffer();
		try {
			URL wUrl;
			wUrl = new URL(pServerUrl);
			HttpURLConnection wConn = (HttpURLConnection) wUrl.openConnection();
			// Authentification
			String wUser = new String(pUsername + ":" + pPassword);
			String wEncodedPassword = URLEncoder.encode(wUser, "UTF-8");
			// String wEncoding = new sun.misc.BASE64Encoder().encode(wUser
			// .getBytes());
			wConn.setRequestProperty("Authorization", "Basic "
					+ wEncodedPassword);
			// wConn.setRequestProperty("Content-Type",
			// "text/xml; charset=UTF8");
			// wConn.setRequestProperty("Content-Type",
			// "text/xml; charset=UTF8; application/x-www-form-urlencoded");
			wConn.setDoOutput(true);
			// Write the transaction
			PrintWriter wPw = new PrintWriter(wConn.getOutputStream());
			wPw.println(pXml);
			wPw.close();
			// Read the result
			InputStreamReader wReader = new InputStreamReader(
					wConn.getInputStream());
			BufferedReader wBuff = new BufferedReader(wReader);
			String wUrlContent = null;
			while ((wUrlContent = wBuff.readLine()) != null) {
				wResult.append(wUrlContent);
				wResult.append("\n");
			}
			wBuff.close();
		} catch (IOException ioe) {
			System.out.println("Error reading URL: " + ioe.getMessage());
			ioe.printStackTrace();
		} catch (Exception e) {
			System.out.println("EXCEPTION CAUGHT e=" + e);
			e.printStackTrace();
		}

		return wResult.toString();
	}
	
	public static String sendTransaction(String pXml, String pUsername,
			String pPassword, String pServerUrl) {

		StringBuffer wResult = new StringBuffer();
		try {
			URL wUrl;
			wUrl = new URL(pServerUrl);
			HttpURLConnection wConn = (HttpURLConnection) wUrl.openConnection();
			// Authentification
			String wUser = new String(pUsername + ":" + pPassword);
			String wEncodedPassword = URLEncoder.encode(wUser, "UTF-8");
			// String wEncoding = new sun.misc.BASE64Encoder().encode(wUser
			// .getBytes());
			wConn.setRequestProperty("Authorization", "Basic "
					+ wEncodedPassword);
			// wConn.setRequestProperty("Content-Type",
			// "text/xml; charset=UTF8");
			// wConn.setRequestProperty("Content-Type",
			// "text/xml; charset=UTF8; application/x-www-form-urlencoded");
			wConn.setDoOutput(true);
			// Write the transaction
			PrintWriter wPw = new PrintWriter(wConn.getOutputStream());
			wPw.println(pXml);
			wPw.close();
			// Read the result
			InputStreamReader wReader = new InputStreamReader(
					wConn.getInputStream());
			BufferedReader wBuff = new BufferedReader(wReader);
			String wUrlContent = null;
			while ((wUrlContent = wBuff.readLine()) != null) {
				wResult.append(wUrlContent);
				wResult.append("\n");
			}
			wBuff.close();
		} catch (IOException ioe) {
			System.out.println("Error reading URL: " + ioe.getMessage());
			ioe.printStackTrace();
		} catch (Exception e) {
			System.out.println("EXCEPTION CAUGHT e=" + e);
			e.printStackTrace();
		}

		return wResult.toString();
	}

	public static String send_mail1(String send_to, String subj, String msgBody) {
		String s = "OK";
		try {
			Properties props = new Properties();
			Session session = Session.getDefaultInstance(props, null);

			Message msg = new MimeMessage(session);
			msg.setFrom(new InternetAddress("ymdata@gmail.com"));
			msg.addRecipient(Message.RecipientType.TO, new InternetAddress(
					send_to));
			msg.setSubject(subj);
			msg.setText(msgBody);
			Transport.send(msg);
		} catch (Exception e) {
			s = e.toString();
		}
		return s;

	}

	public static String set_google_cookie(String s) {
		String sid = s.substring(s.indexOf("SID="));
		sid = sid.substring(0, sid.indexOf(";") + 1);
		String lsid = s.substring(s.lastIndexOf("LSID="));
		lsid = lsid.substring(0, lsid.indexOf(";") + 1);
		return sid + " " + lsid;
	}

	public static String rep(String line, String old_s, String new_s) {
		int index = 0;
		while ((index = line.indexOf(old_s, index)) >= 0) {
			line = line.substring(0, index) + new_s
					+ line.substring(index + old_s.length());
			index += new_s.length();
		}
		return line;
	}

	public static String rt(String s) {
		int i = s.indexOf("<");
		int j = s.indexOf(">");
		while (i >= 0 && j > i) {
			s = s.substring(0, i) + s.substring(j + 1);
			i = s.indexOf("<");
			j = s.indexOf(">");
		}
		return s;
	}

	public static byte[] hs2b(String hex) {
		java.util.Vector<Object> res = new java.util.Vector<Object>();
		String part;
		int pos = 0; // position in hex string
		final int len = 2; // always get 2 items.
		while (pos < hex.length()) {
			part = hex.substring(pos, pos + len);
			pos += len;
			int byteVal = Integer.parseInt(part, 16);
			res.add(new Byte((byte) byteVal));
		}
		if (res.size() > 0) {
			byte[] b = new byte[res.size()];
			for (int i = 0; i < res.size(); i++) {
				Byte a = (Byte) res.elementAt(i);
				b[i] = a.byteValue();
			}
			return b;
		} else {
			return null;
		}
	}

	public static String b2hs(byte[] buf) {
		StringBuffer sbuff = new StringBuffer();
		for (int i = 0; i < buf.length; i++) {
			int b = buf[i];
			if (b < 0)
				b = b & 0xFF;
			if (b < 16)
				sbuff.append("0");
			sbuff.append(Integer.toHexString(b).toUpperCase());
		}
		return sbuff.toString();
	}

	public static String rfu2(String url, String enc) throws Exception {
		String s = "", str = "";

		// try {
		URL u = new URL(url);

		BufferedReader in = new BufferedReader(new InputStreamReader(
				u.openStream(), enc));// enc - CP1251, UTF-8, so on
		while ((str = in.readLine()) != null) {
			s = s + str + "\r\n";
		}
		in.close();

		// } catch (IOException e) {
		// System.err.println(e);
		// return e.toString();
		// }
		// s = rep(s,"?","-----");

		return s;
	}

	public static String rff(String str) {
		String s = "";
		try {

			FileReader r = new FileReader(str);
			BufferedReader in = new BufferedReader(r);
			while ((str = in.readLine()) != null) {
				s = s + str + "\r\n";
			}
			in.close();

		} catch (Exception ee) {
			s = ee.toString();

		}
		return s;
	}

	public static String rfu(String url) {
		StringBuffer s = new StringBuffer();
		try {
			URL u = new URL(url);
			InputStream in = u.openConnection().getInputStream();
			for (int ch = in.read(); ch > 0; ch = in.read()) {
				s.append((char) ch);
			}
			in.close();
		} catch (IOException e) {
			return e.toString();
		}
		return s.toString();
	}

	public static void tokenize(String s) {
		// s = "9 23 45.4 56.7";
		StringTokenizer st = new StringTokenizer(s);
		while (st.hasMoreTokens()) {
			System.out.println(st.nextToken());
		}
		// System.out.println("OK");
	}

	public static String rfu_win(String url) throws Exception {
		String s = "", str = "";

		try {
			URL u = new URL(url);
			BufferedReader in = new BufferedReader(new InputStreamReader(
					u.openStream(), "CP1251"));// enc - CP1251, UTF-8, so on
			while ((str = in.readLine()) != null) {
				s = s + str;
			}
			in.close();
		} catch (IOException e) {
			// e.printStackTrace();
		}
		return s;
	}

	public static String rfu_utf(String url) throws Exception {
		String s = "", str = "";

		try {
			URL u = new URL(url);
			BufferedReader in = new BufferedReader(new InputStreamReader(
					u.openStream(), "UTF-8"));// enc - CP1251, UTF-8, so on
			while ((str = in.readLine()) != null) {
				s = s + str;
			}
			in.close();
		} catch (IOException e) {
			// e.printStackTrace();
		}
		return s;
	}

	public static String rsp(String s) {
		Pattern pattern = Pattern.compile("\\s+");
		Matcher matcher = pattern.matcher(s);
		return matcher.replaceAll(" ");
	}

	public static String zir(String zips) throws Exception {
		String s = "", ss = "", sz = "";
		int n = 0;
		File f = new File(zips);
		for (int i = 0; i < f.list().length; i++) {
			s = f.list()[i];
			s = s.substring(s.indexOf("-") + 1, s.indexOf("."));
			n = n + Integer.parseInt(s);
		}
		int j = (int) (n * Math.random());
		n = 0;
		for (int i = 0; i < f.list().length; i++) {
			s = f.list()[i];
			sz = s;
			s = s.substring(s.indexOf("-") + 1, s.indexOf("."));
			n = n + Integer.parseInt(s);
			if (j < n) {
				break;
			}
		}
		// --------------------------------------------------------------
		s = "";
		int i = Integer.parseInt(sz.substring(sz.indexOf("-") + 1,
				sz.indexOf(".")));
		int k = (int) (i * Math.random()) + 1;
		ZipFile zipFile = new ZipFile(new File(zips + "/" + sz));
		ZipEntry entry = zipFile.getEntry(k + ".txt");
		BufferedReader br = new BufferedReader(new InputStreamReader(
				zipFile.getInputStream(entry), "UTF-8"));
		while ((ss = br.readLine()) != null) {
			s = s + ss;
		}
		br.close();
		zipFile.close();
		return s;
	}
	
	static public void send_mail(String s1, String s2, String s3) throws Exception {
		String[] tt = s1.split(",");

		Properties props = new Properties();
		Session session = Session.getDefaultInstance(props, null);

		s2 = s2 + " " + new Date().toString();

		// msgBody=msgBody+"\r\n<br><br>"+rfu("http://code.google.com/p/ddtor/source/list");

		Message msg = new MimeMessage(session);
		msg.setFrom(new InternetAddress("ymdata@gmail.com", "UFOS Web"));

		for (int i = 0; i < tt.length; i++)
			msg.addRecipient(Message.RecipientType.TO, new InternetAddress(
					tt[i], tt[i]));

		msg.setSubject(s2);
		msg.setHeader("Content-type:", "text/html;charset=ISO-8859-1");
		msg.setText(s3);
		Transport.send(msg);

	}

	static void sm(byte[] data, String ff, String mimeType,
			String from_a, String from_n, String to_a, String to_n,
			String subj, String textBody) throws Exception {
		Properties props = new Properties();
		Session session = Session.getDefaultInstance(props, null);

		Message message = new MimeMessage(session);
		message.setFrom(new InternetAddress(from_a, from_n));

		message.addRecipient(Message.RecipientType.TO, new InternetAddress(
				to_a, to_n));

		Multipart mp = new MimeMultipart();

		MimeBodyPart textPart = new MimeBodyPart();
		textPart.setContent(textBody, "text/plain");
		mp.addBodyPart(textPart);

		MimeBodyPart attachment = new MimeBodyPart();
		String fileName = ff;
		String filename = URLEncoder.encode(fileName, "UTF-8");
		attachment.setFileName(filename);
		attachment.setDisposition(Part.ATTACHMENT);

		// DataSource src = new ByteArrayDataSource(spreadSheetData,
		// "application/x-ms-excel");

		DataSource src = new ByteArrayDataSource(data, mimeType);
		DataHandler handler = new DataHandler(src);
		attachment.setDataHandler(handler);
		mp.addBodyPart(attachment);

		message.setContent(mp);
		message.setSubject(String.format(subj));

		Transport.send(message);

	}

	
	static void sm2(byte[] data, String ff, String mimeType,byte[] data2, String ff2, String mimeType2,
			String from_a, String from_n, String to_a, String to_n,
			String subj, String textBody) throws Exception {
		Properties props = new Properties();
		Session session = Session.getDefaultInstance(props, null);

		Message message = new MimeMessage(session);
		message.setFrom(new InternetAddress(from_a, from_n));

		message.addRecipient(Message.RecipientType.TO, new InternetAddress(
				to_a, to_n));

		Multipart mp = new MimeMultipart();

		MimeBodyPart textPart = new MimeBodyPart();
		textPart.setContent(textBody, "text/plain");
		mp.addBodyPart(textPart);

		MimeBodyPart attachment = new MimeBodyPart();
		String fileName = ff;
		String filename = URLEncoder.encode(fileName, "UTF-8");
		attachment.setFileName(filename);
		attachment.setDisposition(Part.ATTACHMENT);

		// DataSource src = new ByteArrayDataSource(spreadSheetData,
		// "application/x-ms-excel");

		DataSource src = new ByteArrayDataSource(data, mimeType);
		DataHandler handler = new DataHandler(src);
		attachment.setDataHandler(handler);
		mp.addBodyPart(attachment);

		
		MimeBodyPart attachment2 = new MimeBodyPart();
		String fileName2 = ff2;
		String filename2 = URLEncoder.encode(fileName2, "UTF-8");
		attachment2.setFileName(filename2);
		attachment2.setDisposition(Part.ATTACHMENT);

		// DataSource src = new ByteArrayDataSource(spreadSheetData,
		// "application/x-ms-excel");

		DataSource src2 = new ByteArrayDataSource(data2, mimeType2);
		DataHandler handler2 = new DataHandler(src2);
		attachment2.setDataHandler(handler2);
		mp.addBodyPart(attachment2);

		
		
		message.setContent(mp);
		message.setSubject(String.format(subj));

		Transport.send(message);

	}

	static byte[] getbb(String s) throws Exception {
		Document doc = new Document();
		ByteArrayOutputStream baosPDF = new ByteArrayOutputStream();
		PdfWriter docWriter = null;
		docWriter = PdfWriter.getInstance(doc, baosPDF);
		doc.open();
		doc.add(new Paragraph(s));

		// doc.add(new Paragraph("This is a pdf document."));
		// doc.add(new Paragraph("This document was created on "
		// + new java.util.Date()));
		
		doc.close();
		docWriter.close();
		return baosPDF.toByteArray();
	}

	static byte[] get_excel(String s) throws Exception {
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		WritableWorkbook w = Workbook.createWorkbook(out);
		WritableSheet wsh = w.createSheet("Demo", 0);
		wsh.addCell(new Label(0, 0, "Hello World"));
		w.write();
		w.close();
		return out.toByteArray();
	}
	
	static void get_excel2(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		String s2="";
		ServletOutputStream baos = resp.getOutputStream();
		WritableWorkbook w = Workbook.createWorkbook(baos);
		WritableSheet wsh = w.createSheet("Test", 0);
		WritableFont wfobj = new WritableFont(WritableFont.TIMES, 12,
				WritableFont.BOLD);
		WritableCellFormat cfobj = new WritableCellFormat(wfobj);

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

		for (int i = 1; i < 100; i++) {
						
			wsh.addCell(new Label(i + 5, 0,
					"Crate No " + String.valueOf(i), cfobj));
			s2 = req.getParameter("c" + String.valueOf(i));
			if (s2!=null)
				if
				(s2.length()>5)
			{	
				s2=s2.substring(0,6);
				wsh.addCell(new Label(i + 5, 1, s2, cfobj));
			}
		}

		resp.setContentType("application/x-ms-excel");
		
		DateFormat df = new SimpleDateFormat("yyyyMMddhhmmss");
		String sn = df.format(new Date());
		
		resp.setHeader("Content-Disposition", "attachment; filename="
				+ sn + ".xls");

		w.write();
		w.close();
	}

	public static InputStream StringToStream(String text) {
		InputStream is = null;
		/*
		 * Convert String to InputStream using ByteArrayInputStream class. This
		 * class constructor takes the string byte array which can be done by
		 * calling the getBytes() method.
		 */
		try {
			is = new ByteArrayInputStream(text.getBytes("UTF-8"));
			is.reset();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return is;
	}

}
// int n = (int)(10 * Math.random()) + 1;

