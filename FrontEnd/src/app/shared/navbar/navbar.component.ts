import { Component } from '@angular/core';
import { Router, NavigationEnd } from '@angular/router';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.sass']
})
export class NavbarComponent {



  isHomePage: Boolean;
  isListPage: Boolean;


  constructor(private router: Router) {
    this.isHomePage = false;
    this.isListPage = false;
  }


  ngOnInit() {
    this.router.events.subscribe(event => {
      if (event instanceof NavigationEnd) {
        this.isHomePage = this.router.url.includes('home');
        this.isListPage = this.router.url.includes('list');
      }
    });
  }

}
