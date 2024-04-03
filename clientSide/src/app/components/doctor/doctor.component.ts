import {Component, OnInit} from '@angular/core';
import {Staff} from "../../models/Staff.model";

@Component({
  selector: 'app-doctor',
  templateUrl: './doctor.component.html',
  styleUrls: ['./doctor.component.scss']
})
export class DoctorComponent implements OnInit {


  doctor: Staff = {
    address: "",
    departmentId: "",
    email: "",
    firstName: "",
    gender: "MALE",
    identityCode: "",
    identityType: "PASSPORT",
    lastName: "",
    operationId: "",
    pass: "",
    phone: "",
    recruitmentDate: "",
    role: "DOCTOR",
    specialization: ""
  };

  constructor() {
  }

  ngOnInit() {
    this.setAuthenticatedUser();
  }

  setAuthenticatedUser() {
    const authenticatedUser = localStorage.getItem('user');
    if (authenticatedUser) {
      const authResponse = JSON.parse(authenticatedUser);
      if (authResponse.staff) {
        this.doctor = authResponse.staff;
      }
    }
  }


}
