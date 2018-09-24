package br.com.unicap.springboot.springbootbaterPonto.controller;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//import br.com.unicap.springboot.springbootbaterPonto.model.LoginForm;
import br.com.unicap.springboot.springbootbaterPonto.model.Residente;
import br.com.unicap.springboot.springbootbaterPonto.repository.ResidenteRepository;

@CrossOrigin
@RestController
@RequestMapping("residente")
public class ResidenteController {
	
	private final String USER_ADMIN = "1";
	private final String USER_RESIDENTE = "0";	
	
	@Autowired
	ResidenteRepository rRepository;
	
	 /******************************/
	 /*   INSERIR NOVO RESIDENTE   */		
	 /******************************/
	
	@PostMapping
    public ResponseEntity<?> inserir(@RequestHeader ("Authorization") String token, @RequestBody Residente residente) throws NoSuchAlgorithmException {
        
		Residente r;
//		HttpStatus http;
		ResponseEntity<?> res;
		
		r = rRepository.getResidenteByToken(token);
		
		if(r != null && r.getTipo().equals("1")) {
			r  = rRepository.getResidenteByMatricula(residente.getMatricula());
			if(r == null) {
				String retorno = getMD5Hex(residente.getMatricula());
				residente.setToken(retorno);
				residente.setTipo("0");
				rRepository.save(residente);
//				http = HttpStatus.OK;
				res = new ResponseEntity<>(HttpStatus.OK);
			}
			else 
//				http = HttpStatus.CONFLICT;
				res = new ResponseEntity<>(HttpStatus.CONFLICT);
		}
		else 
//			http = HttpStatus.CONFLICT;
			res = new ResponseEntity<>(HttpStatus.UNAUTHORIZED);

		return res;
    }
	
	 /******************************/
	 /*      ATUALIZAR RESIDENTE   */		
	 /******************************/

	@PutMapping
//	@PostMapping("update") // Massa o @CORS tá deixando o PUT passar :D
	public ResponseEntity<?> atualizar( 
			@RequestHeader ("Authorization") String token,
			@RequestBody Residente residente) {
		
		ResponseEntity<?> res;
		
		if(hasAuthorization(token)) { 
			if(validateResidente(residente)) {
				// Desnecessário verificar se o residente existe, né?
				rRepository.atualizarResidente(
						residente.getNome(), 
						residente.getSenha(), 
						residente.getTipo(),  
						residente.getMatricula()); // Calma que a matricula não vai ser atualizada, rsjjjjjjjj
				res = new ResponseEntity<>(HttpStatus.OK);
			} else
				res = new ResponseEntity<>(HttpStatus.BAD_REQUEST); // Residente por algum motivo não válido
		} else
			res = new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		
		return res;
	}
		

	 /******************************/
	 /*      REMOVER RESIDENTE     */		
	 /******************************/
	
	// O residente é removido através do SET do campo STATUS de 1 pra 0
	@DeleteMapping("{matricula}")
	public ResponseEntity<?> remover ( 
			@RequestHeader ("Authorization") String token,
			@PathVariable String matricula) {

		ResponseEntity<?> res;
		
		if(hasAuthorization(token)) { 
			rRepository.removerResidente(matricula);
			res = new ResponseEntity<>(HttpStatus.OK);	// Preciso fazer verificações do tipo "Existe um residente com esta matricula", "consegui remover ele", etc... ?
		} else
			res = new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		
		return res;
	}
			
	
	/******************************/
	 /*    AUTENTICAR RESIDENTE   */		
	 /*****************************/

	@PostMapping("login")
    public ResponseEntity<Residente> login (@RequestBody Residente rLogando) {
		// @RequestBody automaticamente mapeia o Body de um POST para um objetio de domínio
		
		ResponseEntity<Residente> responseEntity;
		
		Residente residente = rRepository.getResidenteByMatricula(rLogando.getMatricula());
		
		if(residente!= null) {
			
			if(residente.getSenha().equals(rLogando.getSenha())) {		
				try {
					String token = getMD5Hex(LocalTime.now().toString());
					rRepository.atualizarToken(token, residente.getMatricula());
					residente.setToken(token);
				} catch(Exception e) {
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
	
	 /******************************/
	 /*     LISTAR RESIDENTE(S)    */		
	 /******************************/
	
	@GetMapping
    public ResponseEntity<java.util.List<Residente>> listarTodos (@RequestHeader ("Authorization") String token) {
		ResponseEntity<java.util.List<Residente>> responseEntity;
		
		Residente r = rRepository.getResidenteByMatricula(rRepository.getResidenteByToken(token).getMatricula());
		
		if(r != null && r.getTipo().equals("1")) {
			responseEntity = ResponseEntity.ok(rRepository.getResidentes());
		}else 
			responseEntity = ResponseEntity.notFound().build();

		return responseEntity;
    } 
	
	@GetMapping("{matricula}")
	public ResponseEntity<Residente> listarByMatricula (@RequestHeader ("Authorization") String token, @PathVariable String matricula){
		ResponseEntity<Residente> responseEntity;
		Residente r, adm;
		
		r = rRepository.getResidenteByMatricula(matricula);
		adm = rRepository.getResidenteByToken(token);
		
		if(adm != null && adm.getTipo().equals("1")) {
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
	
			///////////////////////////////////////////// UTILS /////////////////////////////////////////////
	
	private static String getMD5Hex(String in){
		MessageDigest messageDigest;
		try {
			messageDigest=java.security.MessageDigest.getInstance("SHA-1");
		    messageDigest.update(in.getBytes(),0,in.length());
		    return new BigInteger(1,messageDigest.digest()).toString(16);
		} catch (  NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return "";
	}
	
	// B E S M A R T
	private boolean validateResidente(Residente residente) {
		return  !residente.getMatricula().isEmpty() && 
				!residente.getNome().isEmpty() &&
				!residente.getSenha().isEmpty() &&
				!residente.getTipo().isEmpty() &&
				(residente.getTipo().equals(USER_RESIDENTE) || residente.getTipo().equals(USER_ADMIN));
	}
	
    private boolean hasAuthorization(String token) {
    	Residente residente = rRepository.getResidenteByToken(token);
    	return residente != null && residente.getTipo().equals(USER_ADMIN);
    } 
}        
      

	

