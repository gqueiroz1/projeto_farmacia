package br.com.farmacia.factory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoFactory {
	
	private static final String USUARIO = "root";
	private static final String SENHA = "aqswde123";
	private static final String URL = "jdbc:mysql://localhost:3306/sistema?useTimezone=true&serverTimezone=UTC";

	//MÉTODO CONECTAR - Retorna uma referência de Connection
	public Connection conectar() throws SQLException {
		
		DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver()); //Referência ao Driver mysql
		Connection conexao = DriverManager.getConnection(URL, USUARIO, SENHA);//CONEXÃO RETORNADA
		return conexao;
	}

}
