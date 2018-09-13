package br.com.unicap.springboot.springbootbaterPonto.repository;

import java.util.ArrayList;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import br.com.unicap.springboot.springbootbaterPonto.model.Aluno;
import br.com.unicap.springboot.springbootbaterPonto.model.Professor;
import br.com.unicap.springboot.springbootbaterPonto.model.Relatorio;;

public interface RelatorioRepository extends JpaRepository<Relatorio, Long>{
	
	
	@Query(value = "SELECT * FROM tbl_ponto WHERE aluno_id = (select aluno_id from tbl_alunos where aluno_matr = ?1)",nativeQuery = true)
    public ArrayList<Relatorio> listarRelatoriosByMatriculaAluno(String matricula);
	
	@Query(value = "SELECT * FROM tbl_ponto WHERE prof_id = (select prof_id from tbl_professor where prof_matr = ?1)",nativeQuery = true)
    public ArrayList<Relatorio> listarRelatoriosByMatriculaProfessor(String matricula);
	
	@Query(value = "SELECT * FROM tbl_ponto",nativeQuery = true)
	public ArrayList<Relatorio> listarRelatorios();
	
	//Resgatar Aluno e Professor
	@Query(value = "SELECT * FROM tbl_alunos WHERE aluno_matr = ?1",nativeQuery = true)
    public Aluno getAlunoByMatricula(String matricula);
	
	@Query(value = "SELECT * FROM tbl_professor where prof_matr = ?1",nativeQuery = true)
	public Professor getProfessorByMatricula(String matricula);
	
	//Bater ponto
	@Modifying
	@Query(value = "INSERT INTO tbl_ponto (prof_id,hora_ent) values ((select prof_id from tbl_professor where prof_matr = ?1),sysdate())",nativeQuery = true)
	@Transactional
	public void baterPontoProfessorEntrada(String matricula);
	
	@Modifying
	@Query(value = "UPDATE tbl_ponto SET hora_sai=sysdate() WHERE prof_id=(select prof_id from tbl_professor where prof_matr = ?1) and day(hora_ent)=day(sysdate() )",nativeQuery = true)
	@Transactional
	public void baterPontoProfessorSaida(String matricula);
	
	@Modifying
	@Query(value = "INSERT INTO tbl_ponto (aluno_id,hora_ent) values ((select aluno_id from tbl_alunos where aluno_matr = ?1),sysdate())",nativeQuery = true)
	@Transactional
	public void baterPontoAlunoEntrada(String matricula);
	
	@Modifying
	@Query(value = "UPDATE tbl_ponto SET hora_sai=sysdate() WHERE aluno_id=(select aluno_id from tbl_alunos where aluno_matr = ?1) and day(hora_ent)=day(sysdate() )",nativeQuery = true)
	@Transactional
	public void baterPontoAlunoSaida(String matricula);
	
	//Verificação se já bateu ponto
	@Query(value = "select count(*) from tbl_ponto where aluno_id = (select aluno_id from tbl_alunos where aluno_matr = ?1) and DAY(hora_ent)=DAY(sysdate())",nativeQuery = true)
	public int verificarEntradaAlunoDia(String matricula);
	
	@Query(value = "select count(*) from tbl_ponto where aluno_id = (select aluno_id from tbl_alunos where aluno_matr = ?1) and DAY(hora_sai)=DAY(sysdate())",nativeQuery = true)
	public int verificarSaidaAlunoDia(String matricula);
	
	@Query(value = "select count(*) from tbl_ponto where prof_id = (select prof_id from tbl_professor where prof_matr = ?1) and DAY(hora_ent)=DAY(sysdate())",nativeQuery = true)
	public int verificarEntradaProfessorDia(String matricula);
	
	@Query(value = "select count(*) from tbl_ponto where prof_id = (select prof_id from tbl_professor where prof_matr = ?1) and DAY(hora_sai)=DAY(sysdate())",nativeQuery = true)
	public int verificarSaidaProfessorDia(String matricula);
	
	
}