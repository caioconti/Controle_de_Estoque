package usf.model.estoque;

import usf.model.basic.ModelBasic;

public class Estoque implements ModelBasic {
	protected int id;
	protected String produto;
	protected int quantidade;
	protected double valorUnitario;
	protected double valorTotal;
	
	public Estoque() {
	}
	
	public Estoque(int id) {
		this.id = id;
	}
	
	public Estoque(String produto, double valorUnitario, int quantidade, double valorTotal) {
		this.produto = produto;
		this.quantidade = quantidade;
		this.valorUnitario = valorUnitario;
		this.valorTotal = valorTotal;
	}

	public Estoque(int id, String produto, double valorUnitario, int quantidade, double valorTotal, String fornecedor, String usuario) {
		this(produto, valorUnitario, quantidade, valorTotal);
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
	
	public double getValorUnitario() {
		return valorUnitario;
	}
	public void setValorUnitario(double valorUnitario) {
		this.valorUnitario = valorUnitario;
	}
	
	public double getValorTotal() {
		return valorTotal;
	}
	public void setValorTotal(double valorTotal) {
		this.valorTotal = valorTotal;
	}
}