package br.com.unicap.springboot.springbootbaterPonto.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.unicap.springboot.springbootbaterPonto.model.Residente;;

public interface ResidenteRepository extends JpaRepository<Residente, String>{
	
	@Query(value = "SELECT * from tbl_residente where token = :token and status = 1",nativeQuery = true)
	public Residente getResidenteByToken(@Param("token") String token);

	@Query(value = "SELECT * from tbl_residente where matricula = :matricula and status = 1",nativeQuery = true)
	public Residente getResidenteByMatricula(@Param("matricula") String matricula);
	
	@Query(value = "SELECT * from tbl_residente where tipo=0 and status = 1",nativeQuery = true)
	public List<Residente> getResidentes();
	
	@Modifying
	@Query(value = "UPDATE tbl_residente SET token = :token WHERE matricula = :matricula",nativeQuery = true)
	@Transactional
	public void atualizarToken(@Param("token")String token, @Param("matricula")String matricula);
	
	@Modifying
	@Query(value = "UPDATE tbl_residente SET nome = :nome, senha = :senha, tipo = :tipo WHERE matricula = :matricula",nativeQuery = true)
	@Transactional
	public void atualizarResidente(@Param("nome")String nome, @Param("senha")String senha, @Param("tipo") String tipo, @Param("matricula")String matricula);
	
	@Modifying
	@Query(value = "UPDATE tbl_residente SET status = 0 WHERE matricula = :matricula",nativeQuery = true)
	@Transactional
	public void removerResidente(@Param("matricula") String matricula);

//	ESSA MERDA NÃO TEM CAPACIDADE DE FAZER UM SELECT DEPOIS DO UPDATE, QUAL A DIFICULDADE?
//	@Modifying
//	@Query(value = "UPDATE tbl_residente SET status = 0 WHERE matricula = :matricula ; select if( (select status from tbl_residente where matricula = :matricula2) = 0, true, false)",nativeQuery = true)
//	@Transactional
//	public int removerResidente2(@Param("matricula") String matricula, @Param("matricula2") String matricula2);
	
}