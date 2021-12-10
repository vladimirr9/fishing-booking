import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { config } from "src/shared"

@Injectable({
  providedIn: 'root'
})
export class FeeService {

  private feeURL = "/service-fees"

  constructor(private http: HttpClient) { }

  getFee() {
    return this.http.get<Observable<any>>(`${config.baseUrl}${this.feeURL}`)
  }

  updateFee(fee: any) {
    return this.http.put<Observable<any>>(`${config.baseUrl}${this.feeURL}`, fee)
  }
}
