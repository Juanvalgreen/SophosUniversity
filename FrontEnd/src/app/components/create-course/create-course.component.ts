import { Component } from '@angular/core';
import { students } from 'src/app/models/students.model';
import { teachers } from 'src/app/models/teachers.model';
import { StudentsService } from 'src/app/services/students/students.service';
import { TeachersService } from 'src/app/services/teachers/teachers.service';

@Component({
  selector: 'app-create-course',
  templateUrl: './create-course.component.html',
  styleUrls: ['./create-course.component.sass']
})
export class CreateCourseComponent {

  allTeachers: teachers[];
  allStudents: students[];

  constructor(private teachersService: TeachersService, private studentsService: StudentsService){
    this.allTeachers =[ ];
    this.allStudents = [ ];
  }

  ngOnInit() {

    this.studentsService.getAllStudents().subscribe(
      data => {
        this.allStudents = data;
      },
      error => {
        console.log(error);
    });

    this.teachersService.getAllTeachers().subscribe(
      data => {
        this.allTeachers = data;
      },
      error => {
        console.log(error);
    });

  }


}
