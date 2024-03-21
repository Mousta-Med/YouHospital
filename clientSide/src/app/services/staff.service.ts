import { Injectable } from '@angular/core';
import {environment} from "../../environments/environment";
import {HttpClient} from "@angular/common/http";
import {Staff} from "../models/Staff.model";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class StaffService {

  private readonly staffUrl = `${environment.api.baseUrl}/${environment.api.staffUrl}`;
  constructor(
    private http: HttpClient
  ) { }

  save(staff: Staff): Observable<Staff>{
    return this.http.post<Staff>(this.staffUrl, staff);
  }

  findAll(): Observable<Staff[]>{
    return this.http.get<Staff[]>(this.staffUrl);
  }

  find(id: string): Observable<Staff>{
    return this.http.get<Staff>(`${this.staffUrl}/${id}`);
  }


    update(id: string | undefined, staff: Staff): Observable<Staff>{
    return this.http.put<Staff>(`${this.staffUrl}/${id}`, staff);
  }

    delete(id: string | undefined): Observable<void>{
    return this.http.delete<void>(`${this.staffUrl}/${id}`);
  }
}
