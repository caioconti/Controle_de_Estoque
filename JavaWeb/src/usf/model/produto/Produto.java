package usf.model.produto;

import usf.model.basic.ModelBasic;

public class Produto implements ModelBasic{
	protected int id;
	protected String nome;
	protected double valorUnitario;
	protected int quantidade;
	protected String descricao;
	protected String fornecedor;
	
	public Produto() {
	}
	
	public Produto(int id) {
		this.id = id;
	}
	
	public Produto(String nome, double valorUnitario, int quantidade, String descricao, String fornecedor) {
		this.nome = nome;
		this.valorUnitario = valorUnitario;
		this.quantidade = quantidade;
		this.descricao = descricao;
		this.fornecedor = fornecedor;
	}
	
	public Produto(int id, String nome, double valorUnitario, int quantidade, String descricao, String fornecedor) {
		this(nome, valorUnitario, quantidade, descricao, fornecedor);
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
	public double getValorUnitario() {
		return valorUnitario;
	}
	public void setValorUnitario(double valorUnitario) {
		this.valorUnitario = valorUnitario;
	}
	public int getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
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
