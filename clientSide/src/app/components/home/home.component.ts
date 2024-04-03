import {Component, OnInit} from '@angular/core';
import {Person} from "../../models/Person.model";

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent implements OnInit {

  person!: Person;

  constructor() {
  }

  ngOnInit() {
    this.setAuthenticatedUser();
  }

  setAuthenticatedUser() {
    const authenticatedUser = localStorage.getItem('user');
    if (authenticatedUser) {
      const authResponse = JSON.parse(authenticatedUser);
      if (authResponse.admin) {
        this.person = authResponse.admin;
      } else if (authResponse.staff) {
        this.person = authResponse.staff;
      } else {
        this.person = authResponse.patient;
      }
    }
  }


}
