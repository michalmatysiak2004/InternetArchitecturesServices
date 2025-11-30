import { CommonModule } from '@angular/common';
import { HttpClient } from '@angular/common/http';
import { Component, OnInit , inject} from '@angular/core';
import { Router } from '@angular/router';
import { RouterModule } from '@angular/router';
interface Club {
  id: number;
  name : string;
}

@Component({
  selector: 'app-club-list',
  imports: [CommonModule, RouterModule], 
  templateUrl: './club-list.html',
  styleUrl: './club-list.css',
})
export class ClubList  implements OnInit{
    private http = inject(HttpClient);

    private router = inject(Router);
    clubs : Club[] = [];
     
    readonly apiUrl = 'http://localhost:8083/clubs';
    ngOnInit(): void {
      this.loadClubs();
    }

    loadClubs(): void {
    this.http.get<Club[]>(this.apiUrl).subscribe({
      next: (data) => {
        console.log('DANE Z API', data)
        this.clubs = data
      },
      error: (err) => console.error('Błąd podczas pobierania klubów:', err),

    });

   
    }

     deleteClub(club: Club): void {
      const confirmed = window.confirm('Czy na pewno chcesz usunąc klub "${club.name}" ? ')
      if(!confirmed) return 

      this.http.delete(`${this.apiUrl}/${club.id}`).subscribe({
        next: () => {
          this.clubs = this.clubs.filter(c => c.id !== club.id);

        },
         error: (err) => console.error('Błąd podczas usuwania:', err),

      })
    }

}
