package br.com.unicap.springboot.springbootbaterPonto.controller;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
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
@RequestMapping("ponto")
public class PontoController {
	
	@Autowired
	RelatorioRepository daoRelatorio;
	@Autowired
	ResidenteRepository daoResidente;

	@PostMapping("entrada")
    public ResponseEntity<Relatorio> baterPontoEntrada(@RequestHeader("Authorization") String token) {
    	
		ResponseEntity<Relatorio> responseEntity = null;
		
		Residente residente = daoResidente.getResidenteByToken(token);
    	    	    	
		if(residente != null) {
			if(daoRelatorio.getEntrarHoje(residente.getMatricula()) == 0) {
				daoRelatorio.baterPontoEntrada(residente.getMatricula());
				Relatorio relatorio = daoRelatorio.getOne(new RelatorioKey(residente, LocalDate.now()));
				responseEntity = ResponseEntity.ok(relatorio);
			}else {
				Relatorio relatorio = daoRelatorio.getOne(new RelatorioKey(residente, LocalDate.now()));
				responseEntity = ResponseEntity.ok(relatorio);
			}
		}else {
			responseEntity = ResponseEntity.notFound().build();
		}
		
		return responseEntity;
    }
	
	@PostMapping("saida")
	public ResponseEntity<Relatorio> baterPontoSaida(@RequestHeader("Authorization") String token){
		
		ResponseEntity<Relatorio> responseEntity = null;
		
		Residente residente = daoResidente.getResidenteByToken(token);
		
		if(residente != null) {
			if(daoRelatorio.getSairHoje(residente.getMatricula()) == 0) {
				daoRelatorio.baterPontoSaida(residente.getMatricula());
				Relatorio relatorio = daoRelatorio.getOne(new RelatorioKey(residente,LocalDate.now()));
				responseEntity = ResponseEntity.ok(relatorio);
			}else {
				Relatorio relatorio = daoRelatorio.getOne(new RelatorioKey(residente,LocalDate.now()));
				responseEntity = ResponseEntity.ok(relatorio);
			}
		}else {
			responseEntity = ResponseEntity.notFound().build();
		}
		
		return responseEntity;
	}
	
}
