package br.ufal.aracomp.jdbc.dao;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.ufal.aracomp.jdbc.dataSource.IDataSource;
import br.ufal.aracomp.jdbc.model.Locacao;


public class DAOLocacao implements IDAOLocacao{
    private IDataSource dataSource;

    public DAOLocacao(IDataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void adicionar(Locacao locacao) {
        String sql = "INSERT INTO Locacao(data_locacao, data_devolucao,login_clienteFK, codigo_unidadeFK) VALUES ('"+locacao.getData_locacao()+"','"+locacao.getData_devolucao()+"','"
                + locacao.getCliente().getLogin() + "','" + locacao.getUnidade().getCodigo() + "')";
        try {
            dataSource.executarQueryGeral(sql);
        }catch(Exception e) {
            e.printStackTrace();
        }
    
    }

    @Override
    public Locacao consultar(int codigo, String login, Date data_locacao) {
        String sql = "SELECT data_locacao, data_devolucao FROM Locacao WHERE login_clienteFK = '" + login + "' and WHERE codigo_unidadeFK ='"+codigo+"'and WHERE data_locacao ='"
                + data_locacao + "";
        try {
            ResultSet rs = this.dataSource.executarSelect(sql);
            if (rs.next()) {
                return new Locacao(rs.getDate("data_locacao"), rs.getDate("data_devolucao"));
            }
        } catch (Exception e) {
            e.getStackTrace();
        }
        return null;
    }

    @Override
    public void remover(int codigo, String login, Date data_locacao) {
        String sql = "DELETE FROM Locacao WHERE login_clienteFK = '" + login + "' and WHERE codigo_unidadeFK ='"
                + codigo + "'nd WHERE data_locacao ='" + data_locacao + "";;
        try {
            dataSource.executarQueryGeral(sql);
        }catch(Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void alterar(int codigo, String login, Date data_locacao, Locacao dadosNovos) {
        Locacao cTeste = this.consultar(codigo, login, dadosNovos.getData_locacao());
        if(cTeste == null) {
            String sql = "UPDATE Locacao set data_devolucao = '"+dadosNovos.getData_devolucao()+"', set data_locacao = '"+dadosNovos.getData_devolucao()+"' where codigo = '"+codigo+"'";
            try {
                dataSource.executarQueryGeral(sql);
            }catch(Exception e) {
                e.printStackTrace();
            }
        }
        else {
            System.err.println("ERRO NA ALTERACAO! ESSA LOCACAO JÁ EXISTE");
        }
    }

    @Override
    public List<Locacao> consultarPorCliente(String login) {
        String sql = String.format("SELECT * where login_clienteFK = '%s' ", login);
        ArrayList<Locacao> locacoes =  new ArrayList<Locacao>();

        try {
            ResultSet rs = this.dataSource.executarSelect(sql);

            if (rs.next()) {
                Locacao  locacao =  new Locacao(rs.getDate("data_locacao"), rs.getDate("data_devolucao"));
                locacoes.add(locacao);
            }
        } catch (Exception e) {
            e.getStackTrace();
        }
        return locacoes;
    }
}
