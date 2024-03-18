import { Injectable } from '@angular/core';
import {
  HttpRequest,
  HttpHandler,
  HttpEvent,
  HttpInterceptor, HttpHeaders
} from '@angular/common/http';
import { Observable } from 'rxjs';
import {AuthenticationResponse} from "../models/Authentication-response.model";

@Injectable()
export class AuthInterceptor implements HttpInterceptor {

  constructor() {}

  intercept(request: HttpRequest<unknown>, next: HttpHandler): Observable<HttpEvent<unknown>> {
    const storedUser = localStorage.getItem('user')
    if (storedUser) {
      const authResponse: AuthenticationResponse = JSON.parse(storedUser);
      const token: string = authResponse.token;
      if (token) {
        const authRequest= request.clone({
          headers: new HttpHeaders({
            Authorization: 'Bearer ' + token
          })
        });
        return next.handle(authRequest);
      }
    }
    return next.handle(request);
  }
}
