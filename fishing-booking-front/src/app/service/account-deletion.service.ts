import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { config } from "src/shared"

@Injectable({
  providedIn: 'root'
})
export class AccountDeletionService {


  private accountDeletionsUrl = "/account-deletions"
  constructor(private http: HttpClient) { }


  getAccountDeletions() {
    return this.http.get(`${config.baseUrl}${this.accountDeletionsUrl}`)
  }
  approveDeletion(id: number, reason: string) {
    return this.http.put(`${config.baseUrl}${this.accountDeletionsUrl}/${id}/approve`, {reason: reason})
  }
  denyDeletion(id: number, reason: string) {
    return this.http.put(`${config.baseUrl}${this.accountDeletionsUrl}/${id}/deny`, {reason: reason})
  }
}
