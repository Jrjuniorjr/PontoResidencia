
package br.com.unicap.springboot.springbootbaterPonto.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import br.com.unicap.springboot.springbootbaterPonto.model.Aluno;


public interface RepositoryAluno extends JpaRepository<Aluno, String>{ // Define o tipo e a chave de busca

}