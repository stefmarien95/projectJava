import { Injectable } from '@angular/core';
import { Observable, from } from 'rxjs';
import { Playlist } from '../models/playlist.model';
import { Playlistitem } from '../models/playlistitem.model';
import { HttpClient } from '@angular/common/http';
import {SongService} from "../../Song/services/song.service";

@Injectable({
  providedIn: 'root'
})
export class PlaylistService {

  constructor(private _songservice: SongService, private http: HttpClient) {this.userid = 1; }

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
  addSongPlaylist(playlistId: string, songId:number) {
    return this.http.put("http://localhost:8055/listings/songaddplaylist/",
                   {
                            "playlistId": playlistId,
                            "songId": songId
                         });

  }
  /*
**		- /listings/songaddplaylist/		## BODY:
  **											playlistId: INT
**											songId: INT
  */
}
