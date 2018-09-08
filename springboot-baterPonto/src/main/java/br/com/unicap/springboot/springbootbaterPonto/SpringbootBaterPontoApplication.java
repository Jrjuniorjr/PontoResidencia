package br.com.unicap.springboot.springbootbaterPonto;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.unicap.springboot.springbootbaterPonto.model.Aluno;
import br.com.unicap.springboot.springbootbaterPonto.repository.AlunoRepository;

@SpringBootApplication
public class SpringbootBaterPontoApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootBaterPontoApplication.class, args);
		
		
	}
	
}
