package br.ufal.aracomp.jdbc.dao;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import br.ufal.aracomp.jdbc.dataSource.IDataSource;
import br.ufal.aracomp.jdbc.model.Cliente;
import br.ufal.aracomp.jdbc.model.Locacao;

public class DAOCliente implements IDAOCliente {
	private IDataSource dataSource;

	public DAOCliente(IDataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Override
	public void adicionar(Cliente cliente) {
		Cliente cTeste = this.consultar(cliente.getLogin());
		if(cTeste == null) {
			String sql = "INSERT INTO Cliente(login, nome) VALUES('"+cliente.getLogin()+"','"+cliente.getNome()+"')";
			try {
			dataSource.executarQueryGeral(sql);
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		else {
			System.err.println("LOGIN JA EXISTENTE!");
		}
	}

	@Override
	public void remover(String login) {
		String sql = "DELETE FROM Cliente WHERE login ='" + login +"'";
		try {
		dataSource.executarQueryGeral(sql);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void alterar(String loginAntigo, Cliente dadosNovos) {
		Cliente cTeste = this.consultar(dadosNovos.getLogin());
		if(cTeste == null) {
			String sql = "update cliente set nome = '"+dadosNovos.getNome()+"', set login = '"+dadosNovos.getLogin()+"' where login = '"+loginAntigo+"'";
			try {
			dataSource.executarQueryGeral(sql);
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		else {
			System.err.println("ERRO NA ALTERACAO! LOGIN NOVO JA EXISTE!");
		}
	}

	@Override
	public Cliente consultar(String login) {
		String sql = "SELECT nome FROM cliente WHERE login = '" + login + "'";
		try {
			ResultSet rs = this.dataSource.executarSelect(sql);
			if(rs.next()) {
				return new Cliente(login, rs.getString("nome"));
			}
		} catch(Exception e) {
			e.getStackTrace();
		}
		return null;
	}
  
	@Override
	public List<Cliente> consultarPorNome(String parteNome) {
		ArrayList<Cliente> clientes = new ArrayList<>();
		String sql = "select * from cliente where nome like '%" + parteNome + "%'";
		
		try {
			ResultSet rs = this.dataSource.executarSelect(sql);
		
			while(rs.next()) {
				clientes.add(new Cliente(rs.getString("login"), rs.getString("nome")));
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return clientes;
	}

	@Override
	public List<Cliente> obterTodosClientes() {
		ArrayList<Cliente> clientes = new ArrayList<>();
		String sql = "SELECT * from Cliente";
		
		try {
			ResultSet rs = this.dataSource.executarSelect(sql);
		
			while(rs.next()) {
				Cliente c = new Cliente(rs.getString("login"), rs.getString("nome"));
				clientes.add(c);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return clientes;
	}
	
	
	@Override
	public List<Locacao> obterLocacoesCliente(Cliente cliente){
		//TODO implementar este metodo:
		//Consultar os ids das unidades locadas pelo cliente e os demais atributos do relacionamento "locar"
		//Para cada unidade, obter a unidade (utilizando o DAO de Unidade) 
		//Instanciar o objeto Locacao, passando o cliente, a unidade e os valores dos demais atributos 
		//Adicionar a locacao na lista
		//retornar a lista de Locacoes. Pode ser uma lista vazia, caso o cliente nao tenha locado nada.
		
		

		return null;
	}
}
