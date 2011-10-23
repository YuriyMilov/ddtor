package qq;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.HashSet;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.mail.Address;
import javax.mail.Flags;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.Part;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMultipart;

public class InboxReader {
	static HashSet<String> mails;

	public static void main(String args[]) {
		while (true) {
			try {
				System.out.println("Started.......");
				Start();
				System.out.println("...Read completed.......");

				try {
					Thread.sleep(1000 * 60 * 1);
				} catch (InterruptedException e1) {

				}

			} catch (Exception e) {

				try {
					connecttoMySql();

					e.printStackTrace();
					System.out.println("..Error in connection Sleeping...");

				} catch (Exception e1) {

				}
			}
		}

	}

	public static void Start() throws Exception {
		Properties props = System.getProperties();

		props.setProperty("mail.store.protocol", "imaps");
		try {
			Session session = Session.getDefaultInstance(props, null);
			Store store = session.getStore("imaps");
			store.connect("imap.gmail.com", "test@quicklydone.com", "89898999");
			System.out.println(store);
			int cout = 0;

			Folder inbox = store.getFolder("Inbox");
			inbox.open(Folder.READ_WRITE);
			Message messages[] = inbox.getMessages();
			for (Message message : messages) {
				mails = new HashSet<String>();
				System.out.println("Reading:" + (messages.length - cout));

				System.out.println(message.getSubject());

				cout++;
				InboxReader.storeAddresses(message);
				dumpPart(message);
				for (String temp : mails) {
					System.out.println(temp);

				}
				// connecttoMySql();
				message.setFlag(Flags.Flag.DELETED, true);
			}

		} catch (NoSuchProviderException e) {
			connecttoMySql();
			e.printStackTrace();

		} catch (MessagingException e) {
			// connecttoMySql();
			e.printStackTrace();

		}

	}

	public static void storeAddresses(Message msg) {
		try {
			for (Address adr : msg.getAllRecipients()) {
				addAddresses(adr.toString());

			}

		} catch (Exception e) {

			e.printStackTrace();
		}

	}

	public static void addAddresses(String input_text) {
		Pattern p = Pattern.compile(
				"[A-Z0-9\\._%\\+\\-]+@[A-Z0-9\\.\\-]+\\.[A-Z]{2,4}",
				Pattern.CASE_INSENSITIVE);

		Matcher m = p.matcher(input_text);
		while (m.find()) {
			mails.add(m.group());

		}

	}

	public static void dumpPart(Part p) throws Exception {

		if (p.isMimeType("text/plain")) {

			try {
				addAddresses((String) p.getContent());
			} catch (Exception e) {
			}
		} else {

			MimeMultipart mb = null;
			try {
				mb = (MimeMultipart) (p.getContent());

			} catch (Exception e) {
				try {
					if (p.getContent() instanceof String)

						addAddresses((String) p.getContent());
				} catch (Exception e1) {
				}
				return;
			}

			MimeBodyPart mb1 = (MimeBodyPart) mb.getBodyPart(0);
			mb1.saveFile("c:/emai_body.txt");
			BufferedReader br = new BufferedReader(new FileReader(
					"c:/emailtext.html"));
			StringBuffer content = new StringBuffer();
			String line = "";
			while ((line = br.readLine()) != null) {
				if (line.length() >= 2)
					if (line.substring(line.length() - 1).equals("=")) {
						content.append(line.substring(line.length() - 1));
					} else
						content.append(line + "\n");
			}
			addAddresses(content.toString());

		}
	}

	public static void connecttoMySql() {
		Connection conn = null;

		try {
			Properties details = new Properties();
			details.load(new FileInputStream("details.properties"));
			String userName = details.getProperty("username");
			String password = details.getProperty("password");
			String url = details.getProperty("url");
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			conn = DriverManager.getConnection(url, userName, password);
			System.out.println("Database connection established");
			PreparedStatement st = conn
					.prepareStatement("insert into `Email_list` values(?)");
			for (String mail : mails) {
				try {
					st.setString(1, mail);
					st.execute();
				} catch (Exception e) {
				}
			}

		} catch (Exception e) {
			System.err.println("Cannot connect to database server");
			e.printStackTrace();
		} finally {
			if (conn != null) {
				try {
					conn.close();
					System.out.println("Database connection terminated");
				} catch (Exception e) {
				}
			}
		}
	}

}