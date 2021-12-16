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
}
