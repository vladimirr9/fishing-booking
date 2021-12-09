import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-boats',
  templateUrl: './boats.component.html',
  styleUrls: ['./boats.component.scss']
})
export class BoatsComponent implements OnInit {

  constructor() { }

  //filters
  filterName: string = "";
  filterAdress: string = "";
  filterMark: number=0;
  filterStartDate: Date = new Date();
  filterEndDate: Date = new Date();
  //
  ngOnInit(): void {
  }

}
