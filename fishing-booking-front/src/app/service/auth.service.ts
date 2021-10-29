import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { CredentialsDTO } from 'src/app/dto/CredentialsDTO';
import { Observable } from 'rxjs';
import {config} from "shared"
import { RegistrationDTO } from '../dto/RegistrationDTO';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private loginUrl = "/auth/login"
  private providerRegistrationURL = "/auth/provider-registration"

  constructor(private http: HttpClient) { }

  login(credentialsDTO: CredentialsDTO) {
    return this.http.post<Observable<String>>(`${config.baseUrl}${this.loginUrl}`, credentialsDTO)
  }
  registerProvider(registrationDTO: RegistrationDTO) {
    return this.http.post(`${config.baseUrl}${this.providerRegistrationURL}`, registrationDTO)
  }
}
