package com.rockfeller.servlet;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {

	public static Connection conn;

	/**
	 * This method is used for returning connection to the database
	 * 
	 * @return db connection
	 */
	public Connection getConnection() {

		try {

			Class.forName("com.mysql.jdbc.Driver");  //Why do we need this line?  //load the driver
			System.out.println("Connecting to database...");
			conn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/mywebsite", "root", "root");
			System.out.println("Lorum Ipsum");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException se) {
			se.printStackTrace();
		}
		return conn;
	}

}