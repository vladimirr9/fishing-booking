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
  @Input() chosenDate: Date = new Date();
  @Input() startingTime: string = "";
  @Input() endingTime: string= "";
  ngOnInit(): void {
  }
  
  viewDetails(id: number): void{
    if(this.startingTime==="" || this.endingTime==="")
      this.router.navigate(['/adventures',id,this.chosenDate.toISOString()])
    else{
      let date = new Date(this.chosenDate.setHours(0,0,0,0))
      let from = this.addMinutes(date,this.convertTimeToNum(this.startingTime));
      let to = this.addMinutes(date,this.convertTimeToNum(this.endingTime));
      this.router.navigate(['/adventures',id,from.toISOString(),to.toISOString()])
    }
      
  } 



  convertTimeToNum(time: string): number{
    let timeNum = 0;
    let minutes = parseInt(time.split(" ")[0].split(":")[1]);
    let hours = parseInt(time.split(" ")[0].split(":")[0]);
    if(time.split(" ")[1]=="PM")
      timeNum +=12 * 60;
    else if(hours==12)
      hours = 0
    timeNum+=+ hours*60 + minutes;
  
    return timeNum;
  }

  addMinutes(date: Date, minutes: number): Date {
    return new Date(date.getTime() + minutes*60000);
  }

}
