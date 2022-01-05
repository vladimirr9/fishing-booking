import { AfterViewInit, Component, OnInit, ViewChild } from '@angular/core';
import { BoatsDTO } from 'src/app/dto/BoatsDTO';
import { BoatService } from 'src/app/service/boat.service';
import { FilterComponent } from './filter/filter.component';

@Component({
  selector: 'app-boats',
  templateUrl: './boats.component.html',
  styleUrls: ['./boats.component.scss']
})
export class BoatsComponent implements OnInit,AfterViewInit {

  constructor(private _boatService: BoatService) { }
  
  boats: BoatsDTO[]= [];
  filteredBoats: BoatsDTO[]= [];
  sorterType: string = "";


  @ViewChild(FilterComponent)
  private filterComponent!: FilterComponent;

  ngOnInit(): void {
    //this.boats = this._boatService.getBoats();
    //this.filteredBoats = this.boats;
    this._boatService.getBoats().subscribe((data: BoatsDTO[])=>{
      this.boats = data;
      this.filteredBoats = this.boats;
    });
  }

  ngAfterViewInit(): void {
    this.filterBoats();
  }

  sortBoats(): void{
    switch(this.sorterType){
      case 'name':
        this.filteredBoats.sort((a, b) => a.name.localeCompare(b.name));
        break;
      case 'mark':
        this.filteredBoats.sort((a, b) => b.mark - a.mark);
        break;
      case 'location':
        this.filteredBoats.sort((a, b) => a.address.localeCompare(b.address));
        break;
      default:

    }
  }

  setSorter(sorterType: string):void{
    this.sorterType = sorterType;
    this.sortBoats();
  }

  async filterBoats(): Promise<void>{
      this.filteredBoats = this.boats.filter(boat => boat.name.includes(this.filterComponent.filterName));
      this.filteredBoats = this.filteredBoats.filter(boat => boat.address.includes(this.filterComponent.filterAdress));
      this.filteredBoats = this.filteredBoats.filter(boat => boat.mark >= this.filterComponent.filterMark);
      this.sortBoats();
  }

  
}
