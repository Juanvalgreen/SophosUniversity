import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ReactiveFormsModule } from '@angular/forms';


import { LoginComponent } from '../pages/login/login.component';
import { HomeComponent } from '../pages/home/home.component';
import { ListncreateComponent } from '../pages/listncreate/listncreate.component';
import { ComponentsModule } from '../components/components.module';
import { DetailComponent } from './detail/detail.component';




@NgModule({
  declarations: [
    LoginComponent,
    HomeComponent,
    ListncreateComponent,
    DetailComponent
  ],
  imports: [
    CommonModule,
    ReactiveFormsModule,
    ComponentsModule
  ],
  exports: [
    LoginComponent,
    HomeComponent,
    ListncreateComponent
  ]
})
export class PagesModule { }
