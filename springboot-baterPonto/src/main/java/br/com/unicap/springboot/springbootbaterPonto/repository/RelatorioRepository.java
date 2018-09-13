package br.com.unicap.springboot.springbootbaterPonto.repository;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;

import br.com.unicap.springboot.springbootbaterPonto.model.Aluno;
import br.com.unicap.springboot.springbootbaterPonto.model.Professor;
import br.com.unicap.springboot.springbootbaterPonto.model.Relatorio;;

public interface RelatorioRepository extends JpaRepository<Relatorio, Long>{
	
	@Procedure(name = "VALIDAR_PONTO_ENTRADA")
    public void VALIDAR_PONTO_ENTRADA(@Param("matricula") String matricula);
	
	@Procedure(name = "VALIDAR_PONTO_SAIDA")
    public void VALIDAR_PONTO_SAIDA(@Param("matricula") String matricula);

	@Query(value = "SELECT * FROM tbl_ponto WHERE aluno_id = (select aluno_id from tbl_alunos where aluno_matr = ?1)",nativeQuery = true)
    public ArrayList<Relatorio> listarRelatoriosByMatricula(String matricula);
	
	@Query(value = "SELECT * FROM tbl_ponto",nativeQuery = true)
	public ArrayList<Relatorio> listarRelatorios();
	
	@Query(value = "SELECT * FROM tbl_alunos",nativeQuery = true)
	public ArrayList<Aluno> listarAlunos();
	
	@Query(value = "SELECT * FROM tbl_alunos WHERE aluno_matr = ?1",nativeQuery = true)
    Aluno listarAlunoByMatricula(String matricula);
	
	@Query(value = "SELECT * FROM tbl_professor where prof_matr = ?1",nativeQuery = true)
	public Professor getProfessorByMatricula(String matricula);
	
	@Query(value = "INSERT INTO tbl_ponto (prof_id,hora_ent) values (1?,sysdate())",nativeQuery = true)
	public void inserirTabelaProfessor(Long id);
	
	@Query(value = "INSERT INTO tbl_ponto (aluno_id,hora_ent) values (1?,sysdate())",nativeQuery = true)
	public void inserirTabelaAluno(Long id);
	
	@Query(value = "select count(*) from tbl_ponto where aluno_id = ?1 and DAY(hora_ent)=DAY(sysdate())",nativeQuery = true)
	public int verificarEntradaAlunoDia(int id);
	
	@Query(value = "select count(*) from tbl_ponto where prof_id = ?1 and DAY(hora_ent)=DAY(sysdate())",nativeQuery = true)
	public int verificarEntradaProfessorDia(int id);
	
	
	
}