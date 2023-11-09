import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { Store } from '@ngrx/store';
import * as AuthActions from '../../state/auth/auth.actions'
import { LoginService } from 'src/app/services/auth/login.service';
import { LoginRequest } from 'src/app/services/auth/LoginRequest';

import { SessionDataService } from 'src/app/services/session-data/session-data.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.sass']
})
export class LoginComponent {

  isRegister: boolean = true;
  errorResponse: boolean = false;

  loginForm = this.formBuilder.group({
    user_mail: ['', [Validators.email, Validators.required]],
    user_password: ['',Validators.required]
  });

  constructor(private formBuilder:FormBuilder, private router:Router, private authService:LoginService,private sessionData:SessionDataService, private store:Store){

  }


  login(){
    //console.log(this.loginForm.value);
    if(this.loginForm.valid){

      this.authService.login(this.loginForm.value as LoginRequest).subscribe(data => {
        console.log(data);


        if(data == null || data.token == null){

          this.isRegister = false;


        }else{
          let currentUser = data;
          this.isRegister = true;
          this.store.dispatch(AuthActions.loginSuccess({currentUser}));
          this.sessionData.storeData('currentUser', currentUser);
          this.loginForm.reset();
          this.router.navigateByUrl('/home');
        }
        return (data);
      }, error => {
        this.errorResponse = true;
        console.log(error);
      });



      //this.router.navigateByUrl('/home');
      // this.loginForm.reset();
    }
  }
}
