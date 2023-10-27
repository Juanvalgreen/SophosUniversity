import { Component, OnInit } from '@angular/core';
import { Router, NavigationEnd, NavigationStart } from '@angular/router';
import { students } from 'src/app/models/students.model';
import { DetailsDataService } from 'src/app/services/details-data/details-data.service';
import { ListingService } from 'src/app/services/listing/listing.service';
import { StudentsService } from 'src/app/services/students/students.service';

@Component({
  selector: 'app-students-list',
  templateUrl: './students-list.component.html',
  styleUrls: ['./students-list.component.sass']
})
export class StudentsListComponent implements OnInit {

  isStudentsPage: Boolean;

  allStudents: students[];

  constructor(private studentsService: StudentsService, private listingService:ListingService, private router:Router, private detailService:DetailsDataService) {
    this.allStudents = [   ];
    this.isStudentsPage = false;
  }

  ngOnInit() {


    this.isStudentsPage = this.router.url.includes('list');

    this.studentsService.getAllStudents().subscribe(
      data => {
        this.allStudents = data;
      },
      error => {
        console.log(error);
    });

  }


  redirectToList(){
    this.listingService.componentIndicate = 'Estudiantes';
    this.router.navigateByUrl('/list');

  }

  asignDetailData(student: students){

    this.detailService.detailsData= new Object({
      currentData: student,
      typeData: 'Estudiante'
    });
    this.router.navigateByUrl('/details');

  }


}
