import { NgModule } from '@angular/core';
import { RouterModule, Routes} from '@angular/router';
import { ArticlesComponent} from './articles/articles.component';
import {AppComponent} from './app.component';
import {CategoriesComponent} from './categories/categories.component';

const routes: Routes = [
  { path: '', redirectTo: '/categories', pathMatch: 'full'},
  { path: 'categories', component: CategoriesComponent},
  { path: 'articles', component: ArticlesComponent}
]

@NgModule({
  exports: [ RouterModule],
  imports: [ RouterModule.forRoot(routes)]
})
export class AppRoutingModule { }
