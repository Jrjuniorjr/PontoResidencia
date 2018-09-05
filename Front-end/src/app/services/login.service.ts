import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
// import { DataService } from '../../services/data.service';
import { User } from '../model/user';
import { Observable } from 'rxjs';
import { catchError, map, tap } from 'rxjs/operators';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

// export class Hero {
//   id: number;
//   name: string;
// }


@Injectable({ providedIn: 'root' })
export class LoginService { // TODO: renomear para AuthService
  private BASE_URL = "api"
  private LOGIN_URL = "/users/?login=^LOGIN_STR$&password=^PASS_STR$" // gambiarra pq o regex não quer pegar lul

  // private heroesUrl = 'api/users';  // URL to web api

  constructor(private http:HttpClient) { }

  login(login:string, password:string) {
    const LOGIN_URL = this.LOGIN_URL.replace("LOGIN_STR", login).replace("PASS_STR", password)
    return this.http.get<User>(this.BASE_URL + LOGIN_URL)
    // return UserDB.find(user => user.login === login && user.password === password)
  }

  private auth(login:string, password:string) : Observable<User>{
    return this.http.get<User>(this.BASE_URL + this.LOGIN_URL)
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
