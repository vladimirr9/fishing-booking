import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

import { Observable } from 'rxjs';
import {config} from "src/shared"
import { AvailablePeriod } from '../model/AvailablePeriod';

@Injectable({
  providedIn: 'root'
})
export class AvailablePeriodService {

  private periodsURL = "/available-periods"

  constructor(private http: HttpClient) { }

  getPeriods(providerEmail?: string) : Observable<AvailablePeriod[]> {
    let params
    if (providerEmail)
      params = {providerEmail:providerEmail}
    else params = {}
    return this.http.get<AvailablePeriod[]>(`${config.baseUrl}${this.periodsURL}`, {
      params: params
    } )
  }

  postPeriod(availablePeriod: AvailablePeriod) : Observable<AvailablePeriod> {
    return this.http.post<AvailablePeriod>(`${config.baseUrl}${this.periodsURL}`, availablePeriod)
  }

  deletePeriod(id: number) {
    return this.http.delete(`${config.baseUrl}${this.periodsURL}/${id}`)
  }



}
