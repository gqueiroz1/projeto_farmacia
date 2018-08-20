package br.com.farmacia.bean;

import java.util.ArrayList;

import javax.faces.bean.ManagedBean;
import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.faces.model.ListDataModel;

import br.com.farmacia.DAO.FornecedoresDAO;
import br.com.farmacia.domain.Fornecedores;
import br.com.farmacia.util.JSFUtil;

/*Classe respons�vel por fazer a comunica��o do arquivo xhtml com o banco de dados*/

@ManagedBean(name = "MBFornecedores")
@ViewScoped
public class FornecedoresBean {

	private Fornecedores fornecedores;

	private ListDataModel<Fornecedores> itens;

	public Fornecedores getFornecedores() {
		return fornecedores;
	}

	public void setFornecedores(Fornecedores fornecedores) {
		this.fornecedores = fornecedores;
	}

	public ListDataModel<Fornecedores> getItens() {
		return itens;
	}

	public void setItens(ListDataModel<Fornecedores> itens) {
		this.itens = itens;
	}

	@PostConstruct
	public void preparaPesquisa() {
		try {
			FornecedoresDAO fdao = new FornecedoresDAO();
			ArrayList<Fornecedores> lista = fdao.listar();
			itens = new ListDataModel<Fornecedores>(lista);

		} catch (Exception e) {
			JSFUtil.adicionarMensagemErro("Erro ao pesquisar: " + e.getMessage());
		}
	}

	public void preparaNovo() {
		fornecedores = new Fornecedores();
	}

	// Prepara uma nova inst�ncia do objeto Fornecedor para ser gravada
	public void novo() {
		try {
			FornecedoresDAO fdao = new FornecedoresDAO();
			fdao.salvar(fornecedores);

			// Atualiza a p�gina de listagem automaticamente
			// assim que gravarmos um novo registro
			ArrayList<Fornecedores> lista = fdao.listar();
			itens = new ListDataModel<Fornecedores>(lista);

			JSFUtil.adicionarMensagemSucesso("Registro salvo com sucesso");
		} catch (Exception e) {
			JSFUtil.adicionarMensagemErro("Erro ao adicionar: " + e.getMessage());
		}
	}

	public void preparaExcluir() {
		fornecedores = itens.getRowData();
	}

	public void excluir() {

		try {
			FornecedoresDAO fdao = new FornecedoresDAO();
			fdao.excluir(fornecedores);

			// Atualiza a p�gina de listagem automaticamente
			// assim que excluirmos um novo registro
			ArrayList<Fornecedores> lista = fdao.listar();
			itens = new ListDataModel<Fornecedores>(lista);

			JSFUtil.adicionarMensagemSucesso("Registro exclu�do com sucesso");
		} catch (Exception e) {
			JSFUtil.adicionarMensagemErro("Erro ao excluir: " + e.getMessage());
		}
	}
	
	public void preparaEditar() {
		fornecedores = itens.getRowData();
	}

	public void editar() {

		try {
			FornecedoresDAO fdao = new FornecedoresDAO();
			fdao.editar(fornecedores);

			// Atualiza a p�gina de listagem automaticamente
			// assim que editarmos um novo registro
			ArrayList<Fornecedores> lista = fdao.listar();
			itens = new ListDataModel<Fornecedores>(lista);

			JSFUtil.adicionarMensagemSucesso("Registro editado com sucesso");
		} catch (Exception e) {
			JSFUtil.adicionarMensagemErro("Erro ao editar: " + e.getMessage());
		}
	}

}
