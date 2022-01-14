import { Component, Input, OnInit } from '@angular/core';
import { HolidayHomeService } from 'src/app/service/holiday-home.service';
import { ReservationService } from 'src/app/service/reservation.service';
import { StorageService } from 'src/app/service/storage.service';



@Component({
  selector: 'app-reserve-holiday-home',
  templateUrl: './reserve-holiday-home.component.html',
  styleUrls: ['./reserve-holiday-home.component.scss']
})
export class ReserveHolidayHomeComponent implements OnInit {

  @Input() holidayhome: any
  totalPrice: number = 0;
  @Input() startingDate: Date= new Date();
  @Input() endingDate: Date = new Date();
  personNum: number = 1;
  available: boolean = true;
  statusMessage: string = "";
  _MS_PER_DAY: number = 1000 * 60 * 60 * 24;
  
  constructor(private holidayHomeService: HolidayHomeService,private localStorage: StorageService,private reservationService: ReservationService) { }

  ngOnInit(): void {
  }

  dateDiffInDays(): number {

    const utc1 = Date.UTC(this.startingDate.getFullYear(), this.startingDate.getMonth(), this.startingDate.getDate());
    const utc2 = Date.UTC(this.endingDate.getFullYear(), this.endingDate.getMonth(), this.endingDate.getDate());
    let result = Math.floor((utc2 - utc1) / this._MS_PER_DAY);
    if(result == 0) return 1;
    return 0;
  }
 

  calculatePrice(): void{
    let result = this.dateDiffInDays()*this.personNum*this.holidayhome.pricePerDay;
    if(result<=0){alert("Please enter valid dates!");return;}

    this.totalPrice = this.dateDiffInDays()*this.personNum*this.holidayhome.pricePerDay;
  }

  

  reserve(): void{
    if(this.totalPrice <=0){alert("Please calculate price first!"); return;}
    this.holidayHomeService.IsHomeAvailabile(this.holidayhome.id,this.startingDate,this.endingDate).subscribe((data)=>{
      this.available = data;
      if(this.available)
        this.sendRequest();
      else
        this.statusMessage="Unavailable period!";
    });
  }

  sendRequest(): void{
    let reservationDto={
      price: this.totalPrice,
      from: this.startingDate,
      to: this.endingDate,
      clientUsername: this.localStorage.getUsername(),
      entityId: this.holidayhome.id,
      type: 'HOLIDAY_HOME'
    };
    this.reservationService.createReservation(reservationDto).subscribe(()=>{this.statusMessage="Reservation succesfully sent!";});
  }
}
