import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import { SongService } from '../../services/song.service';
import { Song } from '../../models/song.model';
import { Router, ActivatedRoute, Params } from '@angular/router';
import { Subscription } from 'rxjs';
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
@Input() rating: number;
@Input() itemId: number;
@Input() title: string;

userID=localStorage.getItem("userID");
model: Rating=new Rating("",0,0,this.userID) ;
songModel:Song=new Song(0,"","","","","","",0)



  constructor(private _songService:SongService,  private route: ActivatedRoute,
    private router: Router) { }

  ngOnInit() {  
    this.routeSub=this.route.params.subscribe(params=>{
      
      this.id=params['id']
      console.log(this.id)
    })
    this.getSong();
   // this.getRating();

  
  }


  onClick(rating: number): void {
    
    this.submitted = true;
    this.rating = rating;
    console.log(rating)
    console.log(this.model)
    
    this.model.rating=this.rating
    this.model.songId=(this.id).toString()
    this.model.userId=1
    this._songService.addSongRating(this.model).subscribe();

    this.songModel.title=this.title;
    console.log(this.songModel)
    this._songService.addSong(this.songModel).subscribe();
 
  }

getSong(){
  this._songService.getSongDetail(this.id).subscribe(
    result => {
    this.song=result
    
   
    console.log(this.song);
    }
  );
}

//todo
/*
getRating(){
  
  this._songService.getSongRating(this.id).subscribe(
    result => {
    console.log("rating:"+result)
   }
  );
}
*/



}
