import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

import { config } from "src/shared"

@Injectable({
  providedIn: 'root'
})
export class ReportsService {


  private reportsUrl = "/reports"
  private reservationsUrl = '/reservations'
  constructor(private http: HttpClient) { }

  getUnapprovedReports() {
    return this.http.get(`${config.baseUrl}${this.reportsUrl}`, {params: {
      approved: false
    }})
  }
  sendAdminReponse(reservation_id: number,report_id : number, response: string, reportAnswer: any) {
    return this.http.put(`${config.baseUrl}${this.reservationsUrl}/${reservation_id}${this.reportsUrl}/${report_id}/${response}`, reportAnswer)
  }
}
