import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { User } from '../models/user/user.module';
import { Router } from '@angular/router';
import { UserService } from '../services/user.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  submitted : boolean = false;
  userModel:User=new User("","");

  constructor(private router:Router, private _userService: UserService) { }

  ngOnInit() {
  }
  
  onSubmit() {

    this.submitted = true;

    // localStorage.setItem("userID",this.model.id.toString());

    
    
    console.log(this.userModel);

    this._userService.login(this.userModel).subscribe();
    this.router.navigate(['/song']);

    
  }


}
