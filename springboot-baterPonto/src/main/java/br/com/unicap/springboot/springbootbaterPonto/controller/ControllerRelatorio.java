/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.unicap.springboot.springbootbaterPonto.controller;

import br.com.unicap.springboot.springbootbaterPonto.model.Relatorio;
import br.com.unicap.springboot.springbootbaterPonto.repository.RelatorioRepository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.List;

import javax.naming.InitialContext;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    	
		try {
		InitialContext ctx = new InitialContext();
		DataSource ds;
		ds = (DataSource)ctx.lookup("jdbc:mysql://localhost:3306/db_sistemaponto");
		Connection connection = ds.getConnection();
		java.sql.CallableStatement proc = connection.prepareCall("{ call VALIDAR_PONTO_ENTRADA(matricula) }");            
		proc.setString("matricula", matricula);
		proc.execute();
		}catch(Exception e) {
			e.getMessage();
		}
		
    }
    
    @PostMapping("/saida/{matricula}")
    public void baterPontoSai(@PathVariable String matricula) {
    	
    }

    @GetMapping("/{matricula}")
    public Relatorio consultar(@PathVariable String matricula) {
    	
    	return daoRelatorio.getOne(matricula);
             
    }
    
    @GetMapping
    public List<Relatorio> listar(){
    	return daoRelatorio.findAll();
    }
    
}
