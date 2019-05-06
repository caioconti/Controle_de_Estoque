package usf.model.usuario;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import usf.model.basic.ModelBasic;
import usf.model.usuario.ILogin;

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
		if (senha != null) {
			this.senha = getPassword(senha, ILogin.SHA_256);
		}
	}

	public Usuario(int id, String nome, String telefone, String email, String login, String senha) {
		this(nome, telefone, email, login, senha);
		this.id = id;
	}

	public Usuario(String login, String senha) {
		this.login = login;
		this.senha = senha;
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
		if (senha != null) {
			this.senha = getPassword(senha, ILogin.SHA_256);
		}
	}
	
	private static String stringHexa(byte[] bytes) {
		StringBuilder s = new StringBuilder();
		for (int i = 0; i < bytes.length; i++) {
			int parteAlta = ((bytes[i] >> 4) & 0xf) << 4;
			int parteBaixa = bytes[i] & 0xf;
			if (parteAlta == 0)
				s.append('0');
			s.append(Integer.toHexString(parteAlta | parteBaixa));
		}
		return s.toString();
	}
	
	private static byte[] gerarHash(String frase, String algoritmo) {
		try {
			MessageDigest md = MessageDigest.getInstance(algoritmo);
			md.update(frase.getBytes());
			return md.digest();
		} catch (NoSuchAlgorithmException e) {
			return null;
		}
	}
	
	public static String getPassword(String pwd, String algorithm) {
		return stringHexa(gerarHash(pwd, algorithm));
	}
	
}