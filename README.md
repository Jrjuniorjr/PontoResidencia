# Ponto Residencia

Sistema de ponto para os Residentes em Engenharia de Software
criado pelos mesmos.

### Tecnologia usadas
* [Angular](https://angular.io/)
* [Spring](https://spring.io/)


### Equipes divididas entre back-end e front-end
| Back-end     | Front-end      |
| ------------- |:-------------:|
| Momedes Junior     | Lucas Pitt    |
| Lucas Leal         | Eduardo Porto |
| Avelino Gomes      | Pedro Ribeiro |
| Lucas Rafael       | Giulia Falcão |
| Euller Nobrega     | Lucas Matheus |
| Nathalia Bartholo  | Barbara Lemos |
| Jean Duarte        ||


### Requisições
* Relatorio:
-Listar Todos os Relatorios (GET): localhost:8080/relatorio/
-Consultar Relatorios de uma Matricula (GET): localhost:8080/relatorio/{numeroMatricula}
-Bater Ponto Entrada (POST): localhost:8080/relatorio/entrada/{numeroMatricula}
-Bater Ponto Saida (POST): localhost:8080/relatorio/saida/{numeroMatricula}

*Aluno:
-Listar Todos os Alunos (GET): localhost:8080/aluno/
-Consultar Aluno por Matricula (GET): localhost:8080/aluno/{numeroMatricula}
-Adicionar um Aluno (POST): localhost:8080/aluno/
*Exemplo (CORPO):
*{
"id": 1,

* "matricula": "2014205378",

* "nome": "Lucas Rafaell do Nascimento Santos",

* "senha": "112358",

* "professor": {"id": 1,

*               "matricula": "010101",

*               "nome": "Fernando Wanderley",
*   
            "senha": 112358
}
    
}
-Remover um Aluno (DELETE): localhost:8080/aluno/{numeroMatricula}
-Alterar Senha Aluno (PUT): localhost:8080/aluno/
*Exemplo (CORPO):
*2014205378

*Professor:
-Listar Todos os Professores (GET): localhost:8080/professor/
-Consultar Professor por Matricula (GET): localhost:8080/professor/{numeroMatricula}
