package tmr;


import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.List;
import java.util.Properties;

import javax.jdo.PersistenceManager;
import javax.mail.Flags;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Store;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//import qq.InboxReader;

import tmr.server.PMF;

public class mail extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		doGet(req,resp);
	}

	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		PrintWriter out = resp.getWriter();
		String s = "";
/*
	       Properties props = System.getProperties();
	         
	        props.setProperty("mail.store.protocol", "imaps");
	            try {
	                Session session = Session.getDefaultInstance(props, null);
	                Store store = session.getStore("imaps");
	                store.connect("imap.gmail.com", "test@quicklydone.com", "89898999");
	                System.out.println(store);
	                int cout=0;
	 
	                Folder inbox = store.getFolder("Inbox");
	                inbox.open(Folder.READ_WRITE);
	                Message messages[] = inbox.getMessages();
	                for(Message message:messages) {
	                    mails=new HashSet<String>();
	                    System.out.println("Reading:"+ (messages.length-cout));
	                    cout++;
	                    InboxReader.storeAddresses(message);
	                dumpPart(message);
	                for(String temp:mails)
	                    System.out.println(temp);
	                //connecttoMySql();
	                message.setFlag(Flags.Flag.DELETED, true);
	            }	
		
		*/
		out.println(s);
	}
}