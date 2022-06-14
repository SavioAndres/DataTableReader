import datatableread.create.CreateTableObject;
import datatableread.db.DatabaseConnection;
import datatableread.pojo.Column;
import datatableread.pojo.Table;

/**
 * Classe Main respons�vel por demonstrar a utiliza��o do pacote.
 * @author S�vio Andres
 */
public class DataTableReader {

	public static void main(String[] args) {
		// Conex�o
		DatabaseConnection connectionDB = new DatabaseConnection();
		connectionDB.setServerName(""); 		// 10.1.180.1
		connectionDB.setPortNumber(""); 		// 1523
		connectionDB.setSid(""); 				// meusid
		connectionDB.setUsername(""); 			// root
		connectionDB.setPassword(""); 			// root
		connectionDB.toConnect();
		
		// Consulta os dados da tabela na base de dados e cria 
		// o objeto tabela com as caracteristica da tabela da base de dados.
		CreateTableObject createTableObject = new CreateTableObject();
		createTableObject.setOwnerName(""); 	// GLOBAL
		createTableObject.setTabelaName(""); 	//TABELA_TESTES
		createTableObject.toCreateTable();
		
		Table table = createTableObject.getTable();
		
		// Listar tabela
		System.out.println(table);
		System.out.println("----------------------------------");
		System.out.println("Tabela: " + table.getOwner() + "." + table.getName());
		System.out.println("Coment�rio da tabela: " + table.getComment());
		System.out.println("----------------------------------");
		
		for (Column column : table.getColumns()) {
			System.out.println("Nome da coluna: " + column.getName());
			System.out.println("Tipo da coluna: " + column.getType());
			System.out.println("Tamanho da coluna: " + column.getLength());
			System.out.println("Casas decimais da coluna: " + column.getDecimalScale());
			System.out.println("Coment�rio da coluna: " + column.getComment());
			System.out.println("----------------------------------");
		}
	}

}
