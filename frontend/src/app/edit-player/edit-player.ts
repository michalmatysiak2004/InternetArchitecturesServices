import { Component, inject } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { ActivatedRoute, RouterModule } from '@angular/router';
import { CommonModule } from '@angular/common';
import { HttpBackend, HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';

@Component({
  selector: 'app-edit-player',
  imports: [CommonModule, FormsModule, RouterModule],
  templateUrl: './edit-player.html',
  styleUrl: './edit-player.css',
})
export class EditPlayer {

  id!:string;
  firstName = '';
  lastName = '';
  country = '';
  goals = 0;
  clubId!:string;


  private http = inject(HttpClient);
  private route = inject(ActivatedRoute);
  private router = inject(Router);

  readonly playerUrl = 'http://localhost:8083/players';


  ngOnInit() : void {
    this.route.paramMap.subscribe(params => {
      this.id = params.get('id') ?? '';
      this.clubId = params.get('clubId') ?? '';
    });
    this.http.get<any>(`${this.playerUrl}/${this.id}`).subscribe({
      next: (player) => {
        this.firstName = player.firstName;
        this.lastName = player.lastName;
        this.country = player.country;
        this.goals = player.goals;
      }, error: err => console.error('Blad pobieranoia klubu', err),
    })
    

  }


  submit() : void {
     const payload = {
      firstName: this.firstName,
      lastName: this.lastName,
      country: this.country,
      goals: this.goals,
      clubID: this.clubId,
    };

    this.http.put(`${this.playerUrl}/${this.id}`, payload).subscribe({
      next: () => this.router.navigate(['/']),
      error: (err) => console.error('Błąd edycji:', err),
    });
  }

}
