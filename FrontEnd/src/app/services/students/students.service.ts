import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { students } from 'src/app/models/students.model';

@Injectable({
  providedIn: 'root'
})
export class StudentsService {

  constructor(private httpClient : HttpClient) { }



  getAllStudents() : Observable<students[]>{

    return this.httpClient.get<students[]>('../../../assets/data.json');



  }


}
