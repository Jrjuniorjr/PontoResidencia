import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, of } from 'rxjs';
import { catchError, tap } from 'rxjs/operators';

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
  }

  listarAlunoPorMatricula(matricula:string) : Observable<User> {
    return this.doGet<User>(this.endpointService.listarAlunoByMatricula + matricula)
  }

  removerAluno(matricula:string) : Observable<boolean> {
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
  }

  atualizarAluno(user:User) : Observable<boolean> {
    return this.doPut(this.endpointService.atualizarAluno, user)
  }
}
