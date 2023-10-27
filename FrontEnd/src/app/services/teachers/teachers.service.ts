import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { teachers } from 'src/app/models/teachers.model';

@Injectable({
  providedIn: 'root'
})
export class TeachersService {

  constructor(private httpClient:HttpClient) { }

  getAllTeachers() : Observable<teachers[]>{

    return this.httpClient.get<teachers[]>('../../../assets/dataTeachers.json');

  }



}
