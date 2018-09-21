package br.com.unicap.springboot.springbootbaterPonto.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.unicap.springboot.springbootbaterPonto.model.Residente;;

public interface ResidenteRepository extends JpaRepository<Residente, String>{
	
	@Query(value = "SELECT * from tbl_residente where token like :token",nativeQuery = true)
	public Residente getResidenteByToken(@Param("token") String token);

	@Query(value = "SELECT * from tbl_residente where matricula = ?1",nativeQuery = true)
	public Residente getResidenteByMatricula(String matricula);
	
}