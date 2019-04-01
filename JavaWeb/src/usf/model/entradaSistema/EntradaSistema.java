package usf.model.entradaSistema;

import usf.model.basic.ModelBasic;

public class EntradaSistema implements ModelBasic{
	protected int id;
	protected String nome;
	protected String login;
	protected String data;
	protected String hora;
	
	public EntradaSistema(int id) {
		this.id = id;
	}

	public EntradaSistema(int id, String nome, String data, String hora) {
		this.id = id;
		this.nome = nome;
		this.data = data;
		this.hora = hora;
	}

	public EntradaSistema(int id, String nome, String login, String data, String hora) {
		this.id = id;
		this.nome = nome;
		this.login = login;
		this.data = data;
		this.hora = hora;
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

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getHora() {
		return hora;
	}

	public void setHora(String hora) {
		this.hora = hora;
	}
}
