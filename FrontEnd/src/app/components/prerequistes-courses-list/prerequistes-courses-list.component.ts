import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { PrerequisiteCourse } from 'src/app/models/prerequisiteCourse.model';
import { DetailsDataService } from 'src/app/services/details-data/details-data.service';
import { PrerequisiteCoursesService } from 'src/app/services/prerequiste-courses/prerequisite-courses.service';

@Component({
  selector: 'app-prerequistes-courses-list',
  templateUrl: './prerequistes-courses-list.component.html',
  styleUrls: ['./prerequistes-courses-list.component.sass']
})
export class PrerequistesCoursesListComponent {

  isError: boolean = false;

  prerequisiteCourses: PrerequisiteCourse[]

  constructor(private prerequisiteService :PrerequisiteCoursesService, private detailService: DetailsDataService, private router: Router ){

    this.prerequisiteCourses = [];

  }



  ngOnInit(){
    // student: students = null;

    this.prerequisiteService.getAllPrerequisteCoursesByCourseId(this.detailService.detailsData.currentData.course_id).subscribe(
      data => {
        this.prerequisiteCourses = data;
        console.log(data);
      },
      error => {
        console.log(error);
        this.isError = true;

    });
  }

}
