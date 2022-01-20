import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { Router } from '@angular/router';
import { HomePromotion } from 'src/app/model/HomePromotion';
import { HolidayHomeService } from 'src/app/service/holiday-home.service';
import { StorageService } from 'src/app/service/storage.service';

@Component({
  selector: 'app-home-promotion-card',
  templateUrl: './home-promotion-card.component.html',
  styleUrls: ['./home-promotion-card.component.scss']
})
export class HomePromotionCardComponent implements OnInit {

  @Input() homePromotion !: HomePromotion

  @Output() promotionDeleted = new EventEmitter()
  constructor(private storageService: StorageService, private homeService: HolidayHomeService, private router: Router) { }

  ngOnInit(): void {

  }



  deletePromotion() {

    this.homeService.deletePromotion(this.homePromotion.holidayHome.id, this.homePromotion.id).subscribe((data: any) => {
      this.promotionDeleted.emit(this.homePromotion)
    })
  }

  getDate(dateTime: string) {
    return new Date(Date.parse(dateTime))
  }

}
