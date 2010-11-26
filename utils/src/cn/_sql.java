package cn;

import java.sql.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;


public class _sql {

	public static void main(String[] args) throws Exception {
		String s = sta.rff("ShippingInstruction.txt");

		
		System.out.println(s);

	}
	


	public static void sql() throws Exception {
		Class.forName("com.microsoft.jdbc.sqlserver.SQLServerDriver");
		Connection con = DriverManager.getConnection(
				// "jdbc:microsoft:sqlserver://quicklydone.com:1433;DatabaseName=qqdb",
				"jdbc:microsoft:sqlserver://127.0.0.1:1433;DatabaseName=qqdb",
				"sa", "");
		Statement st = con.createStatement();

		st.executeUpdate("INSERT INTO qqtbl VALUES('44','44')");
		st.executeUpdate("UPDATE qqtbl SET qqcn2=111 WHERE qqcn=111");
		st.executeUpdate("DELETE FROM qqtbl WHERE qqcn=44");
		st.executeUpdate("INSERT INTO qqtbl VALUES('44','new')");

		ResultSet rs = st.executeQuery("SELECT * FROM qqtbl");
		while (rs.next())
			System.out.println(rs.getString("qqcn") + rs.getString("qqcn2"));

		con.close();
	}
}
