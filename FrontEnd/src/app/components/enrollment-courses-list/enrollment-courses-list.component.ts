import { Component } from '@angular/core';
import { Enrollment } from 'src/app/models/enrollment.model';
import { EnrollmentsService } from 'src/app/services/enrollments/enrollments.service';

@Component({
  selector: 'app-enrollment-courses-list',
  templateUrl: './enrollment-courses-list.component.html',
  styleUrls: ['./enrollment-courses-list.component.sass']
})
export class EnrollmentCoursesListComponent {


  enrollmentCourses: Enrollment[]

  constructor(private enrollemntsService :EnrollmentsService ){

    this.enrollmentCourses = [];

  }



  ngOnInit(){
    // student: students = null;

    this.enrollemntsService.getAllEnrollmentsByStudentId(30003).subscribe(
      data => {
        this.enrollmentCourses = data;
      },
      error => {
        console.log(error);
    });
  }








}
