package db;

public class JDBCDriver extends Exception {
	/**
	 * This class contains the required check for the JDBC Driver
	 */
	private static final long serialVersionUID = -2232976324656191269L;
	private String jdbcDriverClass = "";
	public Boolean isDriverSupported() {
		/**
		 * Returns true if the given JDBC driver exists, else returns false.
		 */
		try {
			Class.forName(this.jdbcDriverClass);
			return true;
		} catch(Exception e) {
			return false;
		}
	}
}
