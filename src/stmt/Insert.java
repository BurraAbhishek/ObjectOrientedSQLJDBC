package stmt;

import db.JDBCDriver;
import db.JDBConnection;

import java.sql.*;
import java.util.*;
import java.math.*;

public class Insert {
	private String table = "";
	private PreparedStatement stmt;
	private ArrayList<String> attributes = new ArrayList<String>();
	private ArrayList<String> datatypes = new ArrayList<String>();
	private ArrayList<Object> values = new ArrayList<Object>();
	public void insert() throws Exception {
		try {
			if(new JDBCDriver().isDriverSupported()) {
				int l = this.attributes.size();
				if(l != this.values.size() || l != this.datatypes.size()) {
					throw new Exception("One or more elements is missing");
				}
				Connection conn = new JDBConnection().generateDBConnection();
				if(conn == null) {
					throw new Exception("No connection to server");
				} else {
					String s = "INSERT INTO " + this.table + " (";
					for(int i = 0; i < l - 1; i++) {
						s += this.attributes.get(i);
						s += ", ";
					}
					s += this.attributes.get(l - 1);
					s += ") VALUES (";
					for(int i = 0; i < l - 1; i++) {
						s += "?, ";
					}
					s += "?)";
					this.stmt = conn.prepareStatement(s);
					for(int i = 0; i < l; i++) {
						String type = this.datatypes.get(i);
						switch(type) {
						case "String":
							this.stmt.setString(i+1, (String) this.values.get(i));
							break;
						case "NString":
							this.stmt.setNString(i+1, (String) this.values.get(i));
							break;
						case "Integer":
							this.stmt.setInt(i+1, (Integer) this.values.get(i));
							break;
						case "Double":
							this.stmt.setDouble(i+1, (Double) this.values.get(i));
							break;
						case "Array":
							this.stmt.setArray(i+1, (Array) this.values.get(i));
							break;
						case "Long":
							this.stmt.setLong(i+1, (Long) this.values.get(i));
							break;
						case "BigDecimal":
							this.stmt.setBigDecimal(i+1, (BigDecimal) this.values.get(i));
							break;
						case "Boolean":
							this.stmt.setBoolean(i+1, (Boolean) this.values.get(i));
							break;
						case "Byte":
							this.stmt.setByte(i+1, (Byte) this.values.get(i));
							break;
						case "Time":
							this.stmt.setTime(i+1, (Time) this.values.get(i));
							break;
						case "Timestamp":
							this.stmt.setTimestamp(i+1, (Timestamp) this.values.get(i));
							break;
						default:
							this.stmt.setObject(i+1, this.values.get(i));
							break;
						}
					}
					this.stmt.execute();
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	public Insert(String table, String[] attributes, String[] datatypes, Object[] values) {
		try {
			this.table = table;
			for(int i = 0; i < attributes.length; i++) {
				this.attributes.add(attributes[i]);
			}
			for(int i = 0; i < datatypes.length; i++) {
				this.datatypes.add(datatypes[i]);
			}
			for(int i = 0; i < values.length; i++) {
				this.values.add(values[i]);
			}
			insert();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
