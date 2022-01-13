import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import {  Observable } from 'rxjs';
import { config } from "src/shared"
import { BoatsDTO } from '../dto/BoatsDTO';

@Injectable({
  providedIn: 'root'
})
export class BoatService {

  constructor(private http: HttpClient) { }

  private boatUrl = '/boat';

  getBoats(): Observable<BoatsDTO[]>{
    return this.http.get<BoatsDTO[]>(`${config.baseUrl}${this.boatUrl}/client`);
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

}
