import { Component, Input, OnInit } from '@angular/core';

@Component({
  selector: 'app-reserve-adventure',
  templateUrl: './reserve-adventure.component.html',
  styleUrls: ['./reserve-adventure.component.scss']
})
export class ReserveAdventureComponent implements OnInit {

  constructor() { }

  @Input() adventure: any

  ngOnInit(): void {
  }

  reserve(): void{
    
  }
}
