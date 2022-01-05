import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { Router } from '@angular/router';
import { FishingPromotion } from 'src/app/model/FishingPromotion';
import { AdventureService } from 'src/app/service/adventure.service';
import { StorageService } from 'src/app/service/storage.service';

@Component({
  selector: 'app-fishing-promotion-card',
  templateUrl: './fishing-promotion-card.component.html',
  styleUrls: ['./fishing-promotion-card.component.scss']
})
export class FishingPromotionCardComponent implements OnInit {

  @Input() fishingPromotion !: FishingPromotion

  @Output() promotionDeleted = new EventEmitter()
  constructor(private storageService: StorageService, private adventureService: AdventureService, private router: Router) { }

  ngOnInit(): void {
  }


  isAdventureOwner() : boolean {
    return this.storageService.getUsername() === this.fishingPromotion.fishingAdventure.fishingInstructor.email
  }

  deletePromotion() {

    this.adventureService.deletePromotion(this.fishingPromotion.fishingAdventure.id, this.fishingPromotion.id).subscribe((data: any) => {
      this.promotionDeleted.emit(this.fishingPromotion)
    })
  }

  getDate(dateTime: string) {
    return new Date(Date.parse(dateTime))
  }
}
