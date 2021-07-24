package db;

import java.sql.*;

public class JDBConnection{
	/**
	 * This class contains the connectivity to an SQL database using JDBC
	 */
	private String serveraddr = "";
	private String database = "";
	private String username = "";
	private String password = "";
	public Connection conn = null;
	public Connection generateServerConnection() {
		/**
		 * Generates a JDBC connection directly to the entire web server.
		 * 
		 *  @return java.sql.Connection connection
		 */
		try {
			this.conn = DriverManager.getConnection(this.serveraddr, this.username, this.password);
		} catch(Exception e) {
			this.conn = null;
			e.printStackTrace();
		}
		return this.conn;
	}
	public Connection generateDBConnection() {
		/**
		 * Generates a JDBC connection to a database in the web server.
		 * 
		 *  @return java.sql.Connection connection
		 */
		try {
			this.conn = DriverManager.getConnection(this.serveraddr + this.database, this.username, this.password);
		} catch(Exception e) {
			this.conn = null;
			e.printStackTrace();
		}
		return this.conn;
	}
}
