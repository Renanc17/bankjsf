package factory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory_LocalHost {
	
	private static void registraDriverJdbc() throws SQLException {

		try {
			Class.forName("org.postgresql.Driver");
			Connection connection = null;
			connection = DriverManager.getConnection(
				"jdbc:oracle:thin:@oracle.fiap.com.br:1521:OPS$RM67031","OPS$RM67031","160394");
			connection.close();
		} catch (ClassNotFoundException ex) {

			ex.printStackTrace();

			System.exit(1);

		}
	}

	public static Connection getConnection() throws SQLException{
		
		String server = "oracle.fiap.com.br";
		String port = "1521";
		String database = "OPS$RM67031";
		String usuario = "OPS$RM67031";
		String senha = "160394";

		String url = "jdbc:oracle:thin:@" + server + ":" + port + ":" + database;

		registraDriverJdbc();
		return DriverManager.getConnection(url, usuario, senha);
		
	}
	
}