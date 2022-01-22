import { AfterViewInit, Component, OnInit, ViewChild } from '@angular/core';
import { InstructorAdventureDTO } from 'src/app/dto/InstructorAdventureDTO';
import { AdventureService } from 'src/app/service/adventure.service';
import { FilterComponent } from '../boats/filter/filter.component';


@Component({
  selector: 'app-instructors',
  templateUrl: './instructors.component.html',
  styleUrls: ['./instructors.component.scss']
})
export class InstructorsComponent implements OnInit,AfterViewInit {

  adventures: InstructorAdventureDTO[]= [];
  filteredAdventures: InstructorAdventureDTO[]= [];
  sorterType: string = "";
  searchStartDate: Date = new Date();
  startingTime: string = "";
  endingTime: string= "";

  constructor(private _adventureService: AdventureService) { }
  

  @ViewChild(FilterComponent)
  private filterComponent!: FilterComponent;

  ngOnInit(): void {
    //this.adventures = this._adventureService.getAllAdventures();
    this._adventureService.getAllAdventures().subscribe((data: InstructorAdventureDTO[])=>{
      this.adventures = data;
      this.filteredAdventures = this.adventures;
    });
    
  }

  ngAfterViewInit(): void {
    
  }

  sortAdventures(): void{
    switch(this.sorterType){
      case 'name':
        this.filteredAdventures.sort((a, b) => a.name.localeCompare(b.name));
        break;
      case 'mark':
        this.filteredAdventures.sort((a, b) => b.mark - a.mark);
        break;
      case 'location':
        this.filteredAdventures.sort((a, b) => a.adress.localeCompare(b.adress));
        break;
      default:

    }
  }

  setSorter(sorterType: string):void{
    this.sorterType = sorterType;
    this.sortAdventures();
  }

  async filterAdventures(): Promise<void>{
    this.filteredAdventures = this.adventures.filter(adventure => adventure.name.includes(this.filterComponent.filterName));
    this.filteredAdventures = this.filteredAdventures.filter(adventure => adventure.adress.includes(this.filterComponent.filterAdress));
    this.filteredAdventures = this.filteredAdventures.filter(adventure => adventure.mark >= this.filterComponent.filterMark);
    if(this.filterComponent.subscribed)
      this.filteredAdventures = this.filteredAdventures.filter(adventure => adventure.subscribed);
    this.sortAdventures();
  }

  searchAdventures(): void{
    if(this.startingTime==="" || this.endingTime ===""){alert("Please select time!"); return;}
    let date = new Date(this.searchStartDate.setHours(0,0,0,0))
    let from = this.addMinutes(date,this.convertTimeToNum(this.startingTime));
    let to = this.addMinutes(date,this.convertTimeToNum(this.endingTime));
    this._adventureService.getAvailableAdventures(new Date(from.setHours(from.getHours()+1)),new Date(to.setHours(to.getHours()+1))).subscribe((data: InstructorAdventureDTO[])=>{
      this.filteredAdventures = data;
      if(this.filteredAdventures.length==0)
        alert("No available adventures!")
    });
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
