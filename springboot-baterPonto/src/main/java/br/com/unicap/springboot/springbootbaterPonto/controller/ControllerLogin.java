package br.com.unicap.springboot.springbootbaterPonto.controller;


import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.fasterxml.jackson.databind.util.JSONWrappedObject;

import br.com.unicap.springboot.springbootbaterPonto.model.Aluno;
import br.com.unicap.springboot.springbootbaterPonto.model.Professor;
import br.com.unicap.springboot.springbootbaterPonto.repository.AlunoRepository;
import br.com.unicap.springboot.springbootbaterPonto.repository.ProfessorRepository;

@RestController
@RequestMapping("/login/")
public class ControllerLogin {
    @Autowired
    ProfessorRepository daoProfessor;
    @Autowired
    AlunoRepository daoAluno;

    @PostMapping
    public ResponseEntity<?> login (@RequestBody JSONWrappedObject body) {
    	
    	System.out.println(body);
    	
    	return null;
    }
   
    public ResponseEntity<Professor> loginProfessor (@RequestBody Professor professorLogin) {
    	
    	ResponseEntity<Professor> entity = null;
    	
    	if(daoProfessor.listarProfessorByMatricula(professorLogin.getMatricula())!=null) {
    		Professor professor = daoProfessor.listarProfessorByMatricula(professorLogin.getMatricula());
    		if(professor.getSenha() == professorLogin.getSenha()) {
    			entity = ResponseEntity.ok(professor);
    		}    
    	}else{
    		entity = ResponseEntity.notFound().build();
    	}
    	
    	return entity;
    	
    }
    
    public ResponseEntity<Aluno> loginAluno (@RequestBody Aluno alunoLogin) {
    	
    	ResponseEntity<Aluno> entity = null;
    	
    	System.out.println(alunoLogin);
    	
    	if(daoAluno.listarAlunoByMatricula(alunoLogin.getMatricula())!=null) {
    		Aluno aluno = daoAluno.listarAlunoByMatricula(alunoLogin.getMatricula());
    		System.out.println(aluno);
    		if(aluno.getSenha() == alunoLogin.getSenha()) {
    			entity = ResponseEntity.ok(aluno);
    		}    
    	}else{
    		entity = ResponseEntity.notFound().build();
    	}
    	
    	return entity;
    	
    }
    
}
