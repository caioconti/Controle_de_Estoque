package usf.model.usuario;

import usf.model.basic.ModelBasic;

public class Usuario implements ModelBasic{
	protected int id;
	protected String nome;
	protected String telefone;
	protected String email;
	protected String login;
	protected String senha;
	
	public Usuario() {
	}
	
	public Usuario(int id) {
		this.id = id;
	}

	public Usuario(String nome, String telefone, String email, String login, String senha) {
		this.nome = nome;
		this.telefone = telefone;
		this.email = email;
		this.login = login;
		this.senha = senha;
	}

	public Usuario(int id, String nome, String telefone, String email, String login, String senha) {
		this(nome, telefone, email, login, senha);
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
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
}