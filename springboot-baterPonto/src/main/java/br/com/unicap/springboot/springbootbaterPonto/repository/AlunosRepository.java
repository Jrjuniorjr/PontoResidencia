package br.com.unicap.springboot.springbootbaterPonto.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.unicap.springboot.springbootbaterPonto.model.Aluno;

public interface AlunosRepository extends JpaRepository<Aluno, Long>{
	
	List<Aluno> findByMatricula (String matricula);
	
}
