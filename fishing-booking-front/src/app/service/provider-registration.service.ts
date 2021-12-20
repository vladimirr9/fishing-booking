import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { config } from "src/shared"

@Injectable({
  providedIn: 'root'
})
export class ProviderRegistrationService {


  private providerRegistrationURL = "/provider-registration"

  constructor(private http: HttpClient) { }

  getAllRegistrations(params?: any) {
    return this.http.get(`${config.baseUrl}${this.providerRegistrationURL}`, {
      params: params
    })
  }
  getRegistration(id: number) {
    return this.http.get(`${config.baseUrl}${this.providerRegistrationURL}/${id}`)
  }
  approve(id: number) {
    return this.http.put(`${config.baseUrl}${this.providerRegistrationURL}/${id}/approve`, {})
  }
  deny(id: number, message: string) {
    return this.http.put(`${config.baseUrl}${this.providerRegistrationURL}/${id}/deny`, {message: message})
  }
}
