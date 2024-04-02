import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {PagenotfoundComponent} from "./components/pagenotfound/pagenotfound.component";
import {AppLayoutComponent} from "./components/app-layout/app-layout.component";
import {LoginComponent} from "./components/login/login.component";
import {AccessGuardService, IsRole, IsSigned} from "./guards/auth.guard";
import {AdminComponent} from "./components/admin/admin.component";
import {PatientComponent} from "./components/patient/patient.component";
import {NurseComponent} from "./components/nurse/nurse.component";
import {ReceptionistComponent} from "./components/receptionist/receptionist.component";
import {StaffComponent} from "./components/staff/staff.component";
import {HospitalComponent} from "./components/hospital/hospital.component";
import {PatientsComponent} from "./components/patients/patients.component";
import {BillComponent} from "./components/bill/bill.component";
import {ExaminationComponent} from "./components/examination/examination.component";
import {OperationComponent} from "./components/operation/operation.component";
import {RecipeComponent} from "./components/recipe/recipe.component";

const routes: Routes = [
  {
    path: "",
    component: AppLayoutComponent,
    canActivate: [AccessGuardService],
    children: [
      {
        path: 'admin',
        component: AdminComponent,
        children: [
          {
            path: "staff",
            component: StaffComponent
          },
          {
            path: "hospital",
            component: HospitalComponent
          },
        ],
        canActivate: [IsRole], data: {role: 'ADMIN'}
      },
      {
        path: 'receptionist',
        component: ReceptionistComponent,
        children: [
          {
            path: "patient",
            component: PatientsComponent
          },
          {
            path: "bill",
            component: BillComponent
          },
          {
            path: "examination",
            component: ExaminationComponent
          }
        ],
        canActivate: [IsRole], data: {role: 'RECEPTIONIST'}
      },
      {
        path: 'nurse',
        component: NurseComponent,
        children: [
          {
            path: "operation",
            component: OperationComponent
          },
          {
            path: "recipe",
            component: RecipeComponent
          },
        ],
        canActivate: [IsRole], data: {role: 'NURSE'}
      },
      {
        path: 'patient',
        component: PatientComponent,
        canActivate: [IsRole], data: {role: 'PATIENT'}
      },
      {
        path: 'doctor',
        component: PatientComponent,
        canActivate: [IsRole], data: {role: 'DOCTOR'}
      },
    ]
  },
  {
    path: "login",
    component: LoginComponent,
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

export class AppRoutingModule {
}
