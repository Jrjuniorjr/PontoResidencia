package br.com.unicap.springboot.springbootbaterPonto.model;



import java.time.LocalTime;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_relatorio")
public class Relatorio{
	
	@EmbeddedId
    private RelatorioKey id;
	
	@Column(name = "data", insertable=false, updatable=false)
	private LocalTime data;
	
	@Column(name = "entrada")
	private LocalTime entrada;
	
	@Column(name = "saida")
	private LocalTime saida;
	
	public Relatorio() {}

	public RelatorioKey getId() {
		return id;
	}

	public void setId(RelatorioKey id) {
		this.id = id;
	}

	public LocalTime getData() {
		return data;
	}

	public void setData(LocalTime data) {
		this.data = data;
	}

	public LocalTime getEntrada() {
		return entrada;
	}

	public void setEntrada(LocalTime entrada) {
		this.entrada = entrada;
	}

	public LocalTime getSaida() {
		return saida;
	}

	public void setSaida(LocalTime saida) {
		this.saida = saida;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((data == null) ? 0 : data.hashCode());
		result = prime * result + ((entrada == null) ? 0 : entrada.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((saida == null) ? 0 : saida.hashCode());
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
		if (data == null) {
			if (other.data != null)
				return false;
		} else if (!data.equals(other.data))
			return false;
		if (entrada == null) {
			if (other.entrada != null)
				return false;
		} else if (!entrada.equals(other.entrada))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (saida == null) {
			if (other.saida != null)
				return false;
		} else if (!saida.equals(other.saida))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Relatorio [id=" + id + ", data=" + data + ", entrada=" + entrada + ", saida=" + saida + "]";
	}
	
	

}


