package br.com.unicap.springboot.springbootbaterPonto.model;

import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "tbl_alunos")
public class Aluno {
    
    @NotBlank
    public String matricula;

    @NotBlank
    public String nome;

    @NotBlank
    private int senha;

    public Aluno(String matricula, String nome, int senha) {
        this.matricula = matricula;
        this.nome = nome;
        this.senha = senha;
    }

    public int getSenha() {
        return senha;
    }

    public String getMatricula() {
        return matricula;
    }

    public String getNome() {
        return nome;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setSenha(int senha) {
        this.senha = senha;
    }

    @Override
    public String toString() {
        return "Aluno{" + "matricula=" + matricula + ", nome=" + nome + ", senha=" + senha + '}';
    }

    
}
