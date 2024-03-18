import { Injectable } from '@angular/core';
import {environment} from "../../environments/environment";
import {HttpClient} from "@angular/common/http";
import {Room} from "../models/Room.model";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class RoomService {

  private readonly roomUrl = `${environment.api.baseUrl}/${environment.api.roomUrl}`;
  constructor(
    private http: HttpClient
  ) { }

  save(room: Room): Observable<Room>{
    return this.http.post<Room>(this.roomUrl, room);
  }

  findAll(): Observable<Room[]>{
    return this.http.get<Room[]>(this.roomUrl);
  }

  find(id: string): Observable<Room>{
    return this.http.get<Room>(`${this.roomUrl}/${id}`);
  }


  update(id: string, room: Room): Observable<Room>{
    return this.http.put<Room>(`${this.roomUrl}/${id}`, room);
  }

  delete(id: string): Observable<void>{
    return this.http.delete<void>(`${this.roomUrl}/${id}`);
  }
}
