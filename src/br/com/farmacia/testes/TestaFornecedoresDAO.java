package br.com.farmacia.testes;

import java.sql.SQLException;
import java.util.ArrayList;

import br.com.farmacia.DAO.FornecedoresDAO;
import br.com.farmacia.domain.Fornecedores;

@SuppressWarnings("unused")
public class TestaFornecedoresDAO {

	public static void main(String[] args) {
		Fornecedores f1 = new Fornecedores();
		Fornecedores f2 = new Fornecedores();
		Fornecedores f3 = new Fornecedores();
		Fornecedores f4 = new Fornecedores();
		Fornecedores f5 = new Fornecedores();

		f1.setDescricao("MEGA");
		f2.setDescricao("DRIVER");
		//f1.setCodigo(3l);
		//f2.setCodigo(4L);
		f3.setDescricao("ESQUILO");
		f4.setDescricao("CAMINHÃO");
		//f3.setCodigo(5l);
		//f4.setCodigo(6L);
		f5.setDescricao("LG");
		//f5.setCodigo(7l);

		FornecedoresDAO fdao = new FornecedoresDAO();
		fdao.salvar(f1);
		fdao.salvar(f2);
		fdao.salvar(f3); 
		fdao.salvar(f4); 
		fdao.salvar(f5);
		 

		/*
		 * FornecedoresDAO fdao = new FornecedoresDAO(); fdao.excluir(f2);
		 */
		// f1.setDescricao("NOVA DESCRIÇÃO");
		 //fdao.editar(f5);

		//BUSCANDO PELO CÓDIGO
		/*try {
			System.out.println("BUSCANDO POR CÓDIGO: " + fdao.buscarPorCodigo(f2));
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Erro ao buscar!");
		}*/
		
		//BUSCANDO PELA DESCRIÇÃO
		/*ArrayList<Fornecedores> lista_descricao = fdao.buscarPorDescricao(f1);
		for(Fornecedores f : lista_descricao) {
			System.out.println("BUSCA DESCRIÇÃO: " + f);
		}*/
		
		
		//LISTANDO TODOS OS REGISTROS
		/*ArrayList<Fornecedores> lista = fdao.listar();
		for(Fornecedores f : lista) {
			System.out.println("Resultado: " + f);
		}*/

	}

}
