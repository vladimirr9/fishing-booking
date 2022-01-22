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
  services: any
  activatedServices: number[]=[]
  
  

  ngOnInit(): void {
    //if(isNaN(this.endingDate.getHours())) return;
    let formatedStart = this.chosenDate.toLocaleTimeString().split(" ")[0].slice(0,4) + " " + this.chosenDate.toLocaleTimeString().split(" ")[1]
    let formatedEnd = this.endingDate.toLocaleTimeString().split(" ")[0].slice(0,4) + " " + this.endingDate.toLocaleTimeString().split(" ")[1]
    this.startingTime = formatedStart;
    this.endingTime = formatedEnd;
    this.services = this.adventure.additionalService;
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

    let reservationDto={
      price: this.totalPrice,
      from: new Date(from.setHours(from.getHours()+1)),
      to: new Date(to.setHours(to.getHours()+1)),
      clientUsername: this.localStorage.getUsername(),
      entityId: this.adventure.id,
      type: 'ADVENTURE',
      additionalServices: this.activatedServices
    };
     
    this.reservationService.createReservation(reservationDto).subscribe(()=>{this.statusMessage="Reservation succesfully sent!";this.available=true;},
    error=>{
      this.available=false;
      this.statusMessage="Unavailable period!"
    });
  }


  
   addMinutes(date: Date, minutes: number): Date {
    return new Date(date.getTime() + minutes*60000);
  }

  checkService(service: any,checked: boolean): void{
    alert(checked);
  }


  periodDurationInMinutes(): number{
    return this.convertTimeToNum(this.endingTime)- this.convertTimeToNum(this.startingTime);
  }

  calculatePrice(): void{
    let price = this.periodDurationInMinutes()/60 * this.personNum * this.adventure.hourlyPrice;
    if(price <= 0 || isNaN(price)){
      alert("Please choose valid times!"); return;
    }
    this.totalPrice = this.periodDurationInMinutes()/60 * this.personNum * this.adventure.hourlyPrice;
  }

  isServiceActivated(serviceId: any){
    return this.activatedServices.includes(serviceId);
  }

  addService(service: any){
    this.activatedServices.push(service.id);
    this.totalPrice+=service.price;
  }

  cancelService(service: any){
    this.activatedServices = this.activatedServices.filter(function(item) {return item != service.id})
    this.totalPrice-=service.price;
  }
}
