package br.ufal.aracomp.jdbc.dao;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import br.ufal.aracomp.jdbc.dataSource.IDataSource;
import br.ufal.aracomp.jdbc.model.Filme;
import br.ufal.aracomp.jdbc.model.Unidade;

public class DAOFilme implements IDAOFilme{
    private IDataSource dataSource;

    public DAOFilme(IDataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void adicionar(Filme filme) {
        Filme cTeste = this.consultar(filme.getCodigo());
        if(cTeste == null) {
            String sql = "INSERT INTO Cliente(codigo, nome) VALUES('"+filme.getCodigo()+"','"+filme.getNome()+"')";
            try {
                dataSource.executarQueryGeral(sql);
            }catch(Exception e) {
                e.printStackTrace();
            }
        }
        else {
            System.err.println("NOME JA EXISTENTE!");
        }
    }

    @Override
    public Filme consultar(int codigo) {
        String sql = "SELECT nome FROM Filme WHERE codigo = '" + codigo + "'";
        try {
            ResultSet rs = this.dataSource.executarSelect(sql);
            if (rs.next()) {
                return new Filme(codigo, rs.getString("nome"));
            }
        } catch (Exception e) {
            e.getStackTrace();
        }
        return null;
    }

    @Override
    public void remover(int codigo) {
        String sql = "DELETE FROM Filme WHERE codigo ='" + codigo +"'";
        try {
            dataSource.executarQueryGeral(sql);
        }catch(Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void alterar(int codigo, Filme dadosNovos) {
        Filme cTeste = this.consultar(dadosNovos.getCodigo());
        if(cTeste == null) {
            String sql = "UPDATE Filme set nome = '"+dadosNovos.getNome()+"', set codigo = '"+dadosNovos.getCodigo()+"' where codigo = '"+codigo+"'";
            try {
                dataSource.executarQueryGeral(sql);
            }catch(Exception e) {
                e.printStackTrace();
            }
        }
        else {
            System.err.println("ERRO NA ALTERACAO! CODIGO NOVO JA EXISTE!");
        }
    }


    @Override
    public List<Filme> consultarPorNome(String parteNome) {
        ArrayList<Filme> filmes = new ArrayList<>();
        String sql = "select * from Filme where nome like '%" + parteNome + "%'";

        try {
            ResultSet rs = this.dataSource.executarSelect(sql);

            while(rs.next()) {
                filmes.add(new Filme(rs.getInt("codigo"), rs.getString("nome")));
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
        return filmes;
    }

    @Override
    public List<Filme> obterTodosFilmes() {
        ArrayList<Filme> filmes = new ArrayList<>();
        String sql = "select * from Filme";

        try {
            ResultSet rs = this.dataSource.executarSelect(sql);

            while(rs.next()) {
                Filme f = new Filme(rs.getInt("codigo"), rs.getString("nome"));
                filmes.add(f);
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
        return filmes;
    }

    @Override
    public List<Unidade> obterUnidadeFilme(Filme filme) {
        // TODO Auto-generated method stub
        return null;
    }
  
}
