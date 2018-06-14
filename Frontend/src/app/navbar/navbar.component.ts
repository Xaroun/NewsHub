import {Component, Input, OnInit} from '@angular/core';
import {Router} from '@angular/router';
import {ArticleService} from '../article.service';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {

  searchPhrase: string;

  constructor(private router: Router,
              private articleService: ArticleService) { }

  ngOnInit() {
  }

  search(): void {
    if (this.searchPhrase) {
      this.router.navigate(['/articles'], { queryParams: {search: this.searchPhrase}});
      this.searchPhrase = '';
    }
  }

}
