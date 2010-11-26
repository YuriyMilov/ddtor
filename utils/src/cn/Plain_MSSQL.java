package cn;

import java.sql.*;

public class Plain_MSSQL {

	public static void main(String[] args) throws Exception {
		Class.forName("com.microsoft.jdbc.sqlserver.SQLServerDriver");
		Connection con = DriverManager
				.getConnection(
						"jdbc:microsoft:sqlserver://quicklydone.com:1433;DatabaseName=qqdb",
						"sa", "");
		Statement st = con.createStatement();

		st.executeUpdate("INSERT INTO qqtbl VALUES('44','44')");
		st.executeUpdate("UPDATE qqtbl SET qqcn2=111 WHERE qqcn=111");
		st.executeUpdate("DELETE FROM qqtbl WHERE qqcn=44");
		st.executeUpdate("INSERT INTO qqtbl VALUES('44','new')");

		ResultSet rs = st.executeQuery("SELECT * FROM qqtbl");
		while (rs.next())
			System.out.println(rs.getString("qqcn")+rs.getString("qqcn2"));

		con.close();
	}
}
