import { Injectable } from '@angular/core';
import { CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot } from '@angular/router';
import { AuthService } from '../services/auth.service.';

@Injectable({ providedIn: 'root' })
export class AlunoGuard implements CanActivate {

  constructor(private authService:AuthService) {}

  canActivate(next: ActivatedRouteSnapshot, state: RouterStateSnapshot): boolean {
    return this.authService.authUser.adm === '0'// CLEVER ONE, BOY
  }
}
