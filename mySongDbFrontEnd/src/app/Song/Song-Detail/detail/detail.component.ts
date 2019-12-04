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
//rating:Rating[];
private routeSub: Subscription;
id=0
@Input() rating: number;
@Input() itemId: number;
@Output() ratingClick: EventEmitter<any> = new EventEmitter<any>();
model: Rating=new Rating("",0,0,"") ;



  constructor(private _songService:SongService,  private route: ActivatedRoute,
    private router: Router) { }

  ngOnInit() {  
    this.routeSub=this.route.params.subscribe(params=>{
      
      this.id=params['id']
      console.log(this.id)
    })
    this.getSong();
  
  }


  onClick(rating: number): void {
    this.rating = rating;
    this.submitted = true;
    console.log(this.model);
    this._pollService.addPoll(this.model).subscribe(result =>
    localStorage.setItem("pollID",result.pollID.toString())
  
    );
  /*  this.ratingClick.emit({
      itemId: this.itemId,
      rating: rating
    });
    */
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
