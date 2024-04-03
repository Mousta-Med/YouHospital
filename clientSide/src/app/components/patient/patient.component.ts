import {Component, OnInit} from '@angular/core';
import {Patient} from "../../models/Patient.model";

@Component({
  selector: 'app-patient',
  templateUrl: './patient.component.html',
  styleUrls: ['./patient.component.scss']
})
export class PatientComponent implements OnInit {


  patient: Patient = {
    dateOfBirth: "",
    departmentId: "",
    email: "",
    firstName: "",
    gender: "MALE",
    identityCode: "",
    identityType: "PASSPORT",
    lastName: "",
    pass: "",
    patientType: "",
    phone: "",
    roomId: ""
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
      if (authResponse.patient) {
        this.patient = authResponse.patient;
      }
    }
  }


}
