package usf.model.saidaproduto;

import usf.model.basic.ModelBasic;

public class SaidaProduto implements ModelBasic {
	
	protected int id;
	protected String tipo;
	protected String data;
	protected String produto;
	protected double valorUnitario;
	protected int quantidade;
	protected double valorTotal;
	protected String usuario;
	
	public SaidaProduto() {
	}
	
	public SaidaProduto(int id) {
		this.id = id;
	}
	
	public SaidaProduto(String tipo, String data, String produto, double valorUnitario, int quantidade, double valorTotal, String usuario) {
		this.tipo = tipo;
		this.data = data;
		this.usuario = usuario;
		this.produto = produto;
		this.quantidade = quantidade;
		this.valorUnitario = valorUnitario;
		this.valorTotal = valorTotal;
	}

	public SaidaProduto(int id, String tipo, String data, String produto, double valorUnitario, int quantidade, double valorTotal, String usuario) {
		this(tipo, data, produto, valorUnitario, quantidade, valorTotal, usuario);
		this.id = id;
		
	}

	public SaidaProduto(String tipo, String data, String produto, double valorUnitario, int quantidade, double valorTotal) {
		this.tipo = tipo;
		this.data = data;
		this.produto = produto;
		this.quantidade = quantidade;
		this.valorUnitario = valorUnitario;
		this.valorTotal = valorTotal;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
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
