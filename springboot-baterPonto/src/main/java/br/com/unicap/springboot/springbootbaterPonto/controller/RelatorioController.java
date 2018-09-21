package br.com.unicap.springboot.springbootbaterPonto.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.unicap.springboot.springbootbaterPonto.model.Relatorio;
import br.com.unicap.springboot.springbootbaterPonto.model.RelatorioKey;
import br.com.unicap.springboot.springbootbaterPonto.model.Residente;
import br.com.unicap.springboot.springbootbaterPonto.repository.RelatorioRepository;
import br.com.unicap.springboot.springbootbaterPonto.repository.ResidenteRepository;

@RestController
@RequestMapping("relatorio")
public class RelatorioController{

    @Autowired
    RelatorioRepository daoRelatorio;
    @Autowired
    ResidenteRepository daoResidente;
    
    
	@GetMapping("today")
    public ResponseEntity<Relatorio> consultarHoje (@RequestHeader("Authorization") String token){
    	
		ResponseEntity<Relatorio> responseEntity;
		
		Relatorio relatorio = daoRelatorio.getOne(new RelatorioKey(daoResidente.getResidenteByToken(token),LocalDate.now()));
		
		if(relatorio == null) {
			responseEntity = ResponseEntity.notFound().build();
		}else {
			responseEntity = ResponseEntity.ok(relatorio);
		}
		
		return responseEntity;	
    }

   @GetMapping("getone")
    public ResponseEntity<ArrayList<Relatorio>> consultar(@RequestHeader("Authorization") String token) {
	   
	   ResponseEntity<ArrayList<Relatorio>> responseEntity;
	   
	   Residente residente = daoResidente.getResidenteByToken(token);
	   
	   if(residente != null) {
		   responseEntity = ResponseEntity.ok(daoRelatorio.listarRelatoriosByMatricula(residente.getMatricula()));
	   }
	   else {
		   responseEntity = ResponseEntity.notFound().build();
		   
	   }
	   
	   return responseEntity;
	   
    }
    
    @GetMapping
    public ResponseEntity<List<Relatorio>> listar(){
    	
    	return ResponseEntity.ok(daoRelatorio.listarRelatorios());

    }
   
    
}
