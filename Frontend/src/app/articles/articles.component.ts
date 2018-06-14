import { Component, OnInit } from '@angular/core';
import { ArticleService } from '../article.service';
import { ArticleWrapper } from '../article-wrapper';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-articles',
  templateUrl: './articles.component.html',
  styleUrls: ['./articles.component.css']
})
export class ArticlesComponent implements OnInit {

  articleWrapper: ArticleWrapper;

  constructor(private articleService: ArticleService,
              private route: ActivatedRoute) { }

  ngOnInit() {
    this.getArticleWrapper();
  }

  getArticleWrapper(): void {
    const category = this.route.snapshot.paramMap.get('category');
    this.articleService.getArticleWrapper(category).subscribe(articleWrapper => this.articleWrapper = articleWrapper);
  }
}
