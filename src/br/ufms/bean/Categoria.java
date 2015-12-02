package br.ufms.bean;

import br.ufms.dao.daoCategoria;

public class Categoria {
	private int codigoCategoria;
	private String descricaoCat;
	private daoCategoria dao;

	public Categoria() {
		this.codigoCategoria = 0;
		this.descricaoCat = "";
	}

	public Categoria(int codigoCategoria, String descricaoCat) {
		super();
		this.codigoCategoria = codigoCategoria;
		this.descricaoCat = descricaoCat;
	}

	public int getCodigoCategoria() {
		return codigoCategoria;
	}

	public void setCodigoCategoria(int codigoCategoria) {
		this.codigoCategoria = codigoCategoria;
	}

	public String getDescricaoCat() {
		return descricaoCat;
	}

	public void setDescricaoCat(String descricaoCat) {
		this.descricaoCat = descricaoCat;
	}

	public void cadastrarCategoria() {
		if (dao.inserir(this))
			System.out.println("Categoria inserida com sucesso!");
		else
			System.out.println("Categoria já cadastrada");
	}

	public void deletarCategoria() {
		if (dao.excluir(this)) {
			System.out.println("Categoria excluida com sucesso!");
		} else {
			System.out.println("Não foi possivel excluir!");
		}
	}
	
	public void buscarCategoria(int codCat){

		Categoria cat = new Categoria();
		
		cat = dao.buscarCategoria(codCat);
		
		this.setDescricaoCat(cat.getDescricaoCat());
				
	}
	
	
	
}

