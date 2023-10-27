import { Component } from '@angular/core';
import { ApprovedCourse } from 'src/app/models/approvedCourse.model';
import { ApprovedCoursesService } from 'src/app/services/approved-courses/approved-courses.service';

@Component({
  selector: 'app-approved-courses-list',
  templateUrl: './approved-courses-list.component.html',
  styleUrls: ['./approved-courses-list.component.sass']
})
export class ApprovedCoursesListComponent {


  approvedCourses: ApprovedCourse[]

  constructor(private approvedService :ApprovedCoursesService ){

    this.approvedCourses = [];

  }



  ngOnInit(){
    // student: students = null;

    this.approvedService.getAllEnrollmentsByStudentId(30003).subscribe(
      data => {
        this.approvedCourses = data;
      },
      error => {
        console.log(error);
    });
  }

}
