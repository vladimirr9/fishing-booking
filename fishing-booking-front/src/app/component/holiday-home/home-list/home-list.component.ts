import { Component,Input, OnInit } from '@angular/core';

@Component({
  selector: 'app-home-list',
  templateUrl: './home-list.component.html',
  styleUrls: ['./home-list.component.scss']
})
export class HomeListComponent implements OnInit {

  @Input() homes : any

  constructor() { }

  ngOnInit(): void {
  }

  removeHome(home : any) {
    let index = this.homes.indexOf(home);
      if (index !== -1) {
        this.homes.splice(index, 1);
      }

  }

}
