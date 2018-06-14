import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { ArticleWrapper } from './article-wrapper';


@Injectable({
  providedIn: 'root'
})
export class ArticleService {

  private baseUrl = 'http://127.0.0.1:8080/api/news';

  constructor(private http: HttpClient) { }

  getArticleWrapper(category: string): Observable<ArticleWrapper> {
    const finalUrl = this.baseUrl + '/pl/' + category;
    return this.http.get<ArticleWrapper>(finalUrl);
  }

  search(phrase: string): Observable<ArticleWrapper> {
    const finalUrl = this.baseUrl + '?search=' + phrase.replace(' ', '+');
    return this.http.get<ArticleWrapper>(finalUrl);
  }
}
