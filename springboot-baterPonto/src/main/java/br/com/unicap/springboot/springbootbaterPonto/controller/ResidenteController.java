package br.com.unicap.springboot.springbootbaterPonto.controller;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.unicap.springboot.springbootbaterPonto.model.Residente;
import br.com.unicap.springboot.springbootbaterPonto.repository.ResidenteRepository;

@RestController
@RequestMapping("/residente/")
public class ResidenteController {
	
	@Autowired
	ResidenteRepository rRepository;
	
	@PostMapping
    public HttpStatus inserir(@RequestHeader ("Authorization") String token, @RequestBody Residente residente) throws NoSuchAlgorithmException {
        
		Residente r;
		HttpStatus http;
		
		r = rRepository.getResidenteByToken(token);
		
		if(r != null && r.getAdm().equals("1")) {
			r  = rRepository.getResidenteByMatricula(residente.getMatricula());
			if(r == null) {
				String retorno = gerarToken(residente.getMatricula());
				residente.setToken(retorno);
				residente.setAdm("0");
				rRepository.save(residente);
				http = HttpStatus.OK;
			}
			else 
				http = HttpStatus.CONFLICT;
		}
		else 
			http = HttpStatus.CONFLICT;

		return http;
		
    }

	@PostMapping("login/")
    public ResponseEntity<Residente> baterPontoEntrada (@RequestBody Residente rLogando) {
		ResponseEntity<Residente> responseEntity;
		System.out.println("RESIDENTE: "+rLogando);
		
		Residente residente = rRepository.getResidenteByMatricula(rLogando.getMatricula());
		
		if(residente!= null) {
			
			if(residente.getSenha().equals(rLogando.getSenha())) {		
				residente.setSenha(null);
				responseEntity = ResponseEntity.ok(residente);
			}else {
				responseEntity = ResponseEntity.notFound().build();
			}
			
		}else {
			responseEntity = ResponseEntity.notFound().build();
		}
		
		return responseEntity;
    }
	
	public String gerarToken(String secret) throws NoSuchAlgorithmException {
		
		MessageDigest md = MessageDigest.getInstance("MD5");
		md.update(secret.getBytes());
		byte[] hashMd5 = md.digest();
		
		return hashMd5.toString();
	}
	
}
