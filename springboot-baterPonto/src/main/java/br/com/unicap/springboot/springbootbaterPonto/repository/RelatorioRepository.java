package br.com.unicap.springboot.springbootbaterPonto.repository;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;

import br.com.unicap.springboot.springbootbaterPonto.model.Relatorio;;

public interface RelatorioRepository extends JpaRepository<Relatorio, Long>{
	
	@Procedure(name = "VALIDAR_PONTO_ENTRADA")
    public void VALIDAR_PONTO_ENTRADA(@Param("matricula") String matricula);
	
	@Procedure(name = "VALIDAR_PONTO_SAIDA")
    public void VALIDAR_PONTO_SAIDA(@Param("matricula") String matricula);

	@Query(value = "SELECT * FROM tbl_ponto WHERE aluno_id = (select aluno_id from tbl_alunos where aluno_matr = ?1)",nativeQuery = true)
    ArrayList<Relatorio> listarRelatoriosByMatricula(String matricula);
	
	@Query(value = "SELECT * FROM tbl_ponto",nativeQuery = true)
	ArrayList<Relatorio> listarRelatorios();
}