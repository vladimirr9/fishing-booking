import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { HolidayHouseDTO } from '../dto/HolidayHouseDTO';

@Injectable({
  providedIn: 'root'
})
export class HolidayHouseService {

  constructor(private _http: HttpClient) { }

  private _housesUrl = "";

  getHouses(): HolidayHouseDTO[]{
    return [{
      "imgUrl": "",
      "name": "HolidayHouse 1",
      "adress": "Leaf Rake 2323",
      "description": "orem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley",
      "mark": 3.2,
      "price": 69.95,
      "rules": "Its forbidden to jump on bads!"
    },
    {
      "imgUrl": "",
      "name": "HolidayHouse 2",
      "adress": "Clover Lane BB",
      "description": "orem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley",
      "mark": 2.2,
      "price": 39.95,
      "rules": "Its forbidden to jump on bads!"
    }];
  }
}
