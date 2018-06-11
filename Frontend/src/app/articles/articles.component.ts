import { Component, OnInit } from '@angular/core';
import { ArticleService } from '../article.service';
import { ArticleWrapper } from '../article-wrapper';

@Component({
  selector: 'app-articles',
  templateUrl: './articles.component.html',
  styleUrls: ['./articles.component.css']
})
export class ArticlesComponent implements OnInit {

  articleWrapper: ArticleWrapper;

  constructor(private articleService: ArticleService) { }

  ngOnInit() {
    this.getArticleWrapper();
  }

  getArticleWrapper(): void {
    this.articleService.getArticleWrapper().subscribe(articleWrapper => this.articleWrapper = articleWrapper);
  }
}
