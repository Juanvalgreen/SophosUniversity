import { Component } from '@angular/core';
import { Courses } from 'src/app/models/courses.model';
import { CoursesService } from 'src/app/services/courses/courses.service';

@Component({
  selector: 'app-teacher-courses-list',
  templateUrl: './teacher-courses-list.component.html',
  styleUrls: ['./teacher-courses-list.component.sass']
})
export class TeacherCoursesListComponent {

  teacherCourses: Courses[]

  constructor(private enrollemntsService :CoursesService ){

    this.teacherCourses = [];

  }



  ngOnInit(){
    // student: students = null;

    this.enrollemntsService.getAllCoursesByTeacherId(30003).subscribe(
      data => {
        this.teacherCourses = data;
      },
      error => {
        console.log(error);
    });
  }


}
