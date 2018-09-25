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

	/**
	 * Faz a ENTRADA e retorna o relatório do RESIDENTE identificado pelo TOKEN.
	 * Se a entrada já tiver sido feita então só retorna o RELATÓRIO de HOJE
	 * @param token - TOKEN do RESIDENTE logado
	 * @return - {matricula, data, entrada, saida = NULL } as Relatorio
	 */
	@PostMapping("entrada")
    public ResponseEntity<Relatorio> baterPontoEntrada(@RequestHeader("Authorization") String token) {
    	
		ResponseEntity<Relatorio> responseEntity = null;
		
		Residente residente = daoResidente.getResidenteByToken(token);
				
		if(residente != null) {
			if(daoRelatorio.getEntrarHoje(residente.getMatricula()) == 0) { // If != 0 then FEZ_A_ENTRADA_JÁ
				daoRelatorio.baterPontoEntrada(residente.getMatricula());
//				Relatorio relatorio = daoRelatorio.getOne(new RelatorioKey(residente, LocalDate.now())); // Depois de fazer a entrada busca o relatório cuja matricula é o do residente logado
				Relatorio relatorio = daoRelatorio.listarRelatorioHoje(residente.getMatricula());
				responseEntity = ResponseEntity.ok(relatorio);
			}else { // Já fez a entrada hoje, logo NÃO faz a ENTRADA
//				Relatorio relatorio = daoRelatorio.getOne(new RelatorioKey(residente, LocalDate.now()));
				Relatorio relatorio = daoRelatorio.listarRelatorioHoje(residente.getMatricula());
				responseEntity = ResponseEntity.ok(relatorio);
			}
		}else {
			responseEntity = ResponseEntity.notFound().build();
		}
		
		return responseEntity;
    }
	
	/**
	 * Faz a SAIDA e retorna o relatório do RESIDENTE identificado pelo TOKEN.
	 * Se a saida já tiver sido feita então só retorna o RELATÓRIO de HOJE
	 * @param token - TOKEN do RESIDENTE logado
	 * @return - {matricula, data, entrada, saida = NULL } as Relatorio
	 */
	@PostMapping("saida")
	public ResponseEntity<Relatorio> baterPontoSaida(@RequestHeader("Authorization") String token){
		
		ResponseEntity<Relatorio> responseEntity = null;
		
		Residente residente = daoResidente.getResidenteByToken(token);
				
		if(residente != null) {
			if(daoRelatorio.getSairHoje(residente.getMatricula()) == 1) { // Tava trocado aqui
				daoRelatorio.baterPontoSaida(residente.getMatricula());
//				Relatorio relatorio = daoRelatorio.getOne(new RelatorioKey(residente,LocalDate.now()));
				Relatorio relatorio = daoRelatorio.listarRelatorioHoje(residente.getMatricula());
				responseEntity = ResponseEntity.ok(relatorio);
			}else {
//				Relatorio relatorio = daoRelatorio.getOne(new RelatorioKey(residente,LocalDate.now()));
				Relatorio relatorio = daoRelatorio.listarRelatorioHoje(residente.getMatricula());
				responseEntity = ResponseEntity.ok(relatorio);
			}
		}else {
			responseEntity = ResponseEntity.notFound().build();
		}
		
		return responseEntity;
	}
	
}
