import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { config } from "src/shared"
import { InstructorAdventureDTO } from '../dto/InstructorAdventureDTO';
import { PromotionDTO } from '../dto/PromotionDTO';
import { FishingPromotion } from '../model/FishingPromotion';

@Injectable({
  providedIn: 'root'
})
export class AdventureService {





  private adventureURL = "/fishing-adventures"


  constructor(private http: HttpClient) { }

  getAllAdventures(): Observable<InstructorAdventureDTO[]>{
    return this.http.get<InstructorAdventureDTO[]>(`${config.baseUrl}${this.adventureURL}/client`);
  }

  getAvailableAdventures(from: Date,to: Date): Observable<InstructorAdventureDTO[]>{
    //add
    return this.http.get<InstructorAdventureDTO[]>(`${config.baseUrl}${this.adventureURL}/client/freePeriods`); 
  }

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

  postPromotion(id: number, promotion: PromotionDTO) : Observable<any> {
    return this.http.post(`${config.baseUrl}${this.adventureURL}/${id}/promotions`, promotion)
  }
  deletePromotion(id: number, idPromotion: number) : any {
    return this.http.delete(`${config.baseUrl}${this.adventureURL}/${id}/promotions/${idPromotion}`)
  }

  getPromotions(id: number) : Observable<FishingPromotion[]> {
    return this.http.get<FishingPromotion[]>(`${config.baseUrl}${this.adventureURL}/${id}/promotions`)
  }


}
