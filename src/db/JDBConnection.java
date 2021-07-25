package db;

import java.sql.*;

/**
 * This class contains the connection to an SQL database using JDBC
 * @author Burra Abhishek
 */
public class JDBConnection{
	private String serveraddr = "";
	private String database = "";
	private String username = "";
	private String password = "";
	public Connection conn = null;
	/**
	 * Generates a JDBC connection directly to the entire web server.
	 * 
	 *  @return java.sql.Connection connection, null if no connection
	 */
	public Connection generateServerConnection() {
		try {
			this.conn = DriverManager.getConnection(this.serveraddr, this.username, this.password);
		} catch(Exception e) {
			this.conn = null;
			e.printStackTrace();
		}
		return this.conn;
	}
	/**
	 * Generates a JDBC connection to a database in the web server.
	 * 
	 *  @return java.sql.Connection connection, null if no connection
	 */
	public Connection generateDBConnection() {
		try {
			this.conn = DriverManager.getConnection(this.serveraddr + this.database, this.username, this.password);
		} catch(Exception e) {
			this.conn = null;
			e.printStackTrace();
		}
		return this.conn;
	}
}
