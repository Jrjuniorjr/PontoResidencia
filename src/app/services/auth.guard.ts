import { Injectable } from '@angular/core';
import {
  CanActivate, Router,
  ActivatedRouteSnapshot,
  RouterStateSnapshot,
  CanLoad, Route
}                           from '@angular/router';
import { AuthService } from './auth.service.';
// import { AuthService }      from './auth.service';

/*
  Ordem das chamadas do route
  AuthService -> Login (Componente) + Mudança de Rota -> Auth Guard (this) -> Admin Guard
*/
@Injectable({ providedIn: 'root' })
export class AuthGuard implements CanActivate, CanLoad {

  constructor(
    private authService:AuthService,
    private router: Router
  ) {}

  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): boolean {
    return this.checkLogin(state.url);
  }

  canLoad(route: Route): boolean {
    return  this.checkLogin(`/${route.path}`);
  }

  checkLogin(url: string): boolean {
    if (this.authService.authUser) { return true; }

    // Is the user not logged in? 
    // If not, we need to simply store the protected URL they are attempting to access so we can redirect them back 
    // there once they've authenticated.
    // this.authService.redirectUrl = url;

    this.router.navigate(['/login']);
    return false;
  }
}



  

  
