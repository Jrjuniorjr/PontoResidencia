import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';

import { User } from '../model/user';
import { Observable } from 'rxjs';
import { catchError, map, tap } from 'rxjs/operators';
import { Router } from '@angular/router';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

// Fake Entrada
let userPonto = { entrada:"", saida:""}

@Injectable({ providedIn: 'root' })
export class AlunoService { 

  constructor(private http:HttpClient, private router:Router) { }

    fetchUserPonto(matricula:string) {
      // simula um http get back-end
      // returns a entrada e saida do aluno
      return userPonto
    }

    postUserEntrada(matricula:string) {
      // enviar matricula pra servidor com POST
      if(!userPonto.entrada)
        userPonto.entrada = new Date().toString()

      return userPonto.entrada
    }

    postUserSaida(matricula:string) {
      // enviar matricula pra servidor com POST
      if(!userPonto.saida)
        userPonto.saida = new Date().toString()

      return userPonto.saida

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
  // private handleError<T> (operation = 'operation', result?: T) {
  //   return (error: any): Observable<T> => {

  //     // TODO: send the error to remote logging infrastructure
  //     console.error(error); // log to console instead

  //     // TODO: better job of transforming error for user consumption
  //     this.log(`${operation} failed: ${error.message}`);

  //     // Let the app keep running by returning an empty result.
  //     return of(result as T);
  //   };
  // }


  // auth = (login:string, password:string) => UserDB.find(user => user.login === login && user.password === password)

}

