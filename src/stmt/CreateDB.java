package stmt;

import java.sql.Connection;
import java.sql.PreparedStatement;

import db.JDBCDriver;
import db.JDBConnection;

/**
 * 
 * This class contains methods to create an SQL database
 * @author Burra Abhishek
 */
public class CreateDB {
	private String database = "";
	private String charset = "utf8mb4";
	private String collate = "utf8mb4_general_ci";
	private PreparedStatement stmt;
	protected void createDatabase() throws Exception {
		try {
			if(new JDBCDriver().isDriverSupported()) {
				Connection conn = new JDBConnection().generateServerConnection();
				if(conn == null) {
					throw new Exception("No connection to server");
				} else {
					String s = "CREATE DATABASE IF NOT EXISTS ";
					s += this.database;
					s += " DEFAULT CHARACTER SET ? COLLATE ?";
					this.stmt = conn.prepareStatement(s);
					this.stmt.setString(1, this.charset);
					this.stmt.setString(2, this.collate);
					this.stmt.execute();
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 
	 * @param database The name of the database to which connection should be established
	 * @throws Exception
	 */
	public CreateDB(String database) throws Exception {
		this.database = database;
		this.createDatabase();
	}
	/**
	 * 
	 * @param database The name of the database to which connection should be established
	 * @param charset The character set of the database
	 * @param collate The collation (set of rules for comparing characters in a character set)
	 * @throws Exception
	 */
	public CreateDB(String database, String charset, String collate) throws Exception {
		this.database = database;
		this.charset = charset;
		this.collate = collate;
		this.createDatabase();
	}
}
