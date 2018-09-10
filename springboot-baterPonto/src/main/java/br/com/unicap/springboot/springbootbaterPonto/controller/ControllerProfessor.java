/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.unicap.springboot.springbootbaterPonto.controller;


import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.unicap.springboot.springbootbaterPonto.model.Professor;
import br.com.unicap.springboot.springbootbaterPonto.repository.ProfessorRepository;

@RestController
@RequestMapping("/professor")
public class ControllerProfessor {
    @Autowired
    ProfessorRepository daoProfessor;
    
    @GetMapping("/{matricula}")
    public ResponseEntity<Professor> consultar(@PathVariable String matricula) {
        Professor professor = daoProfessor.listarProfessorByMatricula(matricula).get(0);
        if(professor == null) {
        	return ResponseEntity.notFound().build();
        }else {
        	return ResponseEntity.ok(professor);
        }
    }
    
    @GetMapping
    public ArrayList<Professor> listar(){
        return daoProfessor.listarProfessor();
    }
    
}
