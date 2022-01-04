import { Component, Input, OnInit } from '@angular/core';
import { HolidayHouseDTO } from 'src/app/dto/HolidayHouseDTO';

@Component({
  selector: 'app-holiday-houses-view',
  templateUrl: './holiday-houses-view.component.html',
  styleUrls: ['./holiday-houses-view.component.scss']
})
export class HolidayHousesViewComponent implements OnInit {

  constructor() { }

  @Input() houses: HolidayHouseDTO[]=[];
  
  ngOnInit(): void {
  }

}
