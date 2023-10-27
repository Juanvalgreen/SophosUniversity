import { Component } from '@angular/core';
import { PrerequisiteCourse } from 'src/app/models/prerequisiteCourse.model';
import { PrerequisiteCoursesService } from 'src/app/services/prerequiste-courses/prerequisite-courses.service';

@Component({
  selector: 'app-prerequistes-courses-list',
  templateUrl: './prerequistes-courses-list.component.html',
  styleUrls: ['./prerequistes-courses-list.component.sass']
})
export class PrerequistesCoursesListComponent {

  prerequisiteCourses: PrerequisiteCourse[]

  constructor(private prerequisiteService :PrerequisiteCoursesService ){

    this.prerequisiteCourses = [];

  }



  ngOnInit(){
    // student: students = null;

    this.prerequisiteService.getAllPrerequisteCoursesByCourseId(30003).subscribe(
      data => {
        this.prerequisiteCourses = data;
      },
      error => {
        console.log(error);
    });
  }

}
