import { Component } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { teacherRequest } from 'src/app/services/teachers/teacherRequest';
import { TeachersService } from 'src/app/services/teachers/teachers.service';

@Component({
  selector: 'app-create-teacher',
  templateUrl: './create-teacher.component.html',
  styleUrls: ['./create-teacher.component.sass']
})
export class CreateTeacherComponent {

  isError: boolean = false;

  createTeacherForm = this.formBuilder.group({
    teacher_full_name: ['', [Validators.required]],
    max_degree: ['',Validators.required],
    experience_years: [,Validators.required]
  });

  constructor(private teacherService: TeachersService, private formBuilder:FormBuilder, private router: Router){

  }

  onSubmit(){
    if(this.createTeacherForm.valid){
      const formData = this.createTeacherForm.value as unknown as teacherRequest;
      console.log(formData);
      this.teacherService.addNewTeacher(formData).subscribe(
        (response: any) => {
          console.log(response);
          this.router.navigateByUrl('/home', { skipLocationChange: true }).then(() => {
            this.router.navigate(['/list']);
          });
        }, error => {
          console.log(error);
          this.isError = true;
          this.createTeacherForm.reset();
        }
      );
    }
  }


}
