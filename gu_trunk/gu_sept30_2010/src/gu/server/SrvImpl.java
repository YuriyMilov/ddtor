package gu.server;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import gu.client.Srv;

import com.google.appengine.api.users.User;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

@SuppressWarnings("serial")
public class SrvImpl extends RemoteServiceServlet implements Srv {

	public String get(String s) throws IllegalArgumentException {
		
	s=(String) getThreadLocalRequest().getSession().getAttribute("name");
	User uu=new User(s+"@quicklydone.com","quicklydone.com");
	return "&nbsp;Id:-> "+uu.getNickname();
	//	return "2222222222222222222";
	}
	
	public String set_id(String name) throws IllegalArgumentException {
		if(name.equals(""))
			name="guest";
		getThreadLocalRequest().getSession().setAttribute("name", name);
		User uu=new User(name+"@quicklydone.com","quicklydone.com");
		return "<table border=0 cellspacing=0 cellpadding=0 width=100%><tr width=100% ><td align=right>&nbsp;<b>" + name + "</b> | <a href=Gu.html>Sign out</a>&nbsp;&nbsp;</td></tr></table>";
		 
		
	}
}
