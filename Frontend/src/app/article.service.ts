import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { ArticleWrapper } from './article-wrapper';


@Injectable({
  providedIn: 'root'
})
export class ArticleService {

  private articlesUrl = 'http://127.0.0.1:8080/api/news/pl/technology';

  constructor(private http: HttpClient) { }

  getArticleWrapper(): Observable<ArticleWrapper> {
    return this.http.get<ArticleWrapper>(this.articlesUrl);
  }
}
