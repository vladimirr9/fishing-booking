import { Component, Input, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { HolidayHouseDTO } from 'src/app/dto/HolidayHouseDTO';

@Component({
  selector: 'app-holiday-houses-view',
  templateUrl: './holiday-houses-view.component.html',
  styleUrls: ['./holiday-houses-view.component.scss']
})
export class HolidayHousesViewComponent implements OnInit {

  constructor(private router: Router) { }

  @Input() houses: HolidayHouseDTO[]=[];
  @Input() startingDate: Date = new Date();
  @Input() endingDate: Date = new Date();
  
  ngOnInit(): void {
  }

  getHolidayHomeDetails(id: number): void{
    this.router.navigate(['/holiday-home',id,this.startingDate.toISOString(),this.endingDate.toISOString()])
  }

}
