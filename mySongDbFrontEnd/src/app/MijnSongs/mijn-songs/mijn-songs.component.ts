import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { SongService } from 'src/app/Song/services/song.service';
import { Song } from 'src/app/Song/models/song.model';

@Component({
  selector: 'app-mijn-songs',
  templateUrl: './mijn-songs.component.html',
  styleUrls: ['./mijn-songs.component.css']
})
export class MijnSongsComponent implements OnInit {
  song:Song[];

  constructor(private _songService:SongService,private router: Router) { }

  ngOnInit() {
    this.getSongs();
  }


  getSongs(){
    this._songService.getmijnSongs().subscribe(
      result => {
      this.song=result


      console.log(this.song);
      }
    );
  }
  deleteSong(id: string) {
    this._songService.deleteSong(id).subscribe( result => {
      this.getSongs();
    })
  }
}
