import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { config } from "src/shared"

@Injectable({
  providedIn: 'root'
})
export class AdventureService {

  private adventureURL = "/fishing-adventures"


  constructor(private http: HttpClient) { }

  getAdventuresForInstructor(instructorUsername : string) : Observable<any> {
    return this.http.get(`${config.baseUrl}${this.adventureURL}`, {
      params: {
        instructorUsername: instructorUsername
      }
    })
  }
  createNewAdventure(adventure : any) : Observable<any> {
    return this.http.post(`${config.baseUrl}${this.adventureURL}`, adventure)
  }


}
