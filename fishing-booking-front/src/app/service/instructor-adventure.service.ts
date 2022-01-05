import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { InstructorAdventureDTO} from '../dto/InstructorAdventureDTO'

@Injectable({
  providedIn: 'root'
})
export class InstructorAdventureService {

  constructor(private _http: HttpClient) { }

  getAdventures(): InstructorAdventureDTO[]{
    return [];
  }
}
