package stmt;

import db.JDBCDriver;
import db.JDBConnection;

import java.sql.*;
import java.util.*;
import java.math.*;

/**
 * Updates a given table in an SQL Database
 * 
 * @author Burra Abhishek
 *
 */
public class Update {
	private String table = "";
	private PreparedStatement stmt;
	private ArrayList<String> attributes = new ArrayList<String>();
	private ArrayList<String> datatypes = new ArrayList<String>();
	private ArrayList<String> constraintkeys = new ArrayList<String>();
	private ArrayList<String> constraintdatatypes = new ArrayList<String>();
	private ArrayList<Object> values = new ArrayList<Object>();
	private ArrayList<Object> constraintvalues = new ArrayList<Object>();

	protected void update() throws Exception {
		try {
			if (new JDBCDriver().isDriverSupported()) {
				int l1 = this.attributes.size();
				int l2 = this.constraintkeys.size();
				if (l1 != this.values.size() || l1 != this.datatypes.size()) {
					throw new Exception("One or more elements is missing");
				}
				if (l2 != this.constraintvalues.size() || l2 != this.constraintdatatypes.size()) {
					throw new Exception("One or more constraints is missing");
				}
				Connection conn = new JDBConnection().generateDBConnection();
				if (conn == null) {
					throw new Exception("No connection to server");
				} else {
					String s = "UPDATE " + this.table + " SET ";
					for (int i = 0; i < l1; i++) {
						s += this.attributes.get(i);
						s += " = ?";
						if(i < l1 - 1) {
							s += ", ";
						}
					}
					s += " WHERE ";
					for (int i = 0; i < l2; i++) {
						s += this.constraintkeys.get(i);
						s += " = ?";
						if(i < l2 - 1) {
							s += ", ";
						}
					}
					this.stmt = conn.prepareStatement(s);
					for (int i = 0; i < l1; i++) {
						String type = this.datatypes.get(i);
						switch (type) {
						case "String":
							this.stmt.setString(i + 1, (String) this.values.get(i));
							break;
						case "NString":
							this.stmt.setNString(i + 1, (String) this.values.get(i));
							break;
						case "Integer":
							this.stmt.setInt(i + 1, (Integer) this.values.get(i));
							break;
						case "Double":
							this.stmt.setDouble(i + 1, (Double) this.values.get(i));
							break;
						case "Array":
							this.stmt.setArray(i + 1, (Array) this.values.get(i));
							break;
						case "Long":
							this.stmt.setLong(i + 1, (Long) this.values.get(i));
							break;
						case "BigDecimal":
							this.stmt.setBigDecimal(i + 1, (BigDecimal) this.values.get(i));
							break;
						case "Boolean":
							this.stmt.setBoolean(i + 1, (Boolean) this.values.get(i));
							break;
						case "Byte":
							this.stmt.setByte(i + 1, (Byte) this.values.get(i));
							break;
						case "Time":
							this.stmt.setTime(i + 1, (Time) this.values.get(i));
							break;
						case "Timestamp":
							this.stmt.setTimestamp(i + 1, (Timestamp) this.values.get(i));
							break;
						default:
							this.stmt.setObject(i + 1, this.values.get(i));
							break;
						}
					}
					for (int i = 0; i < l2; i++) {
						String constrainttype = this.constraintdatatypes.get(i);
						switch (constrainttype) {
						case "String":
							this.stmt.setString(i + l1 + 1, (String) this.constraintvalues.get(i));
							break;
						case "NString":
							this.stmt.setNString(i + l1 + 1, (String) this.constraintvalues.get(i));
							break;
						case "Integer":
							this.stmt.setInt(i + l1 + 1, (Integer) this.constraintvalues.get(i));
							break;
						case "Double":
							this.stmt.setDouble(i + l1 + 1, (Double) this.constraintvalues.get(i));
							break;
						case "Array":
							this.stmt.setArray(i + l1 + 1, (Array) this.constraintvalues.get(i));
							break;
						case "Long":
							this.stmt.setLong(i + l1 + 1, (Long) this.constraintvalues.get(i));
							break;
						case "BigDecimal":
							this.stmt.setBigDecimal(i + l1 + 1, (BigDecimal) this.constraintvalues.get(i));
							break;
						case "Boolean":
							this.stmt.setBoolean(i + l1 + 1, (Boolean) this.constraintvalues.get(i));
							break;
						case "Byte":
							this.stmt.setByte(i + l1 + 1, (Byte) this.constraintvalues.get(i));
							break;
						case "Time":
							this.stmt.setTime(i + l1 + 1, (Time) this.constraintvalues.get(i));
							break;
						case "Timestamp":
							this.stmt.setTimestamp(i + l1 + 1, (Timestamp) this.constraintvalues.get(i));
							break;
						default:
							this.stmt.setObject(i + l1 + 1, this.constraintvalues.get(i));
							break;
						}
					}
					this.stmt.execute();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Updates a given table in a database
	 * 
	 * @param table (String) The table to be updated
	 * @param attribute (String[]) The attributes in the table to be updated
	 * @param datatype (String[]) The data type of each updated value
	 * @param value (Object[]) The updated values
	 * @param constraintkeys (String[]) The attributes in the constraints
	 * @param constraintdatatypes (String[]) The data types of each constraint value
	 * @param constraintvalues (Object[]) The constraint values
	 */
	public Update(String table, String[] attributes, String[] datatypes, Object[] values, String[] constraintkeys,
			String[] constraintdatatypes, Object[] constraintvalues) {
		try {
			this.table = table;
			for (int i = 0; i < attributes.length; i++) {
				this.attributes.add(attributes[i]);
			}
			for (int i = 0; i < datatypes.length; i++) {
				this.datatypes.add(datatypes[i]);
			}
			for (int i = 0; i < values.length; i++) {
				this.values.add(values[i]);
			}
			for (int i = 0; i < constraintkeys.length; i++) {
				this.constraintkeys.add(constraintkeys[i]);
			}
			for (int i = 0; i < constraintdatatypes.length; i++) {
				this.constraintdatatypes.add(constraintdatatypes[i]);
			}
			for (int i = 0; i < constraintvalues.length; i++) {
				this.constraintvalues.add(constraintvalues[i]);
			}
			this.update();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Updates a given table in a database
	 * 
	 * @param table (String) The table to be updated
	 * @param attribute (String) The attribute in the table to be updated
	 * @param datatype (String) The data type of the updated value
	 * @param value (Object) The updated value
	 * @param constraintkeys (String[]) The attributes in the constraints
	 * @param constraintdatatypes (String[]) The data types of each constraint value
	 * @param constraintvalues (Object[]) The constraint values
	 */
	public Update(String table, String attribute, String datatype, Object value, String[] constraintkeys,
			String[] constraintdatatypes, Object[] constraintvalues) {
		try {
			this.table = table;
			this.attributes.add(attribute);
			this.datatypes.add(datatype);
			this.values.add(value);
			for (int i = 0; i < constraintkeys.length; i++) {
				this.constraintkeys.add(constraintkeys[i]);
			}
			for (int i = 0; i < constraintdatatypes.length; i++) {
				this.constraintdatatypes.add(constraintdatatypes[i]);
			}
			for (int i = 0; i < constraintvalues.length; i++) {
				this.constraintvalues.add(constraintvalues[i]);
			}
			this.update();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Updates a given table in a database
	 * 
	 * @param table (String) The table to be updated
	 * @param attribute (String[]) The attributes in the table to be updated
	 * @param datatype (String[]) The data type of each updated value
	 * @param value (Object[]) The updated values
	 * @param constraintkeys (String) The attribute in the constraints
	 * @param constraintdatatypes (String) The data types of the constraint value
	 * @param constraintvalues (Object) The constraint value
	 */
	public Update(String table, String[] attributes, String[] datatypes, Object[] values, String constraintkeys,
			String constraintdatatypes, Object constraintvalues) {
		try {
			this.table = table;
			for (int i = 0; i < attributes.length; i++) {
				this.attributes.add(attributes[i]);
			}
			for (int i = 0; i < datatypes.length; i++) {
				this.datatypes.add(datatypes[i]);
			}
			for (int i = 0; i < values.length; i++) {
				this.values.add(values[i]);
			}
			this.constraintkeys.add(constraintkeys);
			this.constraintdatatypes.add(constraintdatatypes);
			this.constraintvalues.add(constraintvalues);
			this.update();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Updates a given table in a database
	 * 
	 * @param table (String) The table to be updated
	 * @param attribute (String) The attribute in the table to be updated
	 * @param datatype (String) The data type of the updated value
	 * @param value (Object) The updated value
	 * @param constraintkeys (String) The attribute in the constraints
	 * @param constraintdatatypes (String) The data type of the constraint value
	 * @param constraintvalues (Object) The constraint value
	 */
	public Update(String table, String attribute, String datatype, Object value, String constraintkeys,
			String constraintdatatypes, Object constraintvalues) {
		try {
			this.table = table;
			this.attributes.add(attribute);
			this.datatypes.add(datatype);
			this.values.add(value);
			this.constraintkeys.add(constraintkeys);
			this.constraintdatatypes.add(constraintdatatypes);
			this.constraintvalues.add(constraintvalues);
			this.update();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
