/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.unicap.springboot.springbootbaterPonto.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.unicap.springboot.springbootbaterPonto.model.Aluno;
import br.com.unicap.springboot.springbootbaterPonto.repository.AlunoRepository;

@RestController
@RequestMapping("/aluno")
public class ControllerAluno {

    @Autowired
    AlunoRepository daoAluno;

    @PostMapping("/cadastrar/")
    public ResponseEntity<Aluno> inserir(@RequestBody Aluno aluno) {
        Aluno alunoSalvo = daoAluno.save(aluno);
        return ResponseEntity.status(HttpStatus.CREATED).body(alunoSalvo);
        
    }

    @DeleteMapping("/deletar/{matricula}")
    public ResponseEntity<Aluno> remover(@PathVariable String matricula){
        Aluno aluno = daoAluno.listarAlunoByMatricula(matricula);
        System.out.println("Aluno"+aluno);
        if(aluno != null) {
        	daoAluno.delete(aluno);
        	return ResponseEntity.ok(aluno);
        }else {
        return ResponseEntity.notFound().build();      
        }
    }
    
    @PostMapping
    public ResponseEntity<Aluno> login (@RequestBody Aluno alunoLogin) {
    	
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

    @GetMapping("/{matricula}")
    public ResponseEntity<Aluno> consultar(@PathVariable String matricula) {
    	Aluno aluno = daoAluno.listarAlunoByMatricula(matricula);
    	if(aluno == null) {
    		return ResponseEntity.notFound().build();    	}
    	else {
    		return ResponseEntity.ok(aluno);
    	}
             
    }
    
    @GetMapping
    public List<Aluno> listar(){
    	return daoAluno.listarAlunos();
    }    

    @PutMapping("/{matricula}")
    public ResponseEntity<Aluno> alterarSenha(@PathVariable String matricula, @RequestBody String senha) {
    	
    	Aluno aluno = daoAluno.listarAlunoByMatricula(matricula);
    	
    	if(aluno == null) {
    		return ResponseEntity.notFound().build();
    	}else {
    		aluno.setSenha(senha);
    		Aluno alunoAlterado = daoAluno.save(aluno);
    		return ResponseEntity.status(HttpStatus.OK).body(alunoAlterado);
    	}
    }
    
    
}
