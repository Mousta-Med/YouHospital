import { Injectable } from '@angular/core';
import {environment} from "../../environments/environment";
import {HttpClient} from "@angular/common/http";
import {Patient} from "../models/Patient.model";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class PatientService {

  private readonly patientUrl = `${environment.api.baseUrl}/${environment.api.patientUrl}`;
  constructor(
    private http: HttpClient
  ) { }

  save(patient: Patient): Observable<Patient>{
    return this.http.post<Patient>(this.patientUrl, patient);
  }

  findAll(): Observable<Patient[]>{
    return this.http.get<Patient[]>(this.patientUrl);
  }

  find(id: string): Observable<Patient>{
    return this.http.get<Patient>(`${this.patientUrl}/${id}`);
  }


  update(id: string, patient: Patient): Observable<Patient>{
    return this.http.put<Patient>(`${this.patientUrl}/${id}`, patient);
  }

  delete(id: string): Observable<void>{
    return this.http.delete<void>(`${this.patientUrl}/${id}`);
  }
}
