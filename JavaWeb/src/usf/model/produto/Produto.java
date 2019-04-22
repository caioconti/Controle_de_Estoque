package usf.model.produto;

import usf.model.basic.ModelBasic;

public class Produto implements ModelBasic{
	protected int id;
	protected String nome;
	protected String descricao;
	protected String fornecedor;
	
	public Produto() {
	}
	
	public Produto(int id) {
		this.id = id;
	}
	
	public Produto(String nome, String descricao, String fornecedor) {
		this.nome = nome;
		this.descricao = descricao;
		this.fornecedor = fornecedor;
	}
	
	public Produto(int id, String nome, String descricao, String fornecedor) {
		this(nome, descricao, fornecedor);
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
