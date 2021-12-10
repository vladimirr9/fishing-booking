import { AfterViewInit, Component, OnInit, ViewChild } from '@angular/core';
import { View } from 'ol';
import { ReservationDTO } from 'src/app/dto/ReservationDTO';
import { ReservationService } from 'src/app/service/reservation.service';
import { FilterComponent } from '../boats/filter/filter.component';

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

  @ViewChild(FilterComponent)
  private filterComponent!: FilterComponent;

  ngAfterViewInit(): void {
    
  }

  ngOnInit(): void {
    this.reservations = this._reservationsService.getReservations();
    this.filteredReservations = this.reservations;
  }

  sortHouses(): void{
    switch(this.sorterType){
      case 'name':
        this.filteredReservations.sort((a, b) => a.name.localeCompare(b.name));
        break;
      case 'mark':
        this.filteredReservations.sort((a, b) => b.mark - a.mark);
        break;
      case 'location':
        this.filteredReservations.sort((a, b) => a.adress.localeCompare(b.adress));
        break;
      default:

    }
  }

  setSorter(sorterType: string):void{
    this.sorterType = sorterType;
    this.sortHouses();
  }

  async filterReservations(): Promise<void>{
      this.filteredReservations = this.reservations.filter(boat => boat.name.includes(this.filterComponent.filterName));
      this.filteredReservations = this.filteredReservations.filter(boat => boat.adress.includes(this.filterComponent.filterAdress));
      this.filteredReservations = this.filteredReservations.filter(boat => boat.mark >= this.filterComponent.filterMark);
      this.sortHouses();
  }

}
