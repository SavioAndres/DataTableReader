package datatableread.create;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import datatableread.db.ConsultTable;
import datatableread.pojo.Column;
import datatableread.pojo.Table;

/**
 * Classe responsável por criar o objeto Table com 
 * os dados consultados na base de dados.
 * 
 * @author Sávio Andres
 */
public class CreateTableObject {
	private String ownerName;
	private String tabelaName;
	private Table table;
	private ConsultTable consultTable;
	
	public CreateTableObject() {
	}
	
	public CreateTableObject(String ownerName, String tabelaName) {
		this(new ConsultTable(ownerName, tabelaName));
	}
	
	private CreateTableObject(ConsultTable consultTable) {
		super();
		this.consultTable = consultTable;
		table = readTable();
	}
	
	public void toCreateTable() {
		this.consultTable = new ConsultTable(ownerName, tabelaName);
		table = readTable();
	}
	
	private Table readTable() {
		table = new Table();
		table.setOwner(consultTable.getOwnerName());
		table.setName(consultTable.getTabelaName());
		
		try {
			ResultSet rsComment = consultTable.consultTableComment();
			ResultSet rsColumn = consultTable.consultColumns();
			
			while (rsComment.next()) {
				table.setComment((rsComment.getString("COMMENTS") == null) ? "" : String.valueOf(rsComment.getString("COMMENTS")));
			}
			
			List<Column> res = new LinkedList<Column>();
			while (rsColumn.next()) {
				Column column = new Column();
				column.setName(String.valueOf(rsColumn.getString("COLUMN_NAME")));
				column.setType(String.valueOf(rsColumn.getString("DATA_TYPE")));
				column.setLength(rsColumn.getString("DATA_PRECISION") != null ? rsColumn.getInt("DATA_PRECISION") : rsColumn.getInt("DATA_LENGTH"));
				column.setDecimalScale(rsColumn.getInt("DATA_SCALE"));
				column.setNullable(rsColumn.getString("NULLABLE").equals("Y"));
				column.setPrimaryKey(rsColumn.getString("PRIMARY_KEY") != null);
				column.setComment((rsColumn.getString("COMMENTS") == null) ? "" : String.valueOf(rsColumn.getString("COMMENTS")));
				
				res.add(column);
			}
			
			if (res.isEmpty())
				throw new SQLException("A Tabela \"" + table.getOwner() + "." + table.getName() + "\" não existe.");
			
			table.setColumns(res.toArray(new Column[0]));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return table;
	}
	
	public Table getTable() {
		return table;
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
