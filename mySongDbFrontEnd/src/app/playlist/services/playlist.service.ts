import { Injectable } from '@angular/core';
import { Observable, from } from 'rxjs';
import { Playlist } from '../models/playlist.model';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class PlaylistService {

  constructor(private http: HttpClient) { }

  getPlaylists(): Observable<Playlist[]> {
    const userid=1;
    return this.http.get<Playlist[]>("http://localhost:8055/listings/playlistsuser/"+userid);
  }
  getPlaylistDetai(id: number): Observable<Playlist>{
    return this.http.get<Playlist>("http://localhost:8055/listings/playlistid/"+id);
  }
}
