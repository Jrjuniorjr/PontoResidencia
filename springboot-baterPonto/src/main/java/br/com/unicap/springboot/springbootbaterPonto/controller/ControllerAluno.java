/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.unicap.springboot.springbootbaterPonto.controller;

import br.com.unicap.springboot.springbootbaterPonto.model.Aluno;
import br.com.unicap.springboot.springbootbaterPonto.repository.AlunoRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/aluno")
public class ControllerAluno {

    @Autowired
    AlunoRepository daoAluno;

    @PostMapping
    public Aluno inserir(@RequestBody Aluno aluno) {
        return daoAluno.save(aluno);
        
    }
    
    @PostMapping("/baterPonto/{matricula}")
    public void baterPonto(@PathVariable String matricula ) {
    	
    }
    
    /*@GetMapping("/baterPonto/{matricula}")
    public HandlerExceptionResolver responderPonto() {
    }*/

    @DeleteMapping("/{matricula}")
    public void remover(@PathVariable String matricula) {
        Aluno aluno = daoAluno.getOne(matricula);
        daoAluno.delete(aluno);
        
    }

    @GetMapping("/{matricula}")
    public Aluno consultar(@PathVariable String matricula) {
    	
    	return daoAluno.getOne(matricula);
             
    }
    
    @GetMapping
    public List<Aluno> listar(){
    	return daoAluno.findAll();
    }

    @PutMapping("/{matricula}")
    public Aluno alterarSenha(@PathVariable String matricula, @RequestBody String senha) { 
    	
    	Aluno aluno = daoAluno.getOne(matricula);
    	aluno.setSenha(senha);
    	
        return daoAluno.save(aluno);
    }
    
}
