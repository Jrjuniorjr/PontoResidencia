import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpResponse } from '@angular/common/http';
import { Observable, of, empty } from 'rxjs';
import { catchError, map, tap } from 'rxjs/operators';

import { AuthService } from '../auth/auth.service';
import { EndpointService } from '../endpoint.service';
import { User } from '../../model/user';
import { BaseService } from '../BaseService';

/**
 * AdminService.ts
 * Faz as requisição necessárias de Admin com o servidor
 * 
 * Utiliza HttpClient (Serviço)
 */

@Injectable({ providedIn: 'root' })
export class AdminService extends BaseService {

  constructor(
    http:HttpClient, 
    private endpointService: EndpointService,
    private authService: AuthService
  ) { 
    super(http)
  }

  listarAlunos(): Observable<User[]> {
    return this.doGet<User[]>(this.endpointService.listarAluno)

    // return this.http.get<HttpResponse<Object>>(this.endpointService.listarAluno, {headers:httpHeaders, observe:"response"})
    //   .pipe(
    //     map( (data:HttpResponse<Object>) =>  data.body as User[]), 
    //     catchError(this.handleError<User[]>("listar alunos", [])) 
    //   )
  }

  listarAlunoPorMatricula(matricula:string) : Observable<User> {
    return this.doGet<User>(this.endpointService.listarAlunoByMatricula + matricula)
  }

  removerAluno(matricula:string) {
    return this.doDelete<boolean>(this.endpointService.removerAluno + matricula)
      .pipe(
          tap(data => console.log(data)),
          catchError(err => { console.log(err); return of(false) }) 
      )
  }

  /**
   * POST
   * @returns - HTTP_OK | HTTP_CONFLICT
   * @param user - Residente
   */
  inserirAluno(user:User) : Observable<boolean> {
    return this.doPostWaitOk(this.endpointService.inserirAluno, user)
    // return this.http.post<HttpResponse<Object>>("http://localhost:8080/residente/", user, {headers:httpHeaders, observe:"response"})
    //     .pipe(
    //       tap(data => console.log("Data:"+data.body)),
    //       // map( (data:HttpResponse<Object>) =>  data.ok),
    //       catchError(this.handleError<boolean>("inserir aluno", false)) 
    //     )
  }

  atualizarAluno(user:User) : Observable<boolean> {
    return this.doPut(this.endpointService.atualizarAluno, user)
  }
}
