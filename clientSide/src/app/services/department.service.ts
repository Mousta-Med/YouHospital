import {Injectable} from '@angular/core';
import {environment} from "../../environments/environment";
import {HttpClient} from "@angular/common/http";
import {Department} from "../models/Department.model";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class DepartmentService {

  private readonly departmentUrl = `${environment.api.baseUrl}/${environment.api.departmentUrl}`;

  constructor(
    private http: HttpClient
  ) {
  }

  save(department: Department): Observable<Department> {
    return this.http.post<Department>(this.departmentUrl, department);
  }

  findAll(): Observable<Department[]> {
    return this.http.get<Department[]>(this.departmentUrl);
  }

  find(id: string): Observable<Department> {
    return this.http.get<Department>(`${this.departmentUrl}/${id}`);
  }


  update(id: string | undefined, department: Department): Observable<Department> {
    return this.http.put<Department>(`${this.departmentUrl}/${id}`, department);
  }

  delete(id: string | undefined): Observable<void> {
    return this.http.delete<void>(`${this.departmentUrl}/${id}`);
  }
}
