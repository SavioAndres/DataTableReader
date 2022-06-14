package datatableread.pojo;

/**
 * Classe responsável por representar a coluna da tabela na base de dados.
 * @author Sávio Andres
 */
public class Column {
	private String name;		// Nome do atributo na tabela
	private String type;		// Tipo do atributo (Coluna)
	private int length;			// Tamanho do atributo
	private int decimalScale;	// Escala do valor de um atributo fracionário
	private boolean nullable;	// Dado não é obrigatório
	private boolean primaryKey; // Chave primária
	private String comment;		// Comentário sobre o atributo
	
	public Column() {
		super();
	}
	
	public Column(String name, String type, int length, int decimalScale, boolean nullable, boolean primaryKey,
			String comment) {
		super();
		this.name = name;
		this.type = type;
		this.length = length;
		this.decimalScale = decimalScale;
		this.nullable = nullable;
		this.primaryKey = primaryKey;
		this.comment = comment;
	}

	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getType() {
		return type;
	}
	
	public void setType(String type) {
		this.type = type;
	}
	
	public int getLength() {
		return length;
	}
	
	public void setLength(int length) {
		this.length = length;
	}
	
	public int getDecimalScale() {
		return decimalScale;
	}
	
	public void setDecimalScale(int decimalScale) {
		this.decimalScale = decimalScale;
	}
	
	public boolean isNullable() {
		return nullable;
	}
	
	public void setNullable(boolean nullable) {
		this.nullable = nullable;
	}
	
	public boolean isPrimaryKey() {
		return primaryKey;
	}
	
	public void setPrimaryKey(boolean primaryKey) {
		this.primaryKey = primaryKey;
	}
	
	public String getComment() {
		return comment;
	}
	
	public void setComment(String comment) {
		this.comment = comment;
	}

	@Override
	public String toString() {
		return "Column [name=" + name + ", type=" + type + ", length=" + length + ", decimalScale=" + decimalScale
				+ ", nullable=" + nullable + ", primaryKey=" + primaryKey + ", comment=" + comment + "]";
	}
	
}
