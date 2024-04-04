import {Component, OnInit} from '@angular/core';
import {Patient} from "../../models/Patient.model";
import {Hospital} from "../../models/Hospital.model";
import {HospitalService} from "../../services/hospital.service";
import {Router} from "@angular/router";

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

  hospital: Hospital = {address: "", name: "", phone: ""}

  constructor(
    private router: Router,
    private hospitalService:HospitalService
  ) {
  }

  ngOnInit() {
    this.getHospital();
    this.setAuthenticatedUser();
  }

  getHospital(){
    this.hospitalService.find('00000000-0000-0064-0000-000000000064').subscribe({
      next: (data) =>{
        this.hospital = data;
      }
    })
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

  logOut() {
    localStorage.removeItem('user');
    this.router.navigate(['login']);
  }


}
