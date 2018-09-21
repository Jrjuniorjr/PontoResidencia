package br.com.unicap.springboot.springbootbaterPonto.repository;

import java.util.ArrayList;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import br.com.unicap.springboot.springbootbaterPonto.model.Relatorio;
import br.com.unicap.springboot.springbootbaterPonto.model.RelatorioKey;

public interface RelatorioRepository extends JpaRepository<Relatorio, RelatorioKey>{
	
	//
	@Query(value = "SELECT * FROM tbl_relatorio WHERE matricula = ?1",nativeQuery = true)
    public ArrayList<Relatorio> listarRelatoriosByMatricula(String matricula);
	
	@Query(value = "SELECT * FROM tbl_relatorio",nativeQuery = true)
	public ArrayList<Relatorio> listarRelatorios();
	
	@Query(value = "SELECT * FROM tbl_relatorio WHERE (matricula = ?1 and data = DATE(sysdate()))",nativeQuery = true)
    public Relatorio listarRelatorioHoje(String matricula);
	
	//Bater ponto
	@Modifying
	@Query(value = "INSERT INTO tbl_relatorio (matricula,data,entrada) values ( ?1,DATE(sysdate()), TIME(sysdate()))",nativeQuery = true)
	@Transactional
	public void baterPontoEntrada(String matricula);
	
	@Modifying
	@Query(value = "UPDATE tbl_relatorio SET saida=sysdate() WHERE (matricula = ?1 and DATE(sysdate())) = data",nativeQuery = true)
	@Transactional
	public void baterPontoSaida(String matricula);
	
	//Verificação se já bateu ponto
	@Query(value = "select count(*) from tbl_relatorio where (matricula = ?1 and data=DATE(sysdate()))",nativeQuery = true)
	public int getEntrarHoje(String matricula);
	
	@Query(value = "select count(*) from tbl_relatorio where (matricula = ?1 and data=DATE(sysdate()) and entrada != null and saida = null)",nativeQuery = true)
	public int getSairHoje(String matricula);
	
	
	
}