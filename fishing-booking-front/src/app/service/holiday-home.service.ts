import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { config } from "src/shared"
import { HolidayHouseDTO } from '../dto/HolidayHouseDTO';

@Injectable({
  providedIn: 'root'
})
export class HolidayHomeService {
  
  

  private homeURL = "/holiday-home"
  constructor(private http: HttpClient) { }

  getAllHomes() : Observable<HolidayHouseDTO[]>{
      return this.http.get<HolidayHouseDTO[]>(`${config.baseUrl}${this.homeURL}/client`);
  }

  getAvailableHomes(from: Date,to: Date):Observable<HolidayHouseDTO[]>{
    let params = Object();
    params.from = from.toISOString();
    params.to = to.toISOString();
    return this.http.get<HolidayHouseDTO[]>(`${config.baseUrl}${this.homeURL}/client/freeHomes`,{params: params})
  }

  IsHomeAvailabile(id: number,from: Date,to: Date): Observable<boolean>{
    let params = Object();
    params.from = from.toISOString();
    params.to = to.toISOString();
    return this.http.get<boolean>(`${config.baseUrl}${this.homeURL}/client/freeHomes/${id}`,{params: params});
  } 

  createNewHome(home : any) : Observable<any> {
    return this.http.post(`${config.baseUrl}${this.homeURL}`, home)
  }

  getHome(id : number) : Observable<any> {
    return this.http.get(`${config.baseUrl}${this.homeURL}/${id}`)
  }

  updateHome(id : number, home : any): Observable<any> {
    return this.http.put(`${config.baseUrl}${this.homeURL}/${id}`, home)
  }

  deleteHome(id : number) : Observable<any> {
    return this.http.delete(`${config.baseUrl}${this.homeURL}/${id}`)
  }

  getHomesForOwner(params : any) : Observable<any> {
    return this.http.get(`${config.baseUrl}${this.homeURL}`, {
      params: params
    })
  }

  postPicture(id_home: number, picture: any, isInterior: boolean) {
    return this.http.post(`${config.baseUrl}${this.homeURL}/${id_home}/pictures/${isInterior}`, picture)
  }

  deletePicture(id_home: number, id_picture : number, isInterior: boolean) {
    return this.http.delete(`${config.baseUrl}${this.homeURL}/${id_home}/pictures/${isInterior}/${id_picture}`)
  }
}
