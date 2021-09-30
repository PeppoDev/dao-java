package br.ufal.aracomp.jdbc.dao;

import java.util.List;
import java.util.Date;

import br.ufal.aracomp.jdbc.model.Locacao;


public interface IDAOLocacao {
    public void adicionar(Locacao locacao);
    public void remover(int codigo, String login, Date data_locacao);
    public void alterar(int codigo, String login, Date data_locacao, Locacao novosDados);
    public Locacao consultar(int codigo, String login, Date data_locacao);
    public List<Locacao> consultarPorCliente(String login);
}
