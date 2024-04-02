import { Injectable } from '@angular/core';
import {environment} from "../../environments/environment";
import {HttpClient} from "@angular/common/http";
import {Examination} from "../models/Examination.model";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class ExaminationService {

  private readonly examinationUrl = `${environment.api.baseUrl}/${environment.api.examinationUrl}`;
  constructor(
    private http: HttpClient
  ) { }

  save(examination: Examination): Observable<Examination>{
    return this.http.post<Examination>(this.examinationUrl, examination);
  }

  findAll(): Observable<Examination[]>{
    return this.http.get<Examination[]>(this.examinationUrl);
  }

  find(id: string): Observable<Examination>{
    return this.http.get<Examination>(`${this.examinationUrl}/${id}`);
  }


  update(id: string | undefined, examination: Examination): Observable<Examination>{
    return this.http.put<Examination>(`${this.examinationUrl}/${id}`, examination);
  }

  delete(id: string | undefined): Observable<void>{
    return this.http.delete<void>(`${this.examinationUrl}/${id}`);
  }
}
