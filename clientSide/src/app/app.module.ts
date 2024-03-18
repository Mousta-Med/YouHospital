import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import {HTTP_INTERCEPTORS, HttpClientModule} from "@angular/common/http";
import {AuthInterceptor} from "./interceptors/auth.interceptor";
import {MenuModule} from "primeng/menu";
import {ChartModule} from "primeng/chart";
import {TableModule} from "primeng/table";
import {RouterModule} from "@angular/router";
import {BrowserAnimationsModule} from "@angular/platform-browser/animations";
import { PagenotfoundComponent } from './components/pagenotfound/pagenotfound.component';
import { AppLayoutComponent } from './components/app-layout/app-layout.component';
import { LoginComponent } from './components/login/login.component';
import {MessageModule} from "primeng/message";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import { RegisterComponent } from './components/register/register.component';
import { AdminComponent } from './components/admin/admin.component';
import { DoctorComponent } from './components/doctor/doctor.component';
import { PatientComponent } from './components/patient/patient.component';
import { NurseComponent } from './components/nurse/nurse.component';
import { ReceptionistComponent } from './components/receptionist/receptionist.component';

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
    ReactiveFormsModule
  ],
  providers: [{provide: HTTP_INTERCEPTORS,useClass: AuthInterceptor,multi: true}],
  bootstrap: [AppComponent]
})

export class AppModule { }
