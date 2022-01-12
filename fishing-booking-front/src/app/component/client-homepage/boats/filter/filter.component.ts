import { Component, Input, OnInit } from '@angular/core';

@Component({
  selector: 'app-filter',
  templateUrl: './filter.component.html',
  styleUrls: ['./filter.component.scss']
})
export class FilterComponent implements OnInit {

  filterName: string = "";
  filterAdress: string = "";
  filterMark: number=0;
  // filterStartDate: Date = new Date();
  // filterEndDate: Date = new Date();

  constructor() { }

  ngOnInit(): void {
  }

  markChanged(): void{
    //alert(this.filterStartDate);  
    //alert(this.filterEndDate);
  }

}
