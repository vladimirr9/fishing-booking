import { Component, Input, OnInit } from '@angular/core';
import { ReservationDTO } from 'src/app/dto/ReservationDTO';

@Component({
  selector: 'app-reservation-view',
  templateUrl: './reservation-view.component.html',
  styleUrls: ['./reservation-view.component.scss']
})
export class ReservationViewComponent implements OnInit {

  constructor() { }
  
  @Input() reservations: ReservationDTO[] = [];
  ngOnInit(): void {
  }

}
