import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { inject } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';
import { CommonModule } from '@angular/common';
@Component({
  selector: 'app-add-player',
  imports: [FormsModule, CommonModule, RouterModule],
  templateUrl: './add-player.html',
  styleUrl: './add-player.css',
})
export class AddPlayer {

  firstName = '';
  lastName = '';
  country = '';
  goals = 0;
  clubId = '';


  private http = inject(HttpClient);
  private router = inject(Router);
  private route = inject(ActivatedRoute);

  readonly apiUrl = 'http://localhost:8083/players'

   ngOnInit(): void {
    this.clubId = this.route.snapshot.paramMap.get('id')!;

  }

  submit(): void {
    this.http.post(this.apiUrl, {
      firstName: this.firstName,
      lastName: this.lastName,
      country: this.country,
      goals: this.goals,
      clubID: this.clubId
    }).subscribe({
      next: () => this.router.navigate(['/']),
      error: (err) => console.error('Blad dodawania:', err)
    }
      
    );
  }
}
