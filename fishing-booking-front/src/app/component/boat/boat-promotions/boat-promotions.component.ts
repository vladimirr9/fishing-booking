import { Component, Input, OnInit } from '@angular/core';
import { BoatService } from 'src/app/service/boat.service';
import { ReservationService } from 'src/app/service/reservation.service';
import { StorageService } from 'src/app/service/storage.service';

@Component({
  selector: 'app-boat-promotions',
  templateUrl: './boat-promotions.component.html',
  styleUrls: ['./boat-promotions.component.scss']
})
export class BoatPromotionsComponent implements OnInit {

  constructor(private boatService: BoatService,private localStorage: StorageService,private reservationService: ReservationService) { }

  @Input() id: number= 0;

  promotions: any;
  available: boolean = true;
  statusMessage: string = "";

  ngOnInit(): void {
   this.boatService.getBoatPromotions(this.id).subscribe((data: any)=>{
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
      type: 'BOAT'
    };
    alert(typeof(promotion.id))
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
