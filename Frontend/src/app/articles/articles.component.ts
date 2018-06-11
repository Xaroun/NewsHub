import { Component, OnInit } from '@angular/core';
import { Article} from '../article';

@Component({
  selector: 'app-articles',
  templateUrl: './articles.component.html',
  styleUrls: ['./articles.component.css']
})
export class ArticlesComponent implements OnInit {

  article: Article = {
    author: 'Jakub Mirowski',
    title: 'Koniec z cenzurą kontrowersyjnych treści na Steamie',
    description: 'Jak wynika z wydanego przez firmę Valve Software oświadczenia, już niedługo zrezygnuje ona z cenzurowania kontrowersyjnych gier na Steamie. Z platformy będą znikać wyłącznie tytuły o treściach nielegalnych oraz będące „oczywistym trollingiem”.',
    date: '2018-06-07T14:37:00Z',
    sourceName: 'Gry-online.pl',
    articleUrl: 'https://www.gry-online.pl/S013.asp?ID=109608',
    imageUrl: 'https://www.gry-online.pl/i/h/17/25340406.jpg'
  };

  constructor() { }

  ngOnInit() {
  }

}
