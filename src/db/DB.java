package db;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.sql.ResultSet;

public class DB {

	public static Connection conn = null;

	// método para obter conexão
	public static Connection getConnection() {

		if (conn == null) {
			try {
				Properties props = loadProperties();
				String url = props.getProperty("dburl");
				conn = DriverManager.getConnection(url, props); // conectado com o banco de dados

			} catch (SQLException e) {
				throw new DbException(e.getMessage());
			}
		} // fechamento do if

		return conn;
	}

	// método para fechar conexão
	public static Connection closeConnection() {
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				throw new DbException(e.getMessage());
			}
		} // fechamente do if
		return conn;
	}

	// método para carregar propiedades do banco de dados
	private static Properties loadProperties() {

		try (FileInputStream fs = new FileInputStream("db.properties")) {
			Properties props = new Properties();
			props.load(fs);
			return props;
		} catch (IOException e) {
			throw new DbException(e.getMessage());
		} // fechando o catch

	} // fechando método
	
	// fechar os comandos sql inserido do java
	public static void closeStatement(Statement st) {
		if (st != null) {
			try {
				st.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				throw new DbException(e.getSQLState());
			}
		}
	}
	// fechar o resultset
	public static void closeResultSet(ResultSet rs) {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				throw new DbException(e.getSQLState());
			}
		}
	}
	
}
