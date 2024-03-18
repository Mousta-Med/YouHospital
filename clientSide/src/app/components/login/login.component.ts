import {Component} from '@angular/core';
import {AuthenticationService} from "../../services/authentication.service";
import {Router} from "@angular/router";
import {FormControl, FormGroup, Validators} from "@angular/forms";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent {

  errMsg: string = '';
  loginForm: FormGroup = new FormGroup({
    email: new FormControl('', [Validators.required]),
    password: new FormControl('', [Validators.required]),
  });

  constructor(
    private authenticationService: AuthenticationService,
    private router: Router
  ) {
  }

  login() {
    this.errMsg = '';
    this.authenticationService.login(this.loginForm.value)
      .subscribe({
        next: (authenticationResponse) => {
          localStorage.setItem('user', JSON.stringify(authenticationResponse))
          this.router.navigate([authenticationResponse.role.toLowerCase()])
        },
        error: (error) => {
          this.errMsg = error.error.error;
          console.log(error)
        }
      })
  }

}
