package br.ufal.aracomp.jdbc.dao;

import java.sql.ResultSet;

import br.ufal.aracomp.jdbc.dataSource.IDataSource;
import br.ufal.aracomp.jdbc.model.Unidade;

public class DAOUnidade implements IDAOUnidade{
    private IDataSource dataSource;

    public DAOUnidade(IDataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void adicionar(Unidade unidade) {
        String sql = "INSERT INTO Unidade(codigo, preco, codigo_filmeFK) VALUES('"+unidade.getCodigo()+"','"+unidade.getPreco()+"','"+unidade.getFilme().getCodigo()+"')";
        try {
            dataSource.executarQueryGeral(sql);
        }catch(Exception e) {
            e.printStackTrace();
        }
    }
        

    @Override
    public Unidade consultar(int codigo) {
        String sql = "SELECT nome FROM Unidade WHERE codigo = '" + codigo + "'";
        try {
            ResultSet rs = this.dataSource.executarSelect(sql);
            if (rs.next()) {
                return new Unidade(codigo, rs.getFloat("preco"));
            }
        } catch (Exception e) {
            e.getStackTrace();
        }
        return null;
    }

    @Override
    public void remover(int codigo) {
        String sql = "DELETE FROM Unidade WHERE codigo ='" + codigo +"'";
        try {
            dataSource.executarQueryGeral(sql);
        }catch(Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void alterar(int codigo, Unidade dadosNovos) {
        String sql = "UPDATE Codigo set codigo = '"+dadosNovos.getCodigo()+"', set preco = '"+dadosNovos.getPreco()+"', set codigo_unidadeFK = '"
                + dadosNovos.getFilme().getCodigo() + "' where codigo = '"+ codigo +"'";
        try {
            dataSource.executarQueryGeral(sql);
        }catch(Exception e) {
            e.printStackTrace();
        }
    }
}
