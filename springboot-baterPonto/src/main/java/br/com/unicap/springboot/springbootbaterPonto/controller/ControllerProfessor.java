/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.unicap.springboot.springbootbaterPonto.controller;

import br.com.unicap.springboot.springbootbaterPonto.model.Professor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 *
 * @author Euller
 */
public class ControllerProfessor {
    @Autowired
    DAOProfessor c;
    
    @PostMapping("/professor")
    public void inserir(@RequestBody Professor professor){
        System.out.println(professor);
        daoProfessor.inserir(professor);
    }
    
    @DeleteMapping("/professor/{matricula}")
    public void remover(@PathVariable String matricula){
        Professor professor;
        professor = daoProfessor.consultar(matricula);
        daoProfessor.remover(professor);
    }
    
    @GetMapping("/professor/{matricula}")
    public Professor consultar(@RequestBody String matricula){
        return daoProfessor.consultar(matricula);
    }
    
    @GetMapping("/professor/")
    public Professor listar(){
        return daoProfessor.listar();
    }
    
    @PutMapping("/professor")
    public void atualizar(@RequestBody Professor professor){
        daoProfessor.atualizar(professor);
    }
}
