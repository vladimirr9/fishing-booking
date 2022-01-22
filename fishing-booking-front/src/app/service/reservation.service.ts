import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { ReservationDTO } from '../dto/ReservationDTO';
import {  Observable } from 'rxjs';
import { config } from "src/shared"
import { CreateReservationDTO } from '../dto/CreateReservationDTO';
import { IncomeDTO } from '../dto/IncomeDTO';

@Injectable({
  providedIn: 'root'
})
export class ReservationService {
  incomeReport(incomeDto: IncomeDTO) {
    return this._http.post<number>(`${config.baseUrl}${this._reservationsUrl}/income`, incomeDto);
  }


  constructor(private _http: HttpClient) { }

  private _reservationsUrl = '/reservations';

  getReservations(params?: any): Observable<ReservationDTO[]>{
    return this._http.get<ReservationDTO[]>(`${config.baseUrl}${this._reservationsUrl}`, {params: params});
  }

  getReservationsForClient(clientUsername: string): Observable<ReservationDTO[]>{
    return this._http.get<ReservationDTO[]>(`${config.baseUrl}${this._reservationsUrl}/${clientUsername}`);
  }



  putReport(reservationId: number, report: any) {
    return this._http.put(`${config.baseUrl}${this._reservationsUrl}/${reservationId}/reports`, report)
  }

  createReservation(reservationDto: CreateReservationDTO){
    return this._http.post(`${config.baseUrl}${this._reservationsUrl}/create`,reservationDto);
  }

  createReservationWithPromotion(reservationDto: CreateReservationDTO,id: number){
    return this._http.post(`${config.baseUrl}${this._reservationsUrl}/create/${id}`,reservationDto);
  }

  deleteReservation(id: number){
    return this._http.delete(`${config.baseUrl}${this._reservationsUrl}/${id}`);
  }

  approveReservation(id: any) {
    return this._http.put(`${config.baseUrl}${this._reservationsUrl}/${id}/approve`,{})
  }

}
