import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { config } from "src/shared"

@Injectable({
  providedIn: 'root'
})
export class UserService {

  private userURL = "/users"

  constructor(private http: HttpClient)  { }

  updateProfile(id: number, userDetails : string) : Observable<any> {
    return this.http.put(`${config.baseUrl}${this.userURL}/${id}`, userDetails)
  }
  getProfile(username: string) {
    return this.http.get(`${config.baseUrl}${this.userURL}/${username}`)
  }
}
