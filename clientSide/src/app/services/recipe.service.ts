import { Injectable } from '@angular/core';
import {environment} from "../../environments/environment";
import {HttpClient} from "@angular/common/http";
import {Recipe} from "../models/Recipe.model";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class RecipeService {

  private readonly recipeUrl = `${environment.api.baseUrl}/${environment.api.recipeUrl}`;
  constructor(
    private http: HttpClient
  ) { }

  save(recipe: Recipe): Observable<Recipe>{
    return this.http.post<Recipe>(this.recipeUrl, recipe);
  }

  findAll(): Observable<Recipe[]>{
    return this.http.get<Recipe[]>(this.recipeUrl);
  }

  find(id: string): Observable<Recipe>{
    return this.http.get<Recipe>(`${this.recipeUrl}/${id}`);
  }


    update(id: string | undefined, recipe: Recipe): Observable<Recipe>{
    return this.http.put<Recipe>(`${this.recipeUrl}/${id}`, recipe);
  }

  delete(id: string | undefined): Observable<void>{
    return this.http.delete<void>(`${this.recipeUrl}/${id}`);
  }
}
