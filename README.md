```java
// Conexão
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
```