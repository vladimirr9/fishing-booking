import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { Router } from '@angular/router';
import { BoatPromotion } from 'src/app/model/BoatPromotion';
import { BoatService } from 'src/app/service/boat.service';
import { StorageService } from 'src/app/service/storage.service';

@Component({
  selector: 'app-boat-promotion-card',
  templateUrl: './boat-promotion-card.component.html',
  styleUrls: ['./boat-promotion-card.component.scss']
})
export class BoatPromotionCardComponent implements OnInit {

  @Input() boatPromotion !: BoatPromotion

  @Output() promotionDeleted = new EventEmitter()
  constructor(private storageService: StorageService, private boatService: BoatService, private router: Router) { }

  ngOnInit(): void {

  }



  deletePromotion() {

    this.boatService.deletePromotion(this.boatPromotion.boat.id, this.boatPromotion.id).subscribe((data: any) => {
      this.promotionDeleted.emit(this.boatPromotion)
    })
  }

  getDate(dateTime: string) {
    return new Date(Date.parse(dateTime))
  }

}
