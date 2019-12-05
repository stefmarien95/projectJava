import { Injectable } from '@angular/core';
import { Observable, from } from 'rxjs';
import { Playlist } from '../models/playlist.model';
import { Playlistitem } from '../models/playlistitem.model';
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
  getPlaylistDetail(id: number): Observable<Playlist>{
    return this.http.get<Playlist>("http://localhost:8055/listings/playlistid/"+id);
  }
  addSongPlaylist(item: Playlistitem) {
    console.log(item)
    this.http.put("http://localhost:8055/listings/songaddplaylist/",
                   {
                            "playlistId": "5dc53fd4dec53f6ba7e44e01",
                            "songId": "5",
                         });

  }
  /*
**		- /listings/songaddplaylist/		## BODY:
  **											playlistId: INT
**											songId: INT
  */
}
