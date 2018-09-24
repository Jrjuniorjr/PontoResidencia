import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpResponse } from '@angular/common/http';
import { Observable, of, empty } from 'rxjs';
import { catchError, map, tap } from 'rxjs/operators';

import { User } from '../../model/user'
import { EndpointService } from '../endpoint.service';
import { Relatorio } from '../../model/relatorio';

/**
 * LoginService.ts
 * Faz a requisição de login com o servidor
 * 
 * Utiliza HttpClient (Serviço)
 */

@Injectable({ providedIn: 'root' })
export class RelatorioService {

  constructor(
    private http:HttpClient,
    private endpointService:EndpointService
  ) { }
  
  fetchRelatorio(matricula:string): Observable<Relatorio[]> {
    // Headers para enviar no POST
    let httpHeaders = new HttpHeaders({
      'Content-Type' : 'application/json',  // Padrão para JSON
      'Cache-Control': 'no-cache'
    });

    return this.http.get<HttpResponse<Object>>(this.endpointService.relatorioAll, {headers:httpHeaders, observe:"response"})
      .pipe(
        tap(data => console.log(data)),
        map( (data:HttpResponse<Object>) => this.processRelatorio(data.body) as Relatorio[]), 
        catchError(this.handleError<Relatorio[]>("relatorio", [])) 
      )
  }

  private processRelatorio(data:any) {
    return data.map( r => {
      return new Relatorio(
        r.aluno.matricula,
        r.aluno.nome,
        r.horaent,
        r.horasai
      )
    })
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
