import { Component, Input, OnInit } from '@angular/core';
import { InstructorAdventureDTO } from 'src/app/dto/InstructorAdventureDTO';
import { Router } from '@angular/router';

@Component({
  selector: 'app-adventure-view',
  templateUrl: './adventure-view.component.html',
  styleUrls: ['./adventure-view.component.scss']
})
export class AdventureViewComponent implements OnInit {

  constructor( private router: Router) { }

  @Input() adventures: InstructorAdventureDTO[]=[];
  ngOnInit(): void {
  }
  
  viewDetails(id: number): void{
    this.router.navigateByUrl(`adventures/${id}`)
  } 

}
