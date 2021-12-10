import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { InstructorAdventureDTO} from '../dto/InstructorAdventureDTO'

@Injectable({
  providedIn: 'root'
})
export class InstructorAdventureService {

  constructor(private _http: HttpClient) { }

  getAdventures(): InstructorAdventureDTO[]{
    return [
      {
        "imageUrl": "",
        "name": "Adventure num 1",
        "adress": "Leaf Rake",
        "description": "orem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley",
        "instructorsDescription": "orem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley",
        "mark": 3.2,
        "price": 19.95,
        "freeDates": []
      },
      {
          "imageUrl": "Adventure num 2",
          "name": "Adventure num 2",
          "adress": "Rake",
          "description": "orem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley",
          "instructorsDescription": "orem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley",
          "mark": 4.6,
          "price": 20.95,
          "freeDates": []
        }
    ]
  }
}
