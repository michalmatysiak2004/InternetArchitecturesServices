import { Routes } from '@angular/router';
import { ClubList } from './club-list/club-list';
import { AddClub } from './add-club/add-club';
import { EditClub } from './edit-club/edit-club';
import { ClubDetails } from './club-details/club-details';
import { AddPlayer } from './add-player/add-player';
import { EditPlayer } from './edit-player/edit-player';
export const routes: Routes = [
    { path: '', component: ClubList},
    {path:  'add', component: AddClub},
    { path: 'edit/:id', component: EditClub },
    {path: 'details/:id', component: ClubDetails},
    {path:'details/:id/addPlayer', component: AddPlayer},
    {path: 'details/:clubId/addPlayer/:id',component: EditPlayer},
];
