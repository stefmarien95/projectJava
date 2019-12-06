import { Component, OnInit } from '@angular/core';
import { SongService } from 'src/app/Song/services/song.service';
import { ActivatedRoute, Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { Song } from 'src/app/Song/models/song.model';

@Component({
  selector: 'app-mijn-songs-detail',
  templateUrl: './mijn-songs-detail.component.html',
  styleUrls: ['./mijn-songs-detail.component.css']
})
export class MijnSongsDetailComponent implements OnInit {
  song:Song[];
  private routeSub: Subscription;
  id=0


  constructor(private _songService:SongService,  private route: ActivatedRoute,
    private router: Router) { }

  ngOnInit() {
    this.routeSub=this.route.params.subscribe(params=>{

      this.id=params['id']
      console.log(this.id)


     });
    this.getSong();
  }

  getSong(){
    this._songService.getmijnSong(this.id).subscribe(result=>{
      this.song=result
    })

  }


}
