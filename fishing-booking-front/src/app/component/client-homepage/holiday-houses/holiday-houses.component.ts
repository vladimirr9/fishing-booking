import { AfterViewInit, Component, OnInit, ViewChild } from '@angular/core';
import { HolidayHouseDTO } from 'src/app/dto/HolidayHouseDTO';
import { HolidayHouseService } from 'src/app/service/holiday-house.service';
import { FilterComponent } from '../boats/filter/filter.component';

@Component({
  selector: 'app-holiday-houses',
  templateUrl: './holiday-houses.component.html',
  styleUrls: ['./holiday-houses.component.scss']
})
export class HolidayHousesComponent implements OnInit,AfterViewInit {

  constructor(private _holidayHouseService: HolidayHouseService) { }
 

  houses: HolidayHouseDTO[]=[];
  filteredHouses: HolidayHouseDTO[] = [];
  sorterType: string = "";

  @ViewChild(FilterComponent)
  private filterComponent!: FilterComponent;


  ngOnInit(): void {
    this.houses = this._holidayHouseService.getHouses();
    this.filteredHouses = this.houses;
  }

  ngAfterViewInit(): void {
    this.filterHouses();
  }

  sortHouses(): void{
    switch(this.sorterType){
      case 'name':
        this.filteredHouses.sort((a, b) => a.name.localeCompare(b.name));
        break;
      case 'mark':
        this.filteredHouses.sort((a, b) => b.mark - a.mark);
        break;
      case 'location':
          break;
      default:

    }
  }

  setSorter(sorterType: string):void{
    this.sorterType = sorterType;
    this.sortHouses();
  }

  async filterHouses(): Promise<void>{
      this.filteredHouses = this.houses.filter(boat => boat.name.includes(this.filterComponent.filterName));
      this.filteredHouses = this.filteredHouses.filter(boat => boat.adress.includes(this.filterComponent.filterAdress));
      this.filteredHouses = this.filteredHouses.filter(boat => boat.mark >= this.filterComponent.filterMark);
      this.sortHouses();
  }
  

}
