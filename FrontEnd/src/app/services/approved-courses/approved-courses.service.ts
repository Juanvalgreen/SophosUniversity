import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { ApprovedCourse } from 'src/app/models/approvedCourse.model';

@Injectable({
  providedIn: 'root'
})
export class ApprovedCoursesService {

  constructor(private httpClient: HttpClient) { }



  getAllEnrollmentsByStudentId(studentId: number){

    // return this.httpClient.get<ApprovedCourse[]>('/approvedCourses/'+studentId+'/students');
    return this.httpClient.get<ApprovedCourse[]>('../../../assets/dataApprovedCourses.json');


  }




}
