import { Injectable } from '@angular/core';

import { Observable } from 'rxjs';
import { User } from '../models/user/user.module';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { tap } from 'rxjs/operators';


@Injectable({
  providedIn: 'root'
  })
  export class UserService {

  constructor(private http: HttpClient) { }

  HttpOptions = {
    headers: new HttpHeaders({
      'content-type': 'application/json'
    })
  };
 /* getUsers(): Observable<User[]>
   {
    return this.http.get<User[]>("");
  }
  */

  login(user:User){
    return this.http.post<User>("http://localhost:8762/auth/", user, this.HttpOptions ); bn name,
  }

  setSession(authResult){

  }
  
  
   
   

}
