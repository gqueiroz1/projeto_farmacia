package br.com.farmacia.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import br.com.farmacia.domain.Fornecedores;
import br.com.farmacia.factory.ConexaoFactory;

/*Classes para gerenciar os comandos do Banco de Dados*/

public class FornecedoresDAO {

	// INSERE FORNECEDOR NO BANCO
	public void salvar(Fornecedores f) {
		// Criando o Builder para concatenar o comando de INSERT
		StringBuilder sql = new StringBuilder();
		sql.append("INSERT INTO fornecedores ");
		sql.append("(descricao) ");
		sql.append("VALUES (?)");

		try {// Tenta executar o bloco e criar uma conexão / preparar o comando para inserção
			Connection conexao = new ConexaoFactory().conectar();
			PreparedStatement comando = conexao.prepareStatement(sql.toString());

			comando.setString(1, f.getDescricao());
			comando.executeUpdate();
			System.out.println("Registro salvo com sucesso!");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Falha na Conexão");
		}

	}
	
	public void excluir(Fornecedores f) {
		StringBuilder sql = new StringBuilder();
		sql.append("DELETE FROM fornecedores ");
		sql.append("WHERE codigo = ?");
		
		try {
			Connection conexao = new ConexaoFactory().conectar(); //Retorna uma variável do tipo Connection
			PreparedStatement comando = conexao.prepareStatement(sql.toString());
			
			comando.setLong(1, f.getCodigo());
			comando.executeUpdate();
			
			System.out.println("Registro excluído com sucesso!");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Erro ao deletar!");
		}
	}
	
	public void editar(Fornecedores f) {
		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE fornecedores SET descricao = ? WHERE codigo = ?");
		
		try {
			Connection conexao = new ConexaoFactory().conectar();
			PreparedStatement comando = conexao.prepareStatement(sql.toString());
			
			comando.setString(1, f.getDescricao());
			comando.setLong(2, f.getCodigo());
			comando.executeUpdate();
			System.out.println("Registro ATUALIZADO!");			
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Erro ao fazer atualização!");
		}
	}
	
	public Fornecedores buscarPorCodigo(Fornecedores f) throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT codigo, descricao FROM fornecedores WHERE codigo = ?");
		
			Connection conexao = new ConexaoFactory().conectar();
			PreparedStatement comando = conexao.prepareStatement(sql.toString());
			
			comando.setLong(1, f.getCodigo());
			ResultSet resultSet = comando.executeQuery(); //Jogando o retorno da query dentro de um ResultSet
			
			Fornecedores retorno = null;
			
			if(resultSet.next()) {
				retorno = new Fornecedores();
				retorno.setCodigo(resultSet.getLong("codigo"));
				retorno.setDescricao(resultSet.getString("descricao"));
			}
			
			return retorno;	
	}
	
	public ArrayList<Fornecedores> buscarPorDescricao(Fornecedores f){
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT codigo, descricao FROM fornecedores WHERE descricao LIKE ? ORDER BY descricao ASC");
		ArrayList<Fornecedores> lista = new ArrayList<Fornecedores>();
		
		try {
			Connection conexao = new ConexaoFactory().conectar();
			PreparedStatement comando = conexao.prepareStatement(sql.toString());
			
			comando.setString(1, "%" + f.getDescricao() + "%");//PROCURANDO COM O PARÂMETRO LIKE
			
			ResultSet resultSet = comando.executeQuery();
			
			while(resultSet.next()) {
				Fornecedores f1 = new Fornecedores();
				f1.setCodigo(resultSet.getLong("codigo"));
				f1.setDescricao(resultSet.getString("descricao"));
				lista.add(f1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Erro ao buscar por descrição");
		}
		return lista;
		
	}
	
	public ArrayList<Fornecedores> listar(){
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT * FROM fornecedores ORDER BY descricao ASC");
		
		//Array para armazenar os registros buscados
		ArrayList<Fornecedores> lista = new ArrayList<Fornecedores>();
		
		try {
			Connection conexao = new ConexaoFactory().conectar();
			PreparedStatement comando = conexao.prepareStatement(sql.toString()); //Referência da conexão faz o prepare do sql.
			
			//a variável comando armazenará o comando SQL do SELECT
			//Ao retornar no ResultSet ele armazenará todas as incidências de retorno, ou seja, todos os registros
			ResultSet resultSet = comando.executeQuery();
					
			while(resultSet.next()) {
				Fornecedores f = new Fornecedores();
				f.setCodigo(resultSet.getLong("codigo"));
				f.setDescricao(resultSet.getString("descricao"));
				lista.add(f);	
			}	
			
			//System.out.println(lista);
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Falha ao listar registros!");
		}
		
		return lista;
		
	}

}
