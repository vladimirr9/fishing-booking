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


  deleteAdditionalService(id_adventure: number, id_service : number) {
    return this.http.delete(`${config.baseUrl}${this.adventureURL}/${id_adventure}/additional-services/${id_service}`)
  }
  postAdditionalService(id: number, additionalService: any) {
    return this.http.post(`${config.baseUrl}${this.adventureURL}/${id}/additional-services/`, additionalService)
  }

  postPicture(id_adventure : number, picture: any) {
    return this.http.post(`${config.baseUrl}${this.adventureURL}/${id_adventure}/pictures/`, picture)
  }
  deletePicture(id_adventure: number, id_picture : number) {
    return this.http.delete(`${config.baseUrl}${this.adventureURL}/${id_adventure}/pictures/${id_picture}`)
  }
  getAdventuresForInstructor(params : any) : Observable<any> {
    return this.http.get(`${config.baseUrl}${this.adventureURL}`, {
      params: params
    })
  }
  createNewAdventure(adventure : any) : Observable<any> {
    return this.http.post(`${config.baseUrl}${this.adventureURL}`, adventure)
  }

  deleteAdventure(id : number) : Observable<any> {
    return this.http.delete(`${config.baseUrl}${this.adventureURL}/${id}`)
  }

  getAdventure(id : number) : Observable<any> {
    return this.http.get(`${config.baseUrl}${this.adventureURL}/${id}`)
  }

  updateAdventure(id : number, adventure : any): Observable<any> {
    return this.http.put(`${config.baseUrl}${this.adventureURL}/${id}`, adventure)

  }


}
