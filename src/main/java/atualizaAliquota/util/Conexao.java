package atualizaAliquota.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class Conexao {
	
	private Properties properties;
	private static String db_host;
	private static String db_port = "0000";
	private static String db_name;
	private static String db_password;
	private static String db_username;
	
	
	public void getProperties()  {
		
		properties = new Properties();
		
		try {
			FileInputStream file = new FileInputStream(
					"c:/vr/exec/conexao.properties");
			properties.load(file);
			
			db_host = properties.getProperty("prop.server.host");
			db_name = properties.getProperty("prop.server.dbname"); 
			db_password = properties.getProperty("prop.server.password");
			db_username = properties.getProperty("prop.server.username");
			
		}
		catch (IOException e){
			e.printStackTrace();
		}

	}	
	

	public static Connection conectar() throws SQLException {
		try {
			Class.forName("org.postgresql.Driver");
			return DriverManager.getConnection("jdbc:postgresql://" + db_host + ":" + db_port + "/" + db_name, db_username,
					db_password);

		}
		catch (ClassNotFoundException ex) {
			System.out.println("Driver do banco de dados não encontrado.");
		}
		return null;
	}
}
