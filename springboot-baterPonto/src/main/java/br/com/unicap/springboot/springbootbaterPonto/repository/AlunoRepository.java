/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.unicap.springboot.springbootbaterPonto.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.unicap.springboot.springbootbaterPonto.model.Aluno;

public interface AlunoRepository extends JpaRepository<Aluno, String>{
}