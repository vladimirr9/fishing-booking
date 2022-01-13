import { Component, Input, OnInit } from '@angular/core';

@Component({
  selector: 'app-reserve-boat',
  templateUrl: './reserve-boat.component.html',
  styleUrls: ['./reserve-boat.component.scss']
})
export class ReserveBoatComponent implements OnInit {
  
  @Input() boat: any
  totalPrice: number = 0;
  @Input() startingDate: Date= new Date();
  @Input() endingDate: Date = new Date();
  personNum: number = 1;
  available: boolean = true;
  statusMessage: string = "";

  constructor() { }

  ngOnInit(): void {
  }

  addMinutes(date: Date, minutes: number): Date {
    return new Date(date.getTime() + minutes*60000);
  }


 

  calculatePrice(): void{
  
  }

  

  reserve(): void{}
}
