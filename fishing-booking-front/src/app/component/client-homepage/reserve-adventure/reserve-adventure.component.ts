import { Component, Input, OnInit } from '@angular/core';

@Component({
  selector: 'app-reserve-adventure',
  templateUrl: './reserve-adventure.component.html',
  styleUrls: ['./reserve-adventure.component.scss']
})
export class ReserveAdventureComponent implements OnInit {

  constructor() { }

  @Input() adventure: any
  totalPrice: number = 0;
  chosenDate: Date= new Date();
  startingDate: string = "";
  endingDate: string = ""; 
  personNum: number = 1;

  ngOnInit(): void {
  }

  convertTimeToNum(time: string): number{
    let timeNum = 0;
    let minutes = parseInt(time.split(" ")[0].split(":")[1]);
    let hours = parseInt(time.split(" ")[0].split(":")[0]);
    if(time.split(" ")[1]=="PM")
      timeNum +=12 * 60;
    timeNum+=+ hours*60 + minutes;
  
    return timeNum;
  }


  reserve(): void{
    if(this.totalPrice<=0){alert("Please calculate price first!"); return;}
  }

  periodDurationInMinutes(): number{
    return this.convertTimeToNum(this.endingDate)- this.convertTimeToNum(this.startingDate);
  }

  calculatePrice(): void{
    let price = this.periodDurationInMinutes()/60 * this.personNum * this.adventure.hourlyPrice;
    if(price < 0){
      alert("Please choose valid times!"); return;
    }
    this.totalPrice = this.periodDurationInMinutes()/60 * this.personNum * this.adventure.hourlyPrice;
  }
}
