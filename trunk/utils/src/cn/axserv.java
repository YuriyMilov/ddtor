package cn;

import java.io.*;
import java.util.GregorianCalendar;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class axserv extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		
		ServletOutputStream out = res.getOutputStream();
		res.setContentType("text/html");
		out.println("get method not supported");
		System.out.println("================================");
		System.out.println("get method not supported\r\n"+new java.util.GregorianCalendar().getTime().toString());
		System.out.println("================================");
		out.flush();
		out.close();
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res)
		
		throws ServletException, IOException {
			ServletOutputStream out = res.getOutputStream();
			res.setContentType("text/html");
			InputStream in = req.getInputStream();
			int i=in.read();
			String s="";
			while(i!=-1){
				s=s+(char)i;
				//System.out.print((char)i);
				i=in.read();
			}
			out.println("<?xml version=\"1.0\" encoding=\"ISO-8859-1\" standalone=\"yes\" ?>\r\n<Acknowledgment><Success></Success></Acknowledgment>");
			System.out.println(s);
			sta.wtf(s, "c:/"+new GregorianCalendar().getTimeInMillis()+".xml");
			System.out.println("================================");
			System.out.println(new java.util.GregorianCalendar().getTime().toString());
			System.out.println("================================");
			out.flush();
			out.close();
	}
}

