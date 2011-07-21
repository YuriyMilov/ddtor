package gu.server;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import gu.shta;

import gu.client.Srv;

import com.google.appengine.api.users.User;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

@SuppressWarnings("serial")
public class SrvImpl extends RemoteServiceServlet implements Srv {

	public String get(String s, String s2) throws IllegalArgumentException {

		s = (String) getThreadLocalRequest().getSession().getAttribute("name");
		if(s==null)
			s="qqq";

		//System.out.println(s);

		User uu = new User(s + "@quicklydone.com", "quicklydone.com");
		return uu.getNickname();
	}

	public String set_id(String name, String psw)
			throws IllegalArgumentException {

		
		String s2 = "";
		String s = shta.rff("3.txt");
		//System.out.println("name + psw: "+name + " " + psw);
		if (s.indexOf(name + " " + psw) > -1) {
			s = s.substring(s.indexOf(name + " " + psw));
			s = s.substring(0, s.indexOf("\r"));
			s = s.substring(s.lastIndexOf(" ") + 1);
			s2 = "<table border=0 cellspacing=0 cellpadding=0 width=100%><tr width=100% ><td align=right>&nbsp;<b>"
					+ name
					+ "</b> | <a href=Gu.html>Sign out</a>&nbsp;&nbsp;</td></tr></table>";
		
			//s="";
			} else
			s2 = "qqq";

		getThreadLocalRequest().getSession().setAttribute("name", name);
		User uu = new User(name + "@quicklydone.com", "quicklydone.com");

		//System.out.println("s2: "+s2);

		return s2;
	}
}
