import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';

import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {HTTP_INTERCEPTORS, HttpClientModule} from "@angular/common/http";
import {AuthInterceptor} from "./interceptors/auth.interceptor";
import {MenuModule} from "primeng/menu";
import {ChartModule} from "primeng/chart";
import {TableModule} from "primeng/table";
import {RouterModule} from "@angular/router";
import {BrowserAnimationsModule} from "@angular/platform-browser/animations";
import {PagenotfoundComponent} from './components/pagenotfound/pagenotfound.component';
import {AppLayoutComponent} from './components/app-layout/app-layout.component';
import {LoginComponent} from './components/login/login.component';
import {MessageModule} from "primeng/message";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {RegisterComponent} from './components/register/register.component';
import {AdminComponent} from './components/admin/admin.component';
import {DoctorComponent} from './components/doctor/doctor.component';
import {PatientComponent} from './components/patient/patient.component';
import {NurseComponent} from './components/nurse/nurse.component';
import {ReceptionistComponent} from './components/receptionist/receptionist.component';
import {CardComponent} from './components/card/card.component';
import {ButtonModule} from "primeng/button";
import {ManageStaffComponent} from './components/manage-staff/manage-staff.component';
import {DialogModule} from "primeng/dialog";
import {ConfirmationService, MessageService} from "primeng/api";
import {ToastModule} from "primeng/toast";
import {ConfirmDialogModule} from "primeng/confirmdialog";
import {StaffComponent} from './components/staff/staff.component';
import {MenuItemComponent} from './components/menu-item/menu-item.component';
import {HospitalComponent} from './components/hospital/hospital.component';
import {DepartmentComponent} from './components/department/department.component';
import {RoomComponent} from './components/room/room.component';
import {JWT_OPTIONS, JwtHelperService} from "@auth0/angular-jwt";

@NgModule({
  declarations: [
    AppComponent,
    PagenotfoundComponent,
    AppLayoutComponent,
    LoginComponent,
    RegisterComponent,
    AdminComponent,
    DoctorComponent,
    PatientComponent,
    NurseComponent,
    ReceptionistComponent,
    CardComponent,
    ManageStaffComponent,
    StaffComponent,
    MenuItemComponent,
    HospitalComponent,
    DepartmentComponent,
    RoomComponent,

  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    MenuModule,
    ChartModule,
    TableModule,
    RouterModule,
    BrowserAnimationsModule,
    HttpClientModule,
    MessageModule,
    FormsModule,
    ReactiveFormsModule,
    ButtonModule,
    DialogModule,
    ToastModule,
    ConfirmDialogModule,
  ],
  providers: [
    MessageService,
    ConfirmationService,
    JwtHelperService,
    {provide: JWT_OPTIONS, useValue: JWT_OPTIONS},
    {provide: HTTP_INTERCEPTORS, useClass: AuthInterceptor, multi: true}
  ],
  bootstrap: [AppComponent]
})

export class AppModule {
}
