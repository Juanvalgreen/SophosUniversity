import { Component } from '@angular/core';
import { Router,NavigationEnd } from '@angular/router';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.sass']
})
export class AppComponent {
  title = 'Sophos University';


  isLoginPage: Boolean;


  constructor(private router: Router) {
    this.isLoginPage = false;
  }


  ngOnInit() {
    this.router.events.subscribe(event => {
      if (event instanceof NavigationEnd) {
        this.isLoginPage = this.router.url.includes('login');
      }
    });
  }




}
