import {Component, OnInit} from '@angular/core';
import {JwtHelperService} from "@auth0/angular-jwt";
import {AuthenticationResponse} from "./models/Authentication-response.model";
import {Router} from "@angular/router";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent implements OnInit {
  title = 'clientSide';

  constructor(
    private jwtHelper: JwtHelperService,
    private router: Router,) {
  }

  ngOnInit() {
    const storedUser = localStorage.getItem('user');
    if (storedUser) {
      const authResponse: AuthenticationResponse = JSON.parse(storedUser);
      const token: string = authResponse.token;
      if (this.jwtHelper.isTokenExpired(token)) {
        localStorage.removeItem('user');
        this.router.navigate(['login']);
      }
    }
  }


}
