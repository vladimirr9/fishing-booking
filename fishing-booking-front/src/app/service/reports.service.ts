import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

import { config } from "src/shared"

@Injectable({
  providedIn: 'root'
})
export class ReportsService {

  private reportsUrl = "/reports"
  constructor(private http: HttpClient) { }

  getUnapprovedReports() {
    return this.http.get(`${config.baseUrl}${this.reportsUrl}`, {params: {
      approved: false
    }})
  }
}
