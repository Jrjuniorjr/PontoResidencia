package br.com.unicap.springboot.springbootbaterPonto.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "tbl_alunos")
public class Aluno {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    public Aluno(String matricula, String nome, int senha) {
        
        this.matricula = matricula;
        this.nome = nome;
        this.senha = senha;
    }

    public Aluno() {
    }
    
    public Long getId() {
    	return id;
    }
    
    public void setId(Long id) {
    	this.id = id;
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
    
    @Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Aluno other = (Aluno) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

    
}
