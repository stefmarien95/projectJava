import { Injectable } from '@angular/core';
import { Observable, from } from 'rxjs';
import { Song } from '../models/song.model';
import { HttpClient } from '@angular/common/http';
import { Rating } from '../models/rating.model';


@Injectable({
  providedIn: 'root'
})
export class SongService {

  
  constructor(private http: HttpClient) { }
  getSongs(): Observable<Song[]>
   {
    return this.http.get<Song[]>("https://cors-anywhere.herokuapp.com/http://api.deezer.com/playlist/30595446/tracks");
  }

  getSong(title:string): Observable<Song[]>
   {
    return this.http.get<Song[]>("https://cors-anywhere.herokuapp.com/http://api.deezer.com/search?q=track:"+ title);
  }

  getSongDetail(id:number): Observable<Song[]>
   {
    return this.http.get<Song[]>("https://cors-anywhere.herokuapp.com/http://api.deezer.com/track/"+ id);
  }
  

  addSongRating(rating:Rating)
  {
    return this.http.post<Rating>("http://localhost:8055/listings/useraddrating/", rating);
    
  }

  getSongRating(songID:number)
  {
    return this.http.get<Rating[]>("http://localhost:8055/listings/ratingsong/"+ songID)
    
  }


  

}
