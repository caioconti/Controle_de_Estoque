package usf.model.fornecedor;

import usf.model.basic.ModelBasic;

public class Fornecedor implements ModelBasic{
	protected int id;
	protected String nome;
	protected String telefone;
	protected String email;
	protected String cnpj;
	protected String cidade;
	
	public Fornecedor() {
	}
	
	public Fornecedor(int id) {
		this.id = id;
	}

	public Fornecedor(String nome, String telefone, String email, String cnpj, String cidade) {
		this.nome = nome;
		this.telefone = telefone;
		this.email = email;
		this.cnpj = cnpj;
		this.cidade = cidade;
	}

	public Fornecedor(int id, String nome, String telefone, String email, String cnpj, String cidade) {
		this(nome, telefone, email, cnpj, cidade);
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
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getCnpj() {
		return cnpj;
	}
	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}
	public String getCidade() {
		return cidade;
	}
	public void setCidade(String cidade) {
		this.cidade = cidade;
	}
}
