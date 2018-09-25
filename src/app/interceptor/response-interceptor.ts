import { Injectable } from '@angular/core';
import { HttpInterceptor, HttpRequest, HttpHandler, HttpEvent, HttpResponse, HttpErrorResponse, HttpResponseBase } from '@angular/common/http';
import { Observable, throwError, of } from 'rxjs';

import { tap, catchError } from 'rxjs/operators';

@Injectable({ providedIn: 'root' })
export class ResponseInterceptor implements HttpInterceptor {

  constructor() { }

  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    return next.handle(req).pipe(
        catchError( err => { 
            return throwError(err)
        })
        
    )
  }
}

/*
    HANDLING HTTP RESPONSE GERAIS
    return next.handle(req).pipe(
        tap( (event:HttpEvent<any>) => {
            if (event instanceof HttpResponse) {
                // Fazer algo com os Response (contigo dentro de event)
                // event = event.clone({ body: resolveReferences(event.body) })
            }         
            return event; 
        })

    tap() também possui "errors" !!
    tap( event => { 
        ... 
    }, error => {
        error.status
        error.message
    } )
*/
