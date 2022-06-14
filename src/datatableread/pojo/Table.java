package datatableread.pojo;

import java.util.Arrays;

/**
 * Classe responsável por representar a tabela na base de dados.
 * @author Sávio Andres
 */
public class Table {
	private String owner;
	private String name;
	private String comment;
	private Column[] columns;
	
	public Table() {
		super();
	}
	
	public Table(String owner, String name, String comment, Column[] columns) {
		super();
		this.owner = owner;
		this.name = name;
		this.comment = comment;
		this.columns = columns;
	}
	
	public String getOwner() {
		return owner;
	}
	
	public void setOwner(String owner) {
		this.owner = owner;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getComment() {
		return comment;
	}
	
	public void setComment(String comment) {
		this.comment = comment;
	}
	
	public Column[] getColumns() {
		return columns;
	}
	
	public void setColumns(Column[] columns) {
		this.columns = columns;
	}

	@Override
	public String toString() {
		return "Table [owner=" + owner + ", name=" + name + ", comment=" + comment + ", columns="
				+ Arrays.toString(columns) + "]";
	}
	
}
