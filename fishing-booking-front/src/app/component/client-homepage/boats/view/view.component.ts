import { Component, OnInit } from '@angular/core';
import { BoatsDTO } from 'src/app/dto/BoatsDTO';
import { BoatService } from 'src/app/service/boat.service';

@Component({
  selector: 'app-view',
  templateUrl: './view.component.html',
  styleUrls: ['./view.component.scss']
})
export class ViewComponent implements OnInit {

  boats: BoatsDTO[]= [];
  filteredBoats: BoatsDTO[]= [];

  //filters
  filterName: string = "";
  filterAdress: string = "";
  filterMark: number=0;
  filterStartDate: Date = new Date();
  filterEndDate: Date = new Date();
  //

  constructor(private _boatService: BoatService) {
  }

  ngOnInit(): void {
    this.boats = this._boatService.getBoats();
      
    
  }

}
