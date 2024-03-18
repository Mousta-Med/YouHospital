import {ActivatedRouteSnapshot, CanActivateFn, Router, RouterStateSnapshot} from "@angular/router";
import {AuthenticationResponse} from "../models/Authentication-response.model";
import {JwtHelperService} from "@auth0/angular-jwt";
import {inject, Injectable} from '@angular/core';
import {Location} from "@angular/common";

@Injectable({
  providedIn: 'root'
})

class AdminGuard {

  constructor(
    private router: Router,
    private location: Location,
  ) {
  }

  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot, allowedRoles: string[] = []): boolean {
    const storedUser = localStorage.getItem('user');
    if (storedUser) {
      const authResponse: AuthenticationResponse = JSON.parse(storedUser);
      const userRole = authResponse.role;

      if (!allowedRoles.length || allowedRoles.includes(userRole)) {
        const token: string = authResponse.token;
        if (token && !new JwtHelperService().isTokenExpired(token)) {
          return true;
        }
      }
    }
    this.router.navigate(['login']);
    return false;
  }

  isSigned(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): boolean {
    const storedUser = localStorage.getItem('user');
    if (storedUser) {
      const authResponse: AuthenticationResponse = JSON.parse(storedUser);
      this.router.navigate([authResponse.role.toLowerCase()])
      return false;
    }
    return true;
  }

  isRole(route: ActivatedRouteSnapshot, state: RouterStateSnapshot, expectedRole: string): boolean {
    return this.canActivate(route, state, [expectedRole]);
  }
}

export const AccessGuardService: CanActivateFn = (route: ActivatedRouteSnapshot, state: RouterStateSnapshot): boolean =>
  inject(AdminGuard).canActivate(route, state);
export const IsSigned: CanActivateFn = (route: ActivatedRouteSnapshot, state: RouterStateSnapshot): boolean =>
  inject(AdminGuard).isSigned(route, state);
export const IsRole: CanActivateFn = (route: ActivatedRouteSnapshot, state: RouterStateSnapshot): boolean =>
  inject(AdminGuard).isRole(route, state, route.data["role"]);

