import { Component, OnInit } from '@angular/core';
import { Validators, FormBuilder } from '@angular/forms';
import { Router } from '@angular/router';
import { studentRequest } from 'src/app/services/students/studentRequest';
import { StudentsService } from 'src/app/services/students/students.service';

@Component({
  selector: 'app-create-student',
  templateUrl: './create-student.component.html',
  styleUrls: ['./create-student.component.sass']
})
export class CreateStudentComponent implements OnInit {

  isError: boolean = false;


  createStudentForm = this.formBuilder.group({
    student_full_name: ['', [Validators.required]],
    faculty: ['',Validators.required],
    available_credits: [,Validators.required],
    student_email: [,Validators.email],
    student_phone: [,],
    enrolled_credits: [0]
  })


  constructor(private router:Router, private formBuilder:FormBuilder, private studentService:StudentsService) { }

  ngOnInit() {
  }

  onSubmit(){
    if(this.createStudentForm.valid){

      const formData = this.createStudentForm.value as unknown as studentRequest
      console.log(formData);
      this.studentService.addNewStudent(formData).subscribe(
        response => {
          console.log(response);
          this.router.navigateByUrl('/home', { skipLocationChange: true }).then(() => {
            this.router.navigate(['/list']);
          });

        }, error => {
          console.log(error);
          this.isError= true;
          this.createStudentForm.reset();
        }
      );





    }
  }

}
