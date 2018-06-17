import { Injectable } from '@angular/core';
import {Observable} from 'rxjs';
import {ArticleWrapper} from './article-wrapper';
import {HttpClient} from '@angular/common/http';
import {Category} from './category';

@Injectable({
  providedIn: 'root'
})
export class CategoryService {

  private baseUrl = 'http://127.0.0.1:8080/api/category';

  constructor(private http: HttpClient) { }

  getCategories(): Observable<Category[]> {
    return this.http.get<Category[]>(this.baseUrl);
  }

}
