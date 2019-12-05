import { Component, OnInit } from '@angular/core';
import { SongService } from './services/song.service';
import { Song } from './models/song.model';
import { Router } from "@angular/router";


@Component({
  selector: 'app-song',
  templateUrl: './song.component.html',
  styleUrls: ['./song.component.css']
})
export class SongComponent implements OnInit {
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
    this._songService.getSongs().subscribe(
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
