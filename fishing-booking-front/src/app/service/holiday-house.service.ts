import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { HolidayHouseDTO } from '../dto/HolidayHouseDTO';

@Injectable({
  providedIn: 'root'
})
export class HolidayHouseService {

  constructor(private _http: HttpClient) { }

  private _housesUrl = "";
}
