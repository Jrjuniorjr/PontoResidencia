import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpResponse } from '@angular/common/http';
import { Observable, of, empty } from 'rxjs';
import { catchError, map, tap } from 'rxjs/operators';

import { User } from '../model/user';
import { EndpointService } from './endpoint.service';

/**
 * LoginService.ts
 * Faz a requisição de login com o servidor
 * 
 * Utiliza HttpClient (Serviço)
 */

 // TODO: renomear para AuthService
@Injectable({ providedIn: 'root' })
export class LoginService { 
  
  constructor(
    private http:HttpClient,
    private endpointService:EndpointService
  ) { }

  /**
   * Utiliza o serviço HttpClient para fazer um POST no servidor
   * Tem como resposta um HTTP RESPONSE o qual em seu atributo BODY possui um JSON que representa um Usuário: {matricula: string, nome: string}
   * @param login: string -matricula passada pelo Form
   * @param password: string - senha passada pelo Form 
   */
  

  login(login:string, password:string): Observable<User> {

    // Headers para enviar no POST
    let httpHeaders = new HttpHeaders({
      'Content-Type' : 'application/json',  // Padrão para JSON
      'Cache-Control': 'no-cache',
    });

    let data = {
      login:login,
      senha:password
    }

    // HttpClient.post<T>, onde T é o tipo de retorno
    // Quero que o retorno seja um HttpResponse<Object> para poder ter mais flexibilidade nos erros em vez de pedir para que o Angular converta automaticamente o RESPONSE em Objeto User
    // Obs.: catchError( Function:errorHandler(error) )
    return this.http.post<HttpResponse<Object>>(this.endpointService.login, data, {headers:httpHeaders, observe:"response"})
      .pipe(
        map( (data:HttpResponse<Object>) =>  data.body as User), // TODO: fazer regras de timeout aqui (HttpInterceptor)
        catchError(this.handleError<User>("login", new User("","",null))) // String vazia é melhor do que Null, menos chance de dar merda obg
      )
  }

  // getHero(id: number): Observable<Hero> {
  //   const url = `${this.heroesUrl}/${id}`;
  //   return this.http.get<Hero>(url).pipe(
  //     tap(_ => console.log(`fetched hero id=${id}`)),
  //     catchError(this.handleError<Hero>(`getHero id=${id}`))
  //   );
  // }

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
