package datatableread.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Classe responsável por fazer conexão com o Banco de Dados.
 * @author Sávio Andres
 */
public class DatabaseConnection {
	private static Connection connection;
	private String serverName;
	private String portNumber;
	private String sid;
	private String username;
	private String password;
	
	public DatabaseConnection() {
		super();
	}
	
	public DatabaseConnection(String serverName, String portNumber, String sid, String username, String password) {
		super();
		this.serverName = serverName;
		this.portNumber = portNumber;
		this.sid = sid;
		this.username = username;
		this.password = password;
	}
	
	public Connection toConnect() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		
			// Criar conexão com a base de dados
			String url = "jdbc:oracle:thin:@" + serverName + ":" + portNumber + ":" + sid;
			connection = DriverManager.getConnection(url, username, password);
			return connection;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * É passado o script SQL com os parametros para where (ou não) e devolve o resultado enviado pelo banco de dados
	 * @param pSql
	 * @param pParams
	 * @return ResultSet
	 * @throws SQLException
	 */
	public ResultSet resultSql(String pSql, String... pParams) throws SQLException {
		PreparedStatement preparedStatement = connection.prepareStatement(pSql);
		for (int i = 0; i < pParams.length; i++) {
			preparedStatement.setString(i + 1, pParams[i]);
		}
		return preparedStatement.executeQuery();
	}
	
	/**
	 * Entrega a conexão com o banco de dados
	 * @return Connection
	 */
	public static Connection getConnection() {
		return connection;
	}
	
	public static boolean isConnected() {
		return connection != null;
	}

	public String getServerName() {
		return serverName;
	}

	public void setServerName(String serverName) {
		this.serverName = serverName;
	}

	public String getPortNumber() {
		return portNumber;
	}

	public void setPortNumber(String portNumber) {
		this.portNumber = portNumber;
	}

	public String getSid() {
		return sid;
	}

	public void setSid(String sid) {
		this.sid = sid;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
}
