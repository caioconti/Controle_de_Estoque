package usf.model.entradaproduto;

import usf.model.basic.ModelBasic;

public class EntradaProduto implements ModelBasic {
	protected int id;
	protected String data;
	protected String usuario;
	protected String produto;
	protected String fornecedor;
	protected int quantidade;
	protected double valorUnitario;
	protected double valorTotal;
	
	public EntradaProduto() {
	}
	
	public EntradaProduto(int id) {
		this.id = id;
	}
	
	public EntradaProduto(String data, String produto, double valorUnitario, int quantidade, double valorTotal, String fornecedor, String usuario) {
		this.data = data;
		this.usuario = usuario;
		this.produto = produto;
		this.fornecedor = fornecedor;
		this.quantidade = quantidade;
		this.valorUnitario = valorUnitario;
		this.valorTotal = valorTotal;
	}

	public EntradaProduto(int id, String data, String produto, double valorUnitario, int quantidade, double valorTotal, String fornecedor, String usuario) {
		this(data, produto, valorUnitario, quantidade, valorTotal, fornecedor, usuario);
		this.id = id;
		
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public String getProduto() {
		return produto;
	}
	public void setProduto(String produto) {
		this.produto = produto;
	}
	public String getFornecedor() {
		return fornecedor;
	}
	public void setFornecedor(String fornecedor) {
		this.fornecedor = fornecedor;
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
