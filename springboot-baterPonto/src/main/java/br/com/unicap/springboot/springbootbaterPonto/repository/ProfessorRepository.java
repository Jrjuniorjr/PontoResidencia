/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.unicap.springboot.springbootbaterPonto.repository;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.unicap.springboot.springbootbaterPonto.model.Professor;

public interface ProfessorRepository extends JpaRepository<Professor, Long>{
	
	@Query(value = "SELECT * FROM tbl_professor WHERE prof_matr = ?1",nativeQuery = true)
	ArrayList<Professor> listarProfessorByMatricula(String matricula);
	
	@Query(value = "SELECT * FROM tbl_professor",nativeQuery = true)
	ArrayList<Professor> listarProfessor();

}