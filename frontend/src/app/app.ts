import { Component, signal } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { Header } from './header/header';
import { ClubList } from './club-list/club-list';
@Component({
  selector: 'app-root',
  imports: [RouterOutlet, Header, ClubList],
  templateUrl: './app.html',
  styleUrl: './app.css'
})
export class App {
  protected readonly title = signal('my-angular-app');
}
