package br.ufal.aracomp.jdbc.dao;

import java.util.List;

import br.ufal.aracomp.jdbc.model.Filme;
import br.ufal.aracomp.jdbc.model.Unidade;

public interface IDAOFilme {
    public void adicionar(Filme filme);
    public void remover(int codigo);
    public void alterar(int codigoAntigo, Filme dadosNovos);
    public Filme consultar(int codigo);
    public List<Filme> consultarPorNome(String parteNome);
    public List<Filme> obterTodosFilmes();
    public List<Unidade> obterUnidadeFilme(Filme filme);
}
