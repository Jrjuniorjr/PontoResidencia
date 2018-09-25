package br.com.unicap.springboot.springbootbaterPonto.controller;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.SimpleTimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.unicap.springboot.springbootbaterPonto.model.Relatorio;
import br.com.unicap.springboot.springbootbaterPonto.model.RelatorioKey;
import br.com.unicap.springboot.springbootbaterPonto.model.Residente;
import br.com.unicap.springboot.springbootbaterPonto.repository.RelatorioRepository;
import br.com.unicap.springboot.springbootbaterPonto.repository.ResidenteRepository;

@CrossOrigin
@RestController
@RequestMapping("relatorio")
public class RelatorioController{
	private final String USER_ADMIN = "1";
	private final String USER_RESIDENTE = "0";	
	
    @Autowired
    RelatorioRepository daoRelatorio;
    @Autowired
    ResidenteRepository daoResidente;
    
    
    @GetMapping
    public ResponseEntity<List<Relatorio>> listar(){
    	return ResponseEntity.ok(daoRelatorio.listarRelatorios());
    }
    
    @GetMapping("{matricula}")
    public ResponseEntity<ArrayList<Relatorio>> listarTodosPorMatricula(@RequestHeader("Authorization") String token, @PathVariable String matricula) {
	   ResponseEntity<ArrayList<Relatorio>> responseEntity;
	   
	   if(hasAuthorization(token)) {
		   Residente residente = daoResidente.getResidenteByMatricula(matricula);
		   responseEntity = ResponseEntity.ok(daoRelatorio.listarRelatoriosByMatricula(residente.getMatricula()));
	   }
	   else {
		   responseEntity = ResponseEntity.notFound().build();
	   }
	   return responseEntity;
    }
    
	@GetMapping("today")
    public ResponseEntity<Relatorio> consultarHoje (@RequestHeader("Authorization") String token){
    	
		ResponseEntity<Relatorio> responseEntity;
		
//		Relatorio relatorio = daoRelatorio.getOne(new RelatorioKey(daoResidente.getResidenteByToken(token),LocalDate.now()));
		Relatorio relatorio = daoRelatorio.listarRelatorioHoje(daoResidente.getResidenteByToken(token).getMatricula());
//		System.out.println(relatorio);

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
	
	  private boolean hasAuthorization(String token) {
	    	Residente residente = daoResidente.getResidenteByToken(token);
	    	return residente != null && residente.getTipo().equals(USER_ADMIN);
	    } 
}
