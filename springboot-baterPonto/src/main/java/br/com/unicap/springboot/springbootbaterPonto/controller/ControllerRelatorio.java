package br.com.unicap.springboot.springbootbaterPonto.controller;

import java.util.ArrayList;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.unicap.springboot.springbootbaterPonto.model.Aluno;
import br.com.unicap.springboot.springbootbaterPonto.model.Professor;
import br.com.unicap.springboot.springbootbaterPonto.model.Relatorio;
import br.com.unicap.springboot.springbootbaterPonto.repository.AlunoRepository;
import br.com.unicap.springboot.springbootbaterPonto.repository.ProfessorRepository;
import br.com.unicap.springboot.springbootbaterPonto.repository.RelatorioRepository;

@RestController
@RequestMapping("/relatorio")
public class ControllerRelatorio{

    @Autowired
    RelatorioRepository daoRelatorio;
    
    @Autowired
    AlunoRepository daoAluno;
    @Autowired
    ProfessorRepository daoProfessor;
    
    /*@PostMapping
    public void inserir(@RequestBody Relatorio relatorio) {
        daoRelatorio.save(relatorio);
        
        relatorio.getAluno().getId();
    }*/
    
    @SuppressWarnings("deprecation")
	@PostMapping("/entrada/{matricula}")
    public Relatorio baterPontoEnt(@PathVariable String matricula) {
    	Relatorio relatorio = new Relatorio();
    	Date data = new Date(System.currentTimeMillis());
    	
    	Aluno aluno = daoAluno.listarAlunoByMatricula(matricula);
    	    	
    	if(aluno == null) {
    		
    		Professor professor = daoProfessor.listarProfessorByMatricula(matricula);
    		if(professor!=null) {
    			if(!professor.getRelatorios().isEmpty()) {
		    		if(professor.getRelatorios().get(professor.getRelatorios().size()).getHoraent().getDay() != data.getDay()) {
		    			relatorio.setAluno(null);
		    			relatorio.setHoraent(data);
		    			relatorio.setHorasai(null);
		    			relatorio.setProfessor(professor);
		    			return daoRelatorio.save(relatorio);
		    		}
    			}else {
    				relatorio.setAluno(null);
	    			relatorio.setHoraent(data);
	    			relatorio.setHorasai(null);
	    			relatorio.setProfessor(professor);
	    			return daoRelatorio.save(relatorio);
    			}
    		}else {
    			return null;
    		}
    	}else {
    		if(!aluno.getRelatorios().isEmpty()) {
		    		if(aluno.getRelatorios().get(aluno.getRelatorios().size()).getHoraent().getDay() != data.getDay() ) {
		    			relatorio.setAluno(aluno);
		    			relatorio.setHoraent(data);
		    			relatorio.setHorasai(null);
		    			relatorio.setProfessor(null);
		    			return daoRelatorio.save(relatorio);
		    		}
    		}else {
    			relatorio.setAluno(aluno);
    			relatorio.setHoraent(data);
    			relatorio.setHorasai(null);
    			relatorio.setProfessor(null);
    			return daoRelatorio.save(relatorio);
    		}
    	}
		return relatorio;
    }
    
    @PostMapping("/saida/{matricula}")
    public Relatorio baterPontoSai(@PathVariable String matricula) {
    	Relatorio relatorio = new Relatorio();
    	Date data = new Date(System.currentTimeMillis());
    	
    	Aluno aluno = daoAluno.listarAlunoByMatricula(matricula);
    	    	
    	if(aluno == null) {
    		
    		Professor professor = daoProfessor.listarProfessorByMatricula(matricula);
    		if(professor!=null) {
    			if(!professor.getRelatorios().isEmpty()) {
		    		if(professor.getRelatorios().get(professor.getRelatorios().size()).getHorasai().getDay() != data.getDay()) {
		    			relatorio.setAluno(null);
		    			relatorio.setHoraent(data);
		    			relatorio.setHorasai(null);
		    			relatorio.setProfessor(professor);
		    			return daoRelatorio.save(relatorio);
		    		}
    			}else {
    				relatorio.setAluno(null);
	    			relatorio.setHoraent(null);
	    			relatorio.setHorasai(data);
	    			relatorio.setProfessor(professor);
	    			return daoRelatorio.save(relatorio);
    			}
    		}else {
    			return null;
    		}
    	}else {
    		if(!aluno.getRelatorios().isEmpty()) {
		    		if(aluno.getRelatorios().get(aluno.getRelatorios().size()).getHorasai().getDay() != data.getDay() ) {
		    			relatorio.setAluno(aluno);
		    			relatorio.setHoraent(null);
		    			relatorio.setHorasai(data);
		    			relatorio.setProfessor(null);
		    			return daoRelatorio.save(relatorio);
		    		}
    		}else {
    			relatorio.setAluno(aluno);
    			relatorio.setHoraent(data);
    			relatorio.setHorasai(null);
    			relatorio.setProfessor(null);
    			return daoRelatorio.save(relatorio);
    		}
    	}
		return relatorio;
    }
    
    @GetMapping("/hoje/{matricula}")
    public ResponseEntity<Relatorio> consultarHoje(@PathVariable String matricula){
		return null;
    	
    	
    }

   @GetMapping("/{matricula}")
    public ArrayList<Relatorio> consultar(@PathVariable String matricula) {
    	
    	//Long id = daoRelatorio.ID_BY_MATRICULA(matricula);
    	return daoRelatorio.listarRelatoriosByMatricula(matricula);
    }
    
    @GetMapping
    public ArrayList<Relatorio> listar(){
    	return daoRelatorio.listarRelatorios();

    }
    
}
