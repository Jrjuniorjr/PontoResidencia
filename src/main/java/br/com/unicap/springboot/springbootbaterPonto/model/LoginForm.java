package br.com.unicap.springboot.springbootbaterPonto.model;

public class LoginForm {
	private String matricula;
	private String senha;
	

	public LoginForm() {}
	
	public LoginForm(String matricula, String senha) {
		this.matricula = matricula;
		this.senha = senha;
	}
	
	public String getMatricula() {
		return matricula;
	}
	
	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}
	
	public String getSenha() {
		return senha;
	}
	
	public void setSenha(String senha) {
		this.senha = senha;
	}
}
