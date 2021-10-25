import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { credentialsDTO } from 'src/app/dto/credentialsDTO';
import { Observable } from 'rxjs';
import {config} from "shared"

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private loginUrl = "/auth/login"

  constructor(private http: HttpClient) { }

  login(credentialsDTO: credentialsDTO) {
    return this.http.post<Observable<String>>(`${config.baseUrl}${this.loginUrl}`, credentialsDTO)
  }
}
