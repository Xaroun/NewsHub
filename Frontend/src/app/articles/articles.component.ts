import { Component, OnInit } from '@angular/core';
import { ArticleService } from '../article.service';
import { ArticleWrapper } from '../article-wrapper';
import { ActivatedRoute } from '@angular/router';
import { Location } from '@angular/common';

@Component({
  selector: 'app-articles',
  templateUrl: './articles.component.html',
  styleUrls: ['./articles.component.css']
})
export class ArticlesComponent implements OnInit {

  articleWrapper: ArticleWrapper;
  searchPhrase: string;

  constructor(private articleService: ArticleService,
              private route: ActivatedRoute,
              private location: Location) { }

  ngOnInit() {
    this.route.queryParams
      .subscribe(params => {
        this.searchPhrase = params.search;
        this.getArticleWrapper();
      });
  }

  getArticleWrapper(): void {
    const category = this.route.snapshot.paramMap.get('category');

    if (category) {
      this.articleService.getArticleWrapper(category).subscribe(articleWrapper => this.articleWrapper = articleWrapper);
    } else if (this.searchPhrase) {
      this.articleService.search(this.searchPhrase).subscribe(articleWrapper => this.articleWrapper = articleWrapper);
    }
  }
}
