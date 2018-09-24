import { Observable, of } from "rxjs";
import { HttpClient, HttpHeaders, HttpResponse } from '@angular/common/http';
import { catchError, map, tap } from "rxjs/operators";

enum HttpStatus {
    OK = 200
}

interface IHeader {
    [index:string]:string
}

export abstract class BaseService {
  

    constructor(private http:HttpClient) {}

    protected doGet<T>(url:string) : Observable<T> {
        return this.http.get<T>(url)
    }

    protected doPost<T>(url:string, data:any, headers?:IHeader) : Observable<T> {
        // return this.http.post<T>(url, data, { headers:this.getHeaders(headers), observe:"body" })
        return this.httpPost(url, data, headers)
            .pipe(
                map( (data:HttpResponse<Object>) => data.body as T)
            )
    }

    protected doPostWithStatus<T>(url:string, data:any, status:any[], headers?:HttpHeaders) : Observable<T> | null{
        return this.http.post<HttpResponse<Object>>(url, data, {headers:headers, observe:"response"})
            .pipe(
                map( (data:HttpResponse<Object>) => {
                    if ( status.includes(data.status) )
                        return data.body as T
                    else
                        return null
                })
            )
    }

    /**
     * Faz um POST em {URL} com {DATA} e retorna TRUE se o Response possuir um STATUS.OK
     */
    protected doPostWaitOk(url:string, data:any) : Observable<boolean> {
        return this.httpPost(url, data).pipe(
            map( data => { return data.ok }) ,
            catchError(err =>  of(false))
        )
    }

    /**
     * doDeleteWaitOK
     * O DELETE retornará um HttpStatus (OK, !OK)
     * @param url - endpoint
     */
    protected doDelete<T>(url:string) : Observable<T> {
        return this.http.delete<T>(url).pipe(
            tap(data => console.log(data))
        )
    }

    /**
     * doPutWaitOK
     * O PUT retornará um HttpStatus (OK, !OK)
     * @param url - endpoint
     */
    protected doPut(url:string, data:any) : Observable<boolean> {
        return this.http.put(url, data, { observe:"response" }).pipe(
            map(data => data.ok),
            catchError(err => of(false))
        )
    }

    // POST Básico que retorna o Response do servidor como um HttpResponse
    private httpPost(url:string, data:any, headers?:IHeader) : Observable<HttpResponse<Object>> {
        return this.http.post(url, data, { headers:this.getHeaders(headers), observe:"response" })
    }

    private getHeaders(headers?:IHeader): HttpHeaders {
        let httpHeaders = new HttpHeaders({
            'Content-Type' : 'application/json',  // Padrão para JSON
            'Cache-Control': 'no-cache'
          });

        if(headers) {
            // for (const value of headers.keys()) {
            //     httpHeaders.append(value, headers.get(value))
            // }
            for (const key in headers) {
                if (headers.hasOwnProperty(key)) {
                    httpHeaders.append(key, headers[key])
                }
            }
        }

        return httpHeaders;
    }


    /**
   * Handle de operações Http criado pelo Angular.io Docs
   * Não trava a aplicação quando ocorrer algum erro na operação
   * @param operation: string - Nome da operação que falhou
   * @param result?: T - Caso a operação falhe irá retornar um novo Observable<T> passado através do result
   */
  protected handleError<T> (operation = 'operation', result?: T) {
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