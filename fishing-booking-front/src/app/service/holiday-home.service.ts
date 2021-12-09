import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { config } from "src/shared"

@Injectable({
  providedIn: 'root'
})
export class HolidayHomeService {

  private homeURL = "/holiday-homes"
  constructor(private http: HttpClient) { }

  createNewHome(home : any) : Observable<any> {
    return this.http.post(`${config.baseUrl}${this.homeURL}`, home)
  }

  getHome(id : number) : Observable<any> {
    return this.http.get(`${config.baseUrl}${this.homeURL}/${id}`)
  }

  updateHome(id : number, home : any): Observable<any> {
    return this.http.put(`${config.baseUrl}${this.homeURL}/${id}`, home)

  }

}
