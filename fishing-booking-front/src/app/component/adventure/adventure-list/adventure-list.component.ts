import { Component, Input, OnInit } from '@angular/core';

@Component({
  selector: 'app-adventure-list',
  templateUrl: './adventure-list.component.html',
  styleUrls: ['./adventure-list.component.scss']
})
export class AdventureListComponent implements OnInit {

  @Input() adventures : any

  constructor() { }

  ngOnInit(): void {
  }

  removeAdventure(adventure : any) {
    let index = this.adventures.indexOf(adventure);
      if (index !== -1) {
        this.adventures.splice(index, 1);
      }

  }

}
