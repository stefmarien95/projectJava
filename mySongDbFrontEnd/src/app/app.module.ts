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
import { MijnSongComponent } from './Song/mijn-songs/mijn-song/mijn-song.component';



const appRoutes: Routes = [
 
  { path: 'song', component: SongComponent },
  { path: 'songDetail/:id', component : DetailComponent},
  { path: 'login', component: LoginComponent },
  { path: 'registreer', component: RegistreerComponent },
  { path: 'toevoegen', component: SongToevoegenComponent },
  { path: 'mijnsong', component: MijnSongComponent },
  ];

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    SongComponent,
    DetailComponent,
    RegistreerComponent,
    SongToevoegenComponent,
    MijnSongComponent,
   
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
