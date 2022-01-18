import { Component, Input, OnInit } from '@angular/core';
import { BoatService } from 'src/app/service/boat.service';
import { ReservationService } from 'src/app/service/reservation.service';
import { StorageService } from 'src/app/service/storage.service';

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
  _MS_PER_DAY: number = 1000 * 60 * 60 * 24;

  constructor(private boatService: BoatService,private localStorage: StorageService,private reservationService: ReservationService) { }

  ngOnInit(): void {
  }




   dateDiffInDays(): number {

    const utc1 = Date.UTC(this.startingDate.getFullYear(), this.startingDate.getMonth(), this.startingDate.getDate());
    const utc2 = Date.UTC(this.endingDate.getFullYear(), this.endingDate.getMonth(), this.endingDate.getDate());
    let result = Math.floor((utc2 - utc1) / this._MS_PER_DAY);
    return Math.floor((utc2 - utc1) / this._MS_PER_DAY);
  }
 

  calculatePrice(): void{
    let result = this.dateDiffInDays()*this.personNum*this.boat.pricePerDay;
    if(result<=0){alert("Please enter valid dates!");return;}

    this.totalPrice = this.dateDiffInDays()*this.personNum*this.boat.pricePerDay;
  }

  

  reserve(): void{
    if(this.totalPrice <=0){alert("Please calculate price first!"); return;}

    let reservationDto={
      price: this.totalPrice,
      from: new Date(this.startingDate.setHours(12,0,0,0)),
      to: new Date(this.endingDate.setHours(12,0,0,0)),
      clientUsername: this.localStorage.getUsername(),
      entityId: this.boat.id,
      type: 'BOAT'
    };
    this.reservationService.createReservation(reservationDto).subscribe(()=>
    {
      this.available=true;
      this.statusMessage="Reservation succesfully sent!";},
    error=>{
      this.available=false;
      this.statusMessage="Unavailable period!"});
  }


}
