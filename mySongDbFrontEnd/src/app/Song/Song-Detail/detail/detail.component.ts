import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import { SongService } from '../../services/song.service';
import { Song } from '../../models/song.model';
import { Playlist } from '../../../playlist/models/playlist.model';
import { PlaylistService } from '../../../playlist/services/playlist.service';
import { Router, ActivatedRoute, Params } from '@angular/router';
import {Observable, Subscription} from 'rxjs';
import { Rating } from '../../models/rating.model';

@Component({
  selector: 'app-detail',
  templateUrl: './detail.component.html',
  styleUrls: ['./detail.component.css']
})
export class DetailComponent implements OnInit {
song:Song[];
submitted : boolean = false;


private routeSub: Subscription;
id=0
title=""
artist=""
cover=""
duration=""
album=""
ratingGem=0


@Input() rating: number;
@Input() itemId: number;


userID=localStorage.getItem("userID");
model: Rating=new Rating("",0,0,this.userID) ;
songModel:Song=new Song(0,"","","","","","",0)
  playlists: Playlist[];
playlistId: string;

  constructor(private _songService:SongService, private _playlistService:PlaylistService, private route: ActivatedRoute,
    private router: Router) { }

  ngOnInit() {
    this.routeSub=this.route.params.subscribe(params=>{

      this.id=params['id']

     });
    this.getSong();
    this.getPlaylists()
   // this.getRating();
  }

  getPlaylists() {
    this._playlistService.getPlaylists().subscribe( result => {
      this.playlists = result;
      this.playlistId=result[0].id;
    });
  }

  kiesSpeellijst() {
    console.log(this.playlistId);
    console.log(this.id);
    this._playlistService.addSongPlaylist(this.playlistId, this.id).subscribe();
  }
  onClick(rating: number, title:string): void {

    this.submitted = true;
    this.rating = rating;
    console.log(rating)
    console.log(this.model)

    this.model.rating=this.rating
    this.model.songId=(this.id).toString()
    this.model.userId=1
    this._songService.addSongRating(this.model).subscribe();


    this.songModel.userId=this.id
    this.songModel.title=this.title
    this.songModel.artist=this.artist
    this.songModel.cover=this.cover
    this.songModel.duration=this.duration
    this.songModel.album=this.album
    console.log(this.songModel)
    this._songService.addSong(this.songModel).subscribe();

  }

getSong(){
  this._songService.getSongDetail(this.id).subscribe
  ((data: any) => {
    this.song=data;
    this.title=data.title
    this.artist=data.artist.name
    this.cover=data.album.cover
    this.duration=data.duration
    this.album=data.album.title


 });
}






}
