import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { BoatsDTO } from '../dto/BoatsDTO';

@Injectable({
  providedIn: 'root'
})
export class BoatService {

  constructor(private _http: HttpClient) { }

  private _boatUrl = './api/boats/boats.json';

  getBoats(): BoatsDTO[]{
    //return this._http.get<BoatsDTO[]>(this._boatUrl);
    return [
      {
        "name": "AABrod1",
        "adress": "Leaf Rake",
        "description": "orem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley",
        "mark": 3.2,
        "price": 19.95,
        "freeDates": []
      },
      {
          "name": "ABrod2",
          "adress": "Rake",
          "description": "orem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley",
          "mark": 4.6,
          "price": 20.95,
          "freeDates": []
        }
    ]
  }

  getBoats2(): BoatsDTO[]{
    //return this._http.get<BoatsDTO[]>(this._boatUrl);
    return [
      {
        "name": "Brod1",
        "adress": "Leaf Rake",
        "description": "orem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley",
        "mark": 5.2,
        "price": 19.95,
        "freeDates": []
      }
    ]
  }
}
