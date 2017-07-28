package examnation.webservice;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class oracle {
	public static void main(String[] args) {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			String url = "jdbc:oracle:thin:pm@10.96.33.136:1521/pm"; // orcl为数据库的SID
			String user = "pm";
			String password = "HNpm!23$";
			Connection conn = DriverManager.getConnection(url, user, password);
			Statement stmtNew = conn.createStatement();
			boolean flag = stmtNew.execute("select sysdate from dual");
			System.out.println(flag);
		} catch (Exception e) {

			System.out.println(e);
		}

	}

}
