/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.unicap.springboot.springbootbaterPonto.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.unicap.springboot.springbootbaterPonto.model.Relatorio;
import br.com.unicap.springboot.springbootbaterPonto.repository.RelatorioRepository;

@RestController
@RequestMapping("/relatorio")
public class ControllerRelatorio {

    @Autowired
    RelatorioRepository daoRelatorio;

    @PostMapping
    public void inserir(@RequestBody Relatorio relatorio) {
        daoRelatorio.save(relatorio);
    }
    
    @PostMapping("/entrada/{matricula}")
    public void baterPontoEnt(@PathVariable String matricula ) {
    	daoRelatorio.VALIDAR_PONTO_ENTRADA(matricula);
    }
    
    @PostMapping("/saida/{matricula}")
    public void baterPontoSai(@PathVariable String matricula) {
    	daoRelatorio.VALIDAR_PONTO_SAIDA(matricula);
    }

   /*@GetMapping("/{matricula}")
    public Relatorio consultar(@PathVariable String matricula) {
    	
    	//Long id = daoRelatorio.ID_BY_MATRICULA(matricula);
    	
    	return daoRelatorio.getOne();
    }*/
    
    @GetMapping
    public List<Relatorio> listar(){
    	return daoRelatorio.findAll();
    }
    
}
