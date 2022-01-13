import { Component, Input, OnInit } from '@angular/core';
import { CreateReservationDTO } from 'src/app/dto/CreateReservationDTO';
import { AdventureService } from 'src/app/service/adventure.service';
import { ReservationService } from 'src/app/service/reservation.service';
import { StorageService } from 'src/app/service/storage.service';

@Component({
  selector: 'app-reserve-adventure',
  templateUrl: './reserve-adventure.component.html',
  styleUrls: ['./reserve-adventure.component.scss']
})
export class ReserveAdventureComponent implements OnInit {

  constructor(private adventureService: AdventureService,private localStorage: StorageService,private reservationService: ReservationService) { }

  @Input() adventure: any
  totalPrice: number = 0;
  @Input() chosenDate: Date= new Date();
  @Input() endingDate: Date = new Date();
  startingTime: string = "";
  endingTime: string = ""; 
  personNum: number = 1;
  available: boolean = true;
  statusMessage: string = "";
  
  

  ngOnInit(): void {
    if(isNaN(this.endingDate.getHours())) return;
    let formatedStart = this.chosenDate.toLocaleTimeString().split(" ")[0].slice(0,4) + " " + this.chosenDate.toLocaleTimeString().split(" ")[1]
    let formatedEnd = this.endingDate.toLocaleTimeString().split(" ")[0].slice(0,4) + " " + this.endingDate.toLocaleTimeString().split(" ")[1]
    this.startingTime = formatedStart;
    this.endingTime = formatedEnd;
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


  reserve(): void{
    if(this.totalPrice<=0){alert("Please calculate price first!"); return;}
    let date = new Date(this.chosenDate.setHours(0,0,0,0))
    let from = this.addMinutes(date, this.convertTimeToNum(this.startingTime));
    let to = this.addMinutes(date, this.convertTimeToNum(this.endingTime));
    this.adventureService.getPeriodAvailability(this.adventure.fishingInstructor.email,from,to).subscribe((data)=>{
      this.available = data;
      if(this.available)
        this.sendRequest(from,to);
      else
        this.statusMessage="Unavailable period!";
    });
  }

  sendRequest(from: Date,to: Date): void{
    let reservationDto={
      price: this.totalPrice,
      from: from,
      to: to,
      clientUsername: this.localStorage.getUsername(),
      entityId: this.adventure.id,
      type: 'ADVENTURE'
    };
    this.reservationService.createReservation(reservationDto).subscribe(()=>{this.statusMessage="Reservation succesfully sent!";});
    
  }
  
   addMinutes(date: Date, minutes: number): Date {
    return new Date(date.getTime() + minutes*60000);
  }


  periodDurationInMinutes(): number{
    return this.convertTimeToNum(this.endingTime)- this.convertTimeToNum(this.startingTime);
  }

  calculatePrice(): void{
    let price = this.periodDurationInMinutes()/60 * this.personNum * this.adventure.hourlyPrice;
    if(price < 0 || isNaN(price)){
      alert("Please choose valid times!"); return;
    }
    this.totalPrice = this.periodDurationInMinutes()/60 * this.personNum * this.adventure.hourlyPrice;
  }
}
