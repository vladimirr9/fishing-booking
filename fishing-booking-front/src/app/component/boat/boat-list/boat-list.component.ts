import { Component,Input, OnInit } from '@angular/core';

@Component({
  selector: 'app-boat-list',
  templateUrl: './boat-list.component.html',
  styleUrls: ['./boat-list.component.scss']
})
export class BoatListComponent implements OnInit {

  @Input() boats : any

  constructor() { }

  ngOnInit(): void {
  }

  removeBoat(boat : any) {
    let index = this.boats.indexOf(boat);
      if (index !== -1) {
        this.boats.splice(index, 1);
      }

  }
}
