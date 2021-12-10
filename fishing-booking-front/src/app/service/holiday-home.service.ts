import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { config } from "src/shared"

@Injectable({
  providedIn: 'root'
})
export class HolidayHomeService {
  

  private homeURL = "/holiday-home"
  constructor(private http: HttpClient) { }

  createNewHome(home : any) : Observable<any> {
    return this.http.post(`${config.baseUrl}${this.homeURL}`, home)
  }

  getHome(id : number) : Observable<any> {
    return this.http.get(`${config.baseUrl}${this.homeURL}/${id}`)
  }

  updateHome(id : number, home : any): Observable<any> {
    alert(home.name);
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
}
