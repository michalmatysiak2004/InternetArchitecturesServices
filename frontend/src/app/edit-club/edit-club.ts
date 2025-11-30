import { Component, inject } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { ActivatedRoute, RouterModule } from '@angular/router';
import { CommonModule } from '@angular/common';
import { HttpBackend, HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';
@Component({
  selector: 'app-edit-club',
  imports: [CommonModule, FormsModule, RouterModule],
  templateUrl: './edit-club.html',
  styleUrl: './edit-club.css',
})
export class EditClub {

  id!: string;
  name = '';
  country = '';
  titles = 0;

  private http = inject(HttpClient);
  private route = inject(ActivatedRoute);
  private router = inject(Router)


  readonly apiUrl = 'http://localhost:8083/clubs';

  ngOnInit(): void {
    this.id = this.route.snapshot.paramMap.get('id')!;
    this.http.get<any>(`${this.apiUrl}/${this.id}`).subscribe({
      next: (club) => {
        this.name = club.name;
        this.country = club.country;
        this.titles = club.titles;

      }, error: err => console.error('Blad pobieranoia klubu', err),
        });
  }
  submit() : void {
    const payload = {
      name: this.name,
      country: this.country,
      titles: this.titles,
    };

    this.http.put(`${this.apiUrl}/${this.id}`, payload).subscribe({
      next: () => this.router.navigate(['/']),
      error: (err) => console.error('Błąd edycji:', err),
    });

  }

}
