import {Injectable} from '@angular/core';
import {HttpEvent, HttpHandler, HttpHeaders, HttpInterceptor, HttpRequest} from '@angular/common/http';
import {Observable} from 'rxjs';
import {AuthenticationResponse} from "../models/Authentication-response.model";
import {JwtHelperService} from "@auth0/angular-jwt";
import {Router} from "@angular/router";

@Injectable()
export class AuthInterceptor implements HttpInterceptor {

  constructor(
    private jwtHelper: JwtHelperService,
    private router: Router,) {
  }

  intercept(request: HttpRequest<unknown>, next: HttpHandler): Observable<HttpEvent<unknown>> {
    const storedUser = localStorage.getItem('user')
    if (storedUser) {
      const authResponse: AuthenticationResponse = JSON.parse(storedUser);
      const token: string = authResponse.token;
      if (token) {
        if (this.jwtHelper.isTokenExpired(token)) {
          localStorage.removeItem('user');
          this.router.navigate(['login']);
        } else {
          const authRequest = request.clone({
            headers: new HttpHeaders({
              Authorization: 'Bearer ' + token
            })
          });
          return next.handle(authRequest);
        }
      }
    }
    return next.handle(request);
  }
}
