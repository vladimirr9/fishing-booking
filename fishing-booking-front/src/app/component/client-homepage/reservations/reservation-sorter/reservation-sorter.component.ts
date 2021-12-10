import { Component, EventEmitter, OnInit, Output } from '@angular/core';

@Component({
  selector: 'app-reservation-sorter',
  templateUrl: './reservation-sorter.component.html',
  styleUrls: ['./reservation-sorter.component.scss']
})
export class ReservationSorterComponent implements OnInit {

  constructor() { }

  @Output() sortingEvent: EventEmitter<string> = new EventEmitter<string>();

  ngOnInit(): void {
  }

  setSorter(sorterType: string):void{
    this.sortingEvent.next(sorterType);
  }
}
