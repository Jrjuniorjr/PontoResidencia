/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.unicap.springboot.springbootbaterPonto.repository;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.unicap.springboot.springbootbaterPonto.model.Aluno;

public interface AlunoRepository extends JpaRepository<Aluno, Long> {
	
	@Query(value = "SELECT * FROM tbl_alunos WHERE aluno_matr = ?1",nativeQuery = true)
    Aluno listarAlunoByMatricula(String matricula);
	
	@Query(value = "SELECT * FROM tbl_alunos",nativeQuery = true)
	ArrayList<Aluno> listarAlunos();	
	
}