package br.com.unicap.springboot.springbootbaterPonto.model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.Date;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.NamedStoredProcedureQuery;
import javax.persistence.Table;
import javax.sql.DataSource;
import javax.validation.constraints.NotBlank;

import org.springframework.data.jpa.repository.query.Procedure;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.mysql.cj.jdbc.CallableStatement;

import ch.qos.logback.core.Context;
import ch.qos.logback.core.db.DataSourceConnectionSource;


@Entity
@Table(name = "tbl_ponto")
public class Relatorio {
    
    
    @Id
    @Column(name = "aluno_matr")
    private String alunomatricula;
    
    @Column(name = "aluno_id")
    private Long alunoid;
    
    @Column(name = "prof_id")
    private Long profid;
    
    @Column(name = "hora_ent")
    private Date horaent;

    @Column(name = "hora_sai")
    private Date horasai;

    
    public Relatorio() {
  
    }

    public String getAlunomatricula() {
		return alunomatricula;
	}

	public void setAlunomatricula(String alunomatricula) {
		this.alunomatricula = alunomatricula;
	}

	public Long getProfid() {
		return profid;
	}

	public void setProfid(Long profid) {
		this.profid = profid;
	}

	public void setAlunoid(Long alunoid) {
		this.alunoid = alunoid;
	}

	public Long getAlunoid() {
		return alunoid;
	}
    
	public void setAlunomatr(Long alunoid) {
		this.alunoid = alunoid;
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
		procs.setLong("id", alunoid);
		procs.execute();
		ResultSet rs = procs.getResultSet();
		nome = rs.getString("aluno_nome");
		}catch(Exception e) {
			e.getMessage();
		}
		
		return nome;
	}

	@Override
    public String toString() {
		return "Relatorio{" +"matricula:"+ alunomatricula + ", nome:" + nomeById(alunoid)+", entrada:"+horaent+", saida:"+horasai+'}';
    }
    
    @Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((alunoid == null) ? 0 : alunoid.hashCode());
		result = prime * result + ((horaent == null) ? 0 : horaent.hashCode());
		result = prime * result + ((horasai == null) ? 0 : horasai.hashCode());
		result = prime * result + ((profid == null) ? 0 : profid.hashCode());
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
		if (alunoid == null) {
			if (other.alunoid != null)
				return false;
		} else if (!alunoid.equals(other.alunoid))
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
		if (profid == null) {
			if (other.profid != null)
				return false;
		} else if (!profid.equals(other.profid))
			return false;
		return true;
	}

    
}
