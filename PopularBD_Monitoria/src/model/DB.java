package model;
import java.sql.Connection;
import java.sql.DriverManager;


public class DB {

	private static Connection connection;

	// configuration of the database driver
	private static DB db = null;
	private static String user = "root";
	private static String password = "";
	private static String url = "jdbc:mysql://localhost:3306/";
	private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	private static final String DB_NAME = "monitoria_mysql";
	
	private static void makeConnection() {
		try {
			// This will load the MySQL driver, each DB has its own driver
			Class.forName(JDBC_DRIVER);

			connection = DriverManager.getConnection(url, user, password);

		} catch (Exception e) {
			System.out.println(e);
		} finally {

		}

	}

	private DB() {

		makeConnection();

	}

	public static Connection getConnectionDB() throws Exception {

		if (db == null)
			db = new DB();

		return connection;
	}

	public static String getDbName() {
		return DB_NAME;
	}

}
