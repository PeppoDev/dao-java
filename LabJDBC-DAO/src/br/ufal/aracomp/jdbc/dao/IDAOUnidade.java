package br.ufal.aracomp.jdbc.dao;


import br.ufal.aracomp.jdbc.model.Unidade;

public interface IDAOUnidade {
    public void adicionar(Unidade unidade);
    public void remover(int codigo);
    public void alterar(int codigoAntigo, Unidade dadosNovos);
    public Unidade consultar(int codigo);
}


