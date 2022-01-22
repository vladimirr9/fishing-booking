import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { CredentialsDTO } from 'src/app/dto/CredentialsDTO';
import { Observable } from 'rxjs';
import { config } from "src/shared"
import { RegistrationDTO } from 'src/app/dto/RegistrationDTO';
import { StorageService } from './storage.service';

@Injectable({
  providedIn: 'root'
})
export class AuthService {


  private loginUrl = "/auth/login"
  private providerRegistrationURL = "/auth/provider-registration"
  private clientRegistrationURL = "/auth/clients"

  constructor(private http: HttpClient,
              private storageService: StorageService) { }

  login(credentialsDTO: CredentialsDTO) {
    return this.http.post<Observable<String>>(`${config.baseUrl}${this.loginUrl}`, credentialsDTO)
  }
  registerProvider(registrationDTO: RegistrationDTO) {
    return this.http.post(`${config.baseUrl}${this.providerRegistrationURL}`, registrationDTO)
  }
  registerAdmin(adminRegistration: any) {
    return this.http.post(`${config.baseUrl}/auth/admins`, adminRegistration)
  }

  registerClient(clientRegistration: any){
    return this.http.post(`${config.baseUrl}${this.clientRegistrationURL}`,clientRegistration);
  }

  isFishingInstructor() : boolean {
    return this.storageService.getRole() === "ROLE_FISHING_INSTRUCTOR"
  }

  hasIncome() : boolean {
    return this.storageService.getRole() === "ROLE_FISHING_INSTRUCTOR" || this.storageService.getRole() === "ROLE_HOME_OWNER" ||this.storageService.getRole() === "ROLE_BOAT_OWNER" ||this.storageService.getRole() === "ROLE_ADMIN"
  }

  isAdmin() : boolean {
    return this.storageService.getRole() === "ROLE_ADMIN"
  }

  isLoggedIn() {
    return this.storageService.getToken() !== ""
  }

  logOut() {
    this.storageService.clear()
  }
}
