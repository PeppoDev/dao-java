package br.ufal.aracomp.jdbc.dao;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import br.ufal.aracomp.jdbc.dataSource.IDataSource;
import br.ufal.aracomp.jdbc.model.Idioma;

public class DAOIdioma implements IDAOIdioma {
    private IDataSource dataSource;

    public DAOIdioma(IDataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void adicionar(Idioma idioma) {
        Idioma cTeste = this.consultar(idioma.getCodigo());
        if (cTeste == null) {
            String sql = "INSERT INTO IDIOMA(codigo, nome) VALUES('" + idioma.getCodigo() + "','" + idioma.getNome()
                    + "')";
            try {
                dataSource.executarQueryGeral(sql);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            System.err.println("NOME JA EXISTENTE!");
        }
    }

    @Override
    public Idioma consultar(int codigo) {
        String sql = "SELECT nome FROM IDIOMA WHERE codigo = '" + codigo + "'";
        try {
            ResultSet rs = this.dataSource.executarSelect(sql);
            if (rs.next()) {
                return new Idioma(codigo, rs.getString("nome"));
            }
        } catch (Exception e) {
            e.getStackTrace();
        }
        return null;
    }

    @Override
    public void remover(int codigo) {
        String sql = "DELETE FROM Idioma WHERE codigo ='" + codigo + "'";
        try {
            dataSource.executarQueryGeral(sql);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void alterar(int codigo, Idioma dadosNovos) {
        Idioma cTeste = this.consultar(dadosNovos.getCodigo());
        if (cTeste == null) {
            String sql = "UPDATE Idioma set nome = '" + dadosNovos.getNome() + "', set codigo = '"
                    + dadosNovos.getCodigo() + "' where codigo = '" + codigo + "'";
            try {
                dataSource.executarQueryGeral(sql);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            System.err.println("ERRO NA ALTERACAO! CODIGO NOVO JA EXISTE!");
        }
    }

    @Override
    public List<Idioma> consultarPorNome(String parteNome) {
        ArrayList<Idioma> idiomas = new ArrayList<Idioma>();
        String sql = "select * from Idioma where nome like '%" + parteNome + "%'";

        try {
            ResultSet rs = this.dataSource.executarSelect(sql);

            while (rs.next()) {
                idiomas.add(new Idioma(rs.getInt("codigo"), rs.getString("nome")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return idiomas;
    }

    @Override
    public List<Idioma> obterTodosIdioma() {
        ArrayList<Idioma> idiomas = new ArrayList<>();
        String sql = "select * from cliente";

        try {
            ResultSet rs = this.dataSource.executarSelect(sql);

            while (rs.next()) {
                Idioma i = new Idioma(rs.getInt("codigo"), rs.getString("nome"));
                idiomas.add(i);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return idiomas;
    }

   

}
