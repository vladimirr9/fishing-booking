import { Component, Input, OnInit } from '@angular/core';
import { BoatsDTO } from 'src/app/dto/BoatsDTO';

@Component({
  selector: 'app-view',
  templateUrl: './view.component.html',
  styleUrls: ['./view.component.scss']
})
export class ViewComponent implements OnInit {

  @Input() boats: BoatsDTO[]= [];

  constructor() {
  }

  ngOnInit(): void {
  }

}
