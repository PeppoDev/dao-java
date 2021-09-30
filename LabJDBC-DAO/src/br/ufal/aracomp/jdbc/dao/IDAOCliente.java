package br.ufal.aracomp.jdbc.dao;

import java.util.List;

import br.ufal.aracomp.jdbc.model.Cliente;
import br.ufal.aracomp.jdbc.model.Locacao;

public interface IDAOCliente {
	public void adicionar(Cliente cliente);
	public void remover(String login);
	public void alterar(String loginAntigo, Cliente dadosNovos);
	public Cliente consultar(String login);
	public List<Cliente> consultarPorNome(String parteNome);
	public List<Cliente> obterTodosClientes();
	public List<Locacao> obterLocacoesCliente(Cliente cliente);
}
