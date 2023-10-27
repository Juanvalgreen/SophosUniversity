import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { LoginService } from 'src/app/services/auth/login.service';
import { LoginRequest } from 'src/app/services/auth/LoginRequest';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.sass']
})
export class LoginComponent {

  loginForm = this.formBuilder.group({
    user_mail: ['', [Validators.email, Validators.required]],
    user_password: ['',Validators.required]
  })

  constructor(private formBuilder:FormBuilder, private router:Router, private authService:LoginService){

  }


  login(){
    if(this.loginForm.valid){
      let response = this.authService.login(this.loginForm.value as LoginRequest).subscribe(data => {
        console.log(data);
        return (data);
      }, error => {
        console.log(error);
      });

      console.log(response);
      // this.router.navigateByUrl('/home');
      // this.loginForm.reset();
    }
  }
}
