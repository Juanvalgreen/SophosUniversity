import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Courses } from 'src/app/models/courses.model';

@Injectable({
  providedIn: 'root'
})
export class CoursesService {



  constructor(private httpClient: HttpClient) { }

  getAllCourses(): Observable<Courses[]>{

    // return this.httpClient.get<Courses[]>('/courses');
    return this.httpClient.get<Courses[]>('../../../assets/dataCourses.json');
  }


  getAllCoursesByTeacherId(teacherId: number): Observable<Courses[]>{

        // return this.httpClient.get<Courses[]>('/courses/'+teacherId+'/teachers');
        return this.httpClient.get<Courses[]>('../../../assets/dataCourses.json');
  }

}
