package org.utilities.qa;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import org.base.qa.BaseTest;

public class DB_Utils {

	public static Connection con;
	public static Statement stmt;
	public static ResultSet rs;

	public static void connectionDb() {
		try {
			con = DriverManager.getConnection(BaseTest.prop.getProperty("dbUrl"), BaseTest.prop.getProperty("dbUser"),
					BaseTest.prop.getProperty("dbPassword"));

			stmt = con.createStatement();

			Log.info("PostgreSQL database is connected successfully.");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static ResultSet executeQuery(String query) {
		try {
			rs = stmt.executeQuery(query);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return rs;
	}

	public static void closeDb()
	{
		try {
			if(rs != null)
				rs.close();
			
			if(stmt != null)
				stmt.close();
			
			if(con != null)
				con.close();
			
			Log.info("Database connection closed.");
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}
