import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import {  Observable } from 'rxjs';
import { config } from "src/shared"
import { BoatsDTO } from '../dto/BoatsDTO';

@Injectable({
  providedIn: 'root'
})
export class BoatService {

  constructor(private _http: HttpClient) { }

  private _boatUrl = '/boat';

  getBoats(): Observable<BoatsDTO[]>{
    return this._http.get<BoatsDTO[]>(`${config.baseUrl}${this._boatUrl}/client`);
  }

}
