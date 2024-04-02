import { Injectable } from '@angular/core';
import {BehaviorSubject, Observable} from "rxjs";
import {MenuItem} from "primeng/api";
import {Hospital} from "../models/Hospital.model";

@Injectable({
  providedIn: 'root'
})
export class SharedService {


  private menuSubject: BehaviorSubject<any> = new BehaviorSubject<any>(null);
  private hospitalSubject: BehaviorSubject<any> = new BehaviorSubject<any>(null);

  setMenu(menu: Array<MenuItem>): void {
    this.menuSubject.next(menu);
  }

  getMenu(): Observable<Array<MenuItem>> {
    return this.menuSubject.asObservable();
  }
  setHospital(hospital: Hospital) {
    this.hospitalSubject.next(hospital);
  }

  getHospital(): Observable<Hospital> {
    return this.hospitalSubject.asObservable();
  }


}
