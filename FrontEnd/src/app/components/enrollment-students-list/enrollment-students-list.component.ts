import { Component } from '@angular/core';
import { Enrollment } from 'src/app/models/enrollment.model';
import { students } from 'src/app/models/students.model';
import { EnrollmentsService } from 'src/app/services/enrollments/enrollments.service';

@Component({
  selector: 'app-enrollment-students-list',
  templateUrl: './enrollment-students-list.component.html',
  styleUrls: ['./enrollment-students-list.component.sass']
})
export class EnrollmentStudentsListComponent {

  enrollmentStudents: Enrollment[]

  constructor(private enrollemntsService :EnrollmentsService ){

    this.enrollmentStudents = [];

  }



  ngOnInit(){
    // student: students = null;

    this.enrollemntsService.getAllEnrollmentsByCourseId(30003).subscribe(
      data => {
        this.enrollmentStudents = data;
      },
      error => {
        console.log(error);
    });
  }


}
