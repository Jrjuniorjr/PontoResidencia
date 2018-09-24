package br.com.unicap.springboot.springbootbaterPonto.model;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Embeddable
public class RelatorioKey implements Serializable{

	 private LocalDate data;
	 
	 @ManyToOne
	 @JoinColumn(name="matricula")
	 private Residente residente;
	 
	 public RelatorioKey() {
	}
	 
	 public RelatorioKey(Residente residente, LocalDate data) {
			this.residente = residente;
			this.data = data;
	}
	 

	public LocalDate getData() {
		return data;
	}

	public void setData(LocalDate data) {
		this.data = data;
	}

	public Residente getResidente() {
		return residente;
	}

	public void setResidente(Residente residente) {
		this.residente = residente;
	}
	
	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((data == null) ? 0 : data.hashCode());
		result = prime * result + ((residente == null) ? 0 : residente.hashCode());
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
		RelatorioKey other = (RelatorioKey) obj;
		if (data == null) {
			if (other.data != null)
				return false;
		} else if (!data.equals(other.data))
			return false;
		if (residente == null) {
			if (other.residente != null)
				return false;
		} else if (!residente.equals(other.residente))
			return false;
		return true;
	}
	 
	
	 
}

