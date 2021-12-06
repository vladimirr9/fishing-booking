import { Component, Input, OnInit } from '@angular/core';

@Component({
  selector: 'app-adventure-card',
  templateUrl: './adventure-card.component.html',
  styleUrls: ['./adventure-card.component.scss']
})
export class AdventureCardComponent implements OnInit {

  @Input() adventure : any

  constructor() { }

  ngOnInit(): void {
  }

}
