import { Component, Input, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { BoatsDTO } from 'src/app/dto/BoatsDTO';

@Component({
  selector: 'app-view',
  templateUrl: './view.component.html',
  styleUrls: ['./view.component.scss']
})
export class ViewComponent implements OnInit {

  @Input() boats: BoatsDTO[]= [];

  constructor(private router: Router) {
  }

  ngOnInit(): void {
  }

  getBoatDetails(id: number):void{
    this.router.navigate(['/boats',id])
  }

}
