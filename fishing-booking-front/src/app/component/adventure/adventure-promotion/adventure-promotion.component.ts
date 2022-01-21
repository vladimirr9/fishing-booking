import { Component, Input, OnInit } from '@angular/core';
import { FishingPromotion } from 'src/app/model/FishingPromotion';
import { AdventureService } from 'src/app/service/adventure.service';
import { ReservationService } from 'src/app/service/reservation.service';
import { StorageService } from 'src/app/service/storage.service';

@Component({
  selector: 'app-adventure-promotion',
  templateUrl: './adventure-promotion.component.html',
  styleUrls: ['./adventure-promotion.component.scss']
})
export class AdventurePromotionComponent implements OnInit {

  @Input() id: number= 0;
  
  constructor(private adventureService: AdventureService,private localStorage: StorageService,private reservationService: ReservationService) { }

  promotions: FishingPromotion[]=[];
  available: boolean = true;
  statusMessage: string = "";

  ngOnInit(): void {
    this.adventureService.getPromotions(this.id).subscribe((data:FishingPromotion[])=>{
      this.promotions= data;
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

  formateDate(date:Date){
    let d = new Date(date);
     return d.toLocaleTimeString().split(" ")[0].slice(0,4) + " " + d.toLocaleTimeString().split(" ")[1];
  }

}
