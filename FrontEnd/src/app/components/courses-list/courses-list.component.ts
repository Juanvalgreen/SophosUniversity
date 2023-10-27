import { Component,OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Courses } from 'src/app/models/courses.model';
import { CoursesService } from 'src/app/services/courses/courses.service';
import { DetailsDataService } from 'src/app/services/details-data/details-data.service';
import { ListingService } from 'src/app/services/listing/listing.service';

@Component({
  selector: 'app-courses-list',
  templateUrl: './courses-list.component.html',
  styleUrls: ['./courses-list.component.sass']
})
export class CoursesListComponent implements OnInit {

  isCoursesPage: boolean;
  courses : Courses[];

  constructor(private courseService : CoursesService, private listingService:ListingService,private detailService: DetailsDataService, private router:Router){
    this.courses = [];
    this.isCoursesPage = false
  }




  ngOnInit() {

    this.isCoursesPage = this.router.url.includes('list');

    this.courseService.getAllCourses().subscribe(
      data => {
        this.courses = data
      },error => {
        console.log(error);
      });

  }

  redirectToList(){
    this.listingService.componentIndicate = 'Cursos';
    this.router.navigateByUrl('/list');
  }


  asignDetailData(course: Courses){



    this.detailService.detailsData= new Object({
      currentData: course,
      typeData: 'Curso'
    });
    this.router.navigateByUrl('/details');
  }

}
