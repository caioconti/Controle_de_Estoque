package usf.model.produto;

import usf.model.basic.ModelBasic;

public class Produto implements ModelBasic{
	
	protected int id;
	protected String nome;
	protected double valorCompra;
	protected double valorVenda;
	protected String descricao;
	protected String fornecedor;
	
	public Produto() {
	}
	
	public Produto(int id) {
		this.id = id;
	}
	
	public Produto(String nome, double valorCompra, double valorVenda, String descricao, String fornecedor) {
		this.nome = nome;
		this.valorCompra = valorCompra;
		this.valorVenda = valorVenda;
		this.descricao = descricao;
		this.fornecedor = fornecedor;
	}
	
	public Produto(int id, String nome, double valorCompra, double valorVenda, String descricao, String fornecedor) {
		this(nome, valorCompra, valorVenda, descricao, fornecedor);
		this.id = id;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public double getValorCompra() {
		return valorCompra;
	}

	public void setValorCompra(double valorCompra) {
		this.valorCompra = valorCompra;
	}

	public double getValorVenda() {
		return valorVenda;
	}

	public void setValorVenda(double valorVenda) {
		this.valorVenda = valorVenda;
	}

	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	public String getFornecedor() {
		return fornecedor;
	}
	public void setFornecedor(String fornecedor) {
		this.fornecedor = fornecedor;
	}
}
