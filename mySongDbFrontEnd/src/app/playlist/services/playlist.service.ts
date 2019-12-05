import { Injectable } from '@angular/core';
import { Observable, from } from 'rxjs';
import { Playlist } from '../models/playlist.model';
import { Playlistitem } from '../models/playlistitem.model';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class PlaylistService {

  constructor(private http: HttpClient) {this.userid = 1; }

  userid: number;
  getPlaylists(): Observable<Playlist[]> {
    return this.http.get<Playlist[]>("http://localhost:8055/listings/playlistsuser/"+this.userid);
  }
  getPlaylistDetail(id: number): Observable<Playlist>{
    return this.http.get<Playlist>("http://localhost:8055/listings/playlistid/"+id);
  }
  addPlaylist(naam: string) {
    return this.http.post("http://localhost:8055/listings/useraddplaylist/", {
      "name": naam
    });
  }
  deleteSongFromPlaylist(playlistId: String, songId: number) {
    return this.http.delete("http://localhost:8055/listings/songdeleteplaylist/" + playlistId + "/" + songId);
  }
  deletePlaylist(playlistId: String) {
    return this.http.delete("http://localhost:8055/listings/deleteplaylist/" + playlistId);
  }
  addSongPlaylist(item: Playlistitem) {
    console.log(item)
    this.http.put("http://localhost:8055/listings/songaddplaylist/",
                   {
                            "playlistId": "5dc53fd4dec53f6ba7e44e01",
                            "songId": "5"
                         });

  }
  /*
**		- /listings/songaddplaylist/		## BODY:
  **											playlistId: INT
**											songId: INT
  */
}
