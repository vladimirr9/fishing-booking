import { AfterViewInit, Component, OnInit, ViewChild } from '@angular/core';
import { View } from 'ol';
import { ReservationDTO } from 'src/app/dto/ReservationDTO';
import { ReservationService } from 'src/app/service/reservation.service';
import { FilterComponent } from '../boats/filter/filter.component';
import {MatGridListModule} from '@angular/material/grid-list';

@Component({
  selector: 'app-reservations',
  templateUrl: './reservations.component.html',
  styleUrls: ['./reservations.component.scss']
})
export class ReservationsComponent implements OnInit,AfterViewInit {

  constructor(private _reservationsService: ReservationService) { }

  reservations: ReservationDTO[] = [];
  filteredReservations: ReservationDTO[] = [];
  sorterType: string = "";
  filterStartDate: Date = new Date();
  filterEndDate: Date = new Date();

  @ViewChild(FilterComponent)
  private filterComponent!: FilterComponent;

  ngAfterViewInit(): void {
    
  }

  ngOnInit(): void {
    this._reservationsService.getReservations().subscribe((data: ReservationDTO[])=>{
      this.reservations = data;
      this.filteredReservations = data;
    });
  }

  sortHouses(): void{
    switch(this.sorterType){
      case 'date':
        this.filteredReservations.sort((a, b) => b.startDate.getTime()-a.startDate.getTime());
        break;
      case 'price':
        this.filteredReservations.sort((a, b) => b.mark - a.mark);
        break;
      case 'duration':
        this.filteredReservations.sort((a, b) => b.durationInHours - a.durationInHours);
        break;
      default:

    }
  }

  setSorter(sorterType: string):void{
    this.sorterType = sorterType;
    this.sortHouses();
  }

  async filterReservations(): Promise<void>{
      this.filteredReservations = this.reservations.filter(boat => new Date(boat.startDate).getTime() >= this.filterStartDate.getTime() && new Date(boat.startDate).getTime() <= this.filterEndDate.getTime());
      this.filteredReservations = this.filteredReservations.filter(boat => boat.name.includes(this.filterComponent.filterName));
      this.filteredReservations = this.filteredReservations.filter(boat => boat.address.includes(this.filterComponent.filterAdress));
      this.filteredReservations = this.filteredReservations.filter(boat => boat.mark >= this.filterComponent.filterMark);
      this.sortHouses();
  }

}
