package db;

import java.sql.*;

public class JDBConnection {
	/**
	 * This class contains the connectivity to an SQL database using JDBC
	 */
	
	private String database = "";
	private String username = "";
	private String password = "";
	public Connection conn = null;
	public Connection generateConnection() {
		/**
		 * Generates a JDBC connection to the database.
		 * 
		 *  @return java.sql.Connection connection
		 */
		try {
			this.conn = DriverManager.getConnection(this.database, this.username, this.password);
		} catch(Exception e) {
			this.conn = null;
		}
		return this.conn;
	}
}
