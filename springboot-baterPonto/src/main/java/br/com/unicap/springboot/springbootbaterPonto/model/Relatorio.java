package br.com.unicap.springboot.springbootbaterPonto.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedStoredProcedureQueries;
import javax.persistence.NamedStoredProcedureQuery;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureParameter;
import javax.persistence.Table;



@NamedStoredProcedureQueries({
    @NamedStoredProcedureQuery(name = "VALIDAR_PONTO_ENTRADA", 
                               procedureName = "VALIDAR_PONTO_ENTRADA",
                               parameters = {
                                     @StoredProcedureParameter(mode = ParameterMode.IN, name = "matricula", type = String.class)
                               }),
    @NamedStoredProcedureQuery(name = "VALIDAR_PONTO_SAIDA", 
    procedureName = "VALIDAR_PONTO_SAIDA",
    parameters = {
          @StoredProcedureParameter(mode = ParameterMode.IN, name = "matricula", type = String.class)
    })
})

@Entity
@Table(name = "tbl_ponto")
public class Relatorio {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "relatorio_id")
	private Long id;
	
    @ManyToOne
    @JoinColumn(name = "aluno_id")
    private Aluno aluno;
  
    @ManyToOne
    @JoinColumn(name = "prof_id")
    private Professor professor;
    
    @Column(name = "hora_ent")
    private Date horaent;

    @Column(name = "hora_sai")
    private Date horasai;
    
    public Relatorio() {
  
    }

	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public Aluno getAluno() {
		return aluno;
	}


	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}


	public Professor getProfessor() {
		return professor;
	}


	public void setProfessor(Professor professor) {
		this.professor = professor;
	}


	public Date getHoraent() {
		return horaent;
	}


	public void setHoraent(Date horaent) {
		this.horaent = horaent;
	}


	public Date getHorasai() {
		return horasai;
	}


	public void setHorasai(Date horasai) {
		this.horasai = horasai;
	}


	@Override
	public String toString() {
		return "Relatorio [aluno=" + aluno + ", professor=" + professor + ", horaent=" + horaent + ", horasai="
				+ horasai + "]";
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
		Relatorio other = (Relatorio) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	
    
}
