package db;

/**
 * This class contains methods to check if a given JDBC Driver is available or not
 * @author Burra Abhishek
 *
 */
public class JDBCDriver {
	private String jdbcDriverClass = "";
	/**
	 * Checks if the JDBC Driver Class is available or not.
	 * 
	 * @return true if the given JDBC driver exists, false otherwise.
	 */
	public Boolean isDriverSupported() {
		try {
			Class.forName(this.jdbcDriverClass);
			return true;
		} catch(Exception e) {
			return false;
		}
	}
}
