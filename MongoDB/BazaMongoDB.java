package alskdjfasdlkfj;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

public class BazaMongoDB {

	private final String url;
	private final String user;
	private final String pwd;

	private boolean connect = false;
	public Connection conn = null;

	private Statement stmt;

	public BazaMongoDB(String url, String user, String pwd) {

		this.url = url;
		this.user = user;
		this.pwd = pwd;

		try {
			conn = (Connection) DriverManager.getConnection(url, user, pwd);
			stmt = conn.createStatement();
			connect = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public ArrayList<String> getColumn(String table, String column) throws SQLException {

		ArrayList<String> result = new ArrayList<>();
		String query = "SELECT " + column + " FROM " + table;

		ResultSet res = stmt.executeQuery(query);
		
		while(res.next()) {
			String col_name = res.getString(column);
			result.add(col_name);
		}
		
		return result;
	}

	public HashMap<String, String> getColumn(String table, String column1, String column2) throws SQLException {

		HashMap<String, String> Fresult = new HashMap<>();
		String query = "SELECT " + column1 + ", " + column2 + " FROM " + table;

		ResultSet result = stmt.executeQuery(query);

		while (result.next()) {
			String col_name1 = result.getString(column1);
			String col_name2 = result.getString(column2);
			Fresult.put(col_name1, col_name2);
		}

		return Fresult;
	}

	public void tableInfo(String table) throws SQLException {

		String query = "SELECT * FROM " + table;
		ResultSet rs = stmt.executeQuery(query);

		ResultSetMetaData rsmd = rs.getMetaData();
		int count = rsmd.getColumnCount();
		for (int i = 1; i <= count; i++) {
			System.out.println(
					"---------------------" + "Kolumna " + i + "[" + rsmd.getColumnName(i) + "]---------------------");
			System.out.println("Size: " + rsmd.getColumnDisplaySize(i));
			System.out.println("Class: " + rsmd.getColumnClassName(i));
			System.out.println("Type: " + rsmd.getColumnType(i));
			System.out.println("TypeName: " + rsmd.getColumnTypeName(i));
			System.out.println("------------------------------------------");
		}
	}

	public boolean createDatabase(String name) throws SQLException {
		String query = "CREATE DATABASE " + name;
		try {
			stmt.executeUpdate(query);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean question(String query) throws SQLException {
		try {
			stmt.executeQuery(query);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public void showMetaData() throws SQLException {
		if (!this.connect)
			return;
		System.out.println("--------[META DATA]----------");
		try {
			DatabaseMetaData md = conn.getMetaData();
			System.out.println("Product Name: " + md.getDatabaseProductName());
			System.out.println("Version: " + md.getDatabaseProductVersion());
			System.out.println("Driver: " + md.getDriverName());
			System.out.println("URL: " + md.getURL());
			System.out.println("User name: " + md.getUserName());
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("--------[/META DATA]----------");
	}

}
