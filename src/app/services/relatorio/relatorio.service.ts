import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, of } from 'rxjs';
import { catchError } from 'rxjs/operators';

import { EndpointService } from '../endpoint.service';
import { Relatorio } from '../../model/relatorio';
import { BaseService } from '../BaseService';

/**
 * LoginService.ts
 * Faz a requisição de login com o servidor
 * 
 * Utiliza HttpClient (Serviço)
 */

@Injectable({ providedIn: 'root' })
export class RelatorioService extends BaseService {

  constructor(
    http:HttpClient,
    private endpointService:EndpointService
  ) {
    super(http)
   }
  
  fetchRelatorio(matricula?:string): Observable<Relatorio[]> {
    let endpoint:string
    
    if(matricula)
      endpoint = this.endpointService.relatorioAllByMatricula + matricula
    else
      endpoint = this.endpointService.relatorioAll

    return this.doGet<Relatorio[]>(endpoint)
      .pipe(
        catchError(err => of([]))
      )
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
}
