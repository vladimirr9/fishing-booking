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


  @ViewChild(FilterComponent)
  private filterComponent!: FilterComponent;

  //filters
  filterName: string = "";
  filterAdress: string = "";
  filterMark: number=0;
  filterStartDate: Date = new Date();
  filterEndDate: Date = new Date();
  //
  ngOnInit(): void {
    this.boats = this._boatService.getBoats();
    this.filteredBoats = this.boats;
  }

  ngAfterViewInit(): void {
    //filtriraj ovjde
    this.filterBoats();
  }

  async filterBoats(): Promise<void>{
      this.filteredBoats = this.boats.filter(boat => boat.name.includes(this.filterComponent.filterName));
      this.filteredBoats = this.filteredBoats.filter(boat => boat.adress.includes(this.filterComponent.filterAdress));
      this.filteredBoats = this.filteredBoats.filter(boat => boat.mark >= this.filterComponent.filterMark);
  }

  
}
