import { Component, Input, OnInit } from '@angular/core';
import { InstructorAdventureDTO } from 'src/app/dto/InstructorAdventureDTO';

@Component({
  selector: 'app-adventure-view',
  templateUrl: './adventure-view.component.html',
  styleUrls: ['./adventure-view.component.scss']
})
export class AdventureViewComponent implements OnInit {

  constructor() { }

  @Input() adventures: InstructorAdventureDTO[]=[];
  ngOnInit(): void {
  }

}
