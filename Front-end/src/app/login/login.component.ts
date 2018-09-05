import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators, FormControl } from '@angular/forms';
import {Router} from '@angular/router';
import { LoginService } from '../services/login.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  // userLogin:string;
  // userPass:string;
  userLoginForm:FormGroup
  loggedIn:boolean = false
  user:{nome:string, role:string} = {nome:'', role:''};

  constructor(
    private loginService:LoginService, 
    private formBuilder:FormBuilder,
    private router:Router
  ) {}

  private login():void {
    const userLogin = this.userLogin?  this.userLogin.toString() : ""
    const userPassword = this.userPassword

    this.loginService.login(userLogin, userPassword).subscribe(data => {
      console.log(data)
      data? this.router.navigate(["/admin"]) : this.router.navigate(["/"])
    })
    // if(user) { 
    //   this.loggedIn = true
    //   // this.user = JSON.stringify(user);
    //   this.user = user;

    // }

    // user? alert("User Logged !") : ""//alert("Failed to Login !")
    
  }


  onSubmit() {
    this.login()
    this.userLoginForm.reset()
  }

  ngOnInit() {
    this.userLoginForm = this.formBuilder.group({
      login: ['', Validators.required],
      password:['', Validators.required]
    })

    // this.loginService.getHero(12).forEach(element => {
    //   console.log(element)
    // });
  }

  
get userLogin():number { return this.userLoginForm.get('login').value; }
get userPassword():string { return this.userLoginForm.get('password').value; }


}
