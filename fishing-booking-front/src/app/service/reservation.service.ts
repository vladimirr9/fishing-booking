import { Injectable } from '@angular/core';
import { ReservationDTO } from '../dto/ReservationDTO';

@Injectable({
  providedIn: 'root'
})
export class ReservationService {

  constructor() { }

  getReservations(): ReservationDTO[]{
    return [
      {imgUrl: "",
      name: "Adventure",
      startDate: new Date(2017, 0O5, 0O5, 17, 23, 42, 11),
      price: 23,
      durationInHours: 4,
      mark: 2.2,
      adress: "Cloverfiel 22"
    },
      {
      imgUrl: "",
      name: "Adventure2",
      startDate: new Date(2018, 0O5, 0O5, 17, 23, 42, 11),
      price: 24,
      durationInHours: 3,
      mark: 5.3,
      adress: "Alley 12"
      },
      {imgUrl: "",
      name: "Adventure",
      startDate: new Date(2017, 0O5, 0O5, 17, 23, 42, 11),
      price: 23,
      durationInHours: 4,
      mark: 2.2,
      adress: "Cloverfiel 22"
    },
      {
      imgUrl: "",
      name: "Adventure2",
      startDate: new Date(2018, 0O5, 0O5, 17, 23, 42, 11),
      price: 24,
      durationInHours: 3,
      mark: 5.3,
      adress: "Alley 12"
      },
      {imgUrl: "",
      name: "Adventure",
      startDate: new Date(2017, 0O5, 0O5, 17, 23, 42, 11),
      price: 23,
      durationInHours: 4,
      mark: 2.2,
      adress: "Cloverfiel 22"
    },
      {
      imgUrl: "",
      name: "Adventure2",
      startDate: new Date(2018, 0O5, 0O5, 17, 23, 42, 11),
      price: 24,
      durationInHours: 3,
      mark: 5.3,
      adress: "Alley 12"
      }];
  }
}
