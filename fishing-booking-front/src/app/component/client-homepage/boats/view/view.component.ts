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
  @Input() startingDate: Date = new Date();
  @Input() endingDate: Date = new Date();

  constructor(private router: Router) {
  }

  ngOnInit(): void {
  }

  getBoatDetails(id: number):void{
    this.router.navigate(['/boats',id,this.startingDate.toISOString(),this.endingDate.toISOString()])
  }

}
