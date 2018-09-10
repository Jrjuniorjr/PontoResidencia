package br.com.unicap.springboot.springbootbaterPonto.model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.Date;

import javax.naming.InitialContext;
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
import javax.sql.DataSource;


@Entity
@Table(name = "tbl_ponto")
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
    })/*,
    @NamedStoredProcedureQuery(name = "ID_BY_MATRICULA", 
    procedureName = "ID_BY_MATRICULA",
    parameters = {
          @StoredProcedureParameter(mode = ParameterMode.IN, name = "matricula", type = String.class),
          @StoredProcedureParameter(mode = ParameterMode.IN, name = "id", type = Long.class)
    })*/
})


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

	@Override
	public String toString() {
		return "Relatorio [aluno=" + aluno + ", professor=" + professor + ", horaent=" + horaent + ", horasai="
				+ horasai + "]";
	}

	public String matriculaById(Long alunoid) {
		
		String matricula = null;
		try {
		InitialContext ctx = new InitialContext();
		DataSource ds;
		ds = (DataSource)ctx.lookup("jdbc:mysql://localhost:3306/db_sistemaponto");
		Connection connection = ds.getConnection();
		java.sql.CallableStatement proc = connection.prepareCall("{ call MATRICULA_BY_ID_ALUNO(id)}");            
		proc.setLong("id", alunoid);
		proc.execute();
		ResultSet rs = proc.getResultSet();
		matricula = rs.getString("aluno_matr");
		}catch(Exception e) {
			e.getMessage();
		}
		
		return matricula;
	}
	
	public String nomeById(Long idAluno) {
		String nome = null;
		try {
		InitialContext ctxs = new InitialContext();
		DataSource dss;
		dss = (DataSource)ctxs.lookup("jdbc:mysql://localhost:3306/db_sistemaponto");
		Connection connection = dss.getConnection();
		java.sql.CallableStatement procs = connection.prepareCall("{ select MATRICULA_BY_ID (id)}");            
		//procs.setLong("id", alunoid);
		procs.execute();
		ResultSet rs = procs.getResultSet();
		nome = rs.getString("aluno_nome");
		}catch(Exception e) {
			e.getMessage();
		}
		
		return nome;
	}

    
    

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((aluno == null) ? 0 : aluno.hashCode());
		result = prime * result + ((horaent == null) ? 0 : horaent.hashCode());
		result = prime * result + ((horasai == null) ? 0 : horasai.hashCode());
		result = prime * result + ((professor == null) ? 0 : professor.hashCode());
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
		if (aluno == null) {
			if (other.aluno != null)
				return false;
		} else if (!aluno.equals(other.aluno))
			return false;
		if (horaent == null) {
			if (other.horaent != null)
				return false;
		} else if (!horaent.equals(other.horaent))
			return false;
		if (horasai == null) {
			if (other.horasai != null)
				return false;
		} else if (!horasai.equals(other.horasai))
			return false;
		if (professor == null) {
			if (other.professor != null)
				return false;
		} else if (!professor.equals(other.professor))
			return false;
		return true;
	}

    
}
