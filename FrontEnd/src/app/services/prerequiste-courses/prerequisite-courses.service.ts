import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { PrerequisiteCourse } from 'src/app/models/prerequisiteCourse.model';

@Injectable({
  providedIn: 'root'
})
export class PrerequisiteCoursesService {

  constructor(private httpClient: HttpClient) { }


  getAllPrerequisteCoursesByCourseId(courseId: number){


    // return this.httpClient.get<PrerequisiteCourse[]>('/prerequiste/'+courseId+'/courses');
    return this.httpClient.get<PrerequisiteCourse[]>('../../../assets/dataPrerequisites.json');


  }





}
