package stmt;

import db.JDBCDriver;
import db.JDBConnection;

import java.sql.*;
import java.util.*;

/**
 * 
 * Creates a table in a given database.
 * @author Burra Abhishek
 *
 */
public class CreateTable {
	private String table = "";
	private PreparedStatement stmt;
	private ArrayList<String> attributes = new ArrayList<String>();
	protected void createTable() throws Exception {
		try {
			if(new JDBCDriver().isDriverSupported()) {
				Connection conn = new JDBConnection().generateDBConnection();
				if(conn == null) {
					throw new Exception("No connection to server");
				} else {
					int l = this.attributes.size();
					String s = "CREATE TABLE " + this.table + " (";
					for(int i = 0; i < l - 1; i++) {
						s += this.attributes.get(i);
						s += ", ";
					}
					s += this.attributes.get(l - 1);
					s += ")";
					this.stmt = conn.prepareStatement(s);
					try {
						this.stmt.execute();
					} catch(Exception e) {
						e.printStackTrace();
					}
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * Creates a table in the database
	 * 
	 * @param table The name of the table to be created
	 * @param attributes The list of attributes with data types and other properties. 
	 * For example, 
	 * "`id` TIMESTAMP on update CURRENT_TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP".
	 */
	public CreateTable(String table, String[] attributes) {
		try {
			this.table = table;
			for(int i = 0; i < attributes.length; i++) {
				this.attributes.add(attributes[i]);
			}
			this.createTable();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
