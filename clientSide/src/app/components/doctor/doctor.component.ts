import {Component, OnInit} from '@angular/core';
import {Staff} from "../../models/Staff.model";
import {OperationService} from "../../services/operation.service";
import {RecipeService} from "../../services/recipe.service";
import {ExaminationService} from "../../services/examination.service";
import {Patient} from "../../models/Patient.model";
import {Recipe} from "../../models/Recipe.model";
import {Operation} from "../../models/Operation.model";
import {Examination} from "../../models/Examination.model";

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

  constructor(
  ) {
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
