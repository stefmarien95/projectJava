import { Component, OnInit } from '@angular/core';
import { Song } from '../models/song.model';
import { SongService } from '../services/song.service';
import {Router} from "@angular/router";

@Component({
  selector: 'app-song-toevoegen',
  templateUrl: './song-toevoegen.component.html',
  styleUrls: ['./song-toevoegen.component.css']
})
export class SongToevoegenComponent implements OnInit {
  song:Song[];
  songModel:Song=new Song(0,"","","","","","",1)
  submitted : boolean = false;

  constructor(private _songService: SongService, private router: Router) { }

  ngOnInit() {
  }

  onSubmit() {

    this.submitted = true;

    console.log(this.songModel)
    this._songService.addSong(this.songModel).subscribe( result => {
      this.router.navigate(['/mijnsongs'])
    });
  }

}
