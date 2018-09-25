import { Injectable } from '@angular/core';
import { HttpInterceptor, HttpRequest, HttpHandler, HttpEvent } from '@angular/common/http';
import { Observable } from 'rxjs';

import {AuthService} from '../services/auth/auth.service';

@Injectable({ providedIn: 'root' })
export class TokenInterceptor implements HttpInterceptor{

  constructor(private authService:AuthService) { }

  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    let currentUser = this.authService.authUser

    if (currentUser && currentUser.token) {
        req = req.clone({
            setHeaders: { 
                Authorization: currentUser.token
            }
        });
    }

    //TODO: DEBUG !!
    let headers = []
    for (const key of req.headers.keys()) {
        let o = {}
        o[key] = req.headers.get(key)
        headers.push(o)
    }

    let body = req.body? JSON.stringify(req.body) : "NULL"
    console.log("SENDING...\nMethod:"+req.method+"\nURL:"+req.urlWithParams+"\nBody:"+body+"\nHeaders:"+JSON.stringify(headers, null, 4)+"\n")
    
    return next.handle(req)
  }
}
