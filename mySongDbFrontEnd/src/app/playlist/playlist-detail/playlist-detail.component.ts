import {Component, Input, OnInit} from '@angular/core';
import { Song } from '../../Song/models/song.model';
import { Playlist } from '../models/playlist.model';
import { Router, ActivatedRoute, Params } from '@angular/router';
import {Observable, Subscription} from 'rxjs';
import {PlaylistService} from '../services/playlist.service';
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

  constructor(private _playlistService:PlaylistService, private route: ActivatedRoute, private router:Router) { }

  ngOnInit() {
    this.routeSub = this.route.params.subscribe(params => {
      this.id= params['id'];
    })
    this.getPlaylist();
  }

  deletesong(id: number) {
    console.log(id);
  }

  getPlaylist() {

    this._playlistService.getPlaylistDetail(this.id).subscribe( result => {
      this.playlist = result;
      console.log(this.playlist);
    })
  }
}
