import { Component, Input, OnInit } from '@angular/core';
import { HolidayHomeService } from 'src/app/service/holiday-home.service';
import { ReservationService } from 'src/app/service/reservation.service';
import { StorageService } from 'src/app/service/storage.service';

@Component({
  selector: 'app-home-promotions',
  templateUrl: './home-promotions.component.html',
  styleUrls: ['./home-promotions.component.scss']
})
export class HomePromotionsComponent implements OnInit {

  constructor(private homeService:HolidayHomeService,private reservationService: ReservationService,private localStorage:StorageService) { }

  @Input() id: number= 0;

  promotions: any;
  available: boolean = true;
  statusMessage: string = "";
  
  ngOnInit(): void {
    this.homeService.getHomePromotions(this.id).subscribe((data: any)=>{
        this.promotions=data;
    });
  }

  reserve(promotion: any): void{
    let reservationDto={
      price: promotion.price,
      from: promotion.fromTime,
      to: promotion.toTime,
      clientUsername: this.localStorage.getUsername(),
      entityId: this.id,
      type: 'HOLIDAY_HOME'
    };
    this.reservationService.createReservationWithPromotion(reservationDto,promotion.id).subscribe(      
      (data) => {
        this.available=true;
        this.statusMessage="Reservation succesfully sent!";
       },
      (error) => {
        this.available=false;
        this.statusMessage="Unavailable period!";
      }
    ).add(() => {
      setTimeout(() =>{
        this.promotions = this.promotions.filter((prom: { id: any; }) => prom.id != promotion.id );
      }, 1500); 
    });
  }

}
