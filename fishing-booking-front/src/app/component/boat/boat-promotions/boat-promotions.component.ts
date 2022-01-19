import { Component, Input, OnInit } from '@angular/core';
import { BoatService } from 'src/app/service/boat.service';

@Component({
  selector: 'app-boat-promotions',
  templateUrl: './boat-promotions.component.html',
  styleUrls: ['./boat-promotions.component.scss']
})
export class BoatPromotionsComponent implements OnInit {

  constructor(private boatService: BoatService) { }

  @Input() id: number= 0;

  promotions: any;

  ngOnInit(): void {
   this.boatService.getBoatPromotions(this.id).subscribe((data: any)=>{
      this.promotions=data;
   });
  }

  reserve(): void{
    
  }

}
