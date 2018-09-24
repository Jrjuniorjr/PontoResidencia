import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, of } from 'rxjs';
import { catchError, map, tap } from 'rxjs/operators';

import { User } from '../../model/user';
import { EndpointService } from '../endpoint.service';
import { BaseService } from '../BaseService';

/**
 * AuthService.ts
 * Faz a requisição de login com o servidor
 * 
 * Utiliza HttpClient (Serviço)
 */

@Injectable({ providedIn: 'root' })
export class AuthService extends BaseService { 
  public redirectUrl:string = ""
  private _authUser:User;
  
  constructor(
    http:HttpClient,
    private endpointService:EndpointService
  ) { 
    super(http);
  }

  /**
   * Utiliza o serviço HttpClient para fazer um POST no servidor
   * Tem como resposta um HTTP RESPONSE o qual em seu atributo BODY possui um JSON que representa um Usuário: {matricula: string, nome: string}
   * @param matricula: string -matricula passada pelo Form
   * @param senha: string - senha passada pelo Form 
   */
  // login(matricula:string, senha:string): Observable<User> {
  //     // Headers para enviar no POST
  //   let httpHeaders = new HttpHeaders({
  //     'Content-Type' : 'application/json',  // Padrão para JSON
  //     'Cache-Control': 'no-cache'
  //   });

  //   let data = {
  //     matricula:login,
  //     senha:password
  //   }
    
  //   return this.http.post<HttpResponse<Object>>("http://localhost:8080/residente/login", data, {headers:httpHeaders, observe:"response"})
  //     .pipe(
  //       map( (data:HttpResponse<Object>) => data.body as User), // Recebi o HTTP RESPONSE, ainda não sei o que tem no RESPONSE.body
  //       map( (data:any) => {
  //         this._authUser = data
  //         this.verifyRoute(data)
  //         return data
  //       }),// Verifico se o que tem no RESPONSE.body é um USER
  //       catchError(this.handleError<User>("Auth login", new User("","","",null,""))) // String vazia é melhor do que Null, menos chance de dar merda obg
  //     )
  // }
  login(matricula:string, senha:string) : Observable<User> {
    // console.log(this.endpointService.login)
    return this.doPost<User>(this.endpointService.login,{matricula,senha})
      .pipe(
        map( (data:User) => this.verifyUser(data)),
        catchError( err => of(this.verifyUser(null))) // necessário para quando o servidor não consegue retornar NADA
      )
  }

  public logout() {
    this._authUser = null
  }

  private verifyUser(user:any) : User {
    if(!this.isUser(user)) return new User("", "", "", null)

    user = new User(
      user.matricula,
      user.nome,
      user.token,
      user.tipo,
      null
    )

    this._authUser = user

    if(user.isAdmin())
      this.redirectUrl = '/admin'
    else if(user.isResidente())
      this.redirectUrl = '/aluno'
    else
      this.redirectUrl = ''
      
    return user

  }

  // Type Guard para User
  // Verifica se o que foi retornado do servidor é de fato um User
  private isUser(user:any): user is User {
    // return user? (user.matricula && user.nome && user.tipo && user.token)? true : false : false
    
    let result:boolean

    if(user) {
      if (user.matricula && user.nome && user.tipo && user.token)
        result = true
      else  // algum campo importante faltando
        result = false
    } else // null
      result = false
    
      return result

  }
  
  /**
   * Handle de operações Http criado pelo Angular.io Docs
   * Não trava a aplicação quando ocorrer algum erro na operação
   * @param operation: string - Nome da operação que falhou
   * @param result?: T - Caso a operação falhe irá retornar um novo Observable<T> passado através do result
   */
  // private handleError<T> (operation = 'operation', result?: T) {
  //   // O catchError irá passar um parâmetro "error" caso caia aqui
  //   return (error: any): Observable<T> => {

  //     // log na interface de erro definida
  //     // console.error(error); 

  //     // TODO: Implementar uma forma melhor de tratar o erro
  //     console.error(`${operation} failed: ${error.message}`);

  //     // Se passado um result<T> então retonará um novo Observable<T>
  //     return of(result as T);
  //   };
  // }

  get authUser() { return this._authUser }

}
