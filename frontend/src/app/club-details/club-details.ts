import { Component, inject } from '@angular/core';
import { RouterModule, ActivatedRoute } from '@angular/router';
import { CommonModule } from '@angular/common';
import { HttpClient } from '@angular/common/http';

interface Player{
    id : number;
    firstName : string;
    lastName : string;
}

@Component({
  selector: 'app-club-details',
  imports: [CommonModule, RouterModule],
  templateUrl: './club-details.html',
  styleUrl: './club-details.css',
})

export class ClubDetails {
  id!: string;
  name = '';
  country = '';
  titles = 0;
  
  players : Player[] = [];
  private http = inject(HttpClient);
  private route = inject(ActivatedRoute);


  readonly clubUrl = 'http://localhost:8083/clubs';
  readonly playerUrl = 'http://localhost:8083/players';

  ngOnInit() : void {
    this.fetchClub();
    this.fetchPlayers();
  }

  fetchClub() : void {
   this.id = this.route.snapshot.paramMap.get('id')!;
    this.http.get<any>(`${this.clubUrl}/${this.id}`).subscribe({
      next: (club) => {
        this.name = club.name;
        this.country = club.country;
        this.titles = club.titles;

      }, error: err => console.error('Blad pobieranoia klubu', err),
        });
  }

  fetchPlayers(): void {
    this.http.get<Player[]>(`${this.playerUrl}/by-club/${this.id}`).subscribe({
      next: (data) => {
        console.log('DANE Z API', data)
        this.players = data
      },
      error: (err) => console.error('Błąd podczas pobierania klubów:', err),

    });
  }

  deletePlayer(player: Player): void{
    const confrimed = window.confirm('Czy na pewno chcesz usunąc zawodnika?')
    if(!confrimed) return

    this.http.delete(`${this.playerUrl}/${player.id}`).subscribe({
      next: () => {
        this.players = this.players.filter(p => p.id !==  player.id);
      },
      error: (err) => console.error('Błąd podczas usuwania:', err),
    })
  }
}
