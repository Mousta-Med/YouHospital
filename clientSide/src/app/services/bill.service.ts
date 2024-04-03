import {Injectable} from '@angular/core';
import {Bill} from "../models/Bill.model";
import {Observable} from "rxjs";
import {environment} from "../../environments/environment";
import {HttpClient} from "@angular/common/http";

@Injectable({
  providedIn: 'root'
})
export class BillService {

  private readonly billUrl = `${environment.api.baseUrl}/${environment.api.billUrl}`;

  constructor(
    private http: HttpClient
  ) {
  }

  save(bill: Bill): Observable<Bill> {
    return this.http.post<Bill>(this.billUrl, bill);
  }

  findAll(): Observable<Bill[]> {
    return this.http.get<Bill[]>(this.billUrl);
  }

  find(id: string): Observable<Bill> {
    return this.http.get<Bill>(`${this.billUrl}/${id}`);
  }


  update(id: string | undefined, bill: Bill): Observable<Bill> {
    return this.http.put<Bill>(`${this.billUrl}/${id}`, bill);
  }

  delete(id: string | undefined): Observable<void> {
    return this.http.delete<void>(`${this.billUrl}/${id}`);
  }
}
