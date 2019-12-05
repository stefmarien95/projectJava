import {Component, Input, OnInit} from '@angular/core';
import { Song } from '../../Song/models/song.model';
import { Playlist } from '../models/playlist.model';
import { Router, ActivatedRoute, Params } from '@angular/router';
import {Observable, Subscription} from 'rxjs';
import {PlaylistService} from '../services/playlist.service';
import {SongService} from '../../Song/services/song.service';
import {Playlistitem} from '../models/playlistitem.model';

@Component({
  selector: 'app-playlist-detail',
  templateUrl: './playlist-detail.component.html',
  styleUrls: ['./playlist-detail.component.css']
})
export class PlaylistDetailComponent implements OnInit {
  playlist: Playlist;

  private routeSub: Subscription;
  @Input() id: number;

  constructor(private _playlistService:PlaylistService, private _songService:SongService, private route: ActivatedRoute, private router:Router) { }

  ngOnInit() {
    this.routeSub = this.route.params.subscribe(params => {
      this.id= params['id'];
    })
    this.getPlaylist();
  }

  deletesong(id: number) {
    this._playlistService.deleteSongFromPlaylist(this.playlist.id,id).subscribe( result => {
      this.getPlaylist()
    })
  }

  getPlaylist() {

    this._playlistService.getPlaylistDetail(this.id).subscribe( result => {
      this.playlist = result;
      result.songs.forEach((song) => {
        if(song.title == null) {
          this._songService.getSongDetail(song.id).subscribe( (data: any ) => {
            console.log("RESULT:")
            console.log(result)
            song.title = data.title;
            song.artist = data.artist.name;
            song.cover = data.cover;
            song.duration = data.duration;
            song.album = data.album.title;
          })
        }
      })
      console.log("PLAYLIST:")
      console.log(this.playlist)
    })
  }
}
