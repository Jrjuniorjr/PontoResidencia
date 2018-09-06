package br.com.unicap.springboot.springbootbaterPonto.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "tbl_alunos")
public class Aluno {
    
    @Id
    @Column(name = "aluno_id")
    private Long id;
    
    @NotBlank
    @Column(name = "aluno_matr")
    private String matricula;

    @NotBlank
    @Column(name = "aluno_nome")
    private String nome;

    @NotBlank
    @Column(name = "aluno_senha")
    private int senha;

    public Aluno(Long id, String matricula, String nome, int senha) {
        this.id = id;
        this.matricula = matricula;
        this.nome = nome;
        this.senha = senha;
    }

    public Aluno() {
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
