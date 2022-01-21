import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import {  Observable } from 'rxjs';
import { config } from "src/shared"
import { BoatsDTO } from '../dto/BoatsDTO';
import { PromotionDTO } from '../dto/PromotionDTO';
import { BoatPromotion } from '../model/BoatPromotion';

@Injectable({
  providedIn: 'root'
})
export class BoatService {
  getAllPromotionsForBoatOwner() {
    return this.http.get(`${config.baseUrl}${this.boatUrl}/promotions`)
  }

  constructor(private http: HttpClient) { }

  private boatUrl = '/boat';

  deleteAdditionalService(id_boat: number, id_service : number) {
    return this.http.delete(`${config.baseUrl}${this.boatUrl}/${id_boat}/additional-services/${id_service}`)
  }
  postAdditionalService(id: number, additionalService: any) {
    return this.http.post(`${config.baseUrl}${this.boatUrl}/${id}/additional-services/`, additionalService)
  }

  get() {
    return this.http.get(`${config.baseUrl}${this.boatUrl}`)
  }


  getBoats(): Observable<BoatsDTO[]>{
    return this.http.get<BoatsDTO[]>(`${config.baseUrl}${this.boatUrl}/client`);
  }

  getBoatPromotions(id: number): any{
    return this.http.get(`${config.baseUrl}${this.boatUrl}/${id}/promotions`);
  }

  getBoatsForOwner(params : any) : Observable<any> {
    return this.http.get(`${config.baseUrl}${this.boatUrl}`, {
      params: params
    })
  }

  IsBoatAvailabile(id: number,from: Date,to: Date): Observable<boolean>{
    let params = Object();
    params.from = from.toISOString();
    params.to = to.toISOString();
    return this.http.get<boolean>(`${config.baseUrl}${this.boatUrl}/client/freeBoats/${id}`,{params: params});
  }

  getAvailableBoats(from: Date,to: Date):Observable<BoatsDTO[]>{
    let params = Object();
    params.from = from.toISOString();
    params.to = to.toISOString();
    return this.http.get<BoatsDTO[]>(`${config.baseUrl}${this.boatUrl}/client/freeBoats`,{params: params})
  }

  createNewBoat(boat : any) : Observable<any> {
    return this.http.post(`${config.baseUrl}${this.boatUrl}`, boat)
  }

  getBoat(id : number) : Observable<any> {
    return this.http.get(`${config.baseUrl}${this.boatUrl}/${id}`)
  }

  updateBoat(id : number, boat : any): Observable<any> {
    return this.http.put(`${config.baseUrl}${this.boatUrl}/${id}`, boat)
  }

  deleteBoat(id : number) : Observable<any> {
    return this.http.delete(`${config.baseUrl}${this.boatUrl}/${id}`)
  }

  postPicture(id_boat: number, picture: any, isInterior: boolean) {
    return this.http.post(`${config.baseUrl}${this.boatUrl}/${id_boat}/pictures/${isInterior}`, picture)
  }

  deletePicture(id_boat: number, id_picture : number, isInterior: boolean) {
    return this.http.delete(`${config.baseUrl}${this.boatUrl}/${id_boat}/pictures/${isInterior}/${id_picture}`)
  }

  postPromotion(id: number, promotion: PromotionDTO) : Observable<any> {
    return this.http.post(`${config.baseUrl}${this.boatUrl}/${id}/promotions`, promotion)
  }
  deletePromotion(id: number, idPromotion: number) : any {
    return this.http.delete(`${config.baseUrl}${this.boatUrl}/${id}/promotions/${idPromotion}`)
  }

  getPromotions(id: number) : Observable<BoatPromotion[]> {
    return this.http.get<BoatPromotion[]>(`${config.baseUrl}${this.boatUrl}/${id}/promotions`)
  }
}
