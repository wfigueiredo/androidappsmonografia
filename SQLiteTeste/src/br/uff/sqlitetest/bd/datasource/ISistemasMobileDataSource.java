package br.uff.sqlitetest.bd.datasource;

import java.util.List;

import br.uff.sqlitetest.modelo.SistemaMobile;

public interface ISistemasMobileDataSource {
	
	public long criar(SistemaMobile sistemaMobile);
	
	public void remover(String id);
	
	public void atualizar(SistemaMobile sistemaMobile);
	
	public List<SistemaMobile> buscarTodos();
	
	public SistemaMobile buscarPorNome(String nome);
}