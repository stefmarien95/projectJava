import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { User } from '../models/user/user.module';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  model : User = new User(0,'test','test');
  submitted : boolean = false;

  constructor(private router:Router) { }

  ngOnInit() {
  }
  
  onSubmit() {

    this.submitted = true;

    localStorage.setItem("userID",this.model.id.toString());
    
    console.log('logging in');
    this.router.navigate(['/song']);
    
  }


}
