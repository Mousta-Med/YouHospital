import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {AuthenticationResponse} from "../models/Authentication-response.model";
import {AuthenticationRequest} from "../models/Authentication-request.model";
import {Observable} from "rxjs";
import {Patient} from "../models/Patient.model";
import {environment} from "../../environments/environment";

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {

  private readonly registerUrl = `${environment.api.baseUrl}/${environment.api.registerUrl}`;
  private readonly loginUrl = `${environment.api.baseUrl}/${environment.api.loginUrl}`;

  constructor(
    private http: HttpClient
  ) {
  }

  login(authRequest: AuthenticationRequest): Observable<AuthenticationResponse> {
    return this.http.post<AuthenticationResponse>(this.loginUrl, authRequest);
  }

  register(user: Patient): Observable<AuthenticationResponse> {
    return this.http.post<AuthenticationResponse>(this.registerUrl, user);
  }
}
