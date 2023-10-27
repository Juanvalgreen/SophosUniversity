import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Enrollment } from 'src/app/models/enrollment.model';

@Injectable({
  providedIn: 'root'
})
export class EnrollmentsService {

  constructor(private httpClient: HttpClient) { }




  getAllEnrollmentsByStudentId(studentId: number){

    // return this.httpClient.get<Enrollment[]>('/enrollments/'+studentId+'/students');
    return this.httpClient.get<Enrollment[]>('../../../assets/dataEnrollment.json');


  }


  getAllEnrollmentsByCourseId(courseId: number){

    // return this.httpClient.get<Enrollment[]>('/enrollments/'+courseId+'/courses');
    return this.httpClient.get<Enrollment[]>('../../../assets/dataEnrollment.json');


  }





}
