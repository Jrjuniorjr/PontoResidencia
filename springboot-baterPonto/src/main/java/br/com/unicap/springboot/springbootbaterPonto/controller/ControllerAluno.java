/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.unicap.springboot.springbootbaterPonto.controller;

import br.com.unicap.springboot.springbootbaterPonto.model.Aluno;
import br.com.unicap.springboot.springbootbaterPonto.repository.AlunoRepository;
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
@RequestMapping("/alunos")
public class ControllerAluno {

    @Autowired
    AlunoRepository daoAluno;

    @PostMapping("/aluno/{matriculaProf}")
    public void inserir(@RequestBody Aluno aluno, @PathVariable int matriculaProf) {
        System.out.println(aluno);
        daoAluno.inserir(aluno, matriculaProf);
    }

    @DeleteMapping("/aluno/{matricula}")
    public void remover(@PathVariable String matricula) {
        Aluno aluno;
        aluno = daoAluno.consultar(matricula);
        daoAluno.remover(aluno);
    }

    @GetMapping("/aluno/{matricula}")
    public Aluno consultar(@PathVariable String matricula) {
        return daoAluno.consultar(matricula);
    }

    @PutMapping("/aluno")
    public void atualizar(@RequestBody Aluno aluno) {
        daoAluno.atualizar(aluno);
    }
}
