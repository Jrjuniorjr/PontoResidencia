package br.com.unicap.springboot.springbootbaterPonto.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "tbl_professor")
public class Professor {

    @Id
    @Column(name = "prof_id")
    private Long id;
    
    @NotBlank
    @Column(name = "prof_matr")
    private String matricula;

    @NotBlank
    @Column(name = "prof_nome")
    private String nome;

    @NotBlank
    @Column(name = "prof_senha")
    private int senha;

    public Professor(Long id, String matricula, String nome, int senha) {
        this.id = id;
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
