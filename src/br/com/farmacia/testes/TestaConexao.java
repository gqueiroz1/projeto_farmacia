package br.com.farmacia.testes;

import java.sql.SQLException;

import br.com.farmacia.factory.ConexaoFactory;

public class TestaConexao {
	public static void main(String[] args) {
			
		try {
			ConexaoFactory conexao = new ConexaoFactory();
			conexao.conectar();
			System.out.println("Conectado com sucesso!");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Falha na Conexão!");
		}
	}
}
