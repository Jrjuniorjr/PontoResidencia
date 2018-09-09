package br.com.unicap.springboot.springbootbaterPonto.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import org.hibernate.annotations.ForeignKey;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;


@Entity
@Table(name = "tbl_alunos")
public class Aluno {
    
    
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "aluno_id")
    private Long id;
    
    @Id
    @NotBlank
    @Column(name = "aluno_matr")
    private String matricula;

    @NotBlank
    @Column(name = "aluno_nome")
    private String nome;

    @NotBlank
    @Column(name = "aluno_senha")
    private String senha;
    
    @NotBlank
    //@ManyToOne
    //@JoinColumn(name = "prof_id")
    //private Professor profid;
    @Column(name = "prof_id")
    private Long profid;
    
    
    //Relação entre Professor x Aluno
    /*@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn (name = "prof_id")
    private Professor professor;*/
    
     
    /*public Aluno(Long id, String matricula, String nome, int senha) {
        //this.id = id;
        this.matricula = matricula;
        this.nome = nome;
        this.senha = senha;
    }*/

    public Aluno() {
    }
	
    /*public Professor getProfid() {
	return profid;
}

public void setProfid(Professor profid) {
	this.profid = profid;
}*/
    
    
    
    public Long getId() {
    	return id;
    }
	
	public Long getProfid() {
		return profid;
	}

	public void setProfid(Long profid) {
		this.profid = profid;
	}

	public void setId(Long id) {
    	this.id = id;
    }

    public String getSenha() {
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

    public void setSenha(String senha) {
        this.senha = senha;
    }

    @Override
    public String toString() {
        return "Aluno{" + "matricula=" + matricula + ", nome=" + nome + ", senha=" + senha + ", profid=" + profid + '}';
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
