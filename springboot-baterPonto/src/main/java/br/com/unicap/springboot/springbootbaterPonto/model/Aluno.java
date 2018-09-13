package br.com.unicap.springboot.springbootbaterPonto.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;



@Entity
@Table(name = "tbl_alunos")
public class Aluno {
    
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "aluno_id")
    private Long id;
    
    @Column(name = "aluno_matr")
    private String matricula;

    
    @Column(name = "aluno_nome")
    private String nome;

    
    @Column(name = "aluno_senha")
    private String senha;
    
    @OneToMany(cascade = CascadeType.ALL,  mappedBy = "aluno", orphanRemoval = true)
    private List<Relatorio> relatorios = new ArrayList<>();

    public Aluno() {
    }
    
    



	public Long getId() {
		return id;
	}





	public void setId(Long id) {
		this.id = id;
	}





	public String getMatricula() {
		return matricula;
	}





	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}





	public String getNome() {
		return nome;
	}





	public void setNome(String nome) {
		this.nome = nome;
	}





	public String getSenha() {
		return senha;
	}





	public void setSenha(String senha) {
		this.senha = senha;
	}





	public List<Relatorio> getRelatorios() {
		return relatorios;
	}





	public void setRelatorios(List<Relatorio> relatorios) {
		this.relatorios = relatorios;
	}





	

	@Override
	public String toString() {
		return "Aluno [id=" + id + ", matricula=" + matricula + ", nome=" + nome + ", senha=" + senha + ", relatorios="
				+ relatorios + "]";
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
