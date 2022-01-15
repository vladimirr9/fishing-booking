import { Component, Input, OnInit } from '@angular/core';
import { ReservationDTO } from 'src/app/dto/ReservationDTO';
import { ReservationService } from 'src/app/service/reservation.service';

@Component({
  selector: 'app-reservation-view',
  templateUrl: './reservation-view.component.html',
  styleUrls: ['./reservation-view.component.scss']
})
export class ReservationViewComponent implements OnInit {

  constructor(private reservationService: ReservationService) { }

  _MS_PER_DAY: number = 1000 * 60 * 60 * 24;

  @Input() reservations: ReservationDTO[] = [];

  ngOnInit(): void {
  }

  showTime(startDate: Date): string{
    return (new Date(startDate).toLocaleTimeString());
  }

 isDeletable(date: Date): boolean{
    let startDate = new Date(date)
    const utc1 = Date.UTC(startDate.getFullYear(), startDate.getMonth(), startDate.getDate());
    const utc2 = Date.UTC(new Date().getFullYear(),new Date().getMonth(), new Date().getDate());
    let result = Math.abs(Math.floor((utc2 - utc1) / this._MS_PER_DAY));
    return result >= 3;
 }

  cancelReservation(id: number):void{
    this.reservationService.deleteReservation(id).subscribe(()=>{
        this.reservations = this.reservations.filter(reservation => reservation.id != id );
    },error=>{
        alert("Date is within 3 days");
    });
  }
}
