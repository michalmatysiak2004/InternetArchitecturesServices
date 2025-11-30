import { HttpClient } from '@angular/common/http';
import { Component, inject } from '@angular/core';
import { Router } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';
@Component({
  selector: 'app-add-club',
  imports: [ FormsModule, RouterModule],
  templateUrl: './add-club.html',
  styleUrl: './add-club.css',
})
export class AddClub {
  name = '';
  country = '';
  titles : number = 0;

  private http = inject(HttpClient);
  private router = inject(Router);

  readonly apiUrl = 'http://localhost:8083/clubs'

  submit(): void {
    this.http.post(this.apiUrl, {
      name: this.name,
      country: this.country,
      titles: this.titles
    }).subscribe({
      next: () => this.router.navigate(['/']),
      error: (err) => console.error('Blad dodawania:', err)
    }
      
    );
  }
}
