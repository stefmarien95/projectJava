import { Component, OnInit } from '@angular/core';
import { PlaylistService} from "../services/playlist.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-playlist-toevoegen',
  templateUrl: './playlist-toevoegen.component.html',
  styleUrls: ['./playlist-toevoegen.component.css']
})
export class PlaylistToevoegenComponent implements OnInit {

  naam: string;
  submitted: boolean = false;

  constructor(private _playlistService: PlaylistService, private router: Router) { }

  ngOnInit() {
  }
  onSubmit() {
    this.submitted = false
    this._playlistService.addPlaylist(this.naam).subscribe( result =>{

      this.router.navigate(['/afspeelLijst']);
    });
  }

}
