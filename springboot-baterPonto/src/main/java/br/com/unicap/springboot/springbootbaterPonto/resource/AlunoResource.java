package br.com.unicap.springboot.springbootbaterPonto.resource; 
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.algamoney.api.model.Categoria;
import com.example.algamoney.api.repository.CategoriaRepository;

import br.com.unicap.springboot.springbootbaterPonto.model.Aluno;
import br.com.unicap.springboot.springbootbaterPonto.repository.AlunosRepository;

@RestController
@RequestMapping("/alunos")
public class AlunoResource {
	
	@Autowired
	private AlunosRepository alunoRepository;
	
	  private Map<Integer, Aluno> alunos;
	 
	  public AlunoResource() {
	    alunos = new HashMap<Integer, Aluno>();
	    
	    Aluno c1 = new Aluno("2015114563","Avelino Gomez Alonso Junior", 15101997);
	    Aluno c2 = new Aluno("2015114562","Myrella Oliveira", 123456);
	    Aluno c3 = new Aluno("2015114561","Lucas Carvalho", 987654);
	 
	    alunos.put(1, c1);
	    alunos.put(2, c2);
	    alunos.put(3, c3);
	  }
	 
	  @RequestMapping(value = "/alunos", method = RequestMethod.GET)
	  public ResponseEntity<List<Aluno>> listar() {
	    return new ResponseEntity<List<Aluno>>(new ArrayList<Aluno>(alunos.values()), HttpStatus.OK);
	  }
	  	  
	  @RequestMapping(value = "/alunos/{matricula}", method = RequestMethod.GET)
	  public ResponseEntity<Aluno> buscar(@PathVariable("matricula") String matricula) {
		  
	    Aluno aluno = alunos.get(matricula);
	   
	    if (aluno == null) {
	      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	   
	    return new ResponseEntity<Aluno>(aluno, HttpStatus.OK);
	  }
	
	  @RequestMapping(value = "/alunos/{matricula}", method = RequestMethod.DELETE)
	  public ResponseEntity<?> deletar(@PathVariable("matricula") String matricula) {
		
	  Aluno aluno = alunos.remove(matricula);
	 
	  if (aluno == null) {
	    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	  }
	  
	  return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	}