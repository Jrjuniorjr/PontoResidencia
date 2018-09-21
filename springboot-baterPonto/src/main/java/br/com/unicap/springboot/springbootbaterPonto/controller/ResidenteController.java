package br.com.unicap.springboot.springbootbaterPonto.controller;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.unicap.springboot.springbootbaterPonto.model.Residente;
import br.com.unicap.springboot.springbootbaterPonto.repository.ResidenteRepository;

@RestController
@RequestMapping("residente")
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
				String retorno = getMD5Hex(residente.getMatricula());
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

	@PostMapping("login")
    public ResponseEntity<Residente> login (@RequestBody Residente rLogando) {
		ResponseEntity<Residente> responseEntity;
		
		Residente residente = rRepository.getResidenteByMatricula(rLogando.getMatricula());
				
		if(residente!= null) {
			
			if(residente.getSenha().equals(rLogando.getSenha())) {		
				try {
				String token = getMD5Hex(LocalTime.now().toString());
				rRepository.atualizarToken(token, residente.getMatricula());
				residente.setToken(token);
				}catch(Exception e) {
					e.getMessage();
				}
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
	
	@GetMapping
    public ResponseEntity<java.util.List<Residente>> listarTodos (@RequestHeader ("Authorization") String token) {
		ResponseEntity<java.util.List<Residente>> responseEntity;
		
		Residente r = rRepository.getResidenteByMatricula(rRepository.getResidenteByToken(token).getMatricula());
		
		if(r != null && r.getAdm().equals("1")) {
			responseEntity = ResponseEntity.ok(rRepository.getResidentes());
		}else 
			responseEntity = ResponseEntity.notFound().build();

		return responseEntity;
    } 
	
	@GetMapping("{matricula}")
	public ResponseEntity<Residente> listarByMatricula (@RequestHeader ("Authorization") String token,@PathVariable String matricula){
		ResponseEntity<Residente> responseEntity;
		Residente r, adm;
		
		r = rRepository.getResidenteByMatricula(matricula);
		adm = rRepository.getResidenteByToken(token);
		
		if(adm != null && adm.getAdm().equals("1")) {
			if(r == null) {
				responseEntity = ResponseEntity.notFound().build();
			}else {
				responseEntity = ResponseEntity.ok(r);
			}
		}else {
			responseEntity = ResponseEntity.notFound().build();
		}
		
		return responseEntity;
		
	}	
	
	public static String getMD5Hex(String in){
		  MessageDigest messageDigest;
		  try {
		    messageDigest=java.security.MessageDigest.getInstance("SHA-1");
		    messageDigest.update(in.getBytes(),0,in.length());
		    return new BigInteger(1,messageDigest.digest()).toString(16);
		  }
		 catch (  NoSuchAlgorithmException e) {
		    e.printStackTrace();
		  }
		  return "";
		}
	
}
