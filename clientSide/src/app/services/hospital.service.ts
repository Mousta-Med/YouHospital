import {Injectable} from '@angular/core';
import {environment} from "../../environments/environment";
import {HttpClient} from "@angular/common/http";
import {Hospital} from "../models/Hospital.model";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class HospitalService {

  private readonly hospitalUrl = `${environment.api.baseUrl}/${environment.api.hospitalUrl}`;

  constructor(
    private http: HttpClient
  ) {
  }

  save(hospital: Hospital): Observable<Hospital> {
    return this.http.post<Hospital>(this.hospitalUrl, hospital);
  }

  findAll(): Observable<Hospital[]> {
    return this.http.get<Hospital[]>(this.hospitalUrl);
  }

  find(id: string): Observable<Hospital> {
    return this.http.get<Hospital>(`${this.hospitalUrl}/${id}`);
  }


  update(id: string, hospital: Hospital): Observable<Hospital> {
    return this.http.put<Hospital>(`${this.hospitalUrl}/${id}`, hospital);
  }

  delete(id: string): Observable<void> {
    return this.http.delete<void>(`${this.hospitalUrl}/${id}`);
  }
}
