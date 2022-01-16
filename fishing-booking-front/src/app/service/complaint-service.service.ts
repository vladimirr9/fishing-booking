import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { config } from "src/shared"

@Injectable({
  providedIn: 'root'
})
export class ComplaintServiceService {

  private complaintUrl = '/complaints';

  constructor(private http: HttpClient) { }

  createComplaint(id: number,content: string){
    return this.http.post(`${config.baseUrl}${this.complaintUrl}/create/${id}`, content);
  }

}
