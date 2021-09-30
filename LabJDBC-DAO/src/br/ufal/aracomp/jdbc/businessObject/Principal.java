package br.ufal.aracomp.jdbc.businessObject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import br.ufal.aracomp.jdbc.dao.DAOCliente;
import br.ufal.aracomp.jdbc.dao.IDAOCliente;
import br.ufal.aracomp.jdbc.dataSource.HSQLDataSource;
import br.ufal.aracomp.jdbc.dataSource.IDataSource;
import br.ufal.aracomp.jdbc.model.Cliente;


public class Principal {
	
	public static void mainOld(String[] args) {
		String user = "SA";
		String url = "jdbc:hsqldb:/home/patrick/Documentos/UFAL/disciplinas/graduação/bd1/sql e algebra relacional/labSQL/hsqldb/bd/locadora";
		String driverUrl = "org.hsqldb.jdbcDriver";
		
		Connection sgbdConn = null;
		Statement sqlInterpreter = null;
		try {
			Class.forName(driverUrl);
			sgbdConn = DriverManager.getConnection(url, user, "");
			sqlInterpreter = sgbdConn.createStatement();
			String sql = "SELECT f.nome, COUNT(*) AS quant "
					+ "FROM unidade u, filme f "
					+ "WHERE u.codigo_filmeFK = f.codigo "
					+ "GROUP BY f.codigo";
			ResultSet resultado = sqlInterpreter.executeQuery(sql);
			System.out.println("LISTA DE FILMES E QUANTIDADES DE UNIDADES:");
			while(resultado.next()) {
				System.out.println("   * Filme: " + resultado.getString("nome") + 
						" --> " + resultado.getInt("quant"));
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			try {
				if(sqlInterpreter != null)
					sqlInterpreter.close();
				if(sgbdConn != null)
					sgbdConn.close();
				}
			catch(SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	
	//COM DAO
	public static void main(String[] args) {
		IDataSource dataSource = new HSQLDataSource();
		IDAOCliente daoCliente = new DAOCliente(dataSource);
		
		Cliente c = new Cliente("cadu", "Carlos Eduardo");
		daoCliente.adicionar(c);
		
		Cliente phsb = daoCliente.consultar("phsb");
	}
}
