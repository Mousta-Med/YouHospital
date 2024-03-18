import {Component} from '@angular/core';
import {Router} from "@angular/router";
import {AuthenticationService} from "../../services/authentication.service";
import {FormControl, FormGroup, Validators} from "@angular/forms";

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss']
})
export class RegisterComponent {

  errMsg: string = '';
  registerForm: FormGroup = new FormGroup({
    name: new FormControl('', [Validators.required]),
    password: new FormControl('', [Validators.required]),
    status: new FormControl('PENDING',),
    username: new FormControl('', [Validators.required]),
    familyName: new FormControl('', [Validators.required]),
    accessionDate: new FormControl('', [Validators.required]),
    nationality: new FormControl('', [Validators.required]),
    identityDocument: new FormControl('', [Validators.required, Validators.minLength(3)]),
    role: new FormControl('', [Validators.required, Validators.minLength(3)]),
    identityNumber: new FormControl('0', [Validators.required]),
  });

  constructor(
    private router: Router,
    private authService: AuthenticationService) {
  }

  register() {
    this.authService.register(this.registerForm.value)
      .subscribe({
        next: (authenticationResponse) => {
          localStorage.setItem('user', JSON.stringify(authenticationResponse))
          this.router.navigate(['patient'])
        },
        error: (error) => {
          this.errMsg = error.error.error;
        }
      })
    this.registerForm.reset();
  }


}
