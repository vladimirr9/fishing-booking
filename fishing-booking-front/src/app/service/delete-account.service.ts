import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { config } from "src/shared"

@Injectable({
  providedIn: 'root'
})
export class DeleteAccountService {
  cancelRequest(username: string) {
    return this.http.delete(`${config.baseUrl}${this.deleteAccountURL}/${username}`)
  }
  constructor(private http: HttpClient) { }

  private deleteAccountURL = "/account-deletions"

  sendRequest(accountDeletion : any) {
    return this.http.post(`${config.baseUrl}${this.deleteAccountURL}`, accountDeletion)
  }

  getRequest(username: string) {
    return this.http.get(`${config.baseUrl}${this.deleteAccountURL}/${username}`)
  }



}
