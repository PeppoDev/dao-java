package br.ufal.aracomp.jdbc.dao;

import java.util.List;

import br.ufal.aracomp.jdbc.model.Idioma;

public interface IDAOIdioma {
    public void adicionar(Idioma indioma);
    public void remover(int codigo);
    public void alterar(int codigoAntigo, Idioma dadosNovos);
    public Idioma consultar(int codigo);
    public List<Idioma> consultarPorNome(String parteNome);
    public List<Idioma> obterTodosIdioma();
}
