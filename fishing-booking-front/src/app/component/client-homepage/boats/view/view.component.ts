import { Component, Input, OnInit } from '@angular/core';
import { BoatsDTO } from 'src/app/dto/BoatsDTO';

@Component({
  selector: 'app-view',
  templateUrl: './view.component.html',
  styleUrls: ['./view.component.scss']
})
export class ViewComponent implements OnInit {

  @Input() boats: BoatsDTO[]= [];

  //filters
  filterName: string = "";
  filterAdress: string = "";
  filterMark: number=0;
  filterStartDate: Date = new Date();
  filterEndDate: Date = new Date();
  //

  constructor() {
  }

  ngOnInit(): void {
  }

}
