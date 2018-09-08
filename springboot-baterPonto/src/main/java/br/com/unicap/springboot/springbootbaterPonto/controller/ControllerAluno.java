
package br.com.unicap.springboot.springbootbaterPonto.controller;

import br.com.unicap.springboot.springbootbaterPonto.model.Aluno;
import br.com.unicap.springboot.springbootbaterPonto.repository.AlunoRepository;
import javassist.NotFoundException;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.HandlerExceptionResolver;

@RestController
@RequestMapping("/serviceAluno")
public class ControllerAluno {

    @Autowired
    private AlunoRepository daoAluno;

   
    @GetMapping("/alunos")
    public List<Aluno> listar(){
        
    	return daoAluno.findAll();
    }
   
   @PostMapping("/aluno/baterPonto/{matricula}")
    public void setbaterPonto(@PathVariable String matricula ) {
        
    	Aluno aluno = daoAluno.findOne(matricula);
    	/* salvar os dados na base de relatorios para
    	guardar as informações com a hora que bateu o ponto
    	usando hora local ou de uma fonte segura para pegar data e hora
    	
    	*/
    }
    
    /*@GetMapping("/baterPonto/{matricula}")
    public HandlerExceptionResolver responderPonto() {
    }*/
    
    
    @PostMapping("/aluno/{matriculaProf}")
    public Aluno inserir(@RequestBody Aluno aluno, @PathVariable String matriculaProf) {
        
        return daoAluno.save(aluno);
        
    }
    
    

    @DeleteMapping("/aluno/{matricula}")
    public void remover(@PathVariable String matricula) {
       
        Aluno aluno = daoAluno.getOne(matricula);
        daoAluno.delete(aluno);
        
    }
    
    

    @GetMapping("/aluno/{matricula}")
    public Aluno consultar(@PathVariable String matricula) {
        
        return daoAluno.findOne(matricula);
    }
    
    
    
    @PutMapping("/aluno")
    public Aluno atualizar(@RequestBody Aluno aluno) {
        
        return daoAluno.save(aluno); 
    }
    
}
