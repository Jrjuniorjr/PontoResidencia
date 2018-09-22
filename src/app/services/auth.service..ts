import { AdminDashboardComponent } from './../admin/admindashboard/admindashboard.component';
import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';
import { catchError, map, tap } from 'rxjs/operators';

import { User } from '../model/user';
import { EndpointService } from './endpoint.service';

/**
 * AuthService.ts
 * Faz a requisição de login com o servidor
 * 
 * Utiliza HttpClient (Serviço)
 */

 // TODO: renomear para AuthService
@Injectable({ providedIn: 'root' })
export class AuthService { 
  public redirectUrl:string = ""
  private _authUser:User;
  
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
      'Cache-Control': 'no-cache'
    });

    let data = {
      matricula:login,
      senha:password
    }
    
    return this.http.post<HttpResponse<Object>>("http://localhost:8080/residente/login", data, {headers:httpHeaders, observe:"response"})
      .pipe(
        map( (data:HttpResponse<Object>) => data.body as User), // Recebi o HTTP RESPONSE, ainda não sei o que tem no RESPONSE.body
        map( (data:any) => {
          this._authUser = data
          this.verifyRoute(data)
          return data
        }),// Verifico se o que tem no RESPONSE.body é um USER
        catchError(this.handleError<User>("Auth login", new User("","","",null,""))) // String vazia é melhor do que Null, menos chance de dar merda obg
      )
  }

  public logout() {
    this._authUser = null
    }
  
    public verifyRoute(user:User) {

      if(user.adm == '1') {

        this.redirectUrl = "/admin"

      } else if (user.adm == '0') {

        this.redirectUrl = "/aluno"

      } else {

        this.redirectUrl = "";
      }

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

  get authUser() { return this._authUser }

}
