import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';
import { catchError, map, tap } from 'rxjs/operators';

import { User } from '../model/user';
import {EndpointPonto} from '../api/endpoint';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

// Fake Entrada
let userPonto = { entrada:"", saida:""}

@Injectable({ providedIn: 'root' })
export class AlunoService { 

  constructor(private http:HttpClient) { }

    baterPonto(matricula:string) {
      let httpHeaders = new HttpHeaders({
        'Content-Type' : 'application/json',
        'Cache-Control': 'no-cache'
      });

      const endpoint = EndpointPonto.baseUrl + "/" + matricula
  
      // Quero receber a resposta do servidor (Http Response) crua (completa) em vez do angular tentar converter a resposta em um Objeto, logo eu digo que o .post irá receber um HttpResponse<Object> (Angular)
      // Pra fazer com que o Angular me retorne o Http Response precisa passar o parâmetro { "observe" : "response" } no Options do .post
      // return this.http.post<HttpResponse<Object>>(endpoint, null, {headers:httpHeaders, observe:"response"}).pipe(
      //   tap( (data:HttpResponse<Object>) => { 
      //     if(data && data.ok)
      //       this.setPonto(matricula)
      //   }),
      //   catchError(this.handleError("bater-ponto", {})
      // ))

      this.setPonto(matricula)
    }

    public getPonto(item:string) {
      return localStorage.getItem(item)
    }

    private setPonto(matricula:string) {
      let item = this.getPonto(matricula+"-entrada")? matricula+"-saida" : matricula+"-entrada"

      if(!localStorage.getItem(item))
        localStorage.setItem(item, new Date().toString())
    }

    

  // getHero(id: number): Observable<Hero> {
  //   const url = `${this.heroesUrl}/${id}`;
  //   return this.http.get<Hero>(url).pipe(
  //     tap(_ => console.log(`fetched hero id=${id}`)),
  //     catchError(this.handleError<Hero>(`getHero id=${id}`))
  //   );
  // }

    /**
   * Handle Http operation that failed.
   * Let the app continue.
   * @param operation - name of the operation that failed
   * @param result - optional value to return as the observable result
   */
  private handleError<T> (operation = 'operation', result?: T) {
    return (error: any): Observable<T> => {

      // TODO: send the error to remote logging infrastructure
      console.error(error); // log to console instead

      // TODO: better job of transforming error for user consumption
      console.log(`${operation} failed: ${error.message}`);

      // Let the app keep running by returning an empty result.
      return of(result as T);
    };
  }



  // auth = (login:string, password:string) => UserDB.find(user => user.login === login && user.password === password)

}

