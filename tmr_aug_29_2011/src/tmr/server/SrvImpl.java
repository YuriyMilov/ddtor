package tmr.server;

import java.util.List;
import javax.jdo.PersistenceManager;
import tmr.User2;
import tmr.client.Srv;
import com.google.appengine.api.users.User;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

@SuppressWarnings("serial")
public class SrvImpl extends RemoteServiceServlet implements Srv {

	public String get(String s, String s2) throws IllegalArgumentException {
		s = (String) getThreadLocalRequest().getSession().getAttribute("name");
		if (s == null)
			s = "qqq";
		User uu = new User(s + "@quicklydone.com", "quicklydone.com");
		return uu.getNickname();
	}

	public String set_id(String name, String psw)
			throws IllegalArgumentException {
		String s2 = "";
		PersistenceManager pm = PMF.get().getPersistenceManager();
		@SuppressWarnings("unchecked")
		List<User2> usrs = (List<User2>) pm.newQuery(
				"SELECT FROM " + User2.class.getName() + " WHERE name==\""
						+ name + "\" & password==\"" + psw + "\"").execute();
		if (usrs.size() > 0) {
			s2 = "<table border=0 cellspacing=0 cellpadding=0 width=100%><tr width=100% ><td align=right>&nbsp;<b>"
					+ name
					+ "</b> | <a href=Gu.html>Sign out</a>&nbsp;&nbsp;</td></tr></table>";
		} else
			s2 = "qqq";

		getThreadLocalRequest().getSession().setAttribute("name", name);
		return s2;
	}
}
