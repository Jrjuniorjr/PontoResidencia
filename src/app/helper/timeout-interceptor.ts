import { Injectable } from '@angular/core';
import { HttpInterceptor, HttpRequest, HttpHandler, HttpEvent } from '@angular/common/http';
import { Observable, throwError} from 'rxjs';
import { timeoutWith } from 'rxjs/operators';

@Injectable({ providedIn: 'root' })
export class TimeoutInterceptor implements HttpInterceptor{

  constructor() { }

  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    return next.handle(req).pipe(
      timeoutWith(20000,  throwError(new Error("Timeout in Http !")))
    )
  }
}
