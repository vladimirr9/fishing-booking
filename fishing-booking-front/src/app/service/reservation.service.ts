import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { ReservationDTO } from '../dto/ReservationDTO';
import {  Observable } from 'rxjs';
import { config } from "src/shared"
import { CreateReservationDTO } from '../dto/CreateReservationDTO';

@Injectable({
  providedIn: 'root'
})
export class ReservationService {

  constructor(private _http: HttpClient) { }

  private _reservationsUrl = '/reservations';

  getReservations(): Observable<ReservationDTO[]>{
    return this._http.get<ReservationDTO[]>(`${config.baseUrl}${this._reservationsUrl}`);
  }

  createReservation(reservationDto: CreateReservationDTO){
    return this._http.post(`${config.baseUrl}${this._reservationsUrl}/create`,reservationDto);
  }
}
