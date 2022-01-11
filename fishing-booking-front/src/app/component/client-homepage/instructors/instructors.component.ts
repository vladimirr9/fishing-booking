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
    //this._adventureService.getAvailableAdventures()
    this.filteredAdventures = this.adventures.filter(boat => boat.name.includes(this.filterComponent.filterName));
    this.filteredAdventures = this.filteredAdventures.filter(boat => boat.adress.includes(this.filterComponent.filterAdress));
    this.filteredAdventures = this.filteredAdventures.filter(boat => boat.mark >= this.filterComponent.filterMark);
    this.sortAdventures();
}
}
