import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpResponse } from '@angular/common/http';
import { Observable, of, empty } from 'rxjs';
import { catchError, map, tap } from 'rxjs/operators';

import { Aluno } from '../model/aluno'
import { EndpointService } from '../services/endpoint.service';

/**
 * AdminService.ts
 * Faz as requisição necessárias de Admin com o servidor
 * 
 * Utiliza HttpClient (Serviço)
 */

@Injectable({ providedIn: 'root' })
export class AdminService {

  constructor(
    private http:HttpClient, 
    private endpointService: EndpointService
  ) { }

  listarAlunos(): Observable<Aluno[]> {
    // Headers para enviar no POST
    let httpHeaders = new HttpHeaders({
      'Content-Type' : 'application/json',  // Padrão para JSON
      'Cache-Control': 'no-cache'
    });

    return this.http.get<HttpResponse<Object>>(this.endpointService.listarAluno, {headers:httpHeaders, observe:"response"})
      .pipe(
        map( (data:HttpResponse<Object>) =>  data.body as Aluno[]), // TODO: fazer regras de timeout aqui (HttpInterceptor)
        catchError(this.handleError<Aluno[]>("listar alunos", [])) 
      )
  }

  removerAluno(matricula:string){
    const endPoint = this.endpointService.removerAluno + matricula

    let httpHeaders = new HttpHeaders({
      'Content-Type' : 'application/json',  
      'Cache-Control': 'no-cache'
    });

    // TODO: PREGUIÇA DE CRIAR O MOCK PRA ISSO
    // TODO: Confirmar o que realmente o server tá retornando para indicar que a remoção foi feita !
    // return this.http.get<HttpResponse<Object>>(endPoint, {headers:httpHeaders, observe:"response"})
    //   .pipe(
    //     map( (data:HttpResponse<Object>) =>  data.ok? true : false), 
    //     catchError(this.handleError<boolean>("removerAluno", false)) 
    //   )
    return of(true)
  }

  inserirAluno(matriculaProfessor:string, aluno:Aluno) {
    const endPoint = this.endpointService.inserirAluno + matriculaProfessor

    // Headers para enviar no POST
      let httpHeaders = new HttpHeaders({
        'Content-Type' : 'application/json',  // Padrão para JSON
        'Cache-Control': 'no-cache'
      });
  
      return this.http.post<HttpResponse<Object>>(endPoint, aluno, {headers:httpHeaders, observe:"response"})
        .pipe(
          tap(data => console.log(data)),
          map( (data:HttpResponse<Object>) =>  data.ok),
          catchError(this.handleError<boolean>("inserir aluno", false)) 
        )
  }

  updateAluno(matriculaProfessor:string, aluno:Aluno) {
    const endPoint = this.endpointService.updateAluno + matriculaProfessor

    // Headers para enviar no POST
      let httpHeaders = new HttpHeaders({
        'Content-Type' : 'application/json',  // Padrão para JSON
        'Cache-Control': 'no-cache'
      });
  
      return this.http.put<HttpResponse<Object>>(endPoint, aluno, {headers:httpHeaders, observe:"response"})
        .pipe(
          tap(data => console.log(data)),
          map( (data:HttpResponse<Object>) =>  data.ok),
          catchError(this.handleError<boolean>("inserir aluno", false)) 
        )
  }

  /**
   * Handle de operações Http criado pelo Angular.io Docs
   * Não trava a aplicação quando ocorrer algum erro na operação
   * @param operation: string - Nome da operação que falhou
   * @param result?: T - Caso a operação falhe irá retornar um novo Observable<T> passado através do result
   */
  private handleError<T> (operation = 'operation', result?: T) {
    // O catchError irá passar um parâmetro "error" caso caia aqui
    return (error: any): Observable<T> => {

      // log na interface de erro definida
      // console.error(error); 

      // TODO: Implementar uma forma melhor de tratar o erro
      console.error(`${operation} failed: ${error.message}`);

      // Se passado um result<T> então retonará um novo Observable<T>
      return of(result as T);
    };
  }

}
