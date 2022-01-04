import { Component, OnInit, Output,EventEmitter } from '@angular/core';


@Component({
  selector: 'app-sorter',
  templateUrl: './sorter.component.html',
  styleUrls: ['./sorter.component.scss']
})
export class SorterComponent implements OnInit {

  constructor() { }

  @Output() sortingEvent: EventEmitter<string> = new EventEmitter<string>();

  ngOnInit(): void {
  }

  setSorter(sorterType: string):void{
    this.sortingEvent.next(sorterType);
  }

}
