import { Component, OnInit } from '@angular/core';
import { SongService } from '../../services/song.service';
import { Song } from '../../models/song.model';
import { Router } from "@angular/router";

@Component({
  selector: 'app-mijn-song',
  templateUrl: './mijn-song.component.html',
  styleUrls: ['./mijn-song.component.css']
})
export class MijnSongComponent implements OnInit {

  songs:Song[];
  song:Song[];
  track="";

  constructor(private _songService:SongService,private router: Router) {
   
   }

  ngOnInit() {

    //this.searchSong()
    this.getSongs();
    
  }

  getSongs(){
    this._songService.getMijnSongs(1).subscribe(
      result => {
      this.songs=result
     
      console.log(this.songs);
      }
    );
  }
  

  searchSong()
  {
    this._songService.getSong(this.track).subscribe(
      result => {
      this.songs=result
      
     
      console.log(this.song);
      }
    );
  }
}
