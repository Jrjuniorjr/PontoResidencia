package br.com.unicap.springboot.springbootbaterPonto.model;

public class Professor{

    private String matricula;

    private String nome;

    private int senha;

    public Professor(String matricula, String nome, int senha) {
        this.matricula = matricula;
        this.nome = nome;
        this.senha = senha;
    }

    public String getMatricula() {
        return matricula;
    }

    public String getNome() {
        return nome;
    }

    public int getSenha() {
        return senha;
    }

    public void setSenha(int senha) {
        this.senha = senha;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

}
