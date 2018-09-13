package br.com.unicap.springboot.springbootbaterPonto.controller;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
    
	@PostMapping("/entrada/{matricula}")
    public HttpStatus baterPontoEnt(@PathVariable String matricula) {
    	Aluno aluno = daoAluno.listarAlunoByMatricula(matricula);
    	    	    	
    	if(aluno != null) {
    		if(daoRelatorio.verificarEntradaAlunoDia(matricula) == 0) {
    			daoRelatorio.baterPontoAlunoEntrada(aluno.getMatricula());
	    		return HttpStatus.OK;
	    	}else {
	    		return HttpStatus.CONFLICT;
	    		}
    	}else {
    		Professor professor = daoProfessor.listarProfessorByMatricula(matricula);
    		if(professor != null) {
    			if(daoRelatorio.verificarEntradaProfessorDia(matricula)==0) {
    	    			daoRelatorio.baterPontoProfessorEntrada(professor.getMatricula());
    	    			return HttpStatus.OK;
    				}			
    		}
   		}
    	return HttpStatus.CONFLICT;
    }
    
    
    
    @PostMapping("/saida/{matricula}")
    public HttpStatus baterPontoSai(@PathVariable String matricula) {
    	Aluno aluno = daoAluno.listarAlunoByMatricula(matricula);
    	    	    	
    	if(aluno != null) {
    		if(daoRelatorio.verificarSaidaAlunoDia(matricula) == 0) {
    			if(daoRelatorio.verificarEntradaAlunoDia(matricula) != 0) {
	    			daoRelatorio.baterPontoAlunoSaida(aluno.getMatricula());
	    			return HttpStatus.OK;
    			}
    		}
    	}else {
    		Professor professor = daoProfessor.listarProfessorByMatricula(matricula);
    		if(professor != null) {
    			if(daoRelatorio.verificarSaidaProfessorDia(matricula)==0) {
    				if(daoRelatorio.verificarEntradaProfessorDia(matricula) != 0) {
    	    			daoRelatorio.baterPontoProfessorSaida(professor.getMatricula());
    	    			return HttpStatus.OK;
    				}
    			}			
    		}
   		}
    	return HttpStatus.CONFLICT;
    }
    
	@GetMapping("/hoje/{matricula}")
    public ResponseEntity<Relatorio> consultarHoje(@PathVariable String matricula){
    	List<Relatorio> relatorios;
    	
    	java.util.Date dataHoje = new Date(System.currentTimeMillis());  
    	
    	int anoHoje = Integer.parseInt(dataHoje.toString().substring(0, 4));
		int mesHoje = Integer.parseInt(dataHoje.toString().substring(5,7));
		int diaHoje = Integer.parseInt(dataHoje.toString().substring(8,10));
    	
    	if(matricula.length() == 10)
    		relatorios = daoRelatorio.listarRelatoriosByMatriculaAluno(matricula);
    	else
    		relatorios = daoRelatorio.listarRelatoriosByMatriculaProfessor(matricula);
    	
    	for (Relatorio relatorio : relatorios) {
			String[] split = relatorio.toString().split("horaent=");
			String dataStr = split[1].substring(0, 10);
			int ano = Integer.parseInt(dataStr.substring(0, 4));
			int mes = Integer.parseInt(dataStr.substring(5,7));
			int dia = Integer.parseInt(dataStr.substring(8,10));
			
			if(dia == diaHoje && mes == mesHoje && ano == anoHoje) {
				return ResponseEntity.ok(relatorio);
			}
		}
		return ResponseEntity.notFound().build();
    }

   @GetMapping("/{matricula}")
    public List<Relatorio> consultar(@PathVariable String matricula) {
	   
	   if(matricula.length() == 6) {
		   	return daoRelatorio.listarRelatoriosByMatriculaProfessor(matricula);
	   }else {
	    	return daoRelatorio.listarRelatoriosByMatriculaAluno(matricula);
	   }
	   
    }
    
    @GetMapping
    public ArrayList<Relatorio> listar(){
    	return daoRelatorio.listarRelatorios();

    }
   
    
}
