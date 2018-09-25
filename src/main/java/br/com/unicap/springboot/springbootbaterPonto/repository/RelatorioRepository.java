package br.com.unicap.springboot.springbootbaterPonto.repository;

import java.util.ArrayList;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.unicap.springboot.springbootbaterPonto.model.Relatorio;
import br.com.unicap.springboot.springbootbaterPonto.model.RelatorioKey;

public interface RelatorioRepository extends JpaRepository<Relatorio, RelatorioKey>{
	
	
//	@Query(value = "SELECT * FROM tbl_relatorio WHERE matricula like :matricula",nativeQuery = true)
//    public ArrayList<Relatorio> listarRelatoriosByMatricula(@Param("matricula") String matricula);
	
	@Query(value = "SELECT matricula, data, time(entrada) as entrada, time(saida) as saida FROM tbl_relatorio where matricula = :matricula",nativeQuery = true)
    public ArrayList<Relatorio> listarRelatoriosByMatricula(@Param("matricula") String matricula);
	
//	@Query(value = "SELECT * FROM tbl_relatorio",nativeQuery = true)
//	public ArrayList<Relatorio> listarRelatorios();
	
	// Gambiarra já que o spring/hibernate/java tá convertendo o horário pra UTC sempre (É UTC né?)
	@Query(value = "SELECT matricula, data, time(entrada) as entrada, time(saida) as saida FROM tbl_relatorio",nativeQuery = true)
	public ArrayList<Relatorio> listarRelatorios();
	
//	@Query(value = "SELECT * FROM tbl_relatorio WHERE (matricula like :matricula and data = date(sysdate()))",nativeQuery = true)
//    public Relatorio listarRelatorioHoje(@Param("matricula") String matricula);
	
	// Gambiarra já que o spring/hibernate/java tá convertendo o horário pra UTC sempre (É UTC né?)
	@Query(value = "SELECT matricula, data, time(entrada) as entrada, time(saida) as saida FROM tbl_relatorio WHERE (matricula like :matricula and data = date(sysdate()))",nativeQuery = true)
    public Relatorio listarRelatorioHoje(@Param("matricula") String matricula);
	
	//Bater ponto
	@Modifying
	@Query(value = "INSERT INTO tbl_relatorio (matricula,data,entrada) values (:matricula ,DATE(sysdate()), TIME(sysdate()))",nativeQuery = true)
	@Transactional
	public void baterPontoEntrada(@Param("matricula") String matricula);
	
	@Modifying
	@Query(value = "UPDATE tbl_relatorio SET saida=sysdate() WHERE (matricula = :matricula and DATE(sysdate()) = data )",nativeQuery = true)
	@Transactional
	public void baterPontoSaida(@Param("matricula") String matricula);
	
	//Verificação se já bateu ponto
	@Query(value = "select count(*) from tbl_relatorio where (matricula = :matricula and data=DATE(sysdate()))",nativeQuery = true)
	public int getEntrarHoje(@Param("matricula") String matricula);
	
	@Query(value = "select count(*) from tbl_relatorio where (matricula = :matricula and data=DATE(sysdate()) and entrada is not null and saida is null)",nativeQuery = true)
	public int getSairHoje(@Param("matricula") String matricula);
	
	
	
}