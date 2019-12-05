import { Component, OnInit } from '@angular/core';
import {Song } from '../Song/models/song.model';
import {PlaylistService} from './services/playlist.service';
import {SongService} from '../Song/services/song.service';
import { Playlist } from './models/playlist.model';
import { Router } from '@angular/router';

@Component({
  selector: 'app-playlist',
  templateUrl: './playlist.component.html',
  styleUrls: ['./playlist.component.css']
})
export class PlaylistComponent implements OnInit {
  playlists: Playlist[];

  constructor(private _playlistService:PlaylistService, private _songService:SongService, private router: Router) { }

  ngOnInit() {
    this.getPlaylists();
  }

  getPlaylists(){
    this._playlistService.getPlaylists().subscribe(
      result => {
        this.playlists = result;
    })
    console.log(this.playlists);
  }
  deletePlaylist( id:string) {
    this._playlistService.deletePlaylist(id).subscribe( result => {

      this.getPlaylists();
    })
  }
}
