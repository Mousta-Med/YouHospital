import { Injectable } from '@angular/core';
import {environment} from "../../environments/environment";
import {HttpClient} from "@angular/common/http";
import {Operation} from "../models/Operation.model";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class OperationService {

  private readonly operationUrl = `${environment.api.baseUrl}/${environment.api.operationUrl}`;
  constructor(
    private http: HttpClient
  ) { }

  save(operation: Operation): Observable<Operation>{
    return this.http.post<Operation>(this.operationUrl, operation);
  }

  findAll(): Observable<Operation[]>{
    return this.http.get<Operation[]>(this.operationUrl);
  }

  find(id: string): Observable<Operation>{
    return this.http.get<Operation>(`${this.operationUrl}/${id}`);
  }


  update(id: string, operation: Operation): Observable<Operation>{
    return this.http.put<Operation>(`${this.operationUrl}/${id}`, operation);
  }

  delete(id: string): Observable<void>{
    return this.http.delete<void>(`${this.operationUrl}/${id}`);
  }
}
