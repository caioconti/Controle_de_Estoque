package usf.model.estoque;

import usf.model.basic.ModelBasic;

public class Estoque implements ModelBasic {
	
	protected int id;
	protected String produto;
	protected int quantidade;
	
	public Estoque() {
	}
	
	public Estoque(int id) {
		this.id = id;
	}
	
	public Estoque(String produto, int quantidade) {
		this.produto = produto;
		this.quantidade = quantidade;
	}

	public Estoque(int id, String produto, int quantidade) {
		this(produto, quantidade);
		this.id = id;
		
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public String getProduto() {
		return produto;
	}
	public void setProduto(String produto) {
		this.produto = produto;
	}
	
	public int getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

}