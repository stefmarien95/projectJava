import { Component, OnInit } from '@angular/core';
import { SongService } from '../../services/song.service';
import { Song } from '../../models/song.model';
import { Router, ActivatedRoute, Params } from '@angular/router';
import { Subscription } from 'rxjs';

@Component({
  selector: 'app-detail',
  templateUrl: './detail.component.html',
  styleUrls: ['./detail.component.css']
})
export class DetailComponent implements OnInit {
song:Song[];
private routeSub: Subscription;
id=0

  constructor(private _songService:SongService,  private route: ActivatedRoute,
    private router: Router) { }

  ngOnInit() {
    this.routeSub=this.route.params.subscribe(params=>{
     
      this.id=params['id']
      console.log(this.id)
    })
    this.getSong();
  }

getSong(){
  this._songService.getSongDetail(this.id).subscribe(
    result => {
    this.song=result
   
    console.log(this.song);
    }
  );
}

}
