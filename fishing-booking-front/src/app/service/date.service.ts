import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class DateService {

  constructor() { }

  getDate(dateTime: string) {
    return new Date(Date.parse(dateTime))
  }
}
