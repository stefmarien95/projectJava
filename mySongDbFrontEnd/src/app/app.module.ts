import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { AppComponent } from './app.component';
import { LoginComponent } from './login/login/login.component';
import { HttpClientModule } from '@angular/common/http';
import { SongComponent } from './Song/song.component';
import { RouterModule, Routes } from '@angular/router';
import { DetailComponent } from './Song/Song-Detail/detail/detail.component';
import { MDBBootstrapModule } from 'angular-bootstrap-md';
import { RegistreerComponent } from './Registreer/registreer/registreer.component';
import { SongToevoegenComponent } from './Song/song-toevoegen/song-toevoegen.component';
import { PlaylistComponent } from './playlist/playlist.component';
import { PlaylistDetailComponent } from './playlist/playlist-detail/playlist-detail.component';
import { PlaylistToevoegenComponent } from './playlist/playlist-toevoegen/playlist-toevoegen.component';
import { MijnSongsComponent } from './MijnSongs/mijn-songs/mijn-songs.component';
import { MijnSongsDetailComponent } from './MijnSongs/mijn-songs-detail/mijn-songs-detail.component';


const appRoutes: Routes = [

  { path: 'song', component: SongComponent },
  { path: 'songDetail/:id', component : DetailComponent},
  { path: 'login', component: LoginComponent },
  { path: 'registreer', component: RegistreerComponent },
  { path: 'toevoegen', component: SongToevoegenComponent },
  { path: 'afspeelLijst', component: PlaylistComponent },
  { path: 'afspeelLijstToevoegen', component: PlaylistToevoegenComponent },
  { path: 'afspeelLijstDetail/:id', component: PlaylistDetailComponent },
  { path: 'mijnsongs', component: MijnSongsComponent },
  { path: 'mijnsongs/:id', component: MijnSongsDetailComponent },
  ];

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    SongComponent,
    DetailComponent,
    RegistreerComponent,
    SongToevoegenComponent,
    PlaylistComponent,
    PlaylistDetailComponent,
    PlaylistToevoegenComponent,
    
    MijnSongsComponent,
    
    MijnSongsDetailComponent,
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    FormsModule,
    RouterModule.forRoot(appRoutes, { enableTracing: true }),
    MDBBootstrapModule.forRoot()
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
