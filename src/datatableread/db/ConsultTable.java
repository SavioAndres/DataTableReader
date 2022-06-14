package datatableread.db;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Classe responsável por consultar a tabela na base de dados.
 * @author Sávio Andres
 */
public class ConsultTable {
	private String ownerName;
	private String tabelaName;
	private DatabaseConnection connectionDB;
	
	public ConsultTable() {
		super();
		connectionDB = new DatabaseConnection();
	}
	
	public ConsultTable(String ownerName, String tabelaName) {
		super();
		this.ownerName = ownerName;
		this.tabelaName = tabelaName;
		connectionDB = new DatabaseConnection();
	}
	
	/**
	 * Consulta o comentário sobre a tabela no banco de dados
	 * @return ResultSet
	 */
	public ResultSet consultTableComment() {
		ResultSet res = null;
		
		try {
			String query = "SELECT COMMENTS\r\n"
					+ "  FROM ALL_TAB_COMMENTS\r\n"
					+ " WHERE OWNER = ?\r\n"
					+ "   AND UPPER(TABLE_NAME) = UPPER(?)\r\n"
					+ "   AND TABLE_TYPE = 'TABLE'";
			
			ResultSet rs = connectionDB.resultSql(query, ownerName, tabelaName);
			return rs;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return res;
	}
	
	/**
	 * Consulta informações das colunas em uma tabela do banco de dados
	 * @return ResultSet
	 */
	public ResultSet consultColumns() {
		try {
			String query = "SELECT ALL_TAB_COLUMNS.COLUMN_NAME,\r\n"
					+ "       ALL_TAB_COLUMNS.DATA_TYPE,\r\n"
					+ "       ALL_TAB_COLUMNS.DATA_PRECISION,\r\n"
					+ "       ALL_TAB_COLUMNS.DATA_LENGTH,\r\n"
					+ "       ALL_TAB_COLUMNS.DATA_SCALE,\r\n"
					+ "       ALL_TAB_COLUMNS.NULLABLE,\r\n"
					+ "       P.COLUMN_NAME \"PRIMARY_KEY\",\r\n"
					+ "       C.COMMENTS\r\n"
					+ "  FROM ALL_TAB_COLUMNS\r\n"
					+ "  LEFT JOIN (SELECT COLUMN_NAME\r\n"
					+ "               FROM ALL_CONS_COLUMNS\r\n"
					+ "              WHERE CONSTRAINT_NAME IN\r\n"
					+ "                    (SELECT CONSTRAINT_NAME\r\n"
					+ "                       FROM ALL_CONSTRAINTS\r\n"
					+ "                      WHERE UPPER(TABLE_NAME) = UPPER(?)\r\n"
					+ "                        AND CONSTRAINT_TYPE = 'P')) P\r\n"
					+ "    ON ALL_TAB_COLUMNS.COLUMN_NAME = P.COLUMN_NAME\r\n"
					+ "  LEFT JOIN (SELECT COLUMN_NAME, COMMENTS\r\n"
					+ "               FROM ALL_COL_COMMENTS\r\n"
					+ "              WHERE OWNER = ?\r\n"
					+ "                AND UPPER(TABLE_NAME) = UPPER(?)) C\r\n"
					+ "    ON ALL_TAB_COLUMNS.COLUMN_NAME = C.COLUMN_NAME\r\n"
					+ " WHERE ALL_TAB_COLUMNS.OWNER = ?\r\n"
					+ "   AND ALL_TAB_COLUMNS.TABLE_NAME = UPPER(?)\r\n"
					+ " ORDER BY ALL_TAB_COLUMNS.COLUMN_ID";
			
			ResultSet rs = connectionDB.resultSql(query, tabelaName, ownerName, tabelaName, ownerName, tabelaName);
			return rs;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public String getOwnerName() {
		return ownerName;
	}

	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}

	public String getTabelaName() {
		return tabelaName;
	}

	public void setTabelaName(String tabelaName) {
		this.tabelaName = tabelaName;
	}
	
}
