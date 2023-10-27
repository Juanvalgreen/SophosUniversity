import { Component,OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { teachers } from 'src/app/models/teachers.model';
import { DetailsDataService } from 'src/app/services/details-data/details-data.service';
import { ListingService } from 'src/app/services/listing/listing.service';
import { TeachersService } from 'src/app/services/teachers/teachers.service';

@Component({
  selector: 'app-teachers-list',
  templateUrl: './teachers-list.component.html',
  styleUrls: ['./teachers-list.component.sass']
})
export class TeachersListComponent implements OnInit {

  isTeachersPage : boolean;
  teachers : teachers[];

  constructor(private teacherService : TeachersService, private listingService:ListingService, private router:Router, private detailService: DetailsDataService){
    this.teachers = [];
    this.isTeachersPage = false;
  }




  ngOnInit() {

    this.isTeachersPage = this.router.url.includes('list');

    this.teacherService.getAllTeachers().subscribe(
      data => {
        this.teachers = data
      },error => {
        console.log(error);
      });

  }

  redirectToList(){
    this.listingService.componentIndicate = 'Profesores';
    this.router.navigateByUrl('/list');

  }

  asignDetailData(teacher: teachers){



    this.detailService.detailsData= new Object({
      currentData: teacher,
      typeData: 'Profesor'
    });
    this.router.navigateByUrl('/details');
  }




}
