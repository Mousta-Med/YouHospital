import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {PagenotfoundComponent} from "./components/pagenotfound/pagenotfound.component";
import {AppLayoutComponent} from "./components/app-layout/app-layout.component";
import {LoginComponent} from "./components/login/login.component";
import {RegisterComponent} from "./components/register/register.component";
import {AccessGuardService, IsRole , IsSigned} from "./guards/auth.guard";
import {AdminComponent} from "./components/admin/admin.component";
import {PatientComponent} from "./components/patient/patient.component";
import {DoctorComponent} from "./components/doctor/doctor.component";
import {NurseComponent} from "./components/nurse/nurse.component";
import {ReceptionistComponent} from "./components/receptionist/receptionist.component";

const routes: Routes = [
  {
    path:"",
    component: AppLayoutComponent,
    canActivate:[AccessGuardService],
    children: [
      {
        path: 'admin',
        component: AdminComponent,
        canActivate: [IsRole], data: { role: 'ADMIN' }
      },{
        path: 'patient',
        component: PatientComponent,
        canActivate: [IsRole], data: { role: 'PATIENT' }
      },{
        path: 'doctor',
        component: DoctorComponent,
        canActivate: [IsRole], data: { role: 'DOCTOR' }
      },{
        path: 'nurse',
        component: NurseComponent,
        canActivate: [IsRole], data: { role: 'NURSE' }
      },{
        path: 'receptionist',
        component: ReceptionistComponent,
        canActivate: [IsRole], data: { role: 'RECEPTIONIST' }
      },
    ]
  },
  {
    path: "login",
    component: LoginComponent,
    canActivate: [IsSigned]
  },
  {
    path: "register",
    component: RegisterComponent,
    canActivate: [IsSigned]
  },
  {
    path: "**",
    pathMatch: "full",
    component: PagenotfoundComponent
  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})

export class AppRoutingModule { }
